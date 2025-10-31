package br.com.fiap.dto;

import br.com.fiap.enums.TipoAtendiEnum;

public class PacienteDto {

    private Integer id;
    private String nome;
    private Integer idade;
    private Integer nivelTecnico;
    private TipoAtendiEnum tipoAtendimento;

    public PacienteDto() {}

    public PacienteDto(String nome, Integer idade, Integer nivelTecnico, TipoAtendiEnum tipoAtendimento) {
        this.nome = nome;
        this.idade = idade;
        this.nivelTecnico = nivelTecnico;
        this.tipoAtendimento = tipoAtendimento;
    }

    public PacienteDto(Integer id, String nome, Integer idade, Integer nivelTecnico, TipoAtendiEnum tipoAtendimento) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.nivelTecnico = nivelTecnico;
        this.tipoAtendimento = tipoAtendimento;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getNivelTecnico() {
        return nivelTecnico;
    }

    public void setNivelTecnico(Integer nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }

    public TipoAtendiEnum getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendiEnum tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }
}
