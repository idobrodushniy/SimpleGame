package game.models.players;

import game.models.players.exceptions.ValueIsEmptyException;
import game.models.players.exceptions.ValueIsPickedException;
import game.models.players.exceptions.ValueTooBigException;
import game.models.units.ArcherGroup;
import game.models.units.MageGroup;
import game.models.units.UnitGroupInterface;
import game.models.units.WarriorGroup;

import java.util.HashMap;
import java.util.Scanner;

public class Player implements PlayerInterface {
    private String name;
    private int unitsCapacity = 100;
    private HashMap<Integer, UnitGroupInterface> unitGroupsOrderInArmy;
    private UnitGroupInterface[] groups;
    private Scanner in;

    public Player(String name, Scanner scanner) {
        this.name = name;
        this.groups = new UnitGroupInterface[]{new WarriorGroup(), new MageGroup(), new ArcherGroup()};
        this.unitGroupsOrderInArmy = new HashMap<>();
        this.in = scanner;
    }

    public UnitGroupInterface[] getGroups() {
        return groups;
    }

    public String getName() {
        return name;
    }

    public int getUnitsCapacity() {
        return unitsCapacity;
    }

    public HashMap<Integer, UnitGroupInterface> getUnitGroupsOrderInArmy() {
        return unitGroupsOrderInArmy;
    }

    public void setUpGameInfo() {
        System.out.format("Hi, %s\n", name);
        for (UnitGroupInterface group : groups) {
            group.setUnitsAmount(getUnitsAmountFromInput(group));
        }

        for (UnitGroupInterface group : groups) {
            setGroupOrder(group);
        }
    }

    private int getNextInt() throws ValueIsEmptyException {
        if (!in.hasNextInt()) {
            throw new ValueIsEmptyException("Value could be only integer.");
        } else {
            return in.nextInt();
        }
    }

    private void validateUnitsAmountValue(int value) throws ValueTooBigException {
        if (value > unitsCapacity) {
            throw new ValueTooBigException("Value is too big");
        }
    }

    int getUnitsAmountFromInput(UnitGroupInterface unitGroup) {
        while (true) {
            System.out.format("Please set an amount of %s(not more than %d):", unitGroup.toString(), unitsCapacity);
            try {
                int value = getNextInt();
                validateUnitsAmountValue(value);
                unitsCapacity -= value;
                return value;
            } catch (ValueIsEmptyException | ValueTooBigException exc) {
                System.err.println(exc.getMessage());
                System.out.println("\n");
            }
        }
    }

    private void validateUnitGroupsOrderInArmy(int value) throws ValueTooBigException, ValueIsPickedException {
        if (value <= 0 || value > groups.length) {
            String formattedErrorMessage = String.format("Value should be between %d and %d", 0, groups.length);
            throw new ValueTooBigException(formattedErrorMessage);
        } else if (unitGroupsOrderInArmy.containsKey(value)) {
            throw new ValueIsPickedException("This order id is already picked.");
        }
    }

    void setGroupOrder(UnitGroupInterface unitsGroup) {
        while (true) {
            System.out.format("Please select the order for %s group in your army:", unitsGroup.toString());
            try {
                int value = getNextInt();
                validateUnitGroupsOrderInArmy(value);
                unitGroupsOrderInArmy.put(value, unitsGroup);
                break;
            } catch (ValueIsEmptyException | ValueIsPickedException | ValueTooBigException exc) {
                System.err.println(exc.getMessage());
                System.out.println("\n");
            }
        }
    }

}
