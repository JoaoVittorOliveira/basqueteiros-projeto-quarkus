package br.unitins.joaovittor.basqueteiros.Usuario.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(LocalDate.of(dto.anoAniv(), dto.mesAniv(), dto.diaAniv()));

        repository.persist(usuario);
        return UsuarioResponseDTO.valueof(usuario);
    }

    // metodo nao da certo pois da erro do servidor
    // por estar definido como "unique" no model
    public void verificarCpf(String cpf){
        Usuario usuario = repository.findByCpf(cpf);
        if(usuario != null)
            throw new ValidationException("cpf", "O CPF '"+cpf+"' ja existe");
    }

    @Override
    @Transactional
    public boolean delete(Long id) { 
        return repository.deleteById(id); 
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(LocalDate.of(dto.anoAniv(), dto.mesAniv(), dto.diaAniv()));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return repository.findAll()
                         .stream()
                         .map(e -> UsuarioResponseDTO.valueof(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findById(Long id) { 
        return UsuarioResponseDTO.valueof(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                                .stream()
                                .map(e -> UsuarioResponseDTO.valueof(e)).toList();
    }
    

}