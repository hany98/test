package hanzy.stake.limbo.hanzimbo.service.lowlevel.struct;

import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;

import java.util.List;

public interface AlgoResultEntityService {
    void saveAll(List<AlgoResultEntity> algoResultEntity);

    List<AlgoResultEntity> findAll();

    void save(AlgoResultEntity entity);

    AlgoResultEntity findByAlgoIdAndGenerationSeedIdAndParamIdAndStartNonceAndEndNonce(int algoId, int generationSeedId, int id, int startNonce, int endNonce);
}
