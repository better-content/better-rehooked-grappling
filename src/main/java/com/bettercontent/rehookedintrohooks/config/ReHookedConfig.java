package com.bettercontent.rehookedintrohooks.config;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;
public final class ReHookedConfig {
 public static final ForgeConfigSpec SPEC; private static final ForgeConfigSpec.BooleanValue ENABLED;
 static { var b=new ForgeConfigSpec.Builder(); b.push("rehooked"); ENABLED=b.define("mobGrappling",true); b.pop(); SPEC=b.build(); }
 private ReHookedConfig() {}
 public static boolean rehookedMobGrappling() {
  if (!ModList.get().isLoaded("rehooked")) return false;
  try { return ENABLED.get(); } catch (IllegalStateException ignored) { return true; }
 }
}
