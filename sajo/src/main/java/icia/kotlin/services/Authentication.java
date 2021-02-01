package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Beans;
import icia.kotlin.mapper.Mapper;

@Service
public class Authentication {

	@Autowired
	private Mapper mapper;
	@Autowired
	private PlatformTransactionManager tran; // 클래스명이 100% 일치하는게 아닌경우 Autowired할땐 id값과 변수이름이 같아야함

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

		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		// 내가 트랜잭션 처리할 놈들을 저장

		try {

			if (this.isMember(bean)) {

				if (this.isPass(bean)) {

					mav.addObject("bean", this.selectMember(bean));
					bean.setMId("CUS11");
					bean.setMPwd("1234");
					bean.setMName("hiMan11");
					bean.setMPhone("01093452211");
	
					/* Tran 처리 :: ST INSERT */
					this.insCustomer(bean);
					System.out.println("CUSTOMER 인서트완료");
					
					
					/* Tran 처리 :: ST INSERT */
					bean.setMvCode("20210201");
					bean.setMvName("가나다라");
					bean.setMvGrade("T");
					bean.setMvStatus("I");
					bean.setMvImage("202102jpg");
					bean.setComments("감사합니다");
					this.insMovie(bean);
					tran.commit(status);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("롤백처리완료");
			tran.rollback(status);
		}

		mav.setViewName("logInForm");

		return mav;
	}

	private boolean convetToBoolean(int data) {
		return data == 1 ? true : false;
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

	private boolean insCustomer(Beans bean) {

		return this.convetToBoolean(mapper.insCustomer(bean));
	}
	
	private boolean insMovie(Beans bean) {
		
		return this.convetToBoolean(mapper.insMovie(bean));
	}
}
