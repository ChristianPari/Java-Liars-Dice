package com.christianpari.liars_dice;

public class Player {
  private Cup cup;
  private String name;

  public Player(
    String name,
    int numOfDice
  ) {
    this.name = name;
    cup = new Cup(numOfDice);
  }

  public void roll() {
    cup.roll();
  }

  public void peek() {
    cup.peek();
  }

  public int countOfDie(int dieValue) { return cup.countOfDie(dieValue); }

  public void removeDie() { cup.removeDie(); }

  public int[] getClaim(int maxCount) {
    int dieValue,
        dieCount;

    dieValue = Console.getInt(1, 6, "What die value? (1 - 6)");
    dieCount = Console.getInt(1, 14, "How many " + dieValue + "'s? (1 - " + maxCount + ")");

    return new int[] {dieValue, dieCount};
  }

  public boolean getDecision() {
    return Console.getYesNo("Is the player lying? (Y)es or (N)o");
  }

  public boolean isOut() {
    return cup.amountOfDice() <= 0;
  }

  public String getName() {
    return name;
  }
}
