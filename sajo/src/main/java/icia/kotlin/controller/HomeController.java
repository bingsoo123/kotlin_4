package icia.kotlin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Member;
import icia.kotlin.bean.Movie;
import icia.kotlin.mapper.Mapper;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private Authentication auth;
	@Autowired
	private Reservation res;

	private ModelAndView mv;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		mv = res.entrance(movie);
		return mv;
	}
	
	@RequestMapping(value = "/LogInForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LogInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("LogInForm");
		return mav;
	}
	
	@RequestMapping(value = "/LogIn", method = {RequestMethod.POST})
	public ModelAndView LogIn(@ModelAttribute Member m) {
		ModelAndView mav = null;
		m.setServiceCode("A");
		mav = auth.entrance(m);
		
		return mav;
	}

	
}

