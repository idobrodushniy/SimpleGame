package game.models.players;

import game.models.players.exceptions.ValueIsPickedException;
import game.models.players.exceptions.ValueTooBigException;
import game.models.units.ArcherGroup;
import game.models.units.MageGroup;
import game.models.units.UnitGroupInterface;
import game.models.units.WarriorGroup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Player.class, Scanner.class})
public class PlayerTest {
    private Player player;

    private Scanner generateScanner() {
        return new Scanner(System.in);
    }

    private Scanner generateScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    private Player generateDefaultPlayer(Scanner scanner) {
        return new Player("Default player", scanner);
    }

    @Test
    public void testSetUpGameInfoHappyPath() {
        Scanner scanner = generateScanner("30 30 40 2 1 3 ");
        player = generateDefaultPlayer(scanner);
        player.setUpGameInfo();
        UnitGroupInterface[] groups = player.getGroups();
        HashMap<Integer, UnitGroupInterface> orderMap = player.getUnitGroupsOrderInArmy();

        UnitGroupInterface warriors, mages, archers;

        warriors = groups[0];
        mages = groups[1];
        archers = groups[2];

        Assert.assertEquals(WarriorGroup.class, warriors.getClass());
        Assert.assertEquals(30, warriors.getUnitsAmount());

        Assert.assertEquals(MageGroup.class, mages.getClass());
        Assert.assertEquals(30, mages.getUnitsAmount());

        Assert.assertEquals(ArcherGroup.class, archers.getClass());
        Assert.assertEquals(40, archers.getUnitsAmount());

        Assert.assertEquals(MageGroup.class, orderMap.get(1).getClass());
        Assert.assertEquals(WarriorGroup.class, orderMap.get(2).getClass());
        Assert.assertEquals(ArcherGroup.class, orderMap.get(3).getClass());
    }

    @Test
    public void testValidateUnitsAmountValue() throws Exception {
        player = PowerMockito.spy(generateDefaultPlayer(generateScanner()));
        Assert.assertThrows(
                ValueTooBigException.class,
                () -> {
                    Whitebox.invokeMethod(player, "validateUnitsAmountValue", 1000);
                }
        );
    }

    @Test
    public void testGetUnitsAmountFromInput() throws Exception {
        Scanner scanner = generateScanner(" 74 ");
        UnitGroupInterface warriors = new WarriorGroup();
        player = generateDefaultPlayer(scanner);
        player.getUnitsAmountFromInput(warriors);
        Assert.assertEquals(26, player.getUnitsCapacity());
    }

    @Test
    public void testValidateUnitGroupsOrderInArmyValueTooBig() throws Exception {
        player = PowerMockito.spy(generateDefaultPlayer(generateScanner()));
        Assert.assertThrows(
                ValueTooBigException.class,
                () -> {
                    Whitebox.invokeMethod(player, "validateUnitGroupsOrderInArmy", 4);
                }
        );
    }

    @Test
    public void testValidateUnitGroupsOrderInArmyValueIsPicked() throws Exception {
        player = generateDefaultPlayer(generateScanner("1"));
        player = PowerMockito.spy(player);
        UnitGroupInterface warriors = new WarriorGroup();
        player.setGroupOrder(warriors);
        Assert.assertThrows(
                ValueIsPickedException.class,
                () -> {
                    Whitebox.invokeMethod(player, "validateUnitGroupsOrderInArmy", 1);
                }
        );
    }
}
