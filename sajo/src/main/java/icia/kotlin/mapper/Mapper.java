package icia.kotlin.mapper;

import icia.kotlin.beans.Beans;

public interface Mapper {
   
   public int isMember(Beans m);
   public int isAccess(Beans m);
   public Beans getMemberInfo(Beans m);
}