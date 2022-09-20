package net.kefir.kefirsmod.worldgen.placement;

import com.google.common.collect.ImmutableList;
import net.kefir.kefirsmod.worldgen.feature.ModVegetationFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import javax.annotation.Nullable;
import java.util.List;

public class ModVegetationPlacements {
   public static final Holder<PlacedFeature> PATCH_BLUEBERRY_COMMON = PlacementUtils.register("patch_blueberry_common",
           ModVegetationFeatures.PATCH_BLUEBERRY_BUSH, RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

   public static final Holder<PlacedFeature> TREES_PURE_BEECH_FOREST = PlacementUtils.register("trees_pure_beech_forest", ModVegetationFeatures.TREES_BEECH_FOREST, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));




   public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);
   public static List<PlacementModifier> worldSurfaceSquaredWithCount(int p_195475_) {
      return List.of(CountPlacement.of(p_195475_), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
   }

   private static List<PlacementModifier> getMushroomPlacement(int p_195477_, @Nullable PlacementModifier p_195478_) {
      ImmutableList.Builder<PlacementModifier> builder = ImmutableList.builder();
      if (p_195478_ != null) {
         builder.add(p_195478_);
      }

      if (p_195477_ != 0) {
         builder.add(RarityFilter.onAverageOnceEvery(p_195477_));
      }

      builder.add(InSquarePlacement.spread());
      builder.add(PlacementUtils.HEIGHTMAP);
      builder.add(BiomeFilter.biome());
      return builder.build();
   }

   private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier p_195485_) {
      return ImmutableList.<PlacementModifier>builder().add(p_195485_).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
   }

   public static List<PlacementModifier> treePlacement(PlacementModifier p_195480_) {
      return treePlacementBase(p_195480_).build();
   }

   public static List<PlacementModifier> treePlacement(PlacementModifier p_195482_, Block p_195483_) {
      return treePlacementBase(p_195482_).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(p_195483_.defaultBlockState(), BlockPos.ZERO))).build();
   }
}