package com.seong.pms;

import java.sql.Date;
import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  static Date nextDate(String title) {
    return Date.valueOf(inputString(title));
  }

  static void println(String title) {
    System.out.println(title);
  }

  static String nextLine() {
    return scanner.nextLine();
  }

  static void close() {
    scanner.close();
  }

  static void holiday(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[휴가신청서]");
    System.out.printf("사번 : %d\n", WriteHandler.id[index]);
    System.out.printf("서명 : %s\n", WriteHandler.name[index]);
    System.out.printf("부서 : %s\n", WriteHandler.department[index]);
    System.out.printf("휴가종류 : %s\n", WriteHandler.holiday[index]);
    System.out.printf("휴가날짜 : %s ~ %s\n", WriteHandler.startDate[index], WriteHandler.endDate[index]);
    System.out.printf("상기 %s(본인)은 %s로 인하여 휴가신청서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", WriteHandler.name[index], WriteHandler.holidayReason[index]);
    System.out.printf("[승인여부] : %s\n", WriteHandler.holidayApproval[index]);
    Prompt.println("--------------------------------------------------------");
  }

  static void out(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[사직서]");
    System.out.printf("사번 : %d\n", WriteHandler.id[index]);
    System.out.printf("서명 : %s\n", WriteHandler.name[index]);
    System.out.printf("부서 : %s\n", WriteHandler.department[index]);
    System.out.printf("입사날짜 : %s\n", WriteHandler.joinDate[index]);
    System.out.printf("퇴사날짜 : %s\n", WriteHandler.outDate[index]);
    System.out.printf("상기 %s(본인)은 %s로 인하여 사직서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", WriteHandler.name[index], WriteHandler.outReason[index]);
    System.out.printf("[승인여부] : %s\n", WriteHandler.outApproval[index]);
    Prompt.println("--------------------------------------------------------");
  }
}
