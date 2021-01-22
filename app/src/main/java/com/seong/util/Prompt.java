package com.seong.util;

import java.sql.Date;
import java.util.Scanner;
import com.seong.pms.handler.WriteHandler;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date nextDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void println(String title) {
    System.out.println(title);
  }

  public static String nextLine() {
    return scanner.nextLine();
  }

  public static void close() {
    scanner.close();
  }

  public static void holiday(WriteHandler writeHandler, int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[휴가신청서]");
    System.out.printf("사번 : %d\n", writeHandler.members[index].id);
    System.out.printf("서명 : %s\n", writeHandler.members[index].name);
    System.out.printf("부서 : %s\n", writeHandler.members[index].department);
    System.out.printf("휴가종류 : %s\n", writeHandler.papers[index].holiday);
    System.out.printf("휴가날짜 : %s ~ %s\n", writeHandler.papers[index].startDate, writeHandler.papers[index].endDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 휴가신청서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", writeHandler.members[index].name, writeHandler.papers[index].holidayReason);
    System.out.printf("[승인여부] : %s\n", writeHandler.papers[index].holidayApproval);
    Prompt.println("--------------------------------------------------------");
  }

  public static void out(WriteHandler writeHandler, int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[사직서]");
    System.out.printf("사번 : %d\n", writeHandler.members[index].id);
    System.out.printf("서명 : %s\n", writeHandler.members[index].name);
    System.out.printf("부서 : %s\n", writeHandler.members[index].department);
    System.out.printf("입사날짜 : %s\n", writeHandler.members[index].joinDate);
    System.out.printf("퇴사날짜 : %s\n", writeHandler.papers[index].outDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 사직서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", writeHandler.members[index].name, writeHandler.papers[index].outReason);
    System.out.printf("[승인여부] : %s\n", writeHandler.papers[index].outApproval);
    Prompt.println("--------------------------------------------------------");
  }
}
