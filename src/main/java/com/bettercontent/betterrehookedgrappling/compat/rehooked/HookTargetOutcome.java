package com.bettercontent.betterrehookedgrappling.compat.rehooked;

/** Already-completed native transitions only; no ray candidates, rope samples or native objects. */
public enum HookTargetOutcome {
    BOAT_HIT("attached", "boat_hit"),
    MOB_HIT("attached", "mob_hit"),
    BLOCK_CATCH("attached", "forgiving_block_catch"),
    BOAT_REMOVED("retracting", "boat_unavailable_or_removed"),
    MOB_REMOVED("retracting", "mob_target_unavailable"),
    MOB_INELIGIBLE("retracting", "mob_no_longer_eligible");

    private final String result;
    private final String reason;
    HookTargetOutcome(String result, String reason) { this.result = result; this.reason = reason; }
    public String result() { return result; }
    public String reason() { return reason; }
}
