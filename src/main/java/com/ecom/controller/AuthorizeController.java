package com.ecom.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizeController {

	
	
	@RequestMapping(value = "/authorize/error", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "errorpage";
	}
}
