package hanzy.stake.limbo.hanzimbo.service.lowlevel.struct;

import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;

import java.util.List;
import java.util.Set;

public interface LimboResultEntityService {
    void saveAll(List<LimboResultEntity> limboResultEntityList);

    List<LimboResultEntity> findAll();

    Set<Integer> findNonceByGenerationSeedIdAndNonceRange(int id, int startNonce, int endNonce);

    List<Double> findResultsByGenerationSeedIdAndNonceRange(int id, int startNonce, int endNonce);

    List<LimboResultEntity> findByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(int id, int startNonce, int endNonce);

    void save(LimboResultEntity entity);
}
