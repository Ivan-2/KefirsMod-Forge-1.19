package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }



    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(ModBlocks.BEECH_LOG.get())) {
                return ModBlocks.STRIPPED_BEECH_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.BEECH_WOOD.get())) {
                return ModBlocks.STRIPPED_BEECH_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }


            if(state.is(ModBlocks.CHERRY_LOG.get())) {
                return ModBlocks.STRIPPED_CHERRY_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.CHERRY_WOOD.get())) {
                return ModBlocks.STRIPPED_CHERRY_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }


            if(state.is(ModBlocks.CHESTNUT_LOG.get())) {
                return ModBlocks.STRIPPED_CHESTNUT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.CHESTNUT_WOOD.get())) {
                return ModBlocks.STRIPPED_CHESTNUT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.MAPLE_LOG.get())) {
                return ModBlocks.STRIPPED_MAPLE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.MAPLE_WOOD.get())) {
                return ModBlocks.STRIPPED_MAPLE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.FIR_LOG.get())) {
                return ModBlocks.STRIPPED_FIR_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.FIR_WOOD.get())) {
                return ModBlocks.STRIPPED_FIR_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }

            if(state.is(ModBlocks.POPLAR_LOG.get())) {
                return ModBlocks.STRIPPED_POPLAR_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.POPLAR_WOOD.get())) {
                return ModBlocks.STRIPPED_POPLAR_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}

