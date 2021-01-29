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

import icia.kotlin.beans.Beans;
import icia.kotlin.services.Authentication;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private Authentication auth;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, ModelAndView mv) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		mv.addObject("welcome","어서오세요 ~ 환영합니다");
		mv.addObject("hellow","감사합니다");
		mv.addObject("serverTime", formattedDate );
		
		mv.setViewName("home");
		
		return mv;
	}
	
	@RequestMapping(value = "/LogInForm", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView LogInForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("logInForm");
		return mav;
	}
	
	@RequestMapping(value = "/LogIn", method = {RequestMethod.POST})
	public ModelAndView LogIn(@ModelAttribute Beans m) {
		System.out.println("로그인 도착");
		
		ModelAndView mav = new ModelAndView();		
		mav.addObject("mId" , m.getMId());
		mav.addObject("mPwd" , m.getMPwd());
		mav.setViewName("logInForm");
		
		auth.entrance(m);
		return mav;
	}
	
}
