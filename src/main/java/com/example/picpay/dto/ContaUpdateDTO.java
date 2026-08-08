package com.example.picpay.dto;

import java.math.BigDecimal;

import com.example.picpay.entity.TipoConta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaUpdateDTO(

        @NotBlank(message = "O nome do titular é obrigatório")
        String nomeTitular,

        @NotNull(message = "O saldo é obrigatório")
        @PositiveOrZero(message = "O saldo não pode ser negativo")
        BigDecimal saldo,

        @NotNull(message = "O tipo de conta é obrigatório (CORRENTE ou POUPANCA)")
        TipoConta tipoConta
) {
}
