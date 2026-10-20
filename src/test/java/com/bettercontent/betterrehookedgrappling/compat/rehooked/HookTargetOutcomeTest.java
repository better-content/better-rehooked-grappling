package com.bettercontent.betterrehookedgrappling.compat.rehooked;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

final class HookTargetOutcomeTest {
    @Test void onlyActualAttachmentCausesAreClassifiedAsAttached() {
        for (var outcome : new HookTargetOutcome[] {HookTargetOutcome.BOAT_HIT,
                HookTargetOutcome.MOB_HIT, HookTargetOutcome.BLOCK_CATCH}) {
            assertEquals("attached", outcome.result());
            assertTrue(outcome.reason().matches("[a-z_]+"));
        }
    }
    @Test void LostOrIneligibleTargetsCannotBeReportedAsSuccessfulAttachments() {
        assertEquals("retracting", HookTargetOutcome.BOAT_REMOVED.result());
        assertEquals("retracting", HookTargetOutcome.MOB_REMOVED.result());
        assertEquals("retracting", HookTargetOutcome.MOB_INELIGIBLE.result());
        assertEquals("mob_no_longer_eligible", HookTargetOutcome.MOB_INELIGIBLE.reason());
    }
}
