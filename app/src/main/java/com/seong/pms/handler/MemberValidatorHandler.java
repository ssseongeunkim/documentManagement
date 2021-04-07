package com.seong.pms.handler;

import com.seong.pms.dao.MemberDao;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberValidatorHandler implements Command {

  MemberDao memberDao;

  public MemberValidatorHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service() {}

  public boolean firstMember() {

    return memberDao.firstMember();
  }


  public Member findByMember(String title) {
    Object[] list = memberDao.printMember();

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
