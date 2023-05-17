package hanzy.stake.limbo.hanzimbo.service.lowlevel.impl.params;

import hanzy.stake.limbo.hanzimbo.entity.param.ParamEntity;
import hanzy.stake.limbo.hanzimbo.entity.param.ThresholdAlgoParamEntity;
import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.algo.param.LimboThresholdAlgoParam;
import hanzy.stake.limbo.hanzimbo.repository.ThresholdAlgoParamEntityRepository;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params.ThresholdAlgoParamEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThresholdAlgoParamEntityServiceImpl implements ThresholdAlgoParamEntityService {
    @Autowired
    public ThresholdAlgoParamEntityRepository thresholdAlgoParamEntityRepository;

    @Override
    public void saveAll(List<ThresholdAlgoParamEntity> algoResultEntity) {
        thresholdAlgoParamEntityRepository.saveAll(algoResultEntity);
    }

    @Override
    public List<ThresholdAlgoParamEntity> findAll() {
        return thresholdAlgoParamEntityRepository.findAll();
    }

    @Override
    public ThresholdAlgoParamEntity save(ParamEntity entity) {
        ThresholdAlgoParamEntity thresholdParam = (ThresholdAlgoParamEntity) entity;
        return thresholdAlgoParamEntityRepository.save(thresholdParam);
    }

    @Override
    public Optional<ThresholdAlgoParamEntity> findByParam(AlgoParam params) {
        LimboThresholdAlgoParam thresholdParams = (LimboThresholdAlgoParam) params;
        return thresholdAlgoParamEntityRepository.findByMultiplierAndLossStreakThresholdAndBetsAfterLoss(thresholdParams.getTargetMultiplier(), thresholdParams.getLossStreakThreshold(), thresholdParams.getRepeatsAfterBetLoss());
    }
}
