package com.seong.util;

import java.sql.Date;
import java.util.Scanner;

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

  public static boolean inputCheck(String menu) {
    String outMenu = menu.replaceAll("[^0-9]", ".");

    for (int i = 0; i < outMenu.length(); i++)
    {
      if (outMenu.charAt(i) == '.')
      {
        return true;
      }
    }

    return false;
  }


}
