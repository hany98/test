package hanzy.stake.limbo.hanzimbo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanzy.stake.limbo.hanzimbo.model.dto.LimboGeneratorResult;
import hanzy.stake.limbo.hanzimbo.model.idclass.LimboResultEntityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@IdClass(LimboResultEntityId.class)
@Table(name = "LIMBO_RESULT")
public class LimboResultEntity {
    public LimboResultEntity(LimboGeneratorResult result) {
        this.generationSeedId = result.getGenerationSeedId();
        this.nonce = result.getNonce();
        this.result = result.getResult();
    }
    @Id
    @JsonIgnore
    @Column(name = "generation_seed_id")
    private int generationSeedId;

    @Id
    @Column(name = "nonce")
    private int nonce;

    @Column(name = "result")
    private double result;
}
