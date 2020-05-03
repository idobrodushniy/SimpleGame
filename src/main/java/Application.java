import game.logic.FightCalculator;
import game.logic.FightCalculatorInterface;
import game.models.players.Player;
import game.models.players.PlayerInterface;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayerInterface player1 = new Player("Player1", scanner);
        player1.setUpGameInfo();

        PlayerInterface player2 = new Player("Player2", scanner);
        player2.setUpGameInfo();

        FightCalculatorInterface gameObject = new FightCalculator(player1, player2);
        PlayerInterface winner = gameObject.startMultiplayerFight();

        if (winner == null) {
            System.out.println("Winner is friendship.");
        } else {
            System.out.format("Winner is %s\n", winner.getName());
        }
    }
}
