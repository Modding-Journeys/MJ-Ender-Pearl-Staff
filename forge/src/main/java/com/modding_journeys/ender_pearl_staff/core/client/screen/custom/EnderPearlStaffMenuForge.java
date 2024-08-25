package com.modding_journeys.ender_pearl_staff.core.client.screen.custom;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.modding_journeys.ender_pearl_staff.core.client.screen.ModMenuTypesForge;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnderPearlStaffMenuForge extends AbstractContainerMenu {

    public List<UUID> uuidList = new ArrayList<>();
    public ServerLevel level;

    /**
     * This allows us to register it all pretty with ::new (method reference to constructor)
     * @see ModMenuTypesForge */
    public EnderPearlStaffMenuForge(int pContainerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        super(ModMenuTypesForge.ENDER_PEARL_STAFF_MENU.get(), pContainerId);
    }

    // instantiated by using the Ender Pearl Staff (probably)
    public EnderPearlStaffMenuForge(ServerLevel level, Player player, int pContainerId, Inventory inventory, FriendlyByteBuf friendlyByteBuf) {
        super(ModMenuTypesForge.ENDER_PEARL_STAFF_MENU.get(), pContainerId);

        this.level = level;

        this.level.getAllEntities().forEach(entity -> {
            if (entity instanceof Skeleton skeleton) {
                uuidList.add(skeleton.getUUID());
            }
        });

        // todo: remove debugging logs lol
        uuidList.forEach(uuid -> Constants.debug("Found skeleton with UUID: " + uuid.toString()));
        uuidList.forEach(uuid -> player.sendSystemMessage(Component.literal("Found skeleton with UUID: " + uuid.toString())));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }
}
