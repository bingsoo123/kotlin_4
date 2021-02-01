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
	private PlatformTransactionManager tran;
	
	public Authentication() {}
	
	public ModelAndView entrance(Beans m){
		ModelAndView mav = null;
		
		if(m.getService().equals("LogIn")){
		mav = this.loginCtl(m);
		}
		return mav;
	}
	public ModelAndView loginCtl(Beans m) {
		ModelAndView mav= null;
				
		TransactionStatus status = tran.getTransaction(new DefaultTransactionDefinition()); //COMMIT이나 ROLLBACK 해야됨
		
		mav=new ModelAndView();
		try {
			if(this.isMember(m)) {

				if(this.isAccess(m)) {
					mav.addObject("mId", this.getMemberInfo(m).getMId());					
					mav.addObject("mName", this.getMemberInfo(m).getMName());
					mav.addObject("MPwd", this.getMemberInfo(m).getMPwd());
					mav.addObject("mPhone", this.getMemberInfo(m).getMPhones());
					
					m.setMId("CUS9");					
					m.setMName("soo");
					m.setMPwd("9876");
					m.setMPhones("01012345678");
					
					/* TRANSACTION 처리 : ST INSERT */
					this.insCustomer(m);
					System.out.println("customer");
					tran.commit(status);
				}
			}
		}catch(Exception e) {			
			tran.rollback(status);
			e.printStackTrace();
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
	/* 회원정보 가져오기 */
	private Beans getMemberInfo(Beans m) {		
		return mapper.getMemberInfo(m);
	}

	/* TRANSACTION 처리를 위한 메서드 1 : ST INSERT */
	private boolean insCustomer(Beans m) {
		return convertToBoolean(mapper.insCustomer(m));
	}
	/* TRANSACTION 처리를 위한 메서드 2 : MV INSERT */
}
