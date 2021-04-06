package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberValidatorHandler extends AbstractMemberHandler {

  public MemberValidatorHandler(List<Member> memberList) {
    super(memberList);
  }

  public boolean firstMember() {
    if (memberList.size() == 0) {
      return true;
    }

    return false;
  }


  public Member findByMember(String title) {
    Object[] list = printMember();

    int no = Prompt.inputInt(title);

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      if (m.getNo() == no) {
        return m;
      }
    }

    return null;
  }

}
