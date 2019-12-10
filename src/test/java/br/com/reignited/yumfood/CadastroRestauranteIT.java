package br.com.reignited.yumfood;

import br.com.reignited.yumfood.domain.model.Cozinha;
import br.com.reignited.yumfood.domain.model.Restaurante;
import br.com.reignited.yumfood.domain.repository.CozinhaRepository;
import br.com.reignited.yumfood.domain.repository.RestauranteRepository;
import br.com.reignited.yumfood.util.DatabaseCleaner;
import br.com.reignited.yumfood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    private String JSON_RESTAURANTE_CORRETO;
    private String JSON_RESTAURANTE_COZINHA_INEXISTENTE;

    private static final Long RESTAURANTE_EXISTENTE = 1L;
    private static final Long RESTAURANTE_INEXISTENTE = 100000L;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        JSON_RESTAURANTE_CORRETO = ResourceUtils
                .getContentFromResource("/json/correto/restaurante-new-york-barbecue.json");
        JSON_RESTAURANTE_COZINHA_INEXISTENTE = ResourceUtils
                .getContentFromResource("/json/correto/restaurante-cozinha-inexistente.json");

        databaseCleaner.clearTables();
        preparandoDados();
    }

    public void preparandoDados() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Cozinha Americana");
        cozinha = cozinhaRepository.save(cozinha);

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Burguer Top");
        restaurante1.setTaxaFrete(new BigDecimal(11.5));
        restaurante1.setCozinha(cozinha);
        restauranteRepository.save(restaurante1);
    }

    @Test
    public void deveRetornaStatus200_QuandoConsultaRestauranteExistente() {
        RestAssured.given()
                .pathParam("restauranteId", RESTAURANTE_EXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornaStatus404_QuandoConsultaRestauranteInexistente() {
        RestAssured.given()
                .pathParam("restauranteId", RESTAURANTE_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestauranteCorreto() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(JSON_RESTAURANTE_CORRETO)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornaStatus400_QuandoCadastrarRestauranteCozinhaInexistente() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(JSON_RESTAURANTE_COZINHA_INEXISTENTE)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
