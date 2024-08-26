package com.modding_journeys.ender_pearl_staff.core.client.screen;

import com.modding_journeys.ender_pearl_staff.core.client.screen.custom.EnderPearlStaffMenuForge;
import com.modding_journeys.ender_pearl_staff.core.registry.ModRegistryForge;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypesForge {

    public static void register() {}

    public static final RegistryObject<MenuType<EnderPearlStaffMenuForge>> ENDER_PEARL_STAFF_MENU = ModRegistryForge.registerMenuType("ender_pearl_staff_menu", EnderPearlStaffMenuForge::new);
}
