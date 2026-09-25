package com.bettercontent.rehookedintrohooks.compat.rehooked;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

final class IntroHookContentResourceTest {
    @Test
    void registrationUsesNativeHookItemsAndBoundedProfiles() throws IOException {
        final Path root = Path.of("src/main");
        final String entrypoint = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/ModMain.java"));
        final String content = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/compat/rehooked/IntroHookContent.java"));
        final String profiles = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/compat/rehooked/IntroHookProfile.java"));
        final String mods = Files.readString(root.resolve("resources/META-INF/mods.toml"));
        final JsonObject translations = JsonParser.parseString(Files.readString(
                Path.of("src/main/resources/assets/rehooked_intro_hooks/lang/en_us.json"))).getAsJsonObject();

        assertTrue(entrypoint.contains("IntroHookContent.register(bus)"));
        assertTrue(mods.contains("modId=\"rehooked\"\nmandatory=false"));
        assertTrue(content.contains("new HookItem(new Item.Properties().stacksTo(1), profile.hookType())"));
        assertTrue(content.contains("HookRegistry.registerHook("));
        assertTrue(content.contains("event.enqueueWork"));
        assertTrue(content.contains("profile.count()"));
        assertTrue(content.contains("profile.range()"));
        assertTrue(content.contains("profile.travelSpeed()"));
        assertTrue(content.contains("profile.pullSpeed()"));
        final String handler = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/compat/rehooked/RehookedIntroHookBehaviors.java"));
        final String behaviorPolicy = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/compat/rehooked/IntroHookBehaviorPolicy.java"));
        final String mixin = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/mixin/rehooked/SPlayerHookHandlerMixin.java"));
        assertTrue(handler.contains("IntroHookBehaviorPolicy.ratchetPulls(activePullTick)"));
        assertTrue(handler.contains("IntroHookBehaviorPolicy::climbingVinePullFactor"));
        assertTrue(handler.contains("BlockTags.LEAVES"));
        final String hookEntityMixin = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/mixin/rehooked/HookEntityMixin.java"));
        final String boatTarget = Files.readString(root.resolve("java/com/bettercontent/rehookedintrohooks/compat/rehooked/RehookedBoatTarget.java"));
        assertTrue(hookEntityMixin.contains("betterContent$catchGrapnelBlockEdges"));
        assertTrue(hookEntityMixin.contains("shouldForgivingCatch"));
        assertTrue(behaviorPolicy.contains("GRAPNEL_EDGE_CATCH_RADIUS = 0.2D"));
        assertTrue(hookEntityMixin.contains("\"grapnel_bundle\".equals(hook.getHookType())"));
        assertTrue(hookEntityMixin.contains("betterContent$hitAnglersGaffBoat"));
        assertTrue(hookEntityMixin.contains("betterContent$followMob"));
        assertTrue(hookEntityMixin.contains("betterContent$setBoatTarget(boat)"));
        assertTrue(boatTarget.contains("Optional<Boat> betterContent$getBoatTarget()"));
        assertTrue(handler.contains("IntroHookBehaviorPolicy.shouldBoardBoat"));
        assertTrue(mixin.contains("RehookedIntroHookBehaviors.applyGaffBoarding(handler)"));
        assertTrue(behaviorPolicy.contains("GAFF_BOARDING_DISTANCE = 2.5D"));
        assertTrue(handler.contains("IntroHookBehaviorPolicy.soapGuidedVerticalComponent(pull.y)"));
        assertTrue(mixin.contains("RehookedIntroHookBehaviors.applySoapGuidance(handler)"));
        assertTrue(behaviorPolicy.contains("SOAP_VERTICAL_PULL_FACTOR = 0.55D"));
        assertTrue(handler.contains("handler.countPulling() == 0"));
        assertTrue(mixin.contains("RehookedIntroHookBehaviors.applyClimbingVineLoss(handler)"));
        assertTrue(mixin.contains("RehookedIntroHookBehaviors.applyRatchetCadence(handler, betterContent$ratchetPullTicks++)"));
        assertTrue(mixin.contains("betterContent$ratchetPullTicks = 0L"));
        assertTrue(profiles.contains("new IntroHookProfile(\"soap_on_a_rope\""));
        assertTrue(profiles.contains("new IntroHookProfile(\"anglers_gaff\""));
        assertEquals(6, IntroHookProfile.ALL.size());
        for (IntroHookProfile profile : IntroHookProfile.ALL) {
            final Path modelPath = Path.of("src/main/resources/assets/rehooked_intro_hooks/models/item/" + profile.itemId() + ".json");
            assertTrue(Files.exists(modelPath), "missing item model for " + profile.itemId());
            final JsonObject model = JsonParser.parseString(Files.readString(modelPath)).getAsJsonObject();
            assertEquals("minecraft:item/generated", model.get("parent").getAsString());
            final String sprite = model.getAsJsonObject("textures").get("layer0").getAsString();
            assertTrue(sprite.startsWith("rehooked:item/"),
                    "model must use a pinned ReHooked item sprite for " + profile.itemId());
            final String spriteResource = "/assets/" + sprite.replace(":", "/textures/") + ".png";
            assertNotNull(IntroHookContentResourceTest.class.getResource(spriteResource),
                    "pinned ReHooked sprite is missing for " + profile.itemId());
            assertTrue(translations.has("item.rehooked_intro_hooks." + profile.itemId()),
                    "missing translation for " + profile.itemId());
        }
    }
}
