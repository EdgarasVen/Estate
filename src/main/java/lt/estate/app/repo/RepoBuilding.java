package lt.estate.app.repo;

import lt.estate.app.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoBuilding extends JpaRepository<Building,Long> {
}
