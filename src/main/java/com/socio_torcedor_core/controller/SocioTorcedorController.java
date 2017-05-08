package com.socio_torcedor_core.controller;

import com.socio_torcedor_core.domain.campanha.CampanhaDTO;
import com.socio_torcedor_core.domain.campanha.CampanhaService;
import com.socio_torcedor_core.domain.exceptions.ServiceException;
import com.socio_torcedor_core.domain.socio_torcedor.SocioTorcedor;
import com.socio_torcedor_core.domain.socio_torcedor.SocioTorcedorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/socios-torcedores")
public class SocioTorcedorController {

    @Autowired
    private SocioTorcedorService socioTorcedorService;
    
    @Autowired
    private CampanhaService campanhaService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SocioTorcedor>> findAll() throws ServiceException {
        final Iterable<SocioTorcedor> sociosTorcedores = socioTorcedorService.findAll();
        if (sociosTorcedores.spliterator().getExactSizeIfKnown() <= 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sociosTorcedores, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocioTorcedor> findById(@PathVariable final Long id) throws ServiceException {
        final Optional<SocioTorcedor> socioTorcedor = socioTorcedorService.findOne(id);
        if (socioTorcedor.isPresent()) {
            return new ResponseEntity<>(socioTorcedor.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<CampanhaDTO>> create(@RequestBody final SocioTorcedor socioTorcedor, final UriComponentsBuilder ucBuilder) throws ServiceException {
        socioTorcedorService.save(socioTorcedor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/socios-torcedores/{id}").buildAndExpand(socioTorcedor.getId()).toUri());
        final List<CampanhaDTO> campanhasDTO = campanhaService.findAll(socioTorcedor.getIdTimeCoracao());
        return new ResponseEntity<>(campanhasDTO, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocioTorcedor> update(@PathVariable("id") final Long id, @RequestBody final SocioTorcedor socioTorcedor) throws ServiceException {
        final Optional<SocioTorcedor> currentSocioTorcedor = socioTorcedorService.findOne(id);
        if (currentSocioTorcedor.isPresent()) {
            final SocioTorcedor socioTorcedorExistente = currentSocioTorcedor.get();
            socioTorcedorExistente.setNome(socioTorcedor.getNome());

            socioTorcedorService.save(socioTorcedorExistente);

            return new ResponseEntity<>(socioTorcedorExistente, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SocioTorcedor> delete(@PathVariable("id") final Long id) throws ServiceException {
        final Optional<SocioTorcedor> currentSocioTorcedor = socioTorcedorService.findOne(id);
        if (currentSocioTorcedor.isPresent()) {
            socioTorcedorService.delete(currentSocioTorcedor.get().getId());

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
