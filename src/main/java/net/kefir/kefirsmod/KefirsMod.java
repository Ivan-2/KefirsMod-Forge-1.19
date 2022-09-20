package net.kefir.kefirsmod;

import com.mojang.logging.LogUtils;
import net.kefir.kefirsmod.block.ModBlocks;
import net.kefir.kefirsmod.item.ModItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.ColorResolver;
import net.minecraftforge.api.distmarker.Dist;
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
    private static class ClientHandler
    {
        private static final ColorResolver COLOR_RESOLVER = (biome, x, z) -> biome.getFoliageColor();

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
                    ModBlocks.BEECH_LEAVES.get());
        }
    }
}
