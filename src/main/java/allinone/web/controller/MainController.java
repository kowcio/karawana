package allinone.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping ( value = "/", method = RequestMethod.GET)
	public String mainPage() {

		return "/pages/main";
	}

	@RequestMapping ( value = "/login", method = RequestMethod.GET)
	public String login() {

		return "/pages/login";
	}
	
}
