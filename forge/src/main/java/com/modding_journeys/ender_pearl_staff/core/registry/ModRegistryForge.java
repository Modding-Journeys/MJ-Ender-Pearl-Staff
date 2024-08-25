package com.modding_journeys.ender_pearl_staff.core.registry;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.item.ModItemsForge;
import com.modding_journeys.ender_pearl_staff.core.tab.ModCreativeModeTabsForge;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModRegistryForge {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Constants.MOD_ID);

    public static void register(IEventBus modEventBus) {

        ModItemsForge.register();
        ModCreativeModeTabsForge.register();

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    public static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    public static <T extends CreativeModeTab> RegistryObject<T> registerCreativeModeTab(String tabID, Supplier<T> tab) {
        return CREATIVE_MODE_TABS.register(tabID, tab);
    }
}
