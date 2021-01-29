package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;

@Service
public class Authentication {

	public Authentication() {}
	
	public ModelAndView entrance(Beans m){
		ModelAndView mav=null; 
		System.out.println("success");
		
		switch(m.getServiceCode()) {
		case "A":
			mav = this.loginCtl(m);
			break;
		}
		
			return mav;
	}
	public ModelAndView loginCtl(Beans m) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mId", m.getMId());
		mav.addObject("mPwd", m.getMPwd());
		mav.setViewName("logInForm");
		return mav;
	}
}
