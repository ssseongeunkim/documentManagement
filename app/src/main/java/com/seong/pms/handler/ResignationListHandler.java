package com.seong.pms.handler;

import com.seong.pms.dao.ResignationDao;
import com.seong.pms.domain.Resignation;

public class ResignationListHandler implements Command {

  ResignationDao resignationDao;

  public ResignationListHandler(ResignationDao resignationDao) {
    this.resignationDao = resignationDao;
  }

  @Override
  public void service() {
    if (resignationDao.firstPaper()) {
      System.out.println("\n작성된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/퇴사신청서 조회]");

    Object[] list = resignationDao.findAll();

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      System.out.printf("%d. %s, %s, %s, %s\n",
          r.getNo(),
          r.getName(),
          r.getOutDate(),
          r.getReason(),
          Resignation.getApproval(r.getApproval()));
    }

    System.out.println();
  }
}
