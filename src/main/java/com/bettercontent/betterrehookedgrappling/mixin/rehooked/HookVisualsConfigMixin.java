package com.bettercontent.betterrehookedgrappling.mixin.rehooked;

import com.oe.rehooked.config.client.visuals.HookVisualsConfig;
import java.util.Optional;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** A dedicated server cannot read ReHooked's client-only visual config. */
@Mixin(value = HookVisualsConfig.class, remap = false)
public abstract class HookVisualsConfigMixin {
    @Inject(method = "getChainSetting", at = @At("HEAD"), cancellable = true, require = 1)
    private static void betterContent$ignoreClientSettingOnServer(final String hook,
            final CallbackInfoReturnable<Optional<ForgeConfigSpec.ConfigValue<String>>> cir) {
        if (FMLEnvironment.dist == Dist.DEDICATED_SERVER) cir.setReturnValue(Optional.empty());
    }
}
