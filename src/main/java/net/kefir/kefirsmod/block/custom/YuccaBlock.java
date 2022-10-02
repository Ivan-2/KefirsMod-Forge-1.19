package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class YuccaBlock extends SpikyBushBlock implements BonemealableBlock, net.minecraftforge.common.IForgeShearable {

    public YuccaBlock(Properties properties) {
        super(properties);
    }


    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean p_50900_) {
        return true;
    }
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }
    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        FloweringYuccaBlock floweringyuccablock = (FloweringYuccaBlock) ModBlocks.FLOWERING_YUCCA.get();
        if (floweringyuccablock.defaultBlockState().canSurvive(serverLevel, blockPos) && serverLevel.isEmptyBlock(blockPos.above())) {
            FloweringYuccaBlock.placeAt(serverLevel, floweringyuccablock.defaultBlockState(), blockPos, 2);
        }

    }

    public abstract void performBonemeal(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource randomSource);
}
