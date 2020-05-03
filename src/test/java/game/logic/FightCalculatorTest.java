package game.logic;

import game.models.players.Player;
import game.models.players.PlayerInterface;
import game.models.units.MageGroup;
import game.models.units.UnitGroupInterface;
import game.models.units.WarriorGroup;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class FightCalculatorTest {
    private FightCalculator calculatorObject;
    private PlayerInterface player1, player2;

    private Scanner generateScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    private void generateCalculatorWithMockedPlayers() {
        player1 = player2 = Mockito.mock(Player.class);
        calculatorObject = new FightCalculator(player1, player2);
    }

    private void generateCalculator(Scanner scanner1, Scanner scanner2) {
        player1 = new Player("player1", scanner1);
        player2 = new Player("player2", scanner2);

        player1.setUpGameInfo();
        player2.setUpGameInfo();

        calculatorObject = new FightCalculator(player1, player2);
    }

    @Test
    public void testGetGroupDamageStat() {
        generateCalculatorWithMockedPlayers();
        UnitGroupInterface warriorGroup, mageGroup;
        warriorGroup = new WarriorGroup();
        mageGroup = new MageGroup();

        warriorGroup.setUnitsAmount(2);
        mageGroup.setUnitsAmount(2);
        Assert.assertEquals(4, calculatorObject.getGroupDamageStat(warriorGroup, mageGroup));
        Assert.assertEquals(2, calculatorObject.getGroupDamageStat(mageGroup, warriorGroup));

    }

    @Test
    public void testGameCalculationWinnerPlayer1() {
        generateCalculator(generateScanner("2 2 2 2 3 1"), generateScanner("2 2 2 1 2 3"));

        PlayerInterface winner = calculatorObject.startMultiplayerFight();
        Assert.assertEquals(player1.getName(), winner.getName());
    }

    @Test
    public void testGameCalculationWinnerPlayer2() {
        generateCalculator(generateScanner("10 10 10 2 3 1"), generateScanner("30 30 40 1 2 3"));
        PlayerInterface winner = calculatorObject.startMultiplayerFight();
        Assert.assertEquals(player2.getName(), winner.getName());
    }

    @Test
    public void testGameCalculationWinnerBreakEven() {
        generateCalculator(generateScanner("2 2 2 1 2 3"), generateScanner("2 2 2 1 2 3"));
        PlayerInterface winner = calculatorObject.startMultiplayerFight();
        Assert.assertNull(winner);
    }
}
