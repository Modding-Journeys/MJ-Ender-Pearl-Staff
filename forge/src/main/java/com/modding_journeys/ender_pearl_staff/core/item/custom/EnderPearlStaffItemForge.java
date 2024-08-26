package com.modding_journeys.ender_pearl_staff.core.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class EnderPearlStaffItemForge extends Item {

    public EnderPearlStaffItemForge() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    /**
     * pseudo-code?
     *
     * 1. player uses the item
     * 2. check if player is sneaking or not
     * 3. if sneaking, return player home or sa
     */

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        final ItemStack usedItemStack = pPlayer.getItemInHand(pUsedHand);

        if (pLevel.isClientSide()) {
            return InteractionResultHolder.pass(usedItemStack);
        }

        ServerLevel serverLevel = (ServerLevel) pLevel;
        ServerPlayer serverPlayer = (ServerPlayer) pPlayer;

        if (serverPlayer.isShiftKeyDown()) {
            EnderPearlStaffItemForge.handleSneakInteraction(serverLevel, serverPlayer, usedItemStack);
        }
        else {
            EnderPearlStaffItemForge.handleNormalInteraction(serverLevel, serverPlayer, usedItemStack);
        }

        return InteractionResultHolder.pass(usedItemStack);
    }

    private static void handleSneakInteraction(@NotNull ServerLevel serverLevel, @NotNull ServerPlayer serverPlayer, @NotNull ItemStack usedItemStack) {

        CompoundTag tag = usedItemStack.getTagElement("HomePosition");

        if (tag != null) {
            EnderPearlStaffItemForge.teleportPlayerToHomePositionFromItemStack(serverLevel, serverPlayer, tag);
        } else {
            EnderPearlStaffItemForge.savePlayerPositionToItemStack(serverPlayer, usedItemStack);
        }
    }

    private static void handleNormalInteraction(@NotNull ServerLevel serverLevel, @NotNull ServerPlayer serverPlayer, @NotNull ItemStack usedItemStack) {

        EnderPearlStaffItemForge.savePlayerPositionToItemStack(serverPlayer, usedItemStack);

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

            if (!blockPos.equals(serverPlayer.blockPosition())) {
                moveAndTakeExperience(serverLevel, serverPlayer, blockPos);
            }
        }
    }

    private static void savePlayerPositionToItemStack(@NotNull ServerPlayer player, @NotNull ItemStack stack) {

        BlockPos homePos = player.blockPosition();

        CompoundTag setHomePos = new CompoundTag();

        setHomePos.putInt("x", homePos.getX());
        setHomePos.putInt("y", homePos.getY());
        setHomePos.putInt("z", homePos.getZ());

        stack.addTagElement("HomePosition", setHomePos);
    }

    private static void teleportPlayerToHomePositionFromItemStack(@NotNull ServerLevel level, @NotNull ServerPlayer player, @NotNull CompoundTag tag) {

        final BlockPos blockPos = BlockPos.containing((double) tag.getInt("x"), (double) tag.getInt("y"), (double) tag.getInt("z"));

        if (!blockPos.equals(player.blockPosition())) {
            EnderPearlStaffItemForge.moveAndTakeExperience(level, player, blockPos);
        }
    }

    private static void moveAndTakeExperience(@NotNull ServerLevel level, @NotNull ServerPlayer player, @NotNull BlockPos pos) {
        player.setExperienceLevels(player.experienceLevel - 1);
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
        level.playLocalSound(pos, SoundEvents.PORTAL_TRAVEL, SoundSource.PLAYERS, 0.2f, 1.0f, false);
    }
}
