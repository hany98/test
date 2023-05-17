package hanzy.stake.limbo.hanzimbo.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LimboGeneratorResult {
    private int generationSeedId;
    private int nonce;
    private double result;
}
