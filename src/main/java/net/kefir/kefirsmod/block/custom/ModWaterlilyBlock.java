package net.kefir.kefirsmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

public class ModWaterlilyBlock extends WaterlilyBlock {
    public ModWaterlilyBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        FluidState fluidstate = blockGetter.getFluidState(blockPos);
        FluidState fluidstate1 = blockGetter.getFluidState(blockPos.above());
        return (fluidstate.getType() == Fluids.WATER || blockState.getMaterial() == Material.ICE) && fluidstate1.getType() == Fluids.EMPTY;
    }
}

