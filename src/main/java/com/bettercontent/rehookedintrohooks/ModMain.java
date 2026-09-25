package com.bettercontent.rehookedintrohooks;

import com.bettercontent.rehookedintrohooks.config.ReHookedConfig;
import com.bettercontent.rehookedintrohooks.compat.rehooked.IntroHookContent;
import com.bettercontent.rehookedintrohooks.gametest.RehookedMobGrapplingGameTests;
import net.minecraftforge.event.RegisterGameTestsEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public final class ModMain {
    public static final String MOD_ID = "rehooked_intro_hooks";

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
