package game.models.units;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestWarriorGroup {
    private WarriorGroup warriorGroup;

    @Before
    public void before() {
        warriorGroup = new WarriorGroup();
    }

    @Test
    public void testGroupUnitsAmount() {
        int expectedAmount = 111;
        warriorGroup.setUnitsAmount(expectedAmount);
        Assert.assertEquals(expectedAmount, warriorGroup.getUnitsAmount());
    }

    @Test
    public void testGetGroupType() {
        Assert.assertEquals("Warriors", warriorGroup.getGroupType());
    }

    @Test
    public void testUpdateArmyState() {
        warriorGroup.setUnitsAmount(10);
        warriorGroup.updateArmyState(6);
        Assert.assertEquals(7, warriorGroup.getUnitsAmount());
    }
}
