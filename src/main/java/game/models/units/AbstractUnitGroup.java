package game.models.units;

abstract public class AbstractUnitGroup implements UnitGroupInterface {
    private int unitsAmount;
    public final int hitPoints = 2;

    public void setUnitsAmount(int unitsAmount) {
        this.unitsAmount = unitsAmount;
    }

    public int getUnitsAmount() {
        return unitsAmount;
    }


    public void updateArmyState(int damageDealt) {
        int armyHitPoints = unitsAmount * hitPoints - damageDealt;
        unitsAmount = armyHitPoints / 2;
    }
}
