package hanzy.stake.limbo.hanzimbo.utils;

import hanzy.stake.limbo.hanzimbo.algo.limbo.LimboThresholdAlgo;
import hanzy.stake.limbo.hanzimbo.entity.param.ParamEntity;
import hanzy.stake.limbo.hanzimbo.entity.param.ThresholdAlgoParamEntity;
import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.dto.AlgoResult;

import java.util.ArrayList;

import static hanzy.stake.limbo.hanzimbo.constants.Algorithms.LIMBO_THRESHOLD_ALGO;

public class AlgoUtils {
    public static AlgoResult testAlgorithm(int algoId, AlgoParam params, ArrayList<Double> data){
        if(algoId == LIMBO_THRESHOLD_ALGO)
            return new LimboThresholdAlgo(params, data).testAlgorithm();
        else
            throw new RuntimeException("algoId Not Found");
    }

    public static ParamEntity createParamEntity(int algoId, AlgoParam params){
        if(algoId == LIMBO_THRESHOLD_ALGO)
            return new ThresholdAlgoParamEntity(0,params);
        else
            throw new RuntimeException("algoId Not Found");
    }
}
