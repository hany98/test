package hanzy.stake.limbo.hanzimbo.repository;

import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.model.idclass.LimboResultEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface LimboResultEntityRepository extends JpaRepository<LimboResultEntity, LimboResultEntityId> {
    @Query("SELECT nonce " +
            "FROM LimboResultEntity " +
            "WHERE generationSeedId = :id " +
            "AND nonce >= :startNonce " +
            "AND nonce <= :endNonce")
    Set<Integer> findNonceByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(int id, int startNonce, int endNonce);
    @Query("SELECT result " +
            "FROM LimboResultEntity " +
            "WHERE generationSeedId = :id " +
            "AND nonce >= :startNonce " +
            "AND nonce <= :endNonce " +
            "ORDER BY nonce ASC")
    List<Double> findResultsByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqualOrderByNonceAsc(int id, int startNonce, int endNonce);
    List<LimboResultEntity> findByGenerationSeedIdAndNonceGreaterThanEqualAndNonceLessThanEqual(int id, int startNonce, int endNonce);
}

