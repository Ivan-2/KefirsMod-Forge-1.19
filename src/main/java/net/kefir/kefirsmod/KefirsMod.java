package net.kefir.kefirsmod;

import com.mojang.logging.LogUtils;
import net.kefir.kefirsmod.block.ModBlocks;
import net.kefir.kefirsmod.item.ModItems;
import net.kefir.kefirsmod.particle.ModParticles;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;
import net.kefir.kefirsmod.util.glm.DropModifier;
//import net.kefir.kefirsmod.util.glm.FishingLootModifier;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;




// The value here should match an entry in the META-INF/mods.toml file
@net.minecraftforge.fml.common.Mod(KefirsMod.MOD_ID)
public class KefirsMod
{
    public static final String MOD_ID = "kefirsmod";
    private static final Logger LOGGER = (Logger) LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public KefirsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModParticles.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        GeckoLib.initialize();
        //DropModifier.GLM.register(modEventBus);
        GLM.register(modEventBus);



        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MOD_ID);
    //public static final RegistryObject<Codec<FishingLootModifier>> FISH_LOOT = GLM.register("fishing", () -> FishingLootModifier.CODEC);


    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUEBERRY_BUSH.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLACKBERRY_BUSH.get(), RenderType.cutout());
        }
    }

    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(value = Dist.CLIENT, modid = KefirsMod.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientHandler
    {
        public static final ColorResolver COLOR_RESOLVER = (biome, x, z) -> biome.getFoliageColor();

        @SubscribeEvent
        static void registerColorResolver(RegisterColorHandlersEvent.ColorResolvers event)
        {
            event.register(COLOR_RESOLVER);
        }

        @SubscribeEvent
        static void registerBlockColor(RegisterColorHandlersEvent.Block event)
        {
            event.register(((state, btGetter, pos, tintIndex)
                    -> btGetter == null || pos == null ? 0 : btGetter.getBlockTint(pos, COLOR_RESOLVER)),
                    ModBlocks.BEECH_LEAVES.get(), ModBlocks.CHERRY_LEAVES.get(), ModBlocks.CHESTNUT_LEAVES.get());

            event.register(((state, btGetter, pos, tintIndex) -> 2129968),
                    ModBlocks.WATERLILY.get());

            event.register(((state, btGetter, pos, tintIndex) -> FoliageColor.getEvergreenColor()),
                    ModBlocks.FIR_LEAVES.get());

            event.register(((state, btGetter, pos, tintIndex) -> FoliageColor.getBirchColor()),
                    ModBlocks.POPLAR_LEAVES.get());

            event.register(((state, btGetter, pos, tintIndex) -> 14241293),
                    ModBlocks.MAPLE_LEAVES.get());
        }
        @SubscribeEvent
        static void registerItemColor(RegisterColorHandlersEvent.Item event)
        {
            event.register((itemColor, item) -> {
                BlockState blockstate = ((BlockItem)itemColor.getItem()).getBlock().defaultBlockState();
                return FoliageColor.getDefaultColor();
            },
                    ModBlocks.BEECH_LEAVES.get(), ModBlocks.CHERRY_LEAVES.get(), ModBlocks.CHESTNUT_LEAVES.get());

            event.register((itemColor, item) -> {
                BlockState blockstate = ((BlockItem)itemColor.getItem()).getBlock().defaultBlockState();
                return FoliageColor.get(0.5D, 1.0D);
            },
                    ModBlocks.WATERLILY.get());

            event.register((itemColor, item) -> {
                        BlockState blockstate = ((BlockItem)itemColor.getItem()).getBlock().defaultBlockState();
                        return FoliageColor.getEvergreenColor();
                    },
                    ModBlocks.FIR_LEAVES.get());

            event.register((itemColor, item) -> {
                        BlockState blockstate = ((BlockItem)itemColor.getItem()).getBlock().defaultBlockState();
                        return FoliageColor.getBirchColor();
                    },
                    ModBlocks.POPLAR_LEAVES.get());

            event.register(((itemColor, item) -> 14241293),
                    ModBlocks.MAPLE_LEAVES.get());
        }
    }
}
