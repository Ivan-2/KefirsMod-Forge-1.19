package net.kefir.kefirsmod.worldgen.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

    public class ModPlacedFeatures {
        public static final Holder<PlacedFeature> BEECH_PLACED = PlacementUtils.register("beech_placed",
                ModConfiguredFeatures.BEECH_SPAWN, VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(3, 10f, 2)));
    }

