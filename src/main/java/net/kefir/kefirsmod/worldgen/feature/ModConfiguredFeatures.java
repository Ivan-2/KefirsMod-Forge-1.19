package net.kefir.kefirsmod.worldgen.feature;

import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModConfiguredFeatures {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> BEECH_TREE =
            FeatureUtils.register("beech", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.BEECH_LOG.get()),
                    new StraightTrunkPlacer(12, 2, 3),
                    BlockStateProvider.simple(ModBlocks.BEECH_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 6),
                    new TwoLayersFeatureSize(1, 1, 2)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANCY_BEECH_TREE =
            FeatureUtils.register("fancy_beech", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.BEECH_LOG.get()),
                    new FancyTrunkPlacer(12, 2, 3),
                    BlockStateProvider.simple(ModBlocks.BEECH_LEAVES.get()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 6),
                    new TwoLayersFeatureSize(1, 1, 2)).build());


    public static final Holder<PlacedFeature> BEECH_CHECKED = PlacementUtils.register("beech_checked", BEECH_TREE,
            PlacementUtils.filteredByBlockSurvival(ModBlocks.BEECH_SAPLING.get()));

    public static final Holder<PlacedFeature> FANCY_BEECH_CHECKED = PlacementUtils.register("fancy_beech_checked", FANCY_BEECH_TREE,
            PlacementUtils.filteredByBlockSurvival(ModBlocks.BEECH_SAPLING.get()));



    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CHERRY_TREE =
            FeatureUtils.register("cherry", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.CHERRY_LOG.get()),
                    new StraightTrunkPlacer(4, 1, 1),
                    BlockStateProvider.simple(ModBlocks.CHERRY_BLOSSOM.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 1, 2)).build());



    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> CHESTNUT_TREE =
            FeatureUtils.register("chestnut", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.CHESTNUT_LOG.get()),
                    new FancyTrunkPlacer(12, 2, 3),
                    BlockStateProvider.simple(ModBlocks.CHESTNUT_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 2),
                    new TwoLayersFeatureSize(1, 0, 2)).build());

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> BEECH_SPAWN =
            FeatureUtils.register("beech_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(BEECH_CHECKED,
                            5F)), FANCY_BEECH_CHECKED));
}