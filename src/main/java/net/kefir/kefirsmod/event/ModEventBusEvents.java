package net.kefir.kefirsmod.event;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.particle.ModParticles;
import net.kefir.kefirsmod.particle.custom.CherryBlossomParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = KefirsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.CHERRY_BLOSSOM_PARTICLES.get(),
                CherryBlossomParticles.Provider::new);
    }
}