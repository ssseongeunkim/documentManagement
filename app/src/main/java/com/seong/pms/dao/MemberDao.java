package com.seong.pms.dao;

import com.seong.pms.domain.Member;

public interface MemberDao {

  int insert(Member member);

  int delete(int no);

  Object[] findAll();

  Object[] printMember();

  int indexOf(String title);

  boolean firstMember();
}
