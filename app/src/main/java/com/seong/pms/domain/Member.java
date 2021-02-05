package com.seong.pms.domain;

import java.sql.Date;

public class Member {
  private int id; // 직원번호
  private String name; // 직원이름
  private int age; // 직원나이
  private String department; // 부서
  private Date joinDate; // 입사날짜

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }


}
