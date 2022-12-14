package net.kefir.kefirsmod.block.custom;

import net.kefir.kefirsmod.block.ModBlocks;
import net.kefir.kefirsmod.item.ModItems;
import net.kefir.kefirsmod.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CherryBlossomBlock extends LeavesBlock {

    public CherryBlossomBlock(Properties properties) {
        super(properties);
    }

    public InteractionResult use(BlockState blockState, @NotNull Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (player.getItemInHand(interactionHand).isEmpty()) {
            if (!level.isClientSide) {
                level.setBlock(blockPos, ModBlocks.CHERRY_LEAVES.get().defaultBlockState(), 1);
                level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState));
                double d0 = (double) blockPos.getX() + 0.0D; double d1 = (double) blockPos.getY() - 0.0D; double d2 = (double) blockPos.getZ() + 0.0D;
                level.addParticle(ModParticles.CHERRY_BLOSSOM_PARTICLES.get(), d0, d1, d2, -0.3D, -0.3D, -0.3D);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        else {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }
    }
}
