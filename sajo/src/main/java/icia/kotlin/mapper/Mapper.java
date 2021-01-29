package icia.kotlin.mapper;

import icia.kotlin.beans.Beans;

public interface Mapper {
	
	public int isPass(Beans bean);
	public int isMember(Beans bean);
	public Beans selectMember(Beans bean);
	
}
