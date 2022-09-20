package net.kefir.kefirsmod.item;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.block.ModBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KefirsMod.MOD_ID);

    public static final RegistryObject<Item> BRONZE = ITEMS.register("bronze",
            ()-> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            ()-> new ItemNameBlockItem(ModBlocks.BLUEBERRY_BUSH.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
                            .food(new FoodProperties.Builder().nutrition(1).saturationMod(2f).build())));

    public static final RegistryObject<Item> BLACKBERRY = ITEMS.register("blackberry",
            ()-> new ItemNameBlockItem(ModBlocks.BLACKBERRY_BUSH.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
                            .food(new FoodProperties.Builder().nutrition(2).saturationMod(1f).build())));

    public static final RegistryObject<Item> ROSEHIP = ITEMS.register("rosehip",
            ()-> new ItemNameBlockItem(ModBlocks.ROSEHIP_BUSH.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
                            .food(new FoodProperties.Builder().nutrition(1).saturationMod(5f).build())));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
