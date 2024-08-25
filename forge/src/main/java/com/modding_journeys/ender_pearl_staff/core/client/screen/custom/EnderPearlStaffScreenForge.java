package com.modding_journeys.ender_pearl_staff.core.client.screen.custom;

import com.modding_journeys.ender_pearl_staff.Constants;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class EnderPearlStaffScreenForge extends AbstractContainerScreen<EnderPearlStaffMenuForge> {

    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(Constants.MOD_ID,"textures/gui/ender_pearl_staff.png");

    public EnderPearlStaffScreenForge(EnderPearlStaffMenuForge pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();

        // move "Ender Pearl Staff" and "Inventory" labels off-screen an arbitrary distance
        this.titleLabelY = 10000;
        this.inventoryLabelY = 10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BG_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        pGuiGraphics.blit(BG_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (this.menu.uuidList.isEmpty()) {

            // Constants.debug("Failed to render UUIDs because the list is empty!");

            pGuiGraphics.drawString(Minecraft.getInstance().font, "X", x+5, y+5, 0xFFFF0000);

            // todo: fix this lol

            for (int i = 0; i < 50; i++) {
                this.menu.uuidList.add(UUID.randomUUID());
            }

            return;
        }

        final int beginTextX = x + 5;
        final int beginTextY = y + 5;
        final int lineIncrements = 10;

        final AtomicInteger counter = new AtomicInteger(0);
        this.menu.uuidList.forEach(uuid -> {

            pGuiGraphics.drawString(Minecraft.getInstance().font, uuid.toString(), beginTextX, beginTextY + (lineIncrements * counter.get()), 0xFFFFFFFF);

            counter.getAndAdd(1);
        });

    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
    }
}
