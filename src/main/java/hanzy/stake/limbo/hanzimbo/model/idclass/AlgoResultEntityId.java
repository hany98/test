package hanzy.stake.limbo.hanzimbo.model.idclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AlgoResultEntityId implements Serializable {
    private int generationSeedId;
    private int algoId;
    private int paramId;
    private int startNonce;
    private int endNonce;
}
