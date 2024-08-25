package com.modding_journeys.ender_pearl_staff.core.item.custom;

import com.modding_journeys.ender_pearl_staff.Constants;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class EnderPearlStaffItemForge extends Item {

    public EnderPearlStaffItemForge() {
        super(new Properties().stacksTo(1).fireResistant());
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {

        Level level = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();
        ItemStack itemStack = context.getItemInHand();

        if (level.isClientSide() || player == null) {
            return InteractionResult.PASS;
        }

        Constants.LOG.info("Player attempted to open GUI!");

        return InteractionResult.PASS;
    }
}
