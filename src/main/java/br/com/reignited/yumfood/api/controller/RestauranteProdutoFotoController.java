package br.com.reignited.yumfood.api.controller;

import br.com.reignited.yumfood.api.assembler.FotoProdutoModelAssembler;
import br.com.reignited.yumfood.api.model.FotoProdutoModel;
import br.com.reignited.yumfood.api.model.input.FotoProdutoInput;
import br.com.reignited.yumfood.domain.model.FotoProduto;
import br.com.reignited.yumfood.domain.model.Produto;
import br.com.reignited.yumfood.domain.service.CatalogoFotoProdutoService;
import br.com.reignited.yumfood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @Valid FotoProdutoInput fotoProdutoInput) {

        MultipartFile arquivo = fotoProdutoInput.getArquivo();
        Produto produto = produtoService.buscar(restauranteId, produtoId);

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto);
        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }
}
