package br.com.fiap.service;

import br.com.fiap.dao.PacienteDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteDao dao;

    public List<Paciente> listarTodos() {
        return dao.listar();
    }

    public Paciente buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void cadastrar(Paciente paciente) {
        dao.inserir(paciente);
    }

    public boolean remover(int id) {
        if (!dao.existePaciente(id)) {
            return false;
        }
        dao.excluir(id);
        return true;
    }

    public boolean existe(int id) {
        return dao.existePaciente(id);
    }
}
