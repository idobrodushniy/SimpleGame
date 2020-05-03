package game.models.units;

public class MageGroup extends AbstractUnitGroup {
    private String groupType = "Mages";

    public String getGroupType() {
        return groupType;
    }

    public String toString() {
        return getGroupType();
    }
}
