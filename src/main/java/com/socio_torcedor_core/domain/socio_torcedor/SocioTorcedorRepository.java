package com.socio_torcedor_core.domain.socio_torcedor;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositório do Socio Torcedor.
 * 
 * @author rafael.ferrari
 */
public interface SocioTorcedorRepository extends CrudRepository<SocioTorcedor, Long> {

    /**
     * Busca um Sócio Torcedor por Email.
     * 
     * @param email - Email do Sócio Torcedor.
     * @return Sócio Torcedor se encontrado.
     */
    Optional<SocioTorcedor> findByEmail(final String email);

}
