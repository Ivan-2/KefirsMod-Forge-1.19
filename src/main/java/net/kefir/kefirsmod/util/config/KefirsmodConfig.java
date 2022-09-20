package net.kefir.kefirsmod.util.config;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class KefirsmodConfig {
    private static final KefirsmodConfig INSTANCE;

    public static final ForgeConfigSpec SPEC;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        Pair<KefirsmodConfig, ForgeConfigSpec> specPair =
                new ForgeConfigSpec.Builder().configure(KefirsmodConfig::new);
        INSTANCE = specPair.getLeft();

        SPEC = specPair.getRight();
    }

    //General
    private final ForgeConfigSpec.BooleanValue allowphantomstospawn;
    private final ForgeConfigSpec.BooleanValue allowdynamiclight;
    private final ForgeConfigSpec.BooleanValue mobsdropgenericmeat;
    private final ForgeConfigSpec.BooleanValue oaksdontdropapples;
    private final ForgeConfigSpec.BooleanValue enablerecipeprototyping;
    private final ForgeConfigSpec.BooleanValue terrablender_replace_vanilla_biomes;
    private final ForgeConfigSpec.IntValue terrablender_biome_weight;
    private final ForgeConfigSpec.BooleanValue replacevanilladrops;
    private final ForgeConfigSpec.BooleanValue allow_cavegen_for_mod_biomes;


    private KefirsmodConfig(ForgeConfigSpec.Builder configSpecBuilder) {
        //General
        allowphantomstospawn = configSpecBuilder
                .comment("Whether Phantoms are allowed to spawn with the mod, false by default")
                .define("allowphantomstospawn", false);
        allowdynamiclight = configSpecBuilder
                .comment("Whether non-client dynamic light is enabled")
                .define("allowdynamiclight", true);
        mobsdropgenericmeat = configSpecBuilder
                .comment("Whether passive mobs drop generic meat instead of pork/beef/etc")
                .define("mobsdropgenericmeat", true);
        oaksdontdropapples = configSpecBuilder
                .comment("Whether oaks don't drop apples")
                .define("oaksdontdropapples", true);
        enablerecipeprototyping = configSpecBuilder
                .comment("Whether to enable the game-rule limited crafting, which will force the player to unlock recipes via the Science Machine/Alchemy Engine and Blueprints")
                .define("enablerecipeprototyping", true);
        replacevanilladrops = configSpecBuilder
                .comment("Whether to replace vanilla drops of blocks such as logs instead")
                .define("replacevanilladrops", true);
        terrablender_biome_weight = configSpecBuilder
                .comment("Weight for the mod biomes generated using Terrablender")
                .defineInRange("terrablender_biome_weight", 5, 1, 15);
        terrablender_replace_vanilla_biomes = configSpecBuilder
                .comment("Whether mod biomes will replace vanilla biomes occasionally, keeping this enabled makes the vanilla-bug of mod biomes generating underwater less likely")
                .define("terrablender_replace_vanilla_biomes", true);
        allow_cavegen_for_mod_biomes = configSpecBuilder
                .comment("Whether mod biomes will generate with caves")
                .define("allow_cavegen_for_mod_biomes", false);


    }

    public static KefirsmodConfig getInstance() {
        return INSTANCE;
    }
    // Query Operations

    //General
    public boolean allowphantomstospawn() {
        return allowphantomstospawn.get();
    }

    public boolean allowdynamiclight() {
        return allowdynamiclight.get();
    }

    public boolean mobsdropgenericmeat() {
        return mobsdropgenericmeat.get();
    }

    public boolean oaksdontdropapples() {
        return oaksdontdropapples.get();
    }

    public boolean replacevanilladrops() {
        return replacevanilladrops.get();
    }

    public boolean enablerecipeprototyping() {
        return enablerecipeprototyping.get();
    }

    public int terrablender_biome_weight() {
        return terrablender_biome_weight.get();
    }

    public boolean terrablender_replace_vanilla_biomes() {
        return terrablender_replace_vanilla_biomes.get();
    }

    public boolean allow_cavegen_for_mod_biomes() {
        return allow_cavegen_for_mod_biomes.get();
    }

    public void changeallowphantomstospawn(boolean newValue) {
        allowphantomstospawn.set(newValue);
    }

    public void changeallowdynamiclight(boolean newValue) {
        allowdynamiclight.set(newValue);
    }

    public void changemobsdropgenericmeat(boolean newValue) {
        mobsdropgenericmeat.set(newValue);
    }

    public void changeoaksdontdropapples(boolean newValue) {
        oaksdontdropapples.set(newValue);
    }

    public void changeenablerecipeprototyping(boolean newValue) {
        enablerecipeprototyping.set(newValue);
    }

    public void changereplacevanilladrops(boolean newValue) {
        replacevanilladrops.set(newValue);
    }

    public void changeterrablenderweight(int newValue) {
        terrablender_biome_weight.set(newValue);
    }

    public void changeterrablendereplacebiomes(boolean newValue) {
        terrablender_replace_vanilla_biomes.set(newValue);
    }

    public void changeallow_cavegen_for_mod_biomes(boolean newValue) {
        allow_cavegen_for_mod_biomes.set(newValue);
    }
}


