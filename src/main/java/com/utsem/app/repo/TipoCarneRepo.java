package com.utsem.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utsem.app.model.TipoCarne;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TipoCarneRepo extends JpaRepository<TipoCarne, Long> {
	Optional<TipoCarne> findByUuid(UUID uuid);
}
