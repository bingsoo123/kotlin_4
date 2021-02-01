
package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.Mapper;

@Service
public class Authentication {

	@Autowired
	private Mapper mapper;
	@Autowired
	private PlatformTransactionManager tran;  // 클래스명이 100% 일치하는게 아닌경우 Autowired할땐 id값과 변수이름이 같아야함

	public Authentication() {
	}

	public ModelAndView entrace(Beans bean) {
		
		ModelAndView mav = null;

				
		
		if (bean.getService().equals("LogIn")) {
			mav = this.LoginCtl(bean);
		}
		return mav;
	}

	private ModelAndView LoginCtl(Beans bean) {
		ModelAndView mav = new ModelAndView();

		
		try {
		if (this.isMember(bean)) {

			if (this.isPass(bean)) {
				
				
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
		
	}
		mav.setViewName("logInForm");
		
		return mav;
	}

	private boolean convetToBoolean(int data) {
		return data == 1 ? true : false;
	}

		
	private boolean insMember(Beans bean) {
		return this.convetToBoolean(mapper.insMember(bean));
	}
	
	private boolean isMember(Beans bean) {
		return this.convetToBoolean(mapper.isMember(bean));
	}

	private boolean isPass(Beans bean) {
		return this.convetToBoolean(mapper.isPass(bean));
	}
	
	private Beans selectMember(Beans bean) {
		return mapper.selectMember(bean);
	}
}
