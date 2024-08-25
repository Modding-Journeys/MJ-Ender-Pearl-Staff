package com.modding_journeys.ender_pearl_staff.core.item;

import com.modding_journeys.ender_pearl_staff.core.item.custom.EnderPearlStaffItemForge;
import com.modding_journeys.ender_pearl_staff.core.registry.ModRegistryForge;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsForge {

    public static void register() {}

    public static final RegistryObject<Item> ENDER_PEARL_STAFF = ModRegistryForge.registerItem("ender_pearl_staff", EnderPearlStaffItemForge::new);
}
