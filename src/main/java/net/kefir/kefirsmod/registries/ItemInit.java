package net.kefir.kefirsmod.registries;

import net.kefir.kefirsmod.KefirsMod;
import net.kefir.kefirsmod.item.*;
import net.kefir.kefirsmod.item.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            KefirsMod.MOD_ID);

    //Materials
    //public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
    //        () -> new PineConeItem(new Item.Properties().tab(BTDMain.MATERIALS_TAB)));
    //public static final RegistryObject<Item> BIRCHNUT = ITEMS.register("birchnut",
    //        () -> new BirchnutItem(new Item.Properties().tab(BTDMain.MATERIALS_TAB)));
    //public static final RegistryObject<Item> SPRUCE_LOG = ITEMS.register("spruce_log",
    //        () -> new SpruceLogItem(new Item.Properties().tab(BTDMain.MATERIALS_TAB)));

    //Plants
    //public static final RegistryObject<Item> BERRY_BUSH_ITEM = ITEMS.register("itemberry_bush",
    //        () -> new BerryBushItem(new Item.Properties().tab(BTDMain.NATURAL_TAB)));
}
