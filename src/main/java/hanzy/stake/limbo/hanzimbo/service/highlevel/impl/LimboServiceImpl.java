package hanzy.stake.limbo.hanzimbo.service.highlevel.impl;

import hanzy.stake.limbo.hanzimbo.algo.Compute;
import hanzy.stake.limbo.hanzimbo.entity.AlgoResultEntity;
import hanzy.stake.limbo.hanzimbo.entity.GenerationSeedEntity;
import hanzy.stake.limbo.hanzimbo.entity.LimboResultEntity;
import hanzy.stake.limbo.hanzimbo.generator.LimboGenerator;
import hanzy.stake.limbo.hanzimbo.requests.ComputeRequest;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;
import hanzy.stake.limbo.hanzimbo.service.highlevel.struct.GameService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.AlgoResultEntityService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.GenerationSeedEntityService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.LimboResultEntityService;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.params.ThresholdAlgoParamEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LimboServiceImpl implements GameService {
    @Autowired
    public GenerationSeedEntityService generationSeedEntityService;
    @Autowired
    public LimboResultEntityService limboResultEntityService;
    @Autowired
    public AlgoResultEntityService algoResultEntityService;
    @Autowired
    public ThresholdAlgoParamEntityService thresholdAlgoParamEntityService;

    @Override
    public void generate(GenerateRequest request) {
        long startTime = System.currentTimeMillis();
        Optional<GenerationSeedEntity> optionalGenerationSeedEntity = generationSeedEntityService
                .findByClientSeedAndServerSeed(request.getClientSeed(), request.getServerSeed());

        GenerationSeedEntity generationSeedEntity = optionalGenerationSeedEntity
                .orElseGet(() -> generationSeedEntityService.save(new GenerationSeedEntity(0, request.getClientSeed(), request.getServerSeed())));

        Set<Integer> existingNonce = limboResultEntityService
                .findNonceByGenerationSeedIdAndNonceRange(generationSeedEntity.getId(), request.getStartNonce(), request.getEndNonce());

        long existingNonceTime = System.currentTimeMillis();
        System.out.println("Fetch Time: " + (existingNonceTime - startTime) + "ms");

        List<LimboResultEntity> limboResultEntityList = new ArrayList<>();
        for (int i = request.getStartNonce(); i <= request.getEndNonce(); i++) {
            if (!existingNonce.remove(i)) {
                LimboResultEntity entity = new LimboResultEntity(LimboGenerator.getResultModel(generationSeedEntity.getId(), request, i));
                limboResultEntityList.add(entity);
            }
        }

        long generateTime = System.currentTimeMillis();
        System.out.println("Generate Time: " + (generateTime - existingNonceTime) + "ms");

        limboResultEntityService.saveAll(limboResultEntityList);

        limboResultEntityList = null;
        System.gc();

        long endTime = System.currentTimeMillis();
        System.out.println("Save Time: " + (endTime - generateTime) + "ms");
    }

    @Override
    public void compute(ComputeRequest request) {

        GenerateRequest genReq = new GenerateRequest();
            genReq.setServerSeed(request.getServerSeed());
            genReq.setClientSeed(request.getClientSeed());
            genReq.setGameId(request.getGameId());
            genReq.setStartNonce(request.getStartNonce());
            genReq.setEndNonce(request.getEndNonce());
            genReq.setCursor(request.getCursor());

        generate(genReq);

        long startTime = System.currentTimeMillis();

        GenerationSeedEntity generationSeedEntity = generationSeedEntityService
                .findByClientSeedAndServerSeed(request.getClientSeed(), request.getServerSeed())
                .orElseGet(() -> generationSeedEntityService.save(new GenerationSeedEntity(0, request.getClientSeed(), request.getServerSeed())));

        ArrayList<Double> generatedData = (ArrayList<Double>) limboResultEntityService.findResultsByGenerationSeedIdAndNonceRange(generationSeedEntity.getId(), request.getStartNonce(), request.getEndNonce());

        long generateTime = System.currentTimeMillis();
        System.out.println("Fetch Generated Data Time: " + (generateTime - startTime) + "ms");

        ArrayList<AlgoResultEntity> algoResultEntityList =
                new Compute(request.getAlgoId(),
                            generationSeedEntity.getId(),
                            request.getStartNonce(),
                            request.getEndNonce(),
                            generatedData,
                            thresholdAlgoParamEntityService,
                            algoResultEntityService)
                .compute();

        long computeTime = System.currentTimeMillis();
        System.out.println("Compute Time: " + (computeTime - generateTime) + "ms");

        algoResultEntityService.saveAll(algoResultEntityList);

        long saveTime = System.currentTimeMillis();
        System.out.println("Save Time: " + (saveTime - computeTime) + "ms");
    }
}
