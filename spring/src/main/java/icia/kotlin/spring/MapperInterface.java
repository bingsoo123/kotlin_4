package icia.kotlin.spring;

import org.apache.ibatis.annotations.Select; 

public interface MapperInterface { 
	
	@Select("SELECT SYSDATE FROM DUAL")
	public String getData(); 
	public String getData2();
	
}

// 인터페이스는 실행구문은 없다 . 구조만 짠다.