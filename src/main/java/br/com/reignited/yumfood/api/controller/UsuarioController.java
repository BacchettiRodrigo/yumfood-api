package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.UsuarioModelAssembler;
import br.com.reignited.yumfood.api.disassembler.UsuarioComSenhaDisassembler;
import br.com.reignited.yumfood.api.disassembler.UsuarioInputDisassembler;
import br.com.reignited.yumfood.api.model.UsuarioModel;
import br.com.reignited.yumfood.api.model.input.SenhaInput;
import br.com.reignited.yumfood.api.model.input.UsuarioComSenhaInput;
import br.com.reignited.yumfood.api.model.input.UsuarioInput;
import br.com.reignited.yumfood.api.openapi.controller.UsuarioControllerOpenApi;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @Autowired
    private UsuarioComSenhaDisassembler usuarioComSenhaDisassembler;

    @GetMapping
    public CollectionModel<UsuarioModel> listar() {
        List<Usuario> usuarios = usuarioService.listar();
        return usuarioModelAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return usuarioModelAssembler.toModel(usuarioService.buscar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@Valid @RequestBody UsuarioComSenhaInput input) {
        Usuario usuario = usuarioComSenhaDisassembler.toDomainModel(input);
        return usuarioModelAssembler.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,@Valid @RequestBody UsuarioInput input) {
        Usuario usuarioAtual = usuarioService.buscar(usuarioId);

        usuarioInputDisassembler.copyToDomainObject(input, usuarioAtual);

        return usuarioModelAssembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId,@Valid @RequestBody SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
