package com.oEduardoBorges.service;

import com.oEduardoBorges.dto.request.tela.TelaRequest;
import com.oEduardoBorges.dto.response.tela.TelaResponse;
import com.oEduardoBorges.model.Tela;
import com.oEduardoBorges.repository.TelaRepository;
import com.oEduardoBorges.service.exceptions.DatabaseException;
import com.oEduardoBorges.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelaService {

    private final TelaRepository telaRepository;

    public List<TelaResponse> telaList() {
        List<Tela> telas = telaRepository.findAll();
        return telas.stream().map(TelaResponse::new).toList();
    }

    public Optional<TelaResponse> findTelaById(Long id) {
        Optional<Tela> telaById = Optional.ofNullable(telaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tela não encontrado.")));
        return telaById.map(TelaResponse::new);
    }

    @Transactional
    public TelaResponse createTela(TelaRequest telaRequest) {
        boolean exists = telaRepository.existsByName(telaRequest.getName());

        if(exists){
            throw new DatabaseException("Tela já cadastrada com esse nome.");
        }
        Tela tela = telaRepository.save(new Tela(telaRequest));
        return new TelaResponse(tela);
    }

    @Transactional
    public TelaResponse telaUpdate(Long id, TelaRequest telaRequest) {
        try {
            Tela tela = telaRepository.getReferenceById(id);
            tela.setName(telaRequest.getName());
            tela.setDescription(telaRequest.getDescription());
            tela = telaRepository.save(tela);
            return new TelaResponse(tela);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado." + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!telaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tela não encontrado.");
        }
        try {
            telaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }
}
