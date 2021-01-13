package com.christianpari.liars_dice;

import java.util.Scanner;

public class Console {
  static Scanner sc = new Scanner(System.in);

  static public int getInt(
      int min,
      int max,
      String query
  ) {
    while (true) {
      System.out.println(query);
      int num = sc.nextInt();
      if (num >= min && num <= max) {
        return num;
      }
    }
  }

  static public boolean getYesNo(String query) {
    while (true) {
      System.out.println(query);
      String input = sc.nextLine().substring(0).toLowerCase();
      if (input.equals("y")) {
        return true;
      }
      if (input.equals("n")) {
        return false;
      }
    }
  }

  static public String getString(String query) {
    sc = new Scanner(System.in);
    System.out.println(query);
    return sc.nextLine();
  }

  static public void clearScreen() {
    int lines = 50;
    while (lines > 0) {
      System.out.println("\n");
      lines--;
    }
    sc.nextLine();
  }
}
