package hanzy.stake.limbo.hanzimbo.model.dto;

import lombok.*;

import java.util.Map;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlgoResult {
    private double outcomeValue;
    private double outcomePercentage;
    private Map<Integer, Integer> winStreakMap;
    private Map<Integer, Integer> lossStreakMap;
    private double totalAmountWon;
    private double totalAmountLost;
    private double maxAmountLost;
    private double maxAmountWon;
    private double maxConcurrentAmountLost;
}

