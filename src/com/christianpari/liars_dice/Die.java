package com.christianpari.liars_dice;

import java.util.Random;

public class Die {
  private int sides;
  private int value;
  private Random random = new Random();

  public Die() {
    sides = 6;
    value = 1;
  }

  public void roll() {
    value = random.nextInt(sides) + 1;
  }

  public int getValue() { return value; }
}
