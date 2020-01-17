package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.UsuarioComSenhaDisassembler;
import br.com.reignited.yumfood.api.assembler.UsuarioInputDisassemble;
import br.com.reignited.yumfood.api.assembler.UsuarioModelAssembler;
import br.com.reignited.yumfood.api.model.UsuarioModel;
import br.com.reignited.yumfood.api.model.input.SenhaInput;
import br.com.reignited.yumfood.api.model.input.UsuarioComSenhaInput;
import br.com.reignited.yumfood.api.model.input.UsuarioInput;
import br.com.reignited.yumfood.domain.model.Usuario;
import br.com.reignited.yumfood.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;

    @Autowired
    private UsuarioInputDisassemble usuarioInputDisassemble;

    @Autowired
    private UsuarioComSenhaDisassembler usuarioComSenhaDisassembler;

    @GetMapping
    public List<UsuarioModel> listar() {
        return assembler.toColletionModel(usuarioService.listar());
    }

    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        return assembler.toModel(usuarioService.buscar(usuarioId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@Valid @RequestBody UsuarioComSenhaInput input) {
        Usuario usuario = usuarioComSenhaDisassembler.toDomainModel(input);
        return assembler.toModel(usuarioService.salvar(usuario));
    }

    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,@Valid @RequestBody UsuarioInput input) {
        Usuario usuarioAtual = usuarioService.buscar(usuarioId);

        usuarioInputDisassemble.copyToDomainObject(input, usuarioAtual);

        return assembler.toModel(usuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId,@Valid @RequestBody SenhaInput senha) {
        usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }

}
