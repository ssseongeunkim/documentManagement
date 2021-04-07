package com.seong.pms.handler;

import com.seong.pms.dao.VacationDao;

public class VacationDeleteHandler implements Command {

  VacationDao vacationDao;

  public VacationDeleteHandler(VacationDao vacationDao) {
    this.vacationDao = vacationDao;
  }

  @Override
  public void service() {
    if (vacationDao.firstPaper()) {
      System.out.println("\n입력된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/휴가신청서 삭제]");

    int no = vacationDao.indexOf("삭제할 휴가신청서 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 휴가신청서가 없습니다.\n");
      return;
    }

    vacationDao.delete(no);

    System.out.println("\n휴가신청서가 삭제되었습니다.\n");
  }
}
