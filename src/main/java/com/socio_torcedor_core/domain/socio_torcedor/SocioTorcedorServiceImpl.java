package com.socio_torcedor_core.domain.socio_torcedor;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioTorcedorServiceImpl implements SocioTorcedorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    @Override
    public Iterable<SocioTorcedor> findAll() throws ServiceException {
        try {
            return socioTorcedorRepository.findAll();
        } catch (final Exception e) {
            logger.error("Erro ao buscar Socios Torcedores.");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Optional<SocioTorcedor> findOne(final Long id) throws ServiceException {
        try {
            return Optional.ofNullable(socioTorcedorRepository.findOne(id));
        } catch (final Exception e) {
            logger.error("Erro ao buscar Socio Torcedor por ID.");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Optional<SocioTorcedor> save(final SocioTorcedor socioTorcedor) throws ServiceException {
        try {
            final Optional<SocioTorcedor> socioTorcedorOpcional = socioTorcedorRepository.findByEmail(socioTorcedor.getEmail());
            if (socioTorcedorOpcional.isPresent()) {
                throw new ServiceException("Socio Torcedor ja existente pelo email informado.");
            }
            return Optional.of(socioTorcedorRepository.save(socioTorcedor));
        } catch (final Exception e) {
            logger.error("Erro ao Salvar/Inserir um Socio Torcedor.");
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        try {
            socioTorcedorRepository.delete(id);
        } catch (final Exception e) {
            logger.error(String.format("Erro ao Remover um Socio Torcedor pelo ID: %s", id));
            throw new ServiceException(e.getMessage());
        }
    }

}
