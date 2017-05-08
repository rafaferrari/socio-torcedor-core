package com.socio_torcedor_core.domain.campanha;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.List;

public interface CampanhaService {

    List<CampanhaDTO> findAll(final Long idTimeCoracao) throws ServiceException;

}
