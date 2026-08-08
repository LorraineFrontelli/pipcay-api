package com.example.picpay.service;

import com.example.picpay.dto.ContaRequestDTO;
import com.example.picpay.dto.ContaResponseDTO;
import com.example.picpay.dto.ContaUpdateDTO;
import com.example.picpay.entity.Conta;
import com.example.picpay.exception.BusinessException;
import com.example.picpay.exception.ResourceNotFoundException;
import com.example.picpay.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de serviço: concentra as regras de negócio da aplicação,
 * mantendo o Controller enxuto e o Repository livre de lógica.
 */
@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    @Transactional
    public ContaResponseDTO cadastrar(ContaRequestDTO dto) {
        if (contaRepository.existsByCpf(dto.cpf())) {
            throw new BusinessException("Já existe uma conta cadastrada com o CPF informado.");
        }
        if (contaRepository.existsByNumeroConta(dto.numeroConta())) {
            throw new BusinessException("Já existe uma conta com o número informado.");
        }

        Conta conta = new Conta();
        conta.setNomeTitular(dto.nomeTitular());
        conta.setCpf(dto.cpf());
        conta.setNumeroConta(dto.numeroConta());
        conta.setSaldo(dto.saldo());
        conta.setTipoConta(dto.tipoConta());

        Conta contaSalva = contaRepository.save(conta);
        return ContaResponseDTO.fromEntity(contaSalva);
    }

    @Transactional(readOnly = true)
    public List<ContaResponseDTO> listarTodas() {
        return contaRepository.findAll()
                .stream()
                .map(ContaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContaResponseDTO buscarPorId(Long id) {
        Conta conta = buscarContaOuFalhar(id);
        return ContaResponseDTO.fromEntity(conta);
    }

    @Transactional
    public ContaResponseDTO atualizar(Long id, ContaUpdateDTO dto) {
        Conta conta = buscarContaOuFalhar(id);

        conta.setNomeTitular(dto.nomeTitular());
        conta.setSaldo(dto.saldo());
        conta.setTipoConta(dto.tipoConta());

        Conta contaAtualizada = contaRepository.save(conta);
        return ContaResponseDTO.fromEntity(contaAtualizada);
    }

    private Conta buscarContaOuFalhar(Long id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com o id: " + id));
    }
}
