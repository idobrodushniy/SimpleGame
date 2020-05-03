package game.logic;

import game.models.players.PlayerInterface;
import game.models.units.UnitGroupInterface;

import java.util.HashMap;
import java.util.Map;

public class FightCalculator implements FightCalculatorInterface {
    private final Map<String, Integer> classesCrossDamageMap;
    private PlayerInterface player1, player2;

    public FightCalculator(PlayerInterface player1, PlayerInterface player2) {
        this.classesCrossDamageMap = Map.of(
                "Warriors->Mages", 2,
                "Mages->Archers", 2,
                "Archers->Warriors", 2
        );
        this.player1 = player1;
        this.player2 = player2;
    }

    void calculateFightBetweenGroups(
            UnitGroupInterface firstPlayerGroup,
            UnitGroupInterface secondPlayerGroup
    ) {
        int firstGroupUnitsDamage, secondGroupUnitsDamage;

        firstGroupUnitsDamage = getGroupDamageStat(firstPlayerGroup, secondPlayerGroup);
        secondGroupUnitsDamage = getGroupDamageStat(secondPlayerGroup, firstPlayerGroup);

        firstPlayerGroup.updateArmyState(secondGroupUnitsDamage);
        secondPlayerGroup.updateArmyState(firstGroupUnitsDamage);
    }

    int getGroupDamageStat(UnitGroupInterface group1, UnitGroupInterface group2) {
        return classesCrossDamageMap.getOrDefault(
                group1.getGroupType() + "->" + group2.getGroupType(),
                1
        ) * group1.getUnitsAmount();
    }

    public PlayerInterface startMultiplayerFight() {
        HashMap<Integer, UnitGroupInterface> player1GroupsOrder, player2GroupsOrder;

        player1GroupsOrder = player1.getUnitGroupsOrderInArmy();
        player2GroupsOrder = player2.getUnitGroupsOrderInArmy();

        int player1Wins = 0, player2Wins = 0;

        for (Map.Entry<Integer, UnitGroupInterface> player1OrderEntry : player1GroupsOrder.entrySet()) {
            int key = player1OrderEntry.getKey();
            UnitGroupInterface player1Group, player2Group;
            player1Group = player1GroupsOrder.get(key);
            player2Group = player2GroupsOrder.get(key);
            calculateFightBetweenGroups(player1Group, player2Group);

            if (player1Group.getUnitsAmount() > player2Group.getUnitsAmount()) {
                player1Wins++;
            } else if (player2Group.getUnitsAmount() > player1Group.getUnitsAmount()) {
                player2Wins++;
            }
        }
        PlayerInterface winner;

        if (player1Wins > player2Wins) {
            winner = player1;
        } else if (player2Wins > player1Wins) {
            winner = player2;
        } else {
            winner = null;
        }

        return winner;
    }

}
