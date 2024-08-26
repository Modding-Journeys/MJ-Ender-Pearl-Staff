package com.modding_journeys.ender_pearl_staff.core.client;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.client.screen.ModMenuTypesForge;
import com.modding_journeys.ender_pearl_staff.core.client.screen.custom.EnderPearlStaffScreenForge;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EnderPearlStaffClientForge {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypesForge.ENDER_PEARL_STAFF_MENU.get(), EnderPearlStaffScreenForge::new);
        });
    }
}
