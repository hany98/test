package hanzy.stake.limbo.hanzimbo.algo.thread;

import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;
import hanzy.stake.limbo.hanzimbo.entity.param.ParamEntity;
import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.dto.AlgoResult;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.AlgoResultEntityService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params.AlgoParamEntityService;
import hanzy.stake.limbo.hanzimbo.utils.AlgoUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Objects;

@Data
@AllArgsConstructor
public class AlgoThread extends Thread{

    private AlgoParamEntityService algoParamEntityService;
    private AlgoResultEntityService algoResultEntityService;
    private int startNonce;
    private int endNonce;
    private int algoId;
    private int generationSeedId;
    private AlgoParam params;
    private ArrayList<Double> data;
    ArrayList<AlgoResultEntity> resultEntities;

    @Override
    public void run(){
        if(algoParamEntityService == null) System.out.println("null");

        ParamEntity paramEntity =(ParamEntity) algoParamEntityService
                .findByParam(params)
                .orElseGet(() -> algoParamEntityService.save(AlgoUtils.createParamEntity(algoId, params)));

        AlgoResultEntity entity = algoResultEntityService.findByAlgoIdAndGenerationSeedIdAndParamIdAndStartNonceAndEndNonce(algoId, generationSeedId, paramEntity.getId(), startNonce, endNonce);
        if(Objects.nonNull(entity))
            return;

        AlgoResult result = AlgoUtils.testAlgorithm(algoId, params, data);
        entity = new AlgoResultEntity(generationSeedId,
                algoId,
                paramEntity.getId(),
                startNonce,
                endNonce,
                result.getOutcomeValue(),
                result.getOutcomePercentage(),
                result.getMaxAmountLost(),
                result.getMaxAmountWon(),
                result.getMaxConcurrentAmountLost());

        synchronized (resultEntities){
            resultEntities.add(entity);
        }
    }
}
