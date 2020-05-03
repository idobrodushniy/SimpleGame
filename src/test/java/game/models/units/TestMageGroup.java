package game.models.units;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMageGroup {
    private MageGroup mageGroup;

    @Before
    public void before() {
        mageGroup = new MageGroup();
    }

    @Test
    public void testGroupUnitsAmount() {
        int expectedAmount = 111;
        mageGroup.setUnitsAmount(expectedAmount);
        Assert.assertEquals(expectedAmount, mageGroup.getUnitsAmount());
    }

    @Test
    public void testGetGroupType() {
        Assert.assertEquals("Mages", mageGroup.getGroupType());
    }

    @Test
    public void testUpdateArmyState() {
        mageGroup.setUnitsAmount(10);
        mageGroup.updateArmyState(6);
        Assert.assertEquals(7, mageGroup.getUnitsAmount());
    }
}
