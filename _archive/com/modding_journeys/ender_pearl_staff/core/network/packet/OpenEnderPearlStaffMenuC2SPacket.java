package com.modding_journeys.ender_pearl_staff.core.network.packet;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.network.ModClientPacketHandlerForge;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Supplier;

public class OpenEnderPearlStaffMenuC2SPacket {

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

    public String ATTACHED = "this string is attached to an instance of OpenEnderPearlStaffMenuC2SPacket";

    public OpenEnderPearlStaffMenuC2SPacket(@Nullable ServerLevel level, @Nullable Player player) {
    }

    public void encode(FriendlyByteBuf friendlyByteBuf) {
        
        Constants.debug("attempted to encode ender pearl staff packet! " + this.ATTACHED);
    }

    public static OpenEnderPearlStaffMenuC2SPacket decode(FriendlyByteBuf friendlyByteBuf) {

        OpenEnderPearlStaffMenuC2SPacket returned = new OpenEnderPearlStaffMenuC2SPacket(null, null);

        Constants.debug("attempted to decode ender pearl staff packet! " + returned.ATTACHED);
        return returned;
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {

        Constants.debug("attempted to handle ender pearl staff packet! " + this.ATTACHED);

        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {

            Constants.debug("attempted to handle ender pearl staff packet on the client! " + this.ATTACHED);

            contextSupplier.get().enqueueWork(() ->
                    // Make sure it's only executed on the physical client
                    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModClientPacketHandlerForge.handlePacket(this, contextSupplier))
            );
            contextSupplier.get().setPacketHandled(true);
        } else {

            Constants.debug("attempted to handle ender pearl staff packet on the server! " + this.ATTACHED);
        }
    }

//    public static void handle(OpenEnderPearlStaffMenuC2SPacket msg, Supplier<NetworkEvent.Context> ctx) {
//        ctx.get().enqueueWork(() ->
//                // Make sure it's only executed on the physical client
//                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModClientPacketHandlerForge.handlePacket(msg, ctx))
//        );
//        ctx.get().setPacketHandled(true);
//    }
}
