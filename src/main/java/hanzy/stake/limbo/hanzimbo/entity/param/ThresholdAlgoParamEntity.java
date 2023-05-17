package hanzy.stake.limbo.hanzimbo.entity.param;

import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.algo.param.LimboThresholdAlgoParam;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "THRESHOLD_ALGO_PARAM")
public class ThresholdAlgoParamEntity extends ParamEntity{

    public ThresholdAlgoParamEntity(int id, AlgoParam params){
        super(id);
        LimboThresholdAlgoParam thresholdParams = (LimboThresholdAlgoParam) params;
        this.multiplier = thresholdParams.getTargetMultiplier();
        this.lossStreakThreshold = thresholdParams.getLossStreakThreshold();
        this.betsAfterLoss = thresholdParams.getRepeatsAfterBetLoss();
    }

    @Column(name = "multiplier")
    private double multiplier;

    @Column(name = "loss_streak_threshold")
    private int lossStreakThreshold;

    @Column(name = "bets_after_loss")
    private int betsAfterLoss;
}
