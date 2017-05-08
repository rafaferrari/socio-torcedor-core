package com.socio_torcedor_core.controller;

import com.socio_torcedor_core.domain.socio_torcedor.SocioTorcedor;
import com.socio_torcedor_core.domain.socio_torcedor.SocioTorcedorService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SocioTorcedorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SocioTorcedorService socioTorcedorService;

    @Test
    public void test_deve_retornar_no_content_quando_nao_existir_socios_torcedores() throws Exception {
        final Iterable<SocioTorcedor> sociosTorcedores = new ArrayList<>();
        when(socioTorcedorService.findAll()).thenReturn(sociosTorcedores);

        mvc.perform(MockMvcRequestBuilders.get("/socios-torcedores")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_deve_retornar_as_campanhas_cadastradas() throws Exception {
        final List<SocioTorcedor> sociosTorcedores = new ArrayList<>();
        sociosTorcedores.add(populate().get());
        when(socioTorcedorService.findAll()).thenReturn(sociosTorcedores);

        mvc.perform(MockMvcRequestBuilders.get("/socios-torcedores")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deve_retornar_no_content_quando_nao_existir_socio_torcedor_por_id() throws Exception {
        final Long id = 0L;
        when(socioTorcedorService.findOne(id)).thenReturn(Optional.ofNullable(null));

        mvc.perform(MockMvcRequestBuilders.get("/socios-torcedores/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deve_retornar_o_socio_torcedor_por_id() throws Exception {
        final Long id = 1L;
        final Optional<SocioTorcedor> socioTorcedor = populate();
        when(socioTorcedorService.findOne(id)).thenReturn(socioTorcedor);

        mvc.perform(MockMvcRequestBuilders.get("/socios-torcedores/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deve_inserir_um_socio_torcedor() throws Exception {
        final Optional<SocioTorcedor> socioTorcedor = populate();
        when(socioTorcedorService.save(socioTorcedor.get())).thenReturn(socioTorcedor);

        mvc.perform(MockMvcRequestBuilders.post("/socios-torcedores")
                .content(new JSONObject("{'nome': 'teste'}").toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void test_deve_retornar_bad_request_quando_executar_post_sem_corpo_requisicao() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/socios-torcedores")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_deve_atualizar_uma_campanha() throws Exception {
        final Optional<SocioTorcedor> socioTorcedor = populate();
        when(socioTorcedorService.save(socioTorcedor.get())).thenReturn(socioTorcedor);
        when(socioTorcedorService.findOne(1L)).thenReturn(socioTorcedor);

        mvc.perform(MockMvcRequestBuilders.put("/socios-torcedores/1")
                .content(new JSONObject("{'nome': 'Rafael'}").toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test_deve_retornar_not_found_quando_id_informado_nao_existir_ao_atualizar() throws Exception {
        when(socioTorcedorService.findOne(2L)).thenReturn(Optional.ofNullable(null));

        mvc.perform(MockMvcRequestBuilders.put("/socios-torcedores/2")
                .content(new JSONObject("{'nome': 'Rafael'}").toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_deve_retornar_bad_request_quando_executar_put_sem_corpo_requisicao() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/socios-torcedores/1")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_deve_remover_um_socio_torcedor() throws Exception {
        final Optional<SocioTorcedor> socioTorcedor = populate();
        when(socioTorcedorService.findOne(1L)).thenReturn(socioTorcedor);

        mvc.perform(MockMvcRequestBuilders.delete("/socios-torcedores/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test_deve_retornar_not_found_quando_id_informado_nao_existir_ao_remover() throws Exception {
        when(socioTorcedorService.findOne(2L)).thenReturn(Optional.ofNullable(null));

        mvc.perform(MockMvcRequestBuilders.delete("/socios-torcedores/2"))
                .andExpect(status().isNotFound());
    }

    private Optional<SocioTorcedor> populate() {
        final SocioTorcedor socioTorcedor = new SocioTorcedor();
        socioTorcedor.setNome("Rafael");
        socioTorcedor.setEmail("teste@teste.com");
        socioTorcedor.setDataNascimento(LocalDate.now());
        return Optional.of(socioTorcedor);
    }

}
