package com.socio_torcedor_core.domain.campanha;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CampanhaDTO {

    private Long id;
    private Long idTimeCoracao;
    private String nome;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdTimeCoracao(Long idTimeCoracao) {
        this.idTimeCoracao = idTimeCoracao;
    }

    public Long getIdTimeCoracao() {
        return idTimeCoracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public void setDataInicial(final LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataFinal(final LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

}
