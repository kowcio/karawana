package allinone.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping ( value = "/", method = RequestMethod.GET)
	public String permissionGroupAdd() {

		return "/pages/main";
	}

}
