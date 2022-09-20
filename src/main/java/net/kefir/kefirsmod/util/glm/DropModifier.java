package net.kefir.kefirsmod.util.glm;
import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
//import net.kefir.kefirsmod.BTDMain;
//import net.kefir.kefirsmod.registries.BlockInit;
//import net.kefir.kefirsmod.registries.ItemInit;
//import net.kefir.kefirsmod.util.config.KefirsmodConfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.registries.ItemInit;
import net.kefir.kefirsmod.util.config.KefirsmodConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class DropModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, KefirsMod.MOD_ID);
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> KEFIRSMOD_DROPS = GLM.register("kefirsmod_drops", BlockDropModifier.CODEC);

    public static class BlockDropModifier extends LootModifier {
        public static final Supplier<Codec<BlockDropModifier>> CODEC = Suppliers.memoize(() ->
                RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, BlockDropModifier::new)));

        public BlockDropModifier(LootItemCondition[] lootConditions) {
            super(lootConditions);
        }

        @Nonnull
        @Override
        protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
            if (context.hasParam(LootContextParams.BLOCK_STATE)) {
                BlockState state = context.getParamOrNull(LootContextParams.BLOCK_STATE);
                Block block = state.getBlock();
                if (block == Blocks.OAK_LEAVES) {
                    if (KefirsmodConfig.getInstance().replacevanilladrops()) {
                        generatedLoot.clear();
                        generatedLoot.add(new ItemStack(Items.OAK_SAPLING, 1));
                    }
                }
            }

            //
            //if (block == Blocks.OAK_LEAVES|| block == Blocks.ACACIA_LEAVES|| block == Blocks.SPRUCE_LEAVES) {
            //    if(KefirsmodConfig.getInstance().pineconesassaplings()) {
            //        Random rand = new Random();
            //        int pineconechance = rand.nextInt(100); int twigschance = rand.nextInt(100); int bonustwigschance = rand.nextInt(2);
            //        if(KefirsmodConfig.getInstance().replacevanilladrops()) {
            //            generatedLoot.clear();
            //            if (pineconechance <= 4) {generatedLoot.add(new ItemStack(ItemInit.PINE_CONE.get(), 1));}
            //           if (twigschance <= 1) {if (bonustwigschance == 0) {generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));}
            //               if (bonustwigschance == 1) {generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));}}}}}
            //
            //
            //if (block == Blocks.DEAD_BUSH) {
            //    if(KefirsmodConfig.getInstance().pineconesassaplings()) {
            //        Random rand = new Random();
            //        int twigschance = rand.nextInt(100);
            //        int bonustwigschance = rand.nextInt(2);
            //        if(KefirsmodConfig.getInstance().replacevanilladrops()) {generatedLoot.clear();
            //           if (twigschance <= 49) {if (bonustwigschance == 0) {generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));}
            //                if (bonustwigschance == 1) {generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));generatedLoot.add(new ItemStack(ItemInit.TWIGS.get(), 1));}

            return generatedLoot;

        }
        @Override
        public Codec<? extends IGlobalLootModifier> codec() {
            return KEFIRSMOD_DROPS.get();}
    }
}
