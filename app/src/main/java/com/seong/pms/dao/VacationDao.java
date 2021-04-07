package com.seong.pms.dao;

import com.seong.pms.domain.Vacation;

public interface VacationDao {

  int insert(Vacation v);

  Object[] findAll();

  int delete(int no);



  boolean firstPaper();

  Object[] printVacation();

  Vacation findByVacation(String title);

  int indexOf(String title);

}
