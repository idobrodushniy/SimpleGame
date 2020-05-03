package game.models.players;

import game.models.units.UnitGroupInterface;

import java.util.HashMap;

public interface PlayerInterface {
    String getName();

    void setUpGameInfo();

    int getUnitsCapacity();

    UnitGroupInterface[] getGroups();

    HashMap<Integer, UnitGroupInterface> getUnitGroupsOrderInArmy();
}
