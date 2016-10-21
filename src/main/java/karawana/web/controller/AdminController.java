package karawana.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Secured ( value = { "ROLE_ADMIN" })
public class AdminController {

	@RequestMapping ( value = "/adminPanel", method = RequestMethod.GET)
	public ModelAndView indexForAdmin() {
		System.out.println(" Loading Admin panel");
		ModelAndView mav = new ModelAndView("/admin/adminPanel");

		return mav;

	}

}