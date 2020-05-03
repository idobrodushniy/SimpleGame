package game.models.units;

abstract public class AbstractUnitGroup implements UnitGroupInterface {
    private final int hitPoints = 2;
    String groupType;
    private int unitsAmount;

    public int getUnitsAmount() {
        return unitsAmount;
    }

    public void setUnitsAmount(int unitsAmount) {
        this.unitsAmount = unitsAmount;
    }

    public String getGroupType() {
        return groupType;
    }

    public String toString() {
        return getGroupType();
    }

    public void updateArmyState(int damageDealt) {
        int armyHitPoints = unitsAmount * hitPoints - damageDealt;
        unitsAmount = armyHitPoints / 2;
    }
}
