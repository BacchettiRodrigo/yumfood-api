package br.com.reignited.yumfood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @interface Cozinhas {

        @PreAuthorize(value = "@yumSecurity.podeConsultarCozinhas()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }

    @interface Restaurantes {

        @PreAuthorize(value = "@yumSecurity.podeConsultarRestaurantes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

        @PreAuthorize("@yumSecurity.podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarFuncionamento { }

        @PreAuthorize("@yumSecurity.podeGerenciarCadastroRestaurantes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarCadastro { }

    }

    @interface Pedidos {

        @PreAuthorize(value = "hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or " +
                "@yumSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or " +
                "@yumSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeBuscar { }

        @PreAuthorize(value = "@yumSecurity.podePesquisarPedidos(#filtro.clienteId, #filtro.restauranteId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodePesquisar { }

        @PreAuthorize(value = "@yumSecurity.podeGerenciarPedido(#codigoPedido)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeGerenciarPedido { }

        @PreAuthorize(value = "hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEmitirPedido { }

    }

    @interface FormaPagamento {

        @PreAuthorize(value = "hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }

    @interface Cidade {

        @PreAuthorize(value = "@yumSecurity.podeConsultarCidades()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }

    @interface Estado {

        @PreAuthorize(value = "@yumSecurity.podeConsultarEstados()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

    }

    @interface UsuarioGruposPermissoes {

        @PreAuthorize(value = "hasAuthority('SCOPE_WRITE') and @yumSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAlterarPropriaSenha { }

        @PreAuthorize(value = "hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or " +
                "@yumSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeAlterarUsuario { }

        @PreAuthorize(value = "hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeEditar { }

        @PreAuthorize(value = "@yumSecurity.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }

    }

    @interface Estatisticas {

        @PreAuthorize(value = "@yumSecurity.podeConsultarEstatisticas()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface PodeConsultar { }
    }

}
