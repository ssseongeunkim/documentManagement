package com.seong.pms.handler;

import com.seong.pms.dao.ResignationDao;

public class ResignationDeleteHandler implements Command {

  ResignationDao resignationDao;

  public ResignationDeleteHandler(ResignationDao resignationDao) {
    this.resignationDao = resignationDao;
  }

  @Override
  public void service() {
    if (resignationDao.firstPaper()) {
      System.out.println("\n입력된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/퇴사신청서 삭제]");

    int no = resignationDao.indexOf("삭제할 퇴사신청서 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    resignationDao.delete(no);

    System.out.println("\n퇴사신청서가 삭제되었습니다.\n");
  }


}
