package com.modding_journeys.examplemod;

import com.modding_journeys.ender_pearl_staff.EnderPearlStaff;
import net.fabricmc.api.ModInitializer;

public class EnderPearlStaffFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        EnderPearlStaff.init();
    }
}
