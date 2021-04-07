package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Resignation;
import com.seong.util.Prompt;

public class ResignationUpdateHandler extends AbstractResignationHandler {

  public ResignationUpdateHandler(List<Resignation> resignationList) {
    super(resignationList);
  }

  @Override
  public void service() {
    if (firstPaper()) {
      System.out.println("\n작성된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서수정/퇴사신청서 수정]");

    Resignation r = findByResignation("수정할 퇴사신청서 : ");

    if (r == null) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    r.setOutDate(Prompt.inputDate("퇴사 날짜 : "));
    r.setReason(Prompt.inputString("퇴사사유 : "));

    System.out.printf("\n'%s(%d)'님이 작성한 퇴사신청서가 수정되었습니다.\n\n", r.getName(), r.getMemberNo());
  }
}
