package br.unitins.joaovittor.basqueteiros.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {

    public FornecedorResponseDTO create(@Valid FornecedorDTO dto);
    public void update(Long id, FornecedorDTO dto);
    public void delete(Long id);
    public List<FornecedorResponseDTO> findAll();
    public FornecedorResponseDTO findById(Long id);
    public List<FornecedorResponseDTO> findByNome(String nome);

} 