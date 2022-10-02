package net.kefir.kefirsmod.block;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.block.custom.*;
import net.kefir.kefirsmod.item.ModItems;
import net.kefir.kefirsmod.worldgen.feature.tree.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, KefirsMod.MOD_ID);

    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(6f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BLUEBERRY_BUSH = BLOCKS.register("blueberry_bush",
            ()-> new BlueBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> BLACKBERRY_BUSH = BLOCKS.register("blackberry_bush",
            ()-> new BlackBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> ROSEHIP_BUSH = BLOCKS.register("rosehip_bush",
            ()-> new RosehipBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> YUCCA = registerBlock("yucca",
            ()-> new YuccaBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)), CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Block> FLOWERING_YUCCA = registerBlock("flowering_yucca",
            ()-> new FloweringYuccaBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_YUCCA = BLOCKS.register("potted_yucca",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.YUCCA,
                    BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));


    public static final RegistryObject<Block> SAGEBRUSH = registerBlock("sagebrush",
            ()-> new NotSpikyBushBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_SAGEBRUSH = BLOCKS.register("potted_sagebrush",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.SAGEBRUSH,
                    BlockBehaviour.Properties.copy(Blocks.DEAD_BUSH)));


    public static final RegistryObject<Block> WATERLILY = registerBlock("waterlily",
            ()-> new ModWaterlilyBlock(BlockBehaviour.Properties.copy(LILY_PAD)), CreativeModeTab.TAB_DECORATIONS);




    //beech
    public static final RegistryObject<Block> BEECH_LOG = registerBlock("beech_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BEECH_WOOD = registerBlock("beech_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_BEECH_LOG = registerBlock("stripped_beech_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_BEECH_WOOD = registerBlock("stripped_beech_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_PLANKS = registerBlock("beech_planks",
            () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)) {
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

    public static final RegistryObject<Block> BEECH_STAIRS = registerBlock("beech_stairs",
            () ->  new StairBlock(OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(OAK_STAIRS)) {
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

    public static final RegistryObject<Block> BEECH_SLAB = registerBlock("beech_slab",
            () ->  new SlabBlock(BlockBehaviour.Properties.copy(OAK_SLAB)) {
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

    //to be done
    public static final RegistryObject<Block> BEECH_SIGN = registerBlock("beech_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //public static final RegistryObject<Block> BEECH_WALL_SIGN = registerBlock("beech_wall_sign",
    //        () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_FENCE = registerBlock("beech_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(OAK_FENCE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BEECH_FENCE_GATE = registerBlock("beech_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(OAK_FENCE_GATE)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> BEECH_DOOR = registerBlock("beech_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> BEECH_BUTTON = registerBlock("beech_button",
            ()-> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), CreativeModeTab.TAB_REDSTONE);


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


    public static final RegistryObject<Block> POTTED_BEECH_SAPLING = BLOCKS.register("potted_beech_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.BEECH_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));


    //cherry
    public static final RegistryObject<Block> CHERRY_LOG = registerBlock("cherry_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHERRY_WOOD = registerBlock("cherry_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerBlock("stripped_cherry_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerBlock("stripped_cherry_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_PLANKS = registerBlock("cherry_planks",
            () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)) {
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

    public static final RegistryObject<Block> CHERRY_STAIRS = registerBlock("cherry_stairs",
            () ->  new StairBlock(OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(OAK_STAIRS)) {
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

    public static final RegistryObject<Block> CHERRY_SLAB = registerBlock("cherry_slab",
            () ->  new SlabBlock(BlockBehaviour.Properties.copy(OAK_SLAB)) {
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

    //to be done
    public static final RegistryObject<Block> CHERRY_SIGN = registerBlock("cherry_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //public static final RegistryObject<Block> CHERRY_WALL_SIGN = registerBlock("cherry_wall_sign",
    //        () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_FENCE = registerBlock("cherry_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(OAK_FENCE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = registerBlock("cherry_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(OAK_FENCE_GATE)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> CHERRY_DOOR = registerBlock("cherry_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = registerBlock("cherry_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = registerBlock("cherry_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHERRY_BUTTON = registerBlock("cherry_button",
            ()-> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), CreativeModeTab.TAB_REDSTONE);


    public static final RegistryObject<Block> CHERRY_BLOSSOM = registerBlock("cherry_blossom",
            () -> new CherryBlossomBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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

    public static final RegistryObject<Block> CHERRY_LEAVES = registerBlock("cherry_leaves",
            () -> new CherryLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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


    public static final RegistryObject<Block> CHERRY_SAPLING = registerBlock("cherry_sapling",
            () -> new SaplingBlock(new CherryTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = BLOCKS.register("potted_cherry_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CHERRY_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    //chestnut
    public static final RegistryObject<Block> CHESTNUT_LOG = registerBlock("chestnut_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_WOOD = registerBlock("chestnut_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_LOG = registerBlock("stripped_chestnut_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHESTNUT_WOOD = registerBlock("stripped_chestnut_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHESTNUT_PLANKS = registerBlock("chestnut_planks",
            () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)) {
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

    public static final RegistryObject<Block> CHESTNUT_STAIRS = registerBlock("chestnut_stairs",
            () ->  new StairBlock(OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(OAK_STAIRS)) {
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

    public static final RegistryObject<Block> CHESTNUT_SLAB = registerBlock("chestnut_slab",
            () ->  new SlabBlock(BlockBehaviour.Properties.copy(OAK_SLAB)) {
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

    //to be done
    public static final RegistryObject<Block> CHESTNUT_SIGN = registerBlock("chestnut_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //public static final RegistryObject<Block> CHESTNUT_WALL_SIGN = registerBlock("CHESTNUT_wall_sign",
    //        () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHESTNUT_FENCE = registerBlock("chestnut_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(OAK_FENCE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_FENCE_GATE = registerBlock("chestnut_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(OAK_FENCE_GATE)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> CHESTNUT_DOOR = registerBlock("chestnut_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHESTNUT_TRAPDOOR = registerBlock("chestnut_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHESTNUT_PRESSURE_PLATE = registerBlock("chestnut_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> CHESTNUT_BUTTON = registerBlock("chestnut_button",
            ()-> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), CreativeModeTab.TAB_REDSTONE);


    public static final RegistryObject<Block> CHESTNUT_LEAVES = registerBlock("chestnut_leaves",
            () -> new ChestnutLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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


    public static final RegistryObject<Block> CHESTNUT_SAPLING = registerBlock("chestnut_sapling",
            () -> new SaplingBlock(new ChestnutTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> POTTED_CHESTNUT_SAPLING = BLOCKS.register("potted_chestnut_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CHESTNUT_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));




    //maple
    public static final RegistryObject<Block> MAPLE_LOG = registerBlock("maple_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MAPLE_WOOD = registerBlock("maple_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MAPLE_LOG = registerBlock("stripped_maple_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_MAPLE_WOOD = registerBlock("stripped_maple_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAPLE_PLANKS = registerBlock("maple_planks",
            () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)) {
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

    public static final RegistryObject<Block> MAPLE_STAIRS = registerBlock("maple_stairs",
            () ->  new StairBlock(OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(OAK_STAIRS)) {
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

    public static final RegistryObject<Block> MAPLE_SLAB = registerBlock("maple_slab",
            () ->  new SlabBlock(BlockBehaviour.Properties.copy(OAK_SLAB)) {
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

    //to be done
    public static final RegistryObject<Block> MAPLE_SIGN = registerBlock("maple_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //public static final RegistryObject<Block> MAPLE_WALL_SIGN = registerBlock("maple_wall_sign",
    //        () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAPLE_FENCE = registerBlock("maple_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(OAK_FENCE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = registerBlock("maple_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(OAK_FENCE_GATE)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> MAPLE_DOOR = registerBlock("maple_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = registerBlock("maple_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = registerBlock("maple_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> MAPLE_BUTTON = registerBlock("maple_button",
            ()-> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), CreativeModeTab.TAB_REDSTONE);


    public static final RegistryObject<Block> MAPLE_LEAVES = registerBlock("maple_leaves",
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


    public static final RegistryObject<Block> MAPLE_SAPLING = registerBlock("maple_sapling",
            () -> new SaplingBlock(new MapleTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> POTTED_MAPLE_SAPLING = BLOCKS.register("potted_maple_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.MAPLE_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    //fir
    public static final RegistryObject<Block> FIR_LOG = registerBlock("fir_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FIR_WOOD = registerBlock("fir_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_FIR_LOG = registerBlock("stripped_fir_log",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_FIR_WOOD = registerBlock("stripped_fir_wood",
            ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> FIR_PLANKS = registerBlock("fir_planks",
            () -> new Block(BlockBehaviour.Properties.copy(OAK_PLANKS)) {
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

    public static final RegistryObject<Block> FIR_STAIRS = registerBlock("fir_stairs",
            () ->  new StairBlock(OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(OAK_STAIRS)) {
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

    public static final RegistryObject<Block> FIR_SLAB = registerBlock("fir_slab",
            () ->  new SlabBlock(BlockBehaviour.Properties.copy(OAK_SLAB)) {
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

    //to be done
    public static final RegistryObject<Block> FIR_SIGN = registerBlock("fir_sign",
            () -> new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);
    //public static final RegistryObject<Block> FIR_WALL_SIGN = registerBlock("fir_wall_sign",
    //        () -> new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodType.OAK), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> FIR_FENCE = registerBlock("fir_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(OAK_FENCE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FIR_FENCE_GATE = registerBlock("fir_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(OAK_FENCE_GATE)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> FIR_DOOR = registerBlock("fir_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> FIR_TRAPDOOR = registerBlock("fir_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(5f).requiresCorrectToolForDrops()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> FIR_PRESSURE_PLATE = registerBlock("fir_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.5F).sound(SoundType.WOOD)), CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Block> FIR_BUTTON = registerBlock("fir_button",
            ()-> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)), CreativeModeTab.TAB_REDSTONE);


    public static final RegistryObject<Block> FIR_LEAVES = registerBlock("fir_leaves",
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


    public static final RegistryObject<Block> FIR_SAPLING = registerBlock("fir_sapling",
            () -> new SaplingBlock(new FirTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)), CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> POTTED_FIR_SAPLING = BLOCKS.register("potted_fir_sapling",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.FIR_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));




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
