package game.models.units;

public interface UnitGroupInterface {
    String getGroupType();

    int getUnitsAmount();

    void setUnitsAmount(int unitsAmount);

    void updateArmyState(int damageDealt);
}