package com.socio_torcedor_core.domain.socio_torcedor;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

@Entity
public class SocioTorcedor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long idTimeCoracao;

    @NotNull
    private String nome;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private LocalDate dataNascimento;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
