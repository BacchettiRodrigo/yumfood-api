package br.com.reignited.yumfood.api.v1.controller;

import br.com.reignited.yumfood.api.v1.assembler.FotoProdutoModelAssembler;
import br.com.reignited.yumfood.api.v1.model.FotoProdutoModel;
import br.com.reignited.yumfood.api.v1.model.input.FotoProdutoInput;
import br.com.reignited.yumfood.api.v1.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import br.com.reignited.yumfood.domain.exception.EntidadeNaoEncontradaException;
import br.com.reignited.yumfood.domain.model.FotoProduto;
import br.com.reignited.yumfood.domain.model.Produto;
import br.com.reignited.yumfood.domain.service.CatalogoFotoProdutoService;
import br.com.reignited.yumfood.domain.service.FotoStorageService;
import br.com.reignited.yumfood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;

    @Autowired
    private FotoStorageService fotoStorageService;

    @GetMapping
    public FotoProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoProduto = catalogoFotoProdutoService.buscar(restauranteId, produtoId);
        return fotoProdutoModelAssembler.toModel(fotoProduto);
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> servirFoto(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto fotoProduto = catalogoFotoProdutoService.buscar(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
            List<MediaType> mediaTypes = MediaType.parseMediaTypes(acceptHeader);

            verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypes);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());

            if (fotoRecuperada.temUrl()) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                        .build();
            } else {
                return ResponseEntity
                        .ok()
                        .contentType(mediaTypeFoto)
                        .body(new InputStreamResource(fotoRecuperada.getInputStream()));
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypes)
            throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypes.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypeFoto));

        if (!compativel) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypes);
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @Valid FotoProdutoInput fotoProdutoInput,
            @RequestPart(required = true) MultipartFile arquivo) throws IOException {

        //MultipartFile arquivo = fotoProdutoInput.getArquivo();
        Produto produto = produtoService.buscar(restauranteId, produtoId);

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        catalogoFotoProdutoService.deletar(restauranteId, produtoId);
    }
}
