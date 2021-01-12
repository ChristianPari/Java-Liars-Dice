package com.christianpari.LiarsDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cup {
  private List<Die> dice;

  public Cup(int numOfDice) {
    dice = new ArrayList<>();
    for (int counter = 0; counter < numOfDice; counter++) {
      dice.add(new Die());
    }
  }

  public void addDie() {
    dice.add(new Die());
  }

  public void removeDie() {
    dice.remove(dice.size() - 1);
  }

  public void peek() {
    String output = "";
    for (var die : dice) {
      output += die.getValue() + " ";
    }
    System.out.println(output.trim());
  }

  public void roll() {
    Random random = new Random();
    for (var die : dice) {
      die.roll(random);
    }
  }

  public int amountOfDice() {
    return dice.size();
  }

  public int countOfDie(int dieValue) {
    int count = 0;
    for (var die : dice) {
      if (die.getValue() == dieValue) count++;
    }
    return count;
  }
}
