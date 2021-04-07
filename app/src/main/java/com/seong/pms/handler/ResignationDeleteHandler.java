package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Resignation;

public class ResignationDeleteHandler extends AbstractResignationHandler {

  public ResignationDeleteHandler(List<Resignation> resignationList) {
    super(resignationList);
  }

  @Override
  public void service() {
    if (firstPaper()) {
      System.out.println("\n입력된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/퇴사신청서 삭제]");

    int no = indexOf("삭제할 퇴사신청서 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    resignationList.remove(no);

    System.out.println("\n퇴사신청서가 삭제되었습니다.\n");
  }


}
