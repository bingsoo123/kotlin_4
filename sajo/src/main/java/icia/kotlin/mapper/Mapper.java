package icia.kotlin.mapper;

import org.apache.ibatis.annotations.Select;

public interface Mapper {

	@Select("")
	public String getDate();
	
}
