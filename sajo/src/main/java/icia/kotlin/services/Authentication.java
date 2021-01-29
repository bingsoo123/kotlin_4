package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;

@Service
public class Authentication {

	public Authentication() {
		
	}
	public ModelAndView entrance(Beans m){
		ModelAndView mav=null; 
		System.out.println("success");
		return mav;
	}
}
