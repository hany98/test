package hanzy.stake.limbo.hanzimbo.model.idclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LimboResultEntityId  implements Serializable {
    private int generationSeedId;
    private int nonce;
}
