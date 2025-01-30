package br.com.mrickk.library_api.controller;

import br.com.mrickk.library_api.dto.AutorDTO;
import br.com.mrickk.library_api.model.Autor;
import br.com.mrickk.library_api.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
                .path("{id}")
                .buildAndExpand(autor.getId())
                .toUri();
        return ResponseEntity.created(location).body(autor);
    }

}
