package net.kefir.kefirsmod.worldgen.feature;

import net.kefir.kefirsmod.block.ModBlocks;
import net.kefir.kefirsmod.block.custom.BlueBerryBushBlock;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;


public class ModVegetationFeatures {
   public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_BLUEBERRY_BUSH = FeatureUtils.register("patch_blueberry_bush",
           Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                   new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.BLUEBERRY_BUSH.get().defaultBlockState().setValue(BlueBerryBushBlock.AGE, Integer.valueOf(3)))), List.of(Blocks.GRASS_BLOCK)));

   public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> STEPPE_GRASS = FeatureUtils.register("steppe_grass",
           Feature.RANDOM_PATCH,
           grassPatch(BlockStateProvider.simple(Blocks.GRASS), 64));

   public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_BEECH_FOREST = FeatureUtils.register("trees_beech_forest",
           Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                   new WeightedPlacedFeature(ModConfiguredFeatures.BEECH_CHECKED, 15F)),
                   TreePlacements.FANCY_OAK_CHECKED));


   public static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
      return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
   }
}
