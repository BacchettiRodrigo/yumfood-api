package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.disassembler.RestauranteInputDisassembler;
import br.com.reignited.yumfood.api.assembler.RestauranteModelAssembler;
import br.com.reignited.yumfood.api.model.RestauranteModel;
import br.com.reignited.yumfood.api.model.input.RestauranteInput;
import br.com.reignited.yumfood.api.model.view.RestauranteView;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.exception.NegocioException;
import br.com.reignited.yumfood.domain.exception.RestauranteNaoEncontradoException;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassembler restauranteInputDisassembler;

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteService.listar());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping(params = "projecao=resumo")
    public List<RestauranteModel> listarApenasNome() {
        return listar();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@Valid @RequestBody RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainModel(restauranteInput);
            return restauranteModelAssembler.toModel(restauranteService.salvar(restaurante));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar
            (@PathVariable Long restauranteId, @Valid @RequestBody RestauranteInput restauranteInput) {

        try {
            Restaurante restaurante = restauranteInputDisassembler.toDomainModel(restauranteInput);

            Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
    }

    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    @DeleteMapping("/inativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
}
