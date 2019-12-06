package br.com.reignited.yumfood;

import br.com.reignited.yumfood.domain.service.CozinhaService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }


//	@Test
//	public void deveAtriuirId_QuandoCadastroCozinhaCorreto() {
//		//Cenário
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//
//		//Ação
//		novaCozinha = cozinhaService.salvar(novaCozinha);
//
//		//Validação
//		Assertions.assertThat(novaCozinha).isNotNull();
//		Assertions.assertThat(novaCozinha.getId()).isNotNull();
//	}
//
//	@Test(expected = ConstraintViolationException.class)
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//		novaCozinha = cozinhaService.salvar(novaCozinha);
//	}
//
//	@Test(expected = EntidadeEmUsoException.class)
//	public void deveFalhar_QandoExcluirCozinhaEmUso(){
//		cozinhaService.excluir(1L);
//	}
//
//	@Test(expected = CozinhaNaoEncontradaException.class)
//	public void deveFalhar_QuandoExcluirCozinhaInexistente(){
//		cozinhaService.excluir(100L);
//	}

}
