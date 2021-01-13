package com.christianpari.liars_dice;

import java.util.ArrayList;
import java.util.List;

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
    String output = "Your dice: ";
    for (var die : dice) {
      output += die.getValue() + " ";
    }
    System.out.println(output.trim());
  }

  public void roll() {
    for (var die : dice) {
      die.roll();
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
