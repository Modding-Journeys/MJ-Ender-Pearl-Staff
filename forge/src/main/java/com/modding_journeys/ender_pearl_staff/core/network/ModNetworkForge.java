package com.modding_journeys.ender_pearl_staff.core.network;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.network.packet.OpenEnderPearlStaffMenuC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworkForge {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Constants.MOD_ID, "ender_pearl_staff"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {

        // parameters for register message

        // - int index,
        // - Class<MSG> messageType,
        // - BiConsumer<MSG, FriendlyByteBuf> encoder,
        // - Function<FriendlyByteBuf, MSG> decoder,
        // - BiConsumer<MSG, Supplier<NetworkEvent. Context>> messageConsumer

        // - The first parameter is the discriminator for the packet. This is a per-channel unique ID for the packet. We recommend you use a local variable to hold the ID, and then call registerMessage using id++. This will guarantee 100% unique IDs.
        // - The second parameter is the actual packet class MSG.
        // - The third parameter is a BiConsumer<MSG, FriendlyByteBuf> responsible for encoding the message into the provided FriendlyByteBuf.
        // - The fourth parameter is a Function<FriendlyByteBuf, MSG> responsible for decoding the message from the provided FriendlyByteBuf.
        // - The final parameter is a BiConsumer<MSG, Supplier<NetworkEvent.Context>> responsible for handling the message itself.

        INSTANCE.registerMessage(0, OpenEnderPearlStaffMenuC2SPacket.class, OpenEnderPearlStaffMenuC2SPacket::encode, OpenEnderPearlStaffMenuC2SPacket::decode, OpenEnderPearlStaffMenuC2SPacket::handle);
    }
}
