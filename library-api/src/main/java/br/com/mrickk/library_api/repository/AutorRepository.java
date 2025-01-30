package br.com.mrickk.library_api.repository;

import br.com.mrickk.library_api.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
