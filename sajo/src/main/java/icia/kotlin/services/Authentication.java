package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.mapper.Mapper;

@Service
public class Authentication {
	
	@Autowired
	private Mapper mapper;

	public Authentication() {}
	
	public ModelAndView entrace(Beans bean) {
		ModelAndView mav = null;
		
		if(bean.getService().equals("LogIn")){
		mav = this.LoginCtl(bean);
		}
		return mav;
	}
	
	private ModelAndView LoginCtl(Beans bean) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println("로그인 도착");
		
		mav.addObject("mId",bean.getMId());
		mav.addObject("mPwd",bean.getMPwd());
		mav.addObject("date" , mapper.getData2());
		mav.setViewName("logInForm");
		
		return mav;
	}
	
}
