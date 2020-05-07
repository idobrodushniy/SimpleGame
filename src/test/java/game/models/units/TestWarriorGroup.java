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
    public void testGetGroupType() {
        Assert.assertEquals("Warriors", warriorGroup.getGroupType());
    }

}
