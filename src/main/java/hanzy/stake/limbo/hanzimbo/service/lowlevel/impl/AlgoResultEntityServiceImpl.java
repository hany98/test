package hanzy.stake.limbo.hanzimbo.service.lowlevel.impl;

import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;
import hanzy.stake.limbo.hanzimbo.factory.query.QueryFactory;
import hanzy.stake.limbo.hanzimbo.repository.AlgoResultEntityRepository;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.AlgoResultEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoResultEntityServiceImpl implements AlgoResultEntityService {
    @Autowired
    public AlgoResultEntityRepository algoResultEntityRepository;

    @Autowired
    public QueryFactory queryFactory;

    @Override
    public void saveAll(List<AlgoResultEntity> algoResultEntityList) {
        queryFactory.saveAll(algoResultEntityList, AlgoResultEntity.class);
    }

    @Override
    public List<AlgoResultEntity> findAll() {
        return algoResultEntityRepository.findAll();
    }


    @Override
    public void save(AlgoResultEntity entity) {
        algoResultEntityRepository.save(entity);
    }

    @Override
    public AlgoResultEntity findByAlgoIdAndGenerationSeedIdAndParamIdAndStartNonceAndEndNonce(int algoId, int generationSeedId, int paramId, int startNonce, int endNonce) {
        return algoResultEntityRepository.findByAlgoIdAndGenerationSeedIdAndParamIdAndStartNonceAndEndNonce(algoId, generationSeedId, paramId, startNonce, endNonce);
    }
}
