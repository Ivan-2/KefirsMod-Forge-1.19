package net.kefir.kefirsmod.block;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.block.custom.BlueBerryBushBlock;
import net.kefir.kefirsmod.block.custom.ModFlammableRotatedPillarBlock;
import net.kefir.kefirsmod.block.custom.RosehipBushBlock;
import net.kefir.kefirsmod.item.ModItems;
import net.kefir.kefirsmod.worldgen.feature.tree.BeechTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.FLOWER_POT;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KefirsMod.MOD_ID);

    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush",
            ()-> new BlueBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> BLACKBERRY_BUSH = BLOCKS.register("blackberry_bush",
            ()-> new BlueBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> ROSEHIP_BUSH = BLOCKS.register("rosehip_bush",
            ()-> new RosehipBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));



    public static final RegistryObject<Block> BEECH_LOG = registerBlock("beech_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BEECH_WOOD = registerBlock("beech_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_BEECH_LOG = registerBlock("stripped_beech_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_BEECH_WOOD = registerBlock("stripped_beech_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> BEECH_PLANKS = registerBlock("beech_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_DOOR = registerBlock("beech_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);


    public static final RegistryObject<Block> BEECH_LEAVES = registerBlock("beech_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 30;
                }
            }, CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> BEECH_SAPLING = registerBlock("beech_sapling",
            () -> new SaplingBlock(new BeechTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
