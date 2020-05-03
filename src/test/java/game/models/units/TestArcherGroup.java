package game.models.units;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestArcherGroup {
    private ArcherGroup archerGroup;

    @Before
    public void before() {
        archerGroup = new ArcherGroup();
    }

    @Test
    public void testGroupUnitsAmount() {
        int expectedAmount = 111;
        archerGroup.setUnitsAmount(expectedAmount);
        Assert.assertEquals(expectedAmount, archerGroup.getUnitsAmount());
    }

    @Test
    public void testGetGroupType() {
        Assert.assertEquals("Archers", archerGroup.getGroupType());
    }

    @Test
    public void testUpdateArmyState() {
        archerGroup.setUnitsAmount(10);
        archerGroup.updateArmyState(6);
        Assert.assertEquals(7, archerGroup.getUnitsAmount());
    }
}
