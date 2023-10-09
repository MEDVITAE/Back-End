package org.example.Service;

import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.interfaces.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaRepository repository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.repository = agendaRepository;
    }

    public List<Agenda> listar() {
        List<Agenda> agenda = this.repository.findAll();

        if (agenda.isEmpty()){
            throw new RuntimeException("Lista agenda Vazia");
        }
        return agenda;
    }

    public Agenda salvar(Agenda agenda) {
        Agenda agendaSalva = this.repository.save(agenda);
        return agendaSalva;
    }

    public Agenda atualizar(Long id, Agenda agendaAtualizado) {
        Agenda agenda = this.buscarPorId(id);
        agendaAtualizado.setId(agenda.getId());
        return repository.save(agendaAtualizado);
    }

    private Agenda buscarPorId(Long id) {
        return null;
    }

    public void deletar(int id) {
        Hospital hospital = this.buscarPorId(id);
        repository.deletarAgendaPorId(id);
    }

}
