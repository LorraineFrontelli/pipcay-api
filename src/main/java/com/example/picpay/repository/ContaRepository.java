package com.example.picpay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.picpay.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    boolean existsByCpf(String cpf);

    boolean existsByNumeroConta(String numeroConta);

    Optional<Conta> findByCpf(String cpf);

    Optional<Conta> findByNumeroConta(String numeroConta);
}
