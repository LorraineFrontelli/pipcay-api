package com.example.picpay.controller;

import com.example.picpay.dto.ContaRequestDTO;
import com.example.picpay.dto.ContaResponseDTO;
import com.example.picpay.dto.ContaUpdateDTO;
import com.example.picpay.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> cadastrar(@RequestBody @Valid ContaRequestDTO dto) {
        ContaResponseDTO contaCriada = contaService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(contaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContaUpdateDTO dto) {
        return ResponseEntity.ok(contaService.atualizar(id, dto));
    }
}
