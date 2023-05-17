package hanzy.stake.limbo.hanzimbo.service.lowlevel.impl;

import hanzy.stake.limbo.hanzimbo.entity.GenerationSeedEntity;
import hanzy.stake.limbo.hanzimbo.repository.GenerationSeedEntityRepository;
import hanzy.stake.limbo.hanzimbo.service.lowlevel.struct.GenerationSeedEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerationSeedEntityServiceImpl implements GenerationSeedEntityService {
    @Autowired
    public GenerationSeedEntityRepository generationSeedEntityRepository;

    @Override
    public GenerationSeedEntity save(GenerationSeedEntity generationSeedEntity) {
        return generationSeedEntityRepository.save(generationSeedEntity);
    }

    @Override
    public Optional<GenerationSeedEntity> findByClientSeedAndServerSeed(String clientSeed, String serverSeed) {
        return generationSeedEntityRepository.findByClientSeedAndServerSeed(clientSeed, serverSeed);
    }
}
