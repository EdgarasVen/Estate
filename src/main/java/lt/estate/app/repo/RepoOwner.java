package lt.estate.app.repo;

import lt.estate.app.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoOwner extends JpaRepository<Owner,Long> {
}
