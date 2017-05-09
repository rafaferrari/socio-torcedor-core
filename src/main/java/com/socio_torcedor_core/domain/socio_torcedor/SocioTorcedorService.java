package com.socio_torcedor_core.domain.socio_torcedor;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.Optional;

/**
 * Service do Sócio Torcedor.
 * 
 * @author rafael.ferrari
 */
public interface SocioTorcedorService {

    /**
     * Busca todos os Sócios Torcedores.
     * 
     * @return Sócios Torcedores se encontrados.
     * @throws ServiceException .
     */
    Iterable<SocioTorcedor> findAll() throws ServiceException;

    /**
     * Busca um Sócio Torcedor por ID.
     * 
     * @param id - ID do Sócio Torcedor à ser pesquisado.
     * @return Sócio Torcedor se encontrado.
     * @throws ServiceException .
     */
    Optional<SocioTorcedor> findOne(final Long id) throws ServiceException;

    /**
     * Salva/Atualiza um Sócio Torcedor.
     * 
     * @param socioTorcedor - Sócio Torcedor à ser Salvo/Atualizado.
     * @return Sócio Torcedor.
     * @throws ServiceException . 
     */
    Optional<SocioTorcedor> save(final SocioTorcedor socioTorcedor) throws ServiceException;

    /**
     * Remove um Sócio Torcedor pelo ID.
     * 
     * @param id - ID do Sócio Torcedor à ser removido.
     * @throws ServiceException .
     */
    void delete(final Long id) throws ServiceException;

}
