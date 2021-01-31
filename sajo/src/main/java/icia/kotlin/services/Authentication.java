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
	
	public ModelAndView entrance(Beans m){
		ModelAndView mav = null;
		
		if(m.getService().equals("LogIn")){
		mav = this.loginCtl(m);
		System.out.println("0000");
		}
		return mav;
	}
	public ModelAndView loginCtl(Beans m) {
		ModelAndView mav= null;
		mav=new ModelAndView();
		
		if(this.isMember(m)) {
						
			if(this.isAccess(m)) {
			mav.addObject("mId", this.getMemberInfo(m).getMId());
			mav.addObject("mName", this.getMemberInfo(m).getMName());
			mav.addObject("mPhone", this.getMemberInfo(m).getMPhones());
			}
		}
		
		mav.setViewName("logInForm");
		return mav;
	}
	
	private boolean convertToBoolean(int value) {
		return value==1?true:false;
	}
	
	/* Member 여부 확인 */
	private boolean isMember(Beans m){
		return convertToBoolean(mapper.isMember(m));
		}
	
	/* 액세스 가능 여부 확인 */
	private boolean isAccess(Beans m){
		return convertToBoolean(mapper.isAccess(m));
		}
	
	private Beans getMemberInfo(Beans m) {		
		return mapper.getMemberInfo(m);
	}

}
