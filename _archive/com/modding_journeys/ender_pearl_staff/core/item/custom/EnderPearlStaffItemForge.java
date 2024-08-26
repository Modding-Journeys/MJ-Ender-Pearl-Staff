package com.modding_journeys.ender_pearl_staff.core.item.custom;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.client.screen.custom.EnderPearlStaffMenuForge;
import com.modding_journeys.ender_pearl_staff.core.network.ModNetworkForge;
import com.modding_journeys.ender_pearl_staff.core.network.packet.OpenEnderPearlStaffMenuC2SPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

public final class EnderPearlStaffItemForge extends Item {

    public EnderPearlStaffItemForge() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {

        if (level.isClientSide()) {
            return super.use(level, player, interactionHand);
        }

        Constants.debug(player.getScoreboardName() + "attempted to use an Ender Pearl Staff!");

        EnderPearlStaffItemForge.useWithoutContext((ServerLevel) level, player);

        return super.use(level, player, interactionHand);
    }

    private static void useWithoutContext(ServerLevel level, Player player) {

//        OpenEnderPearlStaffMenuC2SPacket packet = new OpenEnderPearlStaffMenuC2SPacket();

        Constants.debug("Sent a packet to the server!");
//        ModNetworkForge.INSTANCE.sendToServer(new OpenEnderPearlStaffMenuC2SPacket());
        ModNetworkForge.INSTANCE.sendToServer(new OpenEnderPearlStaffMenuC2SPacket(level, player));

        Constants.debug("Sent a packet to the player!");
//        ModNetworkForge.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new OpenEnderPearlStaffMenuC2SPacket());
        ModNetworkForge.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new OpenEnderPearlStaffMenuC2SPacket(level, player));

        player.openMenu(new SimpleMenuProvider(new MenuConstructor() {
            @Override
            public @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
                return new EnderPearlStaffMenuForge(level, player, 0, pPlayerInventory, null);
            }
        }, Component.translatable("menu.ender_pearl_staff.ender_pearl_staff")));
    }
}
