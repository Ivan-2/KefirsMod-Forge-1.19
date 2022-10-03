package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class FloweringYuccaBlock extends YuccaBlock implements BonemealableBlock, net.minecraftforge.common.IForgeShearable {

    public FloweringYuccaBlock(YuccaStemBlock yuccastemblock, BlockBehaviour.Properties properties) {
        super(properties);
        this.plant = yuccastemblock;
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
    }


    @Override
    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean p_50900_) {
        return true;
    }
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState1, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos1) {
        DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || blockState1.is(this) && blockState1.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !blockState.canSurvive(levelAccessor, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, direction, blockState1, levelAccessor, blockPos, blockPos1);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext placeContext) {
        BlockPos blockpos = placeContext.getClickedPos();
        Level level = placeContext.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(placeContext) ? super.getStateForPlacement(placeContext) : null;
    }

    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        BlockPos blockpos = blockPos.above();
        level.setBlock(blockpos, copyWaterloggedFrom(level, blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3);
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return super.canSurvive(blockState, levelReader, blockPos);
        } else {
            BlockState blockstate = levelReader.getBlockState(blockPos.below());
            if (blockState.getBlock() != this) return super.canSurvive(blockState, levelReader, blockPos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    public static void placeAt(LevelAccessor levelAccessor, BlockState blockState, BlockPos blockPos, int i) {
        BlockPos blockpos = blockPos.above();
        levelAccessor.setBlock(blockPos, copyWaterloggedFrom(levelAccessor, blockPos, blockState.setValue(HALF, DoubleBlockHalf.LOWER)), i);
        levelAccessor.setBlock(blockpos, copyWaterloggedFrom(levelAccessor, blockpos, blockState.setValue(HALF, DoubleBlockHalf.UPPER)), i);
    }

    public static BlockState copyWaterloggedFrom(LevelReader levelReader, BlockPos pos, BlockState blockState) {
        return blockState.hasProperty(BlockStateProperties.WATERLOGGED) ? blockState.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(levelReader.isWaterAt(pos))) : blockState;
    }

    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (!level.isClientSide) {
            if (player.isCreative()) {
                preventCreativeDropFromBottomPart(level, blockPos, blockState, player);
            } else {
                dropResources(blockState, level, blockPos, (BlockEntity)null, player, player.getMainHandItem());
            }
        }

        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, blockPos, Blocks.AIR.defaultBlockState(), blockEntity, itemStack);
    }

    protected static void preventCreativeDropFromBottomPart(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = blockPos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(blockState.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        blockStateBuilder.add(HALF);
    }

    public long getSeed(BlockState blockState, BlockPos blockPos) {
        return Mth.getSeed(blockPos.getX(), blockPos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), blockPos.getZ());
    }

    private final YuccaStemBlock plant;

    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        BlockPos blockpos = blockPos.above();
        if (level.isEmptyBlock(blockpos) && blockpos.getY() < level.getMaxBuildHeight()) {
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, blockpos, blockState, true)) {
                boolean flag = false;
                boolean flag1 = false;
                BlockState blockstate = level.getBlockState(blockPos.below());
                if ((blockstate.is(BlockTags.DIRT) || blockstate.is(Blocks.FARMLAND) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.RED_SAND) || blockstate.is(Blocks.COARSE_DIRT) || blockstate.is(ModBlocks.YUCCA_STEM.get()) )) {
                    flag = true;
                } else if (blockstate.is(this.plant)) {
                    int j = 1;

                    for(int k = 0; k < 4; ++k) {
                        BlockState blockstate1 = level.getBlockState(blockPos.below(j + 1));
                        if (!blockstate1.is(this.plant)) {
                            if (blockstate1.is(BlockTags.DIRT) || blockstate1.is(Blocks.FARMLAND) || blockstate1.is(Blocks.SAND) || blockstate1.is(Blocks.RED_SAND) || blockstate1.is(Blocks.COARSE_DIRT) || blockstate1.is(ModBlocks.YUCCA_STEM.get())) {
                                flag1 = true;
                            }
                            break;
                        }

                        ++j;
                    }

                    if (j < 2 || j <= randomSource.nextInt(flag1 ? 5 : 4)) {
                        flag = true;
                    }
                } else if (blockstate.isAir()) {
                    flag = true;
                }

                if (flag && allNeighborsEmpty(level, blockpos, (Direction)null) && level.isEmptyBlock(blockPos.above(2))) {
                    level.setBlock(blockPos, this.plant.getStateForPlacement(level, blockPos), 2);
                    this.placeGrownYucca(level, blockpos);
                } else {
                    int l = randomSource.nextInt(4);
                    if (flag1) {
                        ++l;
                    }

                    boolean flag2 = false;

                    for(int i1 = 0; i1 < l; ++i1) {
                        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
                        BlockPos blockpos1 = blockPos.relative(direction);
                        if (level.isEmptyBlock(blockpos1) && level.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(level, blockpos1, direction.getOpposite())) {
                            this.placeGrownYucca(level, blockpos1);
                            flag2 = true;
                        }
                    }

                    if (flag2) {
                        level.setBlock(blockPos, this.plant.getStateForPlacement(level, blockPos), 2);
                    } else {
                        this.placeDeadFlower(level, blockPos);
                    }
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, blockPos, blockState);
            }
        }
    }

    private void placeGrownYucca(Level level, BlockPos blockPos) {
        level.setBlock(blockPos, ModBlocks.YUCCA.get().defaultBlockState(), 2);
        level.levelEvent(1033, blockPos, 0);
    }

    private void placeDeadFlower(Level level, BlockPos blockPos) {
        level.setBlock(blockPos, this.defaultBlockState(), 2);
        level.levelEvent(1034, blockPos, 0);
    }

    private static boolean allNeighborsEmpty(LevelReader levelReader, BlockPos blockPos, @Nullable Direction direction1) {
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if (direction != direction1 && !levelReader.isEmptyBlock(blockPos.relative(direction))) {
                return false;
            }
        }

        return true;
    }
    public static void generatePlant(@NotNull LevelAccessor levelAccessor, BlockPos blockPos, RandomSource randomSource, int i) {
        levelAccessor.setBlock(blockPos, ((YuccaStemBlock)ModBlocks.YUCCA_STEM.get()).getStateForPlacement(levelAccessor, blockPos), 2);
        growTreeRecursive(levelAccessor, blockPos, randomSource, blockPos, i, 0);
    }

    private static void growTreeRecursive(LevelAccessor levelAccessor, BlockPos blockPos, @NotNull RandomSource randomSource, BlockPos blockPos1, int i1, int i2) {
        YuccaStemBlock yuccastemblock = (YuccaStemBlock)ModBlocks.YUCCA_STEM.get();
        int i = randomSource.nextInt(4) + 1;
        if (i2 == 0) {
            ++i;
        }

        for(int j = 0; j < i; ++j) {
            BlockPos blockpos = blockPos.above(j + 1);
            if (!allNeighborsEmpty(levelAccessor, blockpos, (Direction)null)) {
                return;
            }

            levelAccessor.setBlock(blockpos, yuccastemblock.getStateForPlacement(levelAccessor, blockpos), 2);
            levelAccessor.setBlock(blockpos.below(), yuccastemblock.getStateForPlacement(levelAccessor, blockpos.below()), 2);
        }

        boolean flag = false;
        if (i2 < 4) {
            int l = randomSource.nextInt(4);
            if (i2 == 0) {
                ++l;
            }

            for(int k = 0; k < l; ++k) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
                BlockPos blockpos1 = blockPos.above(i).relative(direction);
                if (Math.abs(blockpos1.getX() - blockPos1.getX()) < i1 && Math.abs(blockpos1.getZ() - blockPos1.getZ()) < i1 && levelAccessor.isEmptyBlock(blockpos1) && levelAccessor.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(levelAccessor, blockpos1, direction.getOpposite())) {
                    flag = true;
                    levelAccessor.setBlock(blockpos1, yuccastemblock.getStateForPlacement(levelAccessor, blockpos1), 2);
                    levelAccessor.setBlock(blockpos1.relative(direction.getOpposite()), yuccastemblock.getStateForPlacement(levelAccessor, blockpos1.relative(direction.getOpposite())), 2);
                    growTreeRecursive(levelAccessor, blockpos1, randomSource, blockPos1, i1, i2 + 1);
                }
            }
        }

        if (!flag) {
            levelAccessor.setBlock(blockPos.above(i), ModBlocks.YUCCA.get().defaultBlockState(), 2);
        }

    }
}
