package hanzy.stake.limbo.hanzimbo.repository;

import hanzy.stake.limbo.hanzimbo.entity.GenerationSeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenerationSeedEntityRepository extends JpaRepository<GenerationSeedEntity, Integer> {
    Optional<GenerationSeedEntity> findByClientSeedAndServerSeed(String getClientSeed, String setServerSeed);
}

