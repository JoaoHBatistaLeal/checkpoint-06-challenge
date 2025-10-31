package br.com.fiap.dao;

import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PacienteDao {

    @Inject
    DataSource dataSource;

    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO PACIENTE (id_pac, nome_pac, idade_pac, nivel_tec, tipo_atendimento) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, paciente.getId_pac());
            ps.setString(2, paciente.getNome_pac());
            ps.setInt(3, paciente.getIdade_pac());
            ps.setInt(4, paciente.getNivel_tec());
            ps.setString(5, paciente.getTipoAtendiEnum().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir paciente", e);
        }
    }


    public void excluir(int id) {
        String sql = "DELETE FROM PACIENTE WHERE id_pac = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir paciente", e);
        }
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM PACIENTE WHERE id_pac = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setId_pac(rs.getInt("id_pac"));
                    paciente.setNome_pac(rs.getString("nome_pac"));
                    paciente.setIdade_pac(rs.getInt("idade_pac"));
                    paciente.setNivel_tec(rs.getInt("nivel_tec"));
                    paciente.setTipoAtendiEnum(TipoAtendiEnum.fromString(rs.getString("tipo_atendimento")));
                    return paciente;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por ID", e);
        }
        return null;
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_pac(rs.getInt("id_pac"));
                paciente.setNome_pac(rs.getString("nome_pac"));
                paciente.setIdade_pac(rs.getInt("idade_pac"));
                paciente.setNivel_tec(rs.getInt("nivel_tec"));
                paciente.setTipoAtendiEnum(TipoAtendiEnum.fromString(rs.getString("tipo_atendimento")));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        }
        return pacientes;
    }

    public boolean existePaciente(int id) {
        String sql = "SELECT 1 FROM PACIENTE WHERE id_pac = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do paciente", e);
        }
    }
}
