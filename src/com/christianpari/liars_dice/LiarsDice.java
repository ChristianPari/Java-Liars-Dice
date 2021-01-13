package com.christianpari.liars_dice;

import java.util.ArrayList;
import java.util.List;

public class LiarsDice {
  public List<Player> players;
  private int roundStarter = 0;
  private int[] currentClaim = new int[0];
  private final int CLAIM_VALUE = 0;
  private final int CLAIM_COUNT = 1;

  public LiarsDice() {
    welcome();
    generatePlayers(
      Console.getInt(2, 4, "How many players? (2-4)"),
      Console.getInt(2, 10, "Starting dice amount? (2-10)")
    );
    runGame();
  }

  private void welcome() {
    System.out.println("Welcome to Liars Dice!\n\n" +
      "Everyone shakes their cups of dice and then makes claims as to how many of a certain die are on the table.\n" +
      "If you think the person before you is wrong CALL OUT THEIR LIE and if you're right then they lose a die,\n" +
      "but if you're wrong YOU LOSE A DIE!\n" +
      "Last player with die remaining wins! Good Luck!\n");
  }

  private void generatePlayers(
    int numOfPlayers,
    int numOfDice
  ) {
    players = new ArrayList<>();
    for (int count = 0; count < numOfPlayers; count++) {
      players.add(new Player(Console.getString("Player " + (count + 1) + "'s name?"), numOfDice));
    }
  }

  private void runGame() {
    while (true) {
      boolean continueGame = runRound();
      if (!continueGame) break;
    }
  }

  private boolean runRound() {
    playersRollDice();
    return endRound(playersClaim());
  }

  private void playersRollDice() {
    for (var player : players) {
      player.roll();
    }
  }

  private int playersClaim() {
    int nextStarter = (roundStarter + 1 > players.size() - 1) ? 0 : roundStarter + 1;
    int curPlayer = roundStarter;
    while (true) {
      boolean continueRound = runTurn(players.get(curPlayer % players.size()));
      if (!continueRound) {
        roundStarter = nextStarter;
        break;
      }
      curPlayer++;
    }

    return curPlayer;
  }

  private boolean runTurn(Player player) {
    Console.getString("\n" + player.getName() + "'s turn... press enter to continue...");
    player.peek();
    if (currentClaim.length != 0) {
      System.out.println("Current claim: " + currentClaim[CLAIM_COUNT] + " " + currentClaim[CLAIM_VALUE] + "(s)");
      boolean decision = player.getDecision();
      if (decision) {
        int curPlayerId = players.indexOf(player);
        String curPlayer = player.getName();
        int prevPayerNum = (curPlayerId == 0) ? players.size() - 1 : curPlayerId - 1;
        String prevPlayer = players.get(prevPayerNum).getName();

        System.out.println("\n" + curPlayer + " thinks " + prevPlayer + " is lying!");
        return false;
      }
    }

    int[] newClaim = player.getClaim();
    while (!isValidClaim(newClaim)) {
      newClaim = player.getClaim();
    }

    Console.clearScreen();
    currentClaim = newClaim;
    return true;
  }

  private boolean isValidClaim(int[] newClaim) {
    int prevVal = 0,
        prevCount = 0;
    if (currentClaim.length != 0) {
      prevVal = currentClaim[CLAIM_VALUE];
      prevCount = currentClaim[CLAIM_COUNT];
    }
    int newVal = newClaim[CLAIM_VALUE],
        newCount = newClaim[CLAIM_COUNT];

    if (newVal == prevVal && newCount == prevCount) {
      System.out.println("Invalid Claim: Same claim");
      return false;
    }
    if (newVal == prevVal && newCount < prevCount) {
      System.out.println("Invalid Claim: Count cannot decrease if the die value remains the same");
      return false;
    }
    if (newVal < prevVal) {
      System.out.println("Invalid Claim: Cannot decrease the die value");
      return false;
    }

    // if nothing above passes then it is a valid claim
    return true;
  }

  private boolean endRound(int curPlayer) {
    if (isLie()) {
      curPlayer = (curPlayer - 1 < 0) ? players.size() - 1 : curPlayer - 1;
      System.out.println("It was a lie!");
    } else {
      System.out.println("It was not a lie!");
    }

    int affectedPlayerId = curPlayer % players.size();
    Player affectedPlayer = players.get(affectedPlayerId);
    String playerName = affectedPlayer.getName();
    affectedPlayer.removeDie();
    System.out.println(playerName + " loses 1 die!");
    if (affectedPlayer.isOut()) {
      players.remove(affectedPlayer);
      System.out.println(playerName + " has no more dice and has been removed from the game.");
    }

    if (players.size() == 1) {
      System.out.println("Game Over " + players.get(0).getName() + " Wins!");
      return false;
    }

    currentClaim = new int[0];
    return true;
  }

  private boolean isLie() {
    int count = 0;
    for (var player : players) {
      count += player.countOfDie(currentClaim[CLAIM_VALUE]);
    }
    return count < currentClaim[CLAIM_COUNT];
  }
}
