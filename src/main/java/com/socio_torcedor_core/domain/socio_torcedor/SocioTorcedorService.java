package com.socio_torcedor_core.domain.socio_torcedor;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.Optional;

public interface SocioTorcedorService {

    Iterable<SocioTorcedor> findAll() throws ServiceException;

    Optional<SocioTorcedor> findOne(final Long id) throws ServiceException;

    Optional<SocioTorcedor> save(final SocioTorcedor socioTorcedor) throws ServiceException;

    void delete(final Long id) throws ServiceException;

}
