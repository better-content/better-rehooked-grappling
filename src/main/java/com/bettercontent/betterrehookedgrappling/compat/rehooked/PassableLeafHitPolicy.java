package com.bettercontent.betterrehookedgrappling.compat.rehooked;

/** Leaves invisible to the projectile collider still count as blocks for hook shots. */
public final class PassableLeafHitPolicy {
    private PassableLeafHitPolicy() {
    }

    public static boolean useLeafShape(boolean isLeaf, boolean hookCollisionEmpty) {
        return isLeaf && hookCollisionEmpty;
    }
}
