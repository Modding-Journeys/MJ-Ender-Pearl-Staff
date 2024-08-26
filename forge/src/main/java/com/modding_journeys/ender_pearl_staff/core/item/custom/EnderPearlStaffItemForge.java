package com.modding_journeys.ender_pearl_staff.core.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class EnderPearlStaffItemForge extends Item {

    public EnderPearlStaffItemForge() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {

        final ItemStack usedItemStack = pPlayer.getItemInHand(pUsedHand);

        if (pLevel.isClientSide()) {
            return InteractionResultHolder.pass(usedItemStack);
        }

        ServerLevel serverLevel = (ServerLevel) pLevel;
        ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

        List<BlockPos> possiblePositions = new ArrayList<>();

        serverLevel.getServer().getAllLevels().forEach(level -> {
            level.getAllEntities().forEach(entity -> {
                if (!(entity.getScoreboardName().equals(serverPlayer.getScoreboardName())) && entity instanceof ServerPlayer otherPlayer) {
                    possiblePositions.add(otherPlayer.blockPosition());
                }
            });
        });

        if (!possiblePositions.isEmpty()) {
            final BlockPos blockPos = possiblePositions.get((serverLevel.random.nextInt(possiblePositions.size())));

            if (blockPos != serverPlayer.blockPosition()) {
                serverPlayer.setExperienceLevels(serverPlayer.experienceLevel - 1);
                serverPlayer.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            }
        }

        return InteractionResultHolder.pass(usedItemStack);
    }
}
