package icia.kotlin.spring;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j   // 로그파일을 만듦
public class Test1 {
										
										// Autohwired >> 해당하는 데이터타입을  메모리에 등록되잇는 녀석으로 자동으로 연결해줌
	@Setter(onMethod_ = {@Autowired})  // 만약 같은데이터타입이 1개보다 많이 올라가잇다면 name=? 으로 구분해준다
	private DataSource datasource;   // DBCP에 접근할 때 사용하는녀석이 DataSource
	@Setter(onMethod_= {@Autowired})
	private SqlSessionFactory sqlSession;
	@Setter(onMethod_= {@Autowired})
	private MapperInterface mapper;
	@Test
	public void connectTest() {
	
		try { 
			SqlSession sql = sqlSession.openSession();
			Connection connect = datasource.getConnection();
			log.info(sql);
			log.info(connect);
			log.info(mapper.getData());
			log.info(mapper.getData2());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
