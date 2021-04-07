package com.seong.pms.handler;

import com.seong.pms.dao.VacationDao;
import com.seong.pms.domain.Vacation;

public class VacationListHandler implements Command {

  VacationDao vacationDao;

  public VacationListHandler(VacationDao vacationDao) {
    this.vacationDao = vacationDao;
  }

  @Override
  public void service() {
    if (vacationDao.firstPaper()) {
      System.out.println("\n작성된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/휴가신청서 조회]");

    Object[] list = vacationDao.findAll();

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      System.out.printf("%d. %s, %s, %s, %s, %s, %s\n",
          v.getNo(),
          v.getName(),
          Vacation.getVacation(v.getVacation()),
          v.getStartDate(),
          v.getEndDate(),
          v.getReason(),
          Vacation.getApproval(v.getApproval()));
    }

    System.out.println();

  }
}
