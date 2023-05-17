package hanzy.stake.limbo.hanzimbo.service.highlevel.impl;

import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.model.dto.AlgoResult;
import hanzy.stake.limbo.hanzimbo.requests.ComputeRequest;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;
import hanzy.stake.limbo.hanzimbo.service.highlevel.struct.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements GameService {

    @Override
    public void generate(GenerateRequest request) {
    }

    @Override
    public void compute(ComputeRequest request) {
    };
}
