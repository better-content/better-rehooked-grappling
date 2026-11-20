package com.bettercontent.betterrehookedgrappling.compat.rehooked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassableLeafHitPolicyTest {
    @Test
    void passableLeavesUseTheirBlockShape() {
        assertTrue(PassableLeafHitPolicy.useLeafShape(true, true));
    }

    @Test
    void normalLeavesAndOtherBlocksKeepNativeCollisions() {
        assertFalse(PassableLeafHitPolicy.useLeafShape(true, false));
        assertFalse(PassableLeafHitPolicy.useLeafShape(false, true));
        assertFalse(PassableLeafHitPolicy.useLeafShape(false, false));
    }
}
