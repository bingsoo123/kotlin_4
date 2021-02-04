package icia.kotlin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.beans.Movie;
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
		return reservation.entrance(movie);
	}

	@RequestMapping(value = "/goScreen", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String goScreen(@ModelAttribute Movie movie) throws UnsupportedEncodingException {	
		System.out.println("도착" + movie.getMvDate());
		reservation.entrance(movie);
		//return URLEncoder.encode(mav.getModel().get("ScreeningData").toString(), "UTF-8");
		return URLEncoder.encode(reservation.entrance(movie).getModel().get("ScreeningData").toString(),"UTF-8");
	}

	@RequestMapping(value = "/step4", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String step4(@ModelAttribute Movie movie) throws UnsupportedEncodingException {
		System.out.println("영화코드:" + movie.getMvCode());
		System.out.println("상영관:" + movie.getMvScreen());
		System.out.println("상영시간:"+movie.getMvTime());
		System.out.println("극장코드:"+movie.getMvThCode());
		return null;
	}
}
