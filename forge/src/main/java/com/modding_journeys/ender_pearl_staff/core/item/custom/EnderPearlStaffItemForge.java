package com.modding_journeys.ender_pearl_staff.core.item.custom;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.client.screen.custom.EnderPearlStaffMenuForge;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.NotNull;

public final class EnderPearlStaffItemForge extends Item {

    public EnderPearlStaffItemForge() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override // called when clicked in the air
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {

        if (level.isClientSide()) {
            return super.use(level, player, interactionHand);
        }

        Constants.LOG.info("{} attempted to use an Ender Pearl Staff!", player.getScoreboardName());

        EnderPearlStaffItemForge.useWithoutContext((ServerLevel) level, player);

        return super.use(level, player, interactionHand);
    }

    private static void useWithoutContext(ServerLevel level, Player player) {

        player.openMenu(new SimpleMenuProvider(new MenuConstructor() {
            @Override
            public @NotNull AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, @NotNull Player pPlayer) {
                return new EnderPearlStaffMenuForge(level, player, 0, pPlayerInventory, null);
            }
        }, Component.translatable("menu.ender_pearl_staff.ender_pearl_staff")));
    }
}
