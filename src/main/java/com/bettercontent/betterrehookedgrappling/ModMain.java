package com.bettercontent.betterrehookedgrappling;

import com.bettercontent.betterrehookedgrappling.config.ReHookedConfig;
import com.bettercontent.betterrehookedgrappling.compat.rehooked.IntroHookContent;
import com.bettercontent.betterrehookedgrappling.gametest.RehookedMobGrapplingGameTests;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public final class ModMain {
    public static final String MOD_ID = "better_rehooked_grappling";

    public ModMain() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ReHookedConfig.SPEC);
        if (ModList.get().isLoaded("rehooked")) {
            IntroHookContent.register(bus);
            bus.addListener(this::registerTests);
        }
    }
    private void registerTests(RegisterGameTestsEvent event) {
        event.register(RehookedMobGrapplingGameTests.class);
    }
}
