package icia.kotlin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.bean.Member;
import icia.kotlin.bean.Movie;
import icia.kotlin.mapper.Mapper;
@Service
public class Authentication {

	@Autowired
	private Mapper mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
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
		
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition());
		
		try {
			if(this.isMember(m)) {
			
				if(this.isAccess(m)) {
				
					mav.addObject("member", this.getMemberInfo(m));
			
					
					
				}	
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			tran.rollback(status);
		}
		mav.setViewName("LogInForm");
		
		return mav;
	}
	private boolean convertToBoolean(int data) {
		return data==1? true: false;
	}
	/* Member 여부 확인 */
	private boolean isMember(Member m) {
		return this.convertToBoolean(mapper.isMember(m));
	}
	/* 엑세스 가능 여부 : 패스워드 일치 여부 */
	private boolean isAccess(Member m) {
		return this.convertToBoolean(mapper.isAccess(m));
}
	/* 회원정보 가져오기 */
	private Member getMemberInfo(Member m){
		return mapper.getMemberInfo(m);
	}

	
	
}