package com.modding_journeys.ender_pearl_staff.core.network;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.network.packet.OpenEnderPearlStaffMenuC2SPacket;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ModClientPacketHandlerForge {

    public static void handlePacket(OpenEnderPearlStaffMenuC2SPacket msg, Supplier<NetworkEvent.Context> ctx) {

        Constants.LOG.info("attempted to handle ender pearl staff packet on the client properly?! {}", msg.ATTACHED);
    }
}
