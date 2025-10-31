package br.com.fiap.resource;

import br.com.fiap.dao.PacienteDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    PacienteDao dao;

    @GET
    public List<Paciente> listar() {
        return dao.listar();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        Paciente paciente = dao.buscarPorId(id);
        if (paciente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(paciente).build();
    }

    @POST
    public Response inserir(Paciente paciente) {
        dao.inserir(paciente);
        return Response.status(Response.Status.CREATED).entity(paciente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") int id) {
        if (!dao.existePaciente(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.excluir(id);
        return Response.noContent().build();
    }}


