package org.ganjp.jpw.tm.controller;

import javax.servlet.http.HttpServletRequest;

import org.ganjp.jpw.core.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JqmController extends BaseController {

	@RequestMapping(value = "/jqm", method = RequestMethod.GET)
	public String toJqm(HttpServletRequest request) {
		return "tm/jqm/jqm";
	}
		
}
