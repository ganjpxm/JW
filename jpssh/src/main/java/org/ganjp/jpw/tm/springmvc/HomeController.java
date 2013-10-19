package org.ganjp.jpw.tm.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends BaseController {

	@RequestMapping(value = "/springmvc", method = RequestMethod.GET)
	public String toSpringmvcShowcaseHome(HttpServletRequest request) {
		return "tm/springmvc/home";
	}
		
}
