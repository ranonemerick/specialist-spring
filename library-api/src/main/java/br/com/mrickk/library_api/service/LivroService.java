package br.com.mrickk.library_api.service;

import br.com.mrickk.library_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

}
