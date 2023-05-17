package hanzy.stake.limbo.hanzimbo.model.algo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LimboThresholdAlgoParam extends AlgoParam {
    private double targetMultiplier;
    private int lossStreakThreshold;
    private int repeatsAfterBetLoss;
}
