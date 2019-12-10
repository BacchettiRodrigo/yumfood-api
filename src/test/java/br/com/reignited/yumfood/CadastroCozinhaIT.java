package br.com.reignited.yumfood;

import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;
import br.com.reignited.yumfood.util.DatabaseCleaner;
import br.com.reignited.yumfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private Integer totalCozinhas;
    private Cozinha primeiraCozinha;
    private static final int COZINHA_INEXISTENTE = 1000000;
    private String COZINHA_CHINESA_JSON;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = this.port;
        RestAssured.basePath = "/cozinhas";

        COZINHA_CHINESA_JSON = ResourceUtils.getContentFromResource("/json/cozinha-chinesa.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    private void prepararDados(){
        primeiraCozinha = new Cozinha();
        primeiraCozinha.setNome("Tailandesa");
        cozinhaRepository.save(primeiraCozinha);

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Americana");
        cozinhaRepository.save(cozinha2);

        Cozinha cozinha3 = new Cozinha();
        cozinha3.setNome("Chilena");
        cozinhaRepository.save(cozinha3);

        totalCozinhas = cozinhaRepository.findAll().size();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConterTodasCozinhas_QuandoConsultarCozinhas() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(totalCozinhas));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha(){
        RestAssured.given()
                .body(ResourceUtils.getContentFromResource(COZINHA_CHINESA_JSON))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostaStatusCorretos_QuandoConsultarCozinhaExistente(){
        RestAssured.given()
                .pathParam("cozinhaId", 1)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(primeiraCozinha.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente(){
        RestAssured.given()
                .pathParam("cozinhaId", COZINHA_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
