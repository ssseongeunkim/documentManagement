package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public abstract class AbstractMemberHandler implements Command {

  protected int employeeNo = 0;
  protected List<Member> memberList;

  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }


  //  protected boolean firstMember() {
  //    if (memberList.size() == 0) {
  //      return true;
  //    }
  //
  //    return false;
  //  }


  protected Object[] printMember() {
    Object[] list = memberList.toArray();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      System.out.printf("%d. %s\n", m.getNo(), m.getName());
    }

    return list;

  }


  //  protected Member findByMember(String title) {
  //    Object[] list = printMember();
  //
  //    int no = Prompt.inputInt(title);
  //
  //    Member m = new Member();
  //
  //    for (Object arr : list) {
  //      m = (Member) arr;
  //
  //      if (m.getNo() == no) {
  //        return m;
  //      }
  //    }
  //
  //    return null;
  //  }

  protected int indexOf(String title) {
    Object[] list = printMember();

    int no = Prompt.inputInt(title);

    Member m = new Member();

    for (int i=0; i<list.length; i++) {
      m = (Member)list[i];

      if (m.getNo() == no) {
        return i;
      }
    }

    return -1;
  }




}
