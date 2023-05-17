package hanzy.stake.limbo.hanzimbo.service.lowlevel.struct;

import hanzy.stake.limbo.hanzimbo.entity.GenerationSeedEntity;

import java.util.Optional;

public interface GenerationSeedEntityService {
    GenerationSeedEntity save(GenerationSeedEntity generationSeedEntity);

    Optional<GenerationSeedEntity> findByClientSeedAndServerSeed(String clientSeed, String serverSeed);
}
