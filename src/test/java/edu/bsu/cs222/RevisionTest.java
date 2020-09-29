package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RevisionTest {
    private final Revision REVISION = new Revision("Bteger", "123");

    @Test
    public void testGetUser() {
        Assertions.assertEquals("Bteger", REVISION.getUser());
    }

    @Test
    public void testGetTimeStamp() {
        Assertions.assertEquals("123", REVISION.getTimeStamp());
    }
}
