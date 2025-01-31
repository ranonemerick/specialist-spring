package br.com.mrickk.library_api.validator;

import br.com.mrickk.library_api.exceptions.RegistroDuplicadoException;
import br.com.mrickk.library_api.model.Autor;
import br.com.mrickk.library_api.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor) {
        if(existeAutorcadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado! ");
        }
    }

    private boolean existeAutorcadastrado(Autor autor) {
        Optional<Autor> autorOpt = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
        if(autor.getId() == null) {
            return autorOpt.isPresent();
        }
        return !autor.getId().equals(autorOpt.get().getId()) && autorOpt.isPresent();
    }

}
