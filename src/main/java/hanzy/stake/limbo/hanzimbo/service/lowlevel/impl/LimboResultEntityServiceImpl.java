package hanzy.stake.limbo.hanzimbo.service.lowlevel.impl;

import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.factory.query.QueryFactory;
import hanzy.stake.limbo.hanzimbo.repository.LimboResultEntityRepository;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.LimboResultEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LimboResultEntityServiceImpl implements LimboResultEntityService {
    @Autowired
    public LimboResultEntityRepository limboResultEntityRepository;
    @Autowired
    public QueryFactory queryFactory;

    @Override
    public void saveAll(List<LimboResultEntity> limboResultEntityList) {
        queryFactory.saveAll(limboResultEntityList, LimboResultEntity.class);
    }

    @Override
    public List<LimboResultEntity> findAll() {
        return limboResultEntityRepository.findAll();
    }

    @Override
    public Set<Integer> findNonceByGenerationSeedIdAndNonceRange(int id, int startNonce, int endNonce) {
        return limboResultEntityRepository.findNonceByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(id, startNonce, endNonce);
    }

    @Override
    public List<Double> findResultsByGenerationSeedIdAndNonceRange(int id, int startNonce, int endNonce) {
        return limboResultEntityRepository.findResultsByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqualOrderByNonceAsc(id, startNonce, endNonce);
    }

    @Override
    public List<LimboResultEntity> findByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(int id, int startNonce, int endNonce) {
        return limboResultEntityRepository.findByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(id, startNonce, endNonce);
    }

    @Override
    public void save(LimboResultEntity entity) {
        limboResultEntityRepository.save(entity);
    }
}
