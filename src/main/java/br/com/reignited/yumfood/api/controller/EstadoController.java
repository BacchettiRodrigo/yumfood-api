package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.domain.exception.EntidadeEmUsoException;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.Estado;
import br.com.reignited.yumfood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estado = estadoService.buscar(estadoId);

        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return estadoService.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
        Optional<Estado> estadoAtual = estadoService.buscar(estadoId);

        if (estadoAtual.isPresent()) {
            BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
            estadoService.salvar(estadoAtual.get());
            return ResponseEntity.ok(estadoAtual.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
        try {
            estadoService.remover(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
