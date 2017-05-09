package com.socio_torcedor_core.domain.campanha;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.List;

/**
 * Service de Campanha.
 * 
 * @author rafael.ferrari
 */
public interface CampanhaService {

    /**
     * Busca todas as Campanhas vigentes pelo ID do Time Do Coração.
     * 
     * @param idTimeCoracao - ID do Time do Coração.
     * @return As Campanhas se encontradas.
     * @throws ServiceException .
     */
    List<CampanhaDTO> findAll(final Long idTimeCoracao) throws ServiceException;

}
