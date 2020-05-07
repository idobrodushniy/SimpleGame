package game.models.units;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestUnitGroup {
    UnitGroupInterface unitGroup;

    @Before
    public void before() {
        unitGroup = new AbstractUnitGroup() {
        };
    }

    @Test
    public void testSetUnitsAmount() {
        int expectedAmount = 100;
        unitGroup.setUnitsAmount(expectedAmount);
        Assert.assertEquals(expectedAmount, unitGroup.getUnitsAmount());
    }

    @Test
    public void testUpdateArmyState() {
        unitGroup.setUnitsAmount(10);
        unitGroup.updateArmyState(6);
        Assert.assertEquals(7, unitGroup.getUnitsAmount());
    }
}
