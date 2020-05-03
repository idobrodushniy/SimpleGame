package game.models.units;

public class ArcherGroup extends AbstractUnitGroup {
    private String groupType = "Archers";

    public String getGroupType() {
        return groupType;
    }

    public String toString() {
        return getGroupType();
    }
}
