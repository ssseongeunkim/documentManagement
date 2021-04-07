package com.seong.pms.dao.mariadb;

import java.util.List;
import com.seong.pms.dao.VacationDao;
import com.seong.pms.domain.Vacation;
import com.seong.util.Prompt;

public class VacationDaoImpl implements VacationDao{

  List<Vacation> vacationList;

  public VacationDaoImpl(List<Vacation> vacationList) {
    this.vacationList = vacationList;
  }

  @Override
  public int insert(Vacation v) {
    vacationList.add(v);

    return 1;
  }


  @Override
  public Object[] findAll() {
    Object[] list = vacationList.toArray();

    return list;
  }


  @Override
  public int delete(int no) {
    vacationList.remove(no);

    return 1;
  }

  @Override
  public boolean firstPaper() {
    if (vacationList.size() == 0) {
      return true;
    }

    return false;
  }

  @Override
  public Object[] printVacation() {
    Object[] list = vacationList.toArray();

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      System.out.printf("%d. 휴가신청서(작성자 : %s(%d))\n", v.getNo(), v.getName(), v.getMemberNo());
    }

    return list;
  }

  @Override
  public Vacation findByVacation(String title) {
    Object[] list = printVacation();

    int no = Prompt.inputInt(title);

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      if (v.getNo() == no) {
        return v;
      }
    }

    return null;
  }

  @Override
  public int indexOf(String title) {
    Object[] list = printVacation();

    int no = Prompt.inputInt(title);

    Vacation v = new Vacation();

    for (int i=0; i<list.length; i++) {
      v = (Vacation)list[i];

      if (v.getNo() == no) {
        return i;
      }
    }

    return -1;
  }
}
