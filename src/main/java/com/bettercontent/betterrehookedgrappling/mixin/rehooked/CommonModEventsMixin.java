package com.bettercontent.betterrehookedgrappling.mixin.rehooked;

import com.oe.rehooked.events.subscribers.common.CommonModEvents;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** ReHooked must not read its own server config while another mod's config is loading. */
@Mixin(value = CommonModEvents.class, remap = false)
public abstract class CommonModEventsMixin {
    @Inject(method = "onLoadConfig", at = @At("HEAD"), cancellable = true, require = 1)
    private static void betterContent$ignoreForeignLoad(final ModConfigEvent.Loading event, final CallbackInfo ci) {
        if (!"rehooked".equals(event.getConfig().getModId())) ci.cancel();
    }

    @Inject(method = "onReloadConfig", at = @At("HEAD"), cancellable = true, require = 1)
    private static void betterContent$ignoreForeignReload(final ModConfigEvent.Reloading event, final CallbackInfo ci) {
        if (!"rehooked".equals(event.getConfig().getModId())) ci.cancel();
    }
}
