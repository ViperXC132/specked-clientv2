package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.SwordItem;

public class AutoToolFeature extends Feature {
    public AutoToolFeature() {
        super("Auto Tool", "Automatically switch tools", FeatureCategory.UTILITY);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null || mc.crosshairTarget == null) return;
        
        for (int i = 0; i < 9; i++) {
            var item = mc.player.getInventory().getStack(i).getItem();
            if (item instanceof ToolItem || item instanceof SwordItem) {
                mc.player.getInventory().selectedSlot = i;
                break;
            }
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
