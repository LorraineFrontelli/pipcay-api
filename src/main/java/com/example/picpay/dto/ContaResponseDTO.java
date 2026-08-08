package com.example.picpay.dto;

import java.math.BigDecimal;

import com.example.picpay.entity.Conta;
import com.example.picpay.entity.TipoConta;

public record ContaResponseDTO(
        Long id,
        String nomeTitular,
        String cpf,
        String numeroConta,
        BigDecimal saldo,
        TipoConta tipoConta
) {
    public static ContaResponseDTO fromEntity(Conta conta) {
        return new ContaResponseDTO(
                conta.getId(),
                conta.getNomeTitular(),
                conta.getCpf(),
                conta.getNumeroConta(),
                conta.getSaldo(),
                conta.getTipoConta()
        );
    }
}
