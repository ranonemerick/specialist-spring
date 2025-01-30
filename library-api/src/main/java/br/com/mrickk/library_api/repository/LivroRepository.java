package br.com.mrickk.library_api.repository;

import br.com.mrickk.library_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
