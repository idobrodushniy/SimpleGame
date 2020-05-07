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
    public void testGetGroupType() {
        Assert.assertEquals("Archers", archerGroup.getGroupType());
    }

}
