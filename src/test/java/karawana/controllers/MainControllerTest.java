//package karawana.controllers;
//
//import karawana.Application;
//import karawana.repositories.GroupRepository;
//import karawana.repositories.UserRepository;
//import karawana.web.controller.MainController;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
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
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {Application.class})
//@Transactional
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//
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
//            MvcResult mvcResult = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        HttpSession session = mvcResult
//                .getRequest()
//                .getSession();
//        ModelAndView modelAndView = mvcResult.getModelAndView();
//
//        //given - original request
//        assertTrue(session.getId().equals("1"));
//        session.getAttribute(MainController.USER_NAME);
//        session.getAttribute(MainController.GROUP_ID);
//        log.info("SessionID:{}", session.getId());
//        log.info("SessionIsNew:{}", session.isNew());
//
//        //when 1 - reload to check session stored
//        MvcResult mvcResultReload = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        HttpSession sessionReload = mvcResultReload
//                .getRequest()
//                .getSession();
//        ModelAndView modelAndViewReload = mvcResultReload.getModelAndView();
//
//        log.info("SessionID sessionReload:{}", sessionReload.getId());
//        log.info("SessionIsNew sessionReload:{}", sessionReload.isNew());
//
//        assertTrue("Sessions should be the same.", sessionReload.getId().equals(sessionReload.getId()));
//        assertTrue("Model and view should be the same.", modelAndView.equals(modelAndViewReload));
//
//        //when 2 - session ivalidated
//        session.invalidate();
//
//        MvcResult mvcResultNew = this.mvc.perform(
//                MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        HttpSession sessionNew = mvcResult
//                .getRequest()
//                .getSession();
//
//        log.info("SessionID sessionNew :{}", sessionNew.getId());
//        log.info("SessionIsNew sessionNew:{}", sessionNew.isNew());
//        assertTrue("Sessions should be different.", !sessionReload.getId().equals(sessionNew.getId()));
//
//        //then - load user from scratch - new one generated
//
//
//    }
//
//
//}