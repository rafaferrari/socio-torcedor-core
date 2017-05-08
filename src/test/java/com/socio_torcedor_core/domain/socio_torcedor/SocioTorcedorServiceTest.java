package com.socio_torcedor_core.domain.socio_torcedor;

import com.socio_torcedor_core.domain.exceptions.ServiceException;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SocioTorcedorServiceTest {

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    @Before
    public void setup() throws ServiceException {
        final SocioTorcedor socioTorcedor = populate();
        socioTorcedorService.save(socioTorcedor);
    }

    private SocioTorcedor populate() {
        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setIdTimeCoracao(Long.MIN_VALUE);
        socioTorcedor.setNome("Rafael");
        socioTorcedor.setEmail("teste@teste.com");
        socioTorcedor.setDataNascimento(LocalDate.now());
        return socioTorcedor;
    }

    @Test
    public void test_deve_buscar_todos_socios_torcedores() throws ServiceException {
        // GIVEN  
        final Long total = 1L;

        // WHEN
        final Iterable<SocioTorcedor> sociosTorcedores = socioTorcedorService.findAll();

        // THEN
        assertThat(sociosTorcedores.spliterator().getExactSizeIfKnown()).isEqualTo(total);
    }

    @Test
    public void test_deve_buscar_um_socio_torcedor_por_id() throws ServiceException {
        // GIVEN 
        final Long id = 1L;

        // WHEN
        final Optional<SocioTorcedor> resultado = socioTorcedorService.findOne(id);

        // THEN
        assertThat(resultado.isPresent()).isEqualTo(true);
        assertThat(resultado.get().getNome()).isEqualTo("Rafael");
    }

    @Test
    public void test_deve_salvar_um_socio_torcedor() throws ServiceException {
        // GIVEN
        final SocioTorcedor socioTorcedor = populate();
        socioTorcedor.setEmail("test@test.com");

        // WHEN
        final Optional<SocioTorcedor> resultado = socioTorcedorService.save(socioTorcedor);

        // THEN
        assertThat(resultado.isPresent()).isEqualTo(true);
        assertThat(resultado.get().getNome()).isEqualTo(socioTorcedor.getNome());
    }

    @Test(expected = ServiceException.class)
    public void test_deve_lancar_excecao_ao_salvar_um_socio_torcedor_com_email_existente() throws ServiceException {
        // GIVEN
        final SocioTorcedor socioTorcedor1 = populate();
        final SocioTorcedor socioTorcedor2 = populate();

        // WHEN
        socioTorcedorService.save(socioTorcedor1);
        socioTorcedorService.save(socioTorcedor2);

        // THEN 
        // Catch the ServiceException
    }

    @Test(expected = ServiceException.class)
    public void test_deve_lancar_excecao_ao_salvar_um_socio_torcedor_sem_nome() throws ServiceException {
        // GIVEN
        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setEmail("teste@teste.com");
        socioTorcedor.setDataNascimento(LocalDate.now());

        // WHEN
        socioTorcedorService.save(socioTorcedor);

        // THEN 
        // Catch the ServiceException
    }

    @Test(expected = ServiceException.class)
    public void test_deve_lancar_excecao_ao_salvar_um_socio_torcedor_sem_data_nascimento() throws ServiceException {
        // GIVEN
        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setEmail("teste@teste.com");
        socioTorcedor.setNome("Rafael");

        // WHEN
        socioTorcedorService.save(socioTorcedor);

        // THEN 
        // Catch the ServiceException
    }

    @Test(expected = ServiceException.class)
    public void test_deve_lancar_excecao_ao_salvar_um_socio_torcedor_sem_email() throws ServiceException {
        // GIVEN
        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setNome("Rafael");
        socioTorcedor.setDataNascimento(LocalDate.now());

        // WHEN
        socioTorcedorService.save(socioTorcedor);

        // THEN 
        // Catch the ServiceException
    }

    @Test
    public void test_deve_atualizar_um_socio_torcedor() throws ServiceException {
        // GIVEN
        final Long id = 1L;
        final Optional<SocioTorcedor> socioTorcedor = socioTorcedorService.findOne(id);
        final SocioTorcedor encontrado = socioTorcedor.get();
        encontrado.setNome("Ferrari");

        // WHEN
        final Optional<SocioTorcedor> resultado = socioTorcedorService.save(encontrado);

        // THEN
        assertThat(resultado.isPresent()).isEqualTo(true);
        assertThat(resultado.get().getNome()).isEqualTo(encontrado.getNome());
    }

    @Test
    public void test_deve_remover_um_socio_torcedor() throws ServiceException {
        // GIVEN
        final Long id = 1L;

        // WHEN
        socioTorcedorService.delete(id);
        final Iterable<SocioTorcedor> sociosTorcedores = socioTorcedorService.findAll();

        // THEN
        assertThat(sociosTorcedores.spliterator().getExactSizeIfKnown()).isEqualTo(0);
    }

    @Test(expected = ServiceException.class)
    public void test_deve_retornar_erro_ao_remover_um_socio_torcedor_com_id_inexistente() throws ServiceException {
        // GIVEN
        final Long id = 5L;

        // WHEN
        socioTorcedorService.delete(id);

        // THEN
        // Catch the ServiceException
    }

}
