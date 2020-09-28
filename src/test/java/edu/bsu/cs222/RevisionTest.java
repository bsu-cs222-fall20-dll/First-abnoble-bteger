package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RevisionTest {
    private Revision revision = new Revision("Bteger", "123");

    @Test
    public void testGetUser() {
        Assertions.assertEquals("Bteger", revision.getUser());
    }

    @Test
    public void testGetTimeStamp() {
        Assertions.assertEquals("123", revision.getTimeStamp());
    }
}
