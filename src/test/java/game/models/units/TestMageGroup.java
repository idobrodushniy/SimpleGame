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
    public void testGetGroupType() {
        Assert.assertEquals("Mages", mageGroup.getGroupType());
    }
}
