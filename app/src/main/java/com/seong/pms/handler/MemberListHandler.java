package com.seong.pms.handler;

import com.seong.pms.dao.MemberDao;
import com.seong.pms.domain.Member;

public class MemberListHandler implements Command {

  MemberDao memberDao;

  public MemberListHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service() {
    if (memberDao.firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/사원정보 조회]");


    Object[] list = memberDao.findAll();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member)arr;

      System.out.printf("%d. %s, %d, %s, %s, %s, %s\n",
          m.getNo(),
          m.getName(),
          m.getAge(),
          Member.getDepartment(m.getDepartment()),
          Member.getPosition(m.getPosition()),
          m.getJoinDate(),
          m.getPhoto());
    }

    System.out.println();


  }
}
