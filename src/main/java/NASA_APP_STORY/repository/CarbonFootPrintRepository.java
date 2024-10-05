package NASA_APP_STORY.repository;

import NASA_APP_STORY.entity.CarbonFootPrint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonFootPrintRepository extends JpaRepository<CarbonFootPrint, Long> {
}
