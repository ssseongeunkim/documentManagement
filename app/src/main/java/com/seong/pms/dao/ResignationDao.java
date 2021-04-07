package com.seong.pms.dao;

import com.seong.pms.domain.Resignation;

public interface ResignationDao {

  int insert(Resignation r);

  Object[] findAll();

  int delete(int no);




  boolean firstPaper();

  Object[] printResignation();

  Resignation findByResignation(String title);

  int indexOf(String title);

}
