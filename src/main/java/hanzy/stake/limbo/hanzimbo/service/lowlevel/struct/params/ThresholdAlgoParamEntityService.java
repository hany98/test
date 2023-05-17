package hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params;

import hanzy.stake.limbo.hanzimbo.entity.param.ParamEntity;
import hanzy.stake.limbo.hanzimbo.entity.param.ThresholdAlgoParamEntity;
import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ThresholdAlgoParamEntityService extends AlgoParamEntityService<ThresholdAlgoParamEntity, ParamEntity> {
    void saveAll(List<ThresholdAlgoParamEntity> entity);

    List<ThresholdAlgoParamEntity> findAll();

    ThresholdAlgoParamEntity save(ParamEntity entity);

    Optional<ThresholdAlgoParamEntity> findByParam(AlgoParam params);
}
