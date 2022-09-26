package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CherryLeavesBlock extends LeavesBlock implements BonemealableBlock {

    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final int DECAY_DISTANCE = 7;
    public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final int TICK_DELAY = 1;

    public CherryLeavesBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }


    public boolean isRandomlyTicking(BlockState p_57284_) {
        return p_57284_.getValue(AGE) < 3;
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int i = blockState.getValue(AGE);
        boolean flag = i == 3;
        if (i < 3 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(serverLevel, blockPos, blockState, randomSource.nextInt(2) == 0)) {
            BlockState blockstate = blockState.setValue(AGE, Integer.valueOf(i + 1));
            serverLevel.setBlock(blockPos, blockstate, 2);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState);
        }
        if (i == 3) {
            popResource(serverLevel, blockPos, new ItemStack(ModItems.CHERRIES.get(), i + (flag ? 1 : 0)));
            BlockState blockstate = blockState.setValue(AGE, Integer.valueOf(0));
            serverLevel.setBlock(blockPos, blockstate, 2);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(serverLevel, blockPos, blockState);
        }

    }
    
    public InteractionResult use(BlockState p_57275_, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        int i = p_57275_.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && player.getItemInHand(interactionHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, blockPos, new ItemStack(ModItems.CHERRIES.get(), j + (flag ? 1 : 0)));
            level.playSound((Player)null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
            BlockState blockstate = p_57275_.setValue(AGE, Integer.valueOf(1));
            level.setBlock(blockPos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.use(p_57275_, level, blockPos, player, interactionHand, blockHitResult);
        }
    }



    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockState) {
        blockState.add(AGE, PERSISTENT, WATERLOGGED, DISTANCE);
    }

    public boolean isValidBonemealTarget(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, boolean p_57263_) {
        return blockState.getValue(AGE) < 3;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel p_222553_, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int i = Math.min(3, blockState.getValue(AGE) + 1);
        p_222553_.setBlock(blockPos, blockState.setValue(AGE, Integer.valueOf(i)), 2);
    }
}
