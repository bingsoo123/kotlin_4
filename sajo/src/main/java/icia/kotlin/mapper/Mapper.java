package icia.kotlin.mapper;

import icia.kotlin.bean.Member;
import icia.kotlin.bean.Movie;

public interface Mapper {
	
	 public int isMember(Member m);
	 public int isAccess(Member m);
	 public Member getMemberInfo(Member m);
	 public int insCustomer(Member m);
	 public int isMovie(Movie v);
}
