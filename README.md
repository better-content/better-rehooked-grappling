# Better Rehooked Grappling

## Scope and authority

This repository owns its mod-specific behavior and authoring inputs. Read [local instructions](AGENTS.md)
and the [shared documentation/policy index](../../better-content-modpack/docs/README.md).


Adds six progression-oriented hook variants, profile-specific pull and catch behaviors, and weight-based mob grappling. It uses ReHooked's normal fire and retract controls. Items are registered under the `better_rehooked_grappling` namespace.

Requires ReHooked 1.8.3 for Minecraft 1.20.1. The pack controls recipe and loot acquisition.

Build with `./gradlew verifyFast`, run runtime and GameTest validation with `./gradlew verifyFull`, and stage the reobfuscated jar with `./gradlew stageRuntimeJar`.
