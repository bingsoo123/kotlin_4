package icia.kotlin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	ModelAndView mav = null;
	
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
	@RequestMapping(value = "/goData", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView step(@ModelAttribute Movie movie) {
		
		
		return res.entrance(movie);
	
	}
	@RequestMapping(value = "/goData2", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView step2(@ModelAttribute Movie movie) {
		
		
		return res.entrance(movie);
	}
	 @RequestMapping(value = "/goScreen", method = {RequestMethod.GET,RequestMethod.POST})
	   public ModelAndView step3(@ModelAttribute Movie movie) {
	      System.out.println("Screen진입성공");
	      System.out.println(movie.getMvDate()+" :: " + movie.getMvCode());
	      return res.entrance(movie);
	   }
	 @ResponseBody
	 @RequestMapping(value = "/step4", method = {RequestMethod.GET,RequestMethod.POST})
	   public String step4(@ModelAttribute Movie movie) throws UnsupportedEncodingException {
	      System.out.println(movie.getSCode());
	      System.out.println(movie.getMvCode());
	      System.out.println(movie.getMvDate());
	      
	      mav = res.entrance(movie);
	      System.out.println(mav.getModel().get("sList"));
	      
	      return URLEncoder.encode(mav.getModel().get("sList").toString(), "UTF-8");
	   }
	 @RequestMapping(value = "/step5", method = {RequestMethod.GET,RequestMethod.POST})
		public ModelAndView step5(@ModelAttribute Movie movie) {
			System.out.println("step5 진입 성공");
			
			return res.entrance(movie);

	 }
}
