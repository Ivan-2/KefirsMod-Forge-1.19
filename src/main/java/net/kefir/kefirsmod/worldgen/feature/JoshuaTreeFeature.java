package net.kefir.kefirsmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.kefir.kefirsmod.block.custom.FloweringYuccaBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class JoshuaTreeFeature extends Feature<NoneFeatureConfiguration> {
    public JoshuaTreeFeature(Codec<NoneFeatureConfiguration> featureConfigurationCodec) {
        super(featureConfigurationCodec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        BlockPos blockpos = featurePlaceContext.origin();
        RandomSource randomsource = featurePlaceContext.random();
        if (worldgenlevel.isEmptyBlock(blockpos) && worldgenlevel.getBlockState(blockpos.below()).is(BlockTags.DEAD_BUSH_MAY_PLACE_ON)) {
            FloweringYuccaBlock.generatePlant(worldgenlevel, blockpos, randomsource, 8);
            return true;
        } else {
            return false;
        }
    }
}