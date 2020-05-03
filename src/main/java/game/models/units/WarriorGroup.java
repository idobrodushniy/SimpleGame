package game.models.units;

public class WarriorGroup extends AbstractUnitGroup {
    private String groupType = "Warriors";

    public String getGroupType() {
        return groupType;
    }

    public String toString() {
        return getGroupType();
    }
}
