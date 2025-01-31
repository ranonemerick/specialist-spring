package br.com.mrickk.library_api.service;

import br.com.mrickk.library_api.model.Autor;
import br.com.mrickk.library_api.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletarPorId(Autor autor) {
        autorRepository.delete(autor);
    }
}
