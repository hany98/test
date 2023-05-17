package hanzy.stake.limbo.hanzimbo.repository;

import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;
import hanzy.stake.limbo.hanzimbo.model.idclass.AlgoResultEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgoResultEntityRepository extends JpaRepository<AlgoResultEntity, AlgoResultEntityId> {

    AlgoResultEntity findByAlgoIdAndGenerationSeedIdAndParamIdAndStartNonceAndEndNonce(int algoId, int generationSeedId, int paramId, int startNonce, int endNonce);
}