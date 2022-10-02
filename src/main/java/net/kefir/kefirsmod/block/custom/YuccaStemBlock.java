package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class YuccaStemBlock extends PipeBlock {

    public YuccaStemBlock(BlockBehaviour.Properties properties) {
    super(0.3125F, properties);
    this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
}

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.getStateForPlacement(blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
    }

    public BlockState getStateForPlacement(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockstate = blockGetter.getBlockState(blockPos.below());
        BlockState blockstate1 = blockGetter.getBlockState(blockPos.above());
        BlockState blockstate2 = blockGetter.getBlockState(blockPos.north());
        BlockState blockstate3 = blockGetter.getBlockState(blockPos.east());
        BlockState blockstate4 = blockGetter.getBlockState(blockPos.south());
        BlockState blockstate5 = blockGetter.getBlockState(blockPos.west());
        return this.defaultBlockState().setValue(DOWN, Boolean.valueOf(blockstate.is(this) || blockstate.is(Blocks.CHORUS_FLOWER) || blockstate.is(Blocks.END_STONE))).setValue(UP, Boolean.valueOf(blockstate1.is(this) || blockstate1.is(Blocks.CHORUS_FLOWER))).setValue(NORTH, Boolean.valueOf(blockstate2.is(this) || blockstate2.is(Blocks.CHORUS_FLOWER))).setValue(EAST, Boolean.valueOf(blockstate3.is(this) || blockstate3.is(Blocks.CHORUS_FLOWER))).setValue(SOUTH, Boolean.valueOf(blockstate4.is(this) || blockstate4.is(Blocks.CHORUS_FLOWER))).setValue(WEST, Boolean.valueOf(blockstate5.is(this) || blockstate5.is(Blocks.CHORUS_FLOWER)));
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        if (!blockState.canSurvive(levelAccessor, blockPos)) {
            levelAccessor.scheduleTick(blockPos, this, 1);
            return super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
        } else {
            boolean flag = blockState1.is(this) || blockState1.is(ModBlocks.FLOWERING_YUCCA.get()) || direction == Direction.DOWN && blockState1.is(BlockTags.DIRT) || blockState1.is(Blocks.FARMLAND) || blockState1.is(Blocks.SAND) || blockState1.is(Blocks.RED_SAND) || blockState1.is(Blocks.COARSE_DIRT);
            return blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), Boolean.valueOf(flag));
        }
    }

    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (!blockState.canSurvive(serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        }

    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState blockstate = levelReader.getBlockState(blockPos.below());
        boolean flag = !levelReader.getBlockState(blockPos.above()).isAir() && !blockstate.isAir();

        for(Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = blockPos.relative(direction);
            BlockState blockstate1 = levelReader.getBlockState(blockpos);
            if (blockstate1.is(this)) {
                if (flag) {
                    return false;
                }

                BlockState blockstate2 = levelReader.getBlockState(blockpos.below());
                if (blockstate2.is(this) || blockstate2.is(BlockTags.DIRT) || blockstate2.is(Blocks.FARMLAND) || blockstate2.is(Blocks.SAND) || blockstate2.is(Blocks.RED_SAND) || blockstate2.is(Blocks.COARSE_DIRT)) {
                    return true;
                }
            }
        }

        return blockstate.is(this) || blockstate.is(BlockTags.DIRT) || blockstate.is(Blocks.FARMLAND) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.RED_SAND) || blockstate.is(Blocks.COARSE_DIRT);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51735_) {
        p_51735_.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

@Override
    public boolean isPathfindable(BlockState p_51719_, BlockGetter p_51720_, BlockPos p_51721_, PathComputationType p_51722_) {
        return false;
    }
}
