package org.example.model.Client.types;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class typeTest {

    @Test
    void premiumTest() {
        Type premium = new Premium();
        assertEquals(premium.getReduction(),0.7);
    }

    @Test
    void normalTest() {
        Type normal = new Normal();
        assertEquals(normal.getReduction(),1);
    }

}