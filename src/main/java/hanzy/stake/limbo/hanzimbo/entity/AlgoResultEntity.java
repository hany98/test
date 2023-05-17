package hanzy.stake.limbo.hanzimbo.entity;

import hanzy.stake.limbo.hanzimbo.model.idclass.AlgoResultEntityId;
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
@IdClass(AlgoResultEntityId.class)
@Table(name = "ALGO_RESULT")
public class AlgoResultEntity {
    @Id
    @Column(name = "generation_seed_id")
    private int generationSeedId;

    @Id
    @Column(name = "algo_id")
    private int algoId;

    @Id
    @Column(name = "param_id")
    private int paramId;

    @Id
    @Column(name = "start_nonce")
    private int startNonce;

    @Id
    @Column(name = "end_nonce")
    private int endNonce;

    @Column(name = "amount_result")
    private double amountResult;

    @Column(name = "amount_result_percentage")
    private double amountResultPercentage;

    @Column(name = "max_amount_lost")
    private double maxAmountLost;

    @Column(name = "max_amount_won")
    private double maxAmountWon;

    @Column(name = "max_concurrent_amount_lost")
    private double maxConcurrentAmountLost;
}
