package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeResponse;

public interface CodeResponseRepository extends JpaRepository<CodeResponse, Long> {
}
