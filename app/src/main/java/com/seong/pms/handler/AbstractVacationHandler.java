package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Vacation;
import com.seong.util.Prompt;

public abstract class AbstractVacationHandler {
  protected int paperNo = 0;
  protected List<Vacation> vacationList;

  public AbstractVacationHandler(List<Vacation> vacationHandler) {
    this.vacationList = vacationHandler;
  }
  
  protected boolean firstPaper() {
    if (vacationList.size() == 0) {
      return true;
    }

    return false;
  }


  protected Object[] printVacation() {
    Object[] list = vacationList.toArray();

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      System.out.printf("%d. 휴가신청서(작성자 : %s(%d))\n", v.getNo(), v.getName(), v.getMemberNo());
    }

    return list;
  }


  protected Vacation findByVacation(String title) {
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


  protected int indexOf(String title) {
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
