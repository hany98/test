package hanzy.stake.limbo.hanzimbo.repository;

import hanzy.stake.limbo.hanzimbo.entity.param.ThresholdAlgoParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThresholdAlgoParamEntityRepository extends JpaRepository<ThresholdAlgoParamEntity, Integer> {
    Optional<ThresholdAlgoParamEntity> findByMultiplierAndLossStreakThresholdAndBetsAfterLoss(double multiplier, int lossStreakThreshold, int repeatsAfterBetLoss);
}