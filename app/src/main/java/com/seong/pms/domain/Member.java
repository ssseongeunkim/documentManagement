package com.seong.pms.domain;

import java.sql.Date;

public class Member {
  private int no; // 직원번호
  private String name; // 직원이름
  private int age; // 직원나이
  private int department; // 부서
  private int position; // 직위
  private Date joinDate; // 입사날짜
  private String photo; // 사진

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getDepartment() {
    return department;
  }

  public void setDepartment(int department) {
    this.department = department;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public static String getDepartment(int department) {
    String part = "";

    switch (department) {
      case 1:
        part = "개발팀";
        break;
      case 2:
        part = "디자인팀";
        break;
      case 3:
        part = "마케팅팀";
        break;
    }

    return part;
  }

  public static String getPosition(int position) {
    String part = "";

    switch (position) {
      case 1:
        part = "대표";
        break;
      case 2:
        part = "부장";
        break;
      case 3:
        part = "과장";
        break;
      case 4:
        part = "대리";
        break;
      case 5:
        part = "사원";
        break;
    }

    return part;
  }




}
