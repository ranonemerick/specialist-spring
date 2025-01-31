package br.com.mrickk.library_api.controller;

import br.com.mrickk.library_api.dto.AutorDTO;
import br.com.mrickk.library_api.model.Autor;
import br.com.mrickk.library_api.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AutorDTO autorDTO) {
        Autor autor = autorDTO.mapearParaAutor();
        autorService.salvar(autor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autor.getId())
                .toUri();
        return ResponseEntity.created(location).body(autor);
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOpt = autorService.obterPorId(idAutor);
        if(autorOpt.isPresent()) {
            Autor autor = autorOpt.get();
            AutorDTO autorDTO = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(),
                    autor.getNacionalidade());
            return ResponseEntity.ok().body(autorDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable("id") String id) {
        UUID idAutor = UUID.fromString(id);
        Optional<Autor> autorOpt = autorService.obterPorId(idAutor);
        if(autorOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletarPorId(autorOpt.get());
        return ResponseEntity.noContent().build();
    }

}
