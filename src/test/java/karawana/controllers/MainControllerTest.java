//package karawana.controllers;
//
//import karawana.Application;
//import karawana.entities.User;
//import karawana.repositories.GroupRepository;
//import karawana.repositories.UserRepository;
//import karawana.web.controller.MainController;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {Application.class})
//@Transactional
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class MainControllerTest {
//
//    @Autowired
//    private GroupRepository groupRepository;
//    @Autowired
//    private UserRepository userRepository;
//    private final Logger log = LoggerFactory.getLogger(getClass());
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void exampleTest() throws Exception {
//
////1st request
//
//        MvcResult mvcResult = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn()
//                ;
//        HttpSession session = mvcResult
//                .getRequest().getSession();
//        ModelAndView modelAndView = mvcResult.getModelAndView();
//        User user = (User) modelAndView.getModel().get("user");
//        log.info("SessionID:{}", session.getId());
//        log.info("SessionIsNew:{}", session.isNew());
//
////2nd request
//
//        MvcResult mvcResultReload = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        HttpSession sessionReload = mvcResultReload
//                .getRequest()
//                .getSession();
//        ModelAndView modelAndViewReload = mvcResultReload.getModelAndView();
//        User userReload = (User) modelAndView.getModel().get("user");
//        log.info("SessionID sessionReload:{}", sessionReload.getId());
//        log.info("SessionIsNew sessionReload:{}", sessionReload.isNew());
//
////requests asserts
//
//        assertTrue("Sessions should be the same but are not .", sessionReload.getId().equals(sessionReload.getId()));
//        assertTrue("Model and view should be the same.", modelAndView.equals(modelAndViewReload));
//
////3rd request after invalidated session
//        session.invalidate();
//
//        MvcResult mvcResultNew = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        HttpSession sessionNew = mvcResult
//                .getRequest()
//                .getSession();
//        User userNew = (User) modelAndView.getModel().get("user");
//
//        log.info("SessionID session : {}", session.getId());
//        log.info("SessionID sessionReload : {}", sessionReload.getId());
//        log.info("SessionID sessionNew : {}", sessionNew.isNew());
//        assertFalse("Sessions should be the same but are not .", sessionReload.getId().equals(sessionReload.getId()));
//
//        //then - load user from scratch - new one generated
//
//
//    }
//
//
//}