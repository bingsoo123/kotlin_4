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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.Mapper;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.Reservation;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private Authentication auth;
	@Autowired
	private Reservation reservation;
	
	ModelAndView mav = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {		
		movie.setSCode("0");
		mav = reservation.entrance(movie);
		return mav;
	}	
	
	@RequestMapping(value = "/LogInForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LogInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("logInForm");
		return mav;
	}
	
	@RequestMapping(value = "/{str}", method = RequestMethod.POST)
	public ModelAndView LogIn(@ModelAttribute Beans m,@PathVariable String str) {
		m.setService(str);

		return auth.entrance(m);
	}
	@RequestMapping(value = "/goData", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView step(@ModelAttribute Movie movie) {
		System.out.println("진입성공 sCode=" + movie.getSCode());
		
		
		return reservation.entrance(movie);
	}
}