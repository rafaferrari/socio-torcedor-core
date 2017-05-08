package com.socio_torcedor_core.domain.socio_torcedor;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SocioTorcedorRepository extends CrudRepository<SocioTorcedor, Long> {

    Optional<SocioTorcedor> findByEmail(final String email);

}
