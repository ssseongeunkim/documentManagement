package com.seong.pms.dao.mariadb;

import java.util.List;
import com.seong.pms.dao.MemberDao;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberDaoImpl implements MemberDao{

  private List<Member> memberList;

  public MemberDaoImpl(List<Member> memberList) {
    this.memberList = memberList;
  }


  @Override
  public int insert(Member member) {
    memberList.add(member);

    return 1;
  }

  @Override
  public int delete(int no) {
    memberList.remove(no);

    return 1;
  }

  @Override
  public Object[] findAll() {
    Object[] list = memberList.toArray();

    return list;
  }

  @Override
  public Object[] printMember() {
    Object[] list = memberList.toArray();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      System.out.printf("%d. %s\n", m.getNo(), m.getName());
    }

    return list;

  }


  @Override
  public int indexOf(String title) {
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


  @Override
  public boolean firstMember() {
    if (memberList.size() == 0) {
      return true;
    }

    return false;
  }
}
