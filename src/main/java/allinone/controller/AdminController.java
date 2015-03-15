package allinone.controller;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {

	@RequestMapping ( value = "/adminPanel", method = RequestMethod.GET)
	public ModelAndView indexForAdmin() {
		System.out.println(" Loading Admin panel");
		ModelAndView mav = new ModelAndView("/admin/adminPanel");

		return mav;

	}

}