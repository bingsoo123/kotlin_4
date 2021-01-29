package icia.kotlin.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Member;
@Service
public class Authentication {

	public Authentication() {}
	
	public ModelAndView entrance(Member m) {
		
		ModelAndView mav = null;
		
		switch(m.getServiceCode()) { 
		case "A":
			mav = this.loginCtl(m);
			break;
		}
		return mav;
	}
	private ModelAndView loginCtl(Member m) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("mId", m.getMId());
		mav.addObject("mPwd", m.getMPwd());
		
		mav.setViewName("LogInForm");
		
		return mav;
	}
}
