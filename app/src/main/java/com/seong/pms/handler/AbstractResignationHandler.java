package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Resignation;
import com.seong.util.Prompt;

public abstract class AbstractResignationHandler implements Command{

  protected int paperNo = 0;
  protected List<Resignation> resignationList;

  public AbstractResignationHandler(List<Resignation> resignationList) {
    this.resignationList = resignationList;
  }

  protected boolean firstPaper() {
    if (resignationList.size() == 0) {
      return true;
    }

    return false;
  }

  protected Object[] printResignation() {
    Object[] list = resignationList.toArray();

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      System.out.printf("%d. 퇴사신청서(작성자 : %s(%d))\n", r.getNo(), r.getName(), r.getMemberNo());
    }

    return list;
  }


  protected Resignation findByResignation(String title) {
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


  protected int indexOf(String title) {
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
