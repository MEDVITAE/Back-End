package org.example.Service;

import org.example.Controller.AgendaController;
import org.example.Domain.Agenda;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.interfaces.AgendaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    private final AgendaRepository repository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.repository = agendaRepository;
    }

    public List<Agenda> listaAgendamentos() {
        List<Agenda> listaAgenda = this.repository.findAll();
        if (listaAgenda.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return listaAgenda;
    }

    public Agenda salvar(@Validated Agenda agenda) {
        Agenda agendaSalva = this.repository.save(agenda);

        return agendaSalva;
    }

    public Agenda atualizar(@Validated long id, Agenda agendaAtualizado) {
        Agenda agenda = buscarPorId(id);
        agendaAtualizado.setIdAgenda(id);
        repository.save(agendaAtualizado);

        return agendaAtualizado;
    }

    public void deletar(long id) {
        Agenda agenda = this.buscarPorId(id);
        repository.deleteById(id);
    }

    public Agenda buscarPorId(long id) {
        Optional<Agenda> agendaOpt = this.repository.findById(id);

        if (agendaOpt.isEmpty()){
            throw new RuntimeException();
        }
        return agendaOpt.get();
    }
}
