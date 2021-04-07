package com.seong.pms.dao.mariadb;

import java.util.List;
import com.seong.pms.dao.ResignationDao;
import com.seong.pms.domain.Resignation;
import com.seong.util.Prompt;

public class ResignationDaoImpl implements ResignationDao{

  List<Resignation> resignationList;

  public ResignationDaoImpl(List<Resignation> resignationList) {
    this.resignationList = resignationList;
  }

  @Override
  public int insert(Resignation r) {
    resignationList.add(r);

    return 1;
  }


  @Override
  public Object[] findAll() {
    Object[] list = resignationList.toArray();

    return list;
  }


  @Override
  public int delete(int no) {
    resignationList.remove(no);

    return 1;
  }


  @Override
  public boolean firstPaper() {

    if (resignationList.size() == 0) {
      return true;
    }

    return false;
  }


  @Override
  public Object[] printResignation() {
    Object[] list = resignationList.toArray();

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      System.out.printf("%d. 퇴사신청서(작성자 : %s(%d))\n", r.getNo(), r.getName(), r.getMemberNo());
    }

    return list;
  }


  @Override
  public Resignation findByResignation(String title) {
    Object[] list = printResignation();

    int no = Prompt.inputInt(title);

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      if (r.getNo() == no) {
        return r;
      }
    }

    return null;
  }


  @Override
  public int indexOf(String title) {
    Object[] list = printResignation();

    int no = Prompt.inputInt(title);

    Resignation r = new Resignation();

    for (int i=0; i<list.length; i++) {
      r = (Resignation) list[i];

      if (r.getNo() == no) {
        return i;
      }
    }

    return -1;
  }



}
