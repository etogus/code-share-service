package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.model.CodeResponse;

import java.util.Optional;

public interface CodeResponseRepository extends JpaRepository<CodeResponse, Long> {
    Optional<CodeResponse> findByUuid(String uuid);
}
