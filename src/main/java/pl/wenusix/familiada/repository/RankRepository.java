package pl.wenusix.familiada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wenusix.familiada.domain.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
