package hanzy.stake.limbo.hanzimbo.algo;

import hanzy.stake.limbo.hanzimbo.algo.thread.AlgoThread;
import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;
import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.algo.param.LimboThresholdAlgoParam;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.AlgoResultEntityService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params.ThresholdAlgoParamEntityService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static hanzy.stake.limbo.hanzimbo.constants.Algorithms.LIMBO_THRESHOLD_ALGO;


@Data
@AllArgsConstructor
public class Compute {
    private int algoId;
    private int generationSeedId;
    private int startNonce;
    private int endNonce;
    private ArrayList<Double> data;
    private ThresholdAlgoParamEntityService thresholdAlgoParamEntityService;
    private AlgoResultEntityService algoResultEntityService;
    public ArrayList<AlgoResultEntity> compute(){
        if(algoId == LIMBO_THRESHOLD_ALGO)
            return computeLimboThresholdAlgo();
        else
            throw new RuntimeException("algoId Not Found");
    }


    private ArrayList<AlgoResultEntity> computeLimboThresholdAlgo(){
        ArrayList<AlgoResultEntity> resultEntities = new ArrayList<AlgoResultEntity>();
        // minimum and maximum values of the params
        double minTargetMultiplier = 1.01;
        int minLossStreakThreshold = 0;
        int minRepeatsAfterBetLoss = 0;

        double maxTargetMultiplier = 11;
        int maxLossStreakThreshold = 1000;
        int maxRepeatsAfterBetLoss = 300;

        // Define the increment values for targetMultiplier
        double multiplierExpIncrement = 1.05;
        // The maximum ratio between lossStreakThreshold and targetMultiplier, used to limit the range of lossStreakThreshold values.
        double lossStreakMaxMultiplier = 5;

        int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfAvailableProcessors);

        for(double targetMultiplier = minTargetMultiplier; targetMultiplier <= maxTargetMultiplier; targetMultiplier = Math.floor(targetMultiplier * multiplierExpIncrement * 100) / 100.0){

            for(int lossStreakThreshold = minLossStreakThreshold; lossStreakThreshold <= maxLossStreakThreshold; lossStreakThreshold++){

                for(int repeatsAfterBetLoss = minRepeatsAfterBetLoss; repeatsAfterBetLoss <= maxRepeatsAfterBetLoss; repeatsAfterBetLoss++){

                    AlgoParam params = new LimboThresholdAlgoParam(targetMultiplier, lossStreakThreshold, repeatsAfterBetLoss);
                    executorService.execute(new AlgoThread(thresholdAlgoParamEntityService,
                            algoResultEntityService,
                            startNonce,
                            endNonce,
                            algoId,
                            generationSeedId,
                            params,
                            data,
                            resultEntities));

                    if(repeatsAfterBetLoss > targetMultiplier)
                        break;
                }
                if (lossStreakThreshold > (targetMultiplier * lossStreakMaxMultiplier + 15))
                    break;
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resultEntities;
    }

}
