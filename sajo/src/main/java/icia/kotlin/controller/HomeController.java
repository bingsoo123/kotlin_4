package icia.kotlin.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.beans.Movie;
import icia.kotlin.services.Authentication;
import icia.kotlin.services.MovieList;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private Authentication auth;
	@Autowired
	private MovieList mList;
	
	ModelAndView mv = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute Movie movie) {
		mv = mList.entrance(movie);
		return mv;
	}
	
	@RequestMapping(value = "/LogInForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LogInForm() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("logInForm");
		return mav;
	}
	
	@RequestMapping(value = "/{str}", method = RequestMethod.POST)
	public ModelAndView LogIn(@ModelAttribute Beans test,@PathVariable String str) {
		test.setService(str);
		return auth.entrace(test);
	}
	
	@RequestMapping(value = "/goData", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView step(@ModelAttribute Movie movie) {
		return mList.entrance(movie);
	}
	
	@RequestMapping(value = "/goScreen", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView step3(@ModelAttribute Movie movie) {
		System.out.println("Screen진입성공");
		System.out.println(movie.getMvDate()+" :: " + movie.getMvCode());
		return mList.entrance(movie);
	}
	
}
