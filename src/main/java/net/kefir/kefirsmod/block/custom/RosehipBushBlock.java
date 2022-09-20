package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RosehipBushBlock extends SweetBerryBushBlock {

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(ModItems.ROSEHIP.get());
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        int i = blockState.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, blockPos, new ItemStack(ModItems.ROSEHIP.get(), j + (flag ? 1 : 0)));
            level.playSound((Player)null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = blockState.setValue(AGE, Integer.valueOf(1));
            level.setBlock(blockPos, blockstate, 1);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }
    }
    public RosehipBushBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }


    public void tick(BlockState p_222543_, ServerLevel p_222544_, BlockPos p_222545_, RandomSource p_222546_) {
        if (!p_222543_.canSurvive(p_222544_, p_222545_)) {
            p_222544_.destroyBlock(p_222545_, true);
        }

    }

    public void randomTick(BlockState p_222548_, ServerLevel p_222549_, BlockPos p_222550_, RandomSource p_222551_) {
        if (p_222549_.isEmptyBlock(p_222550_.above())) {
            int i;
            for(i = 1; p_222549_.getBlockState(p_222550_.below(i)).is(this); ++i) {
            }

            if (i < 3) {
                int j = p_222548_.getValue(AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_222549_, p_222550_, p_222548_, true)) {
                    if (j == 15) {
                        p_222549_.setBlockAndUpdate(p_222550_.above(), this.defaultBlockState());
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_222549_, p_222550_.above(), this.defaultBlockState());
                        p_222549_.setBlock(p_222550_, p_222548_.setValue(AGE, Integer.valueOf(0)), 4);
                    } else {
                        p_222549_.setBlock(p_222550_, p_222548_.setValue(AGE, Integer.valueOf(j + 1)), 4);
                    }
                }
            }
        }

    }

    public BlockState updateShape(BlockState p_57179_, Direction p_57180_, BlockState p_57181_, LevelAccessor p_57182_, BlockPos p_57183_, BlockPos p_57184_) {
        if (!p_57179_.canSurvive(p_57182_, p_57183_)) {
            p_57182_.scheduleTick(p_57183_, this, 1);
        }

        return super.updateShape(p_57179_, p_57180_, p_57181_, p_57182_, p_57183_, p_57184_);
    }

    public boolean canSurvive(BlockState p_57175_, LevelReader p_57176_, BlockPos p_57177_) {
        BlockState soil = p_57176_.getBlockState(p_57177_.below());
        if (soil.canSustainPlant(p_57176_, p_57177_.below(), Direction.UP, this)) return true;
        BlockState blockstate = p_57176_.getBlockState(p_57177_.below());
        if (blockstate.is(this)) {
            return true;
        } else {
            if (blockstate.is(BlockTags.DIRT) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.RED_SAND)) {
                BlockPos blockpos = p_57177_.below();
                return true;
                    }
                }

            return false;
        }
    }
