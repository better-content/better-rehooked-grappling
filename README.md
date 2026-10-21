# Better Rehooked Grappling

## Scope and authority

This repository owns its mod-specific behavior and authoring inputs. Read [local instructions](AGENTS.md)
and the [shared documentation/policy index](../../better-content-modpack/docs/README.md).

## Cold source native fixture

`verifyFull` binds one fresh `build/gametest/<UUID>` to Forge's actual RunConfig, settings,
structures and audited sink. No previous latest.log/world or ordinary/visual instance is deleted
or adopted. CREATE_NEW settings preserve existing bytes exactly. Four completed/passed native
cases and strict latest/debug ERROR/FATAL/mixin-failure checks remain required, with pure
creation/preservation and missing/count-mismatch/duplicate/severity controls. Retain prior build
outputs before `clean`; historical warm passes are not proof of this new cold profile.

## Bounded playtest diagnostics

Loader SLF4J `bc.playtest.better_rehooked_grappling.*` reports INFO startup/provider/own-source
status (UNKNOWN without own metadata), and DEBUG observed boat/mob/forgiving-block attachments
or target-loss retraction. Causal hook UUID, existing profile and native tick identify the hook;
owner names, rope samples, candidate scans, item tags and per-tick vectors are omitted. Existing
RNG and target/physics authority are unchanged. No detail is emitted by rope rendering or normal
following ticks. One mod-wide 120/minute burst20 budget, 256 aggregate keys, 2048-character
records and 30-second summaries bound output. Disconnect/server stop clears aggregation without
refilling allowance. Standalone remains INFO; the pack selects scoped DEBUG. Logs do not prove
rendered rope or complete client/server playthrough acceptance.


Adds six progression-oriented hook variants, profile-specific pull and catch behaviors, and weight-based mob grappling. It uses ReHooked's normal fire and retract controls. Items are registered under the `better_rehooked_grappling` namespace.

Requires ReHooked 1.8.3 for Minecraft 1.20.1. The pack controls recipe and loot acquisition.

Build with `./gradlew verifyFast`, run runtime and GameTest validation with `./gradlew verifyFull`, and stage the reobfuscated jar with `./gradlew stageRuntimeJar`.
