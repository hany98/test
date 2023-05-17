package hanzy.stake.limbo.hanzimbo.controller;

import hanzy.stake.limbo.hanzimbo.constants.Endpoints;
import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.requests.ComputeRequest;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;
import hanzy.stake.limbo.hanzimbo.service.highlevel.impl.LimboServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.LIMBO_ENDPOINT)
@RequiredArgsConstructor
public class LimboController {
    @Autowired
    private LimboServiceImpl limboServiceImpl;

    @PostMapping(Endpoints.GENERATE_ENDPOINT)
    public ResponseEntity<String> generate(@RequestBody GenerateRequest request) {
        limboServiceImpl.generate(request);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }

    @PostMapping(Endpoints.THRESHOLD_ALGO_ENDPOINT)
    public ResponseEntity<String> computeThresholdAlgo(@RequestBody ComputeRequest request) {
        limboServiceImpl.compute(request);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
}