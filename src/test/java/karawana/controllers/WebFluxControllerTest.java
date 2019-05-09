package karawana.controllers;

import karawana.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
        , properties = "spring.main.web-application-type=reactive")
//@WebFluxTest
public class WebFluxControllerTest {

    //    @Autowired
//    private GroupRepository groupRepository;
//    @Autowired
//    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void exampleTest() throws Exception {

        //return html and headers all in a nice long string, formatted (as website)
        WebTestClient.BodyContentSpec bodyContentSpec = webTestClient
                .get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
        EntityExchangeResult<byte[]> entityExchangeResult = bodyContentSpec
//                .xpath("//*[@id=\"test2\"]").isEqualTo("Test2")
//                .jsonPath("#allUsers").isNotEmpty()
//                .xml("").exists()
                .returnResult()
//                .expectBodyList(User.class).hasSize(1)
                ;
        //check from the HTML returned.
        String htmlPure = new String(entityExchangeResult.getResponseBodyContent());
//hamcrest example working
//        Pattern compile = Pattern.compile(".*?(test2).*?", Pattern.DOTALL);
//        assertThat(htmlPure, MatchesPattern.matchesPattern(compile));
//ASSERTJ - 4 times better
        assertThat(htmlPure).contains("<div id=\"map\" class=\"container-fluid\"></div>");
//        assertThat(htmlPure).containsPattern("map\">([a-zA-Z0-9]{5,})</div>.*?");
//        assertThat(htmlPure).doesNotContain("test2\">([a-zA-Z0-9]{6,})</div>.*?");

        log.info("Log:{}", htmlPure);


//    }


    }


    @Test
    public void changeGroupTest() throws Exception {

//        WebTestClient webTestClient = this.webTestClient;
//// second request
//        WebTestClient.ResponseSpec ok = webTestClient
//                .mutate().responseTimeout(Duration.ofMillis(30000))
//                .build().get().uri("/").exchange().expectStatus()
//                .isOk();
//        WebTestClient.BodyContentSpec bodyContentSpec1 = ok
//                .expectBody();
//        EntityExchangeResult<byte[]> entityExchangeResult1 = bodyContentSpec1.returnResult();
////        log.info(entityExchangeResult1.toString());
//        String mainPage = new String(entityExchangeResult1.getResponseBodyContent());
////        log.info(mainPage);
        //return html and headers all in a nice long string, formatted (as website)
        WebTestClient.BodyContentSpec bodyContentSpec =
                webTestClient
                        .mutate().responseTimeout(Duration.ofMillis(30000))
                        .build().get()
                        .uri("/api/changeGroup/restTestGroupName")
                        .exchange().expectStatus()
                        .isOk()
                        .expectBody();

        EntityExchangeResult<byte[]> entityExchangeResult = bodyContentSpec
                .jsonPath("users").isNotEmpty()
                .jsonPath("id").isEqualTo("1")
//                .jsonPath("users[0].location").exists()
                .returnResult();

        String htmlPure = new String(entityExchangeResult.getResponseBodyContent());
        log.info(htmlPure);

    }

    /**
     * //https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
     * Works very nice, can work all api around that !! !! !! !! !! !!
     *
     * @throws Exception
     */
    @Test
    public void qweasdqwe() throws Exception {

        WebTestClient webTestClient = this.webTestClient;

//        EntityExchangeResult<Group> groupEntityExchangeResult =
                webTestClient
                .mutate().responseTimeout(Duration.ofMillis(30000))
                .build().get()
                .uri("/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(personMono, Person.class)
                .exchange();
//                .expectStatus()
//                .isOk()
//                .expectBody(Group.class).returnResult();
//        log.info("Got group : {}", groupEntityExchangeResult.getResponseBody().toString());
        webTestClient
                .mutate().responseTimeout(Duration.ofMillis(30000))
                .build().get()
                .uri("/api/changeGroup/restTestGroupName").exchange();
        webTestClient
                .mutate().responseTimeout(Duration.ofMillis(30000))
                .build().get()
                .uri("/api/changeGroup/restTestGroupName23").exchange();

    }


}