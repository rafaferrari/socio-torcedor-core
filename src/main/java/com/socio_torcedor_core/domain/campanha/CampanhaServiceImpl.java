package com.socio_torcedor_core.domain.campanha;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CampanhaServiceImpl implements CampanhaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Value("${campanhaService.uri}")
    private String campanhaServiceUri;

    @Override
    @HystrixCommand(groupKey = "campanha-core", fallbackMethod = "reliable")
    public List<CampanhaDTO> findAll(final Long idTimeCoracao) throws ServiceException {
        final ResponseEntity<CampanhaDTO[]> responseEntity = restTemplate.getForEntity(campanhaServiceUri + "?idTimeCoracao={idTimeCoracao}", CampanhaDTO[].class, idTimeCoracao);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(responseEntity.getBody());
        }
        return new ArrayList<>();
    }

    public List<CampanhaDTO> reliable(final Long idTimeCoracao) {
        logger.error("O Servico campanha-core nao esta disponivel no momento.");
        return new ArrayList<>();
    }

}
