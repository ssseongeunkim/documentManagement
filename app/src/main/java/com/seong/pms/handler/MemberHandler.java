package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.util.List;
import com.seong.util.Prompt;

public class MemberHandler {

  private int employeeNo = 0;
  private List memberList = new List();

  public void add() {
    System.out.println("\n[문서관리 시스템/사원정보 입력]");

    Member member = new Member();

    member.setNo(++employeeNo);
    member.setName(Prompt.inputString("이름 : "));
    member.setAge(Prompt.inputInt("나이 : "));
    member.setDepartment(Prompt.inputInt("부서 (1. 개발팀 2. 디자인팀 3. 마케팅팀) : "));
    member.setPosition(Prompt.inputInt("직위 (1. 대표 2. 부장 3. 과장 4. 대리  5. 사원) : "));
    member.setJoinDate(Prompt.inputDate("입사 날짜 : "));
    member.setPhoto(Prompt.inputString("사진 : "));

    memberList.add(member);

    System.out.printf("%s님의 정보가 입력되었습니다.\n\n", member.getName());

  }


  public void list() {
    if (firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/사원정보 조회]");

    Object[] list = memberList.toArray();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

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


  public void detail() {
    if (firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/선택조회/사원정보 조회]");

    Member m = findByMember("조회할 직원 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n==================================");
    System.out.println("[사원정보]");
    System.out.printf("\n사번 : %d "
        + "\n이름 : %s "
        + "\n나이 : %d "
        + "\n부서 : %s "
        + "\n직급 : %s "
        + "\n입사날짜 : %s "
        + "\n사진 : %s\n",
        m.getNo(), m.getName(), m.getAge(), 
        Member.getDepartment(m.getDepartment()), 
        Member.getPosition(m.getPosition()), 
        m.getJoinDate(), m.getPhoto());
    System.out.println("==================================\n");
  }


  public void update() {
    if (firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서수정/사원정보 수정]");

    Member m = findByMember("수정할 사원 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    m.setName(Prompt.inputString("이름 : "));
    m.setDepartment(Prompt.inputInt("부서 (1. 개발팀 2. 디자인팀 3. 마케팅팀) : "));
    m.setPosition(Prompt.inputInt("직위 (1. 대표 2. 부장 3. 과장 4. 대리  5. 사원) : "));
    m.setPhoto(Prompt.inputString("사진 : "));

    System.out.printf("\n'%s'님의 정보가 수정되었습니다.\n\n", m.getName());

  }


  public void delete() {
    if (firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/사원정보 삭제]");

    int no = indexOf("삭제할 사원 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 사원이 없습니다.\n");
      return;
    }

    memberList.delete(no);

    System.out.println("\n사원의 정보가 삭제되었습니다.\n");

  }

  boolean firstMember() {
    if (memberList.size() == 0) {
      return true;
    }

    return false;
  }


  Object[] printMember() {
    Object[] list = memberList.toArray();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      System.out.printf("%d. %s\n", m.getNo(), m.getName());
    }

    return list;

  }


  Member findByMember(String title) {
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

  int indexOf(String title) {
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
