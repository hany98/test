package hanzy.stake.limbo.hanzimbo.service.highlevel.struct;

import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.model.dto.AlgoResult;
import hanzy.stake.limbo.hanzimbo.requests.ComputeRequest;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;

import java.util.List;

public interface GameService {
    void generate(GenerateRequest request);

    void compute(ComputeRequest request);


}
