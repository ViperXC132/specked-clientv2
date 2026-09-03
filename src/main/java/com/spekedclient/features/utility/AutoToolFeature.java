package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;

public class AutoToolFeature extends Feature {
    public AutoToolFeature() { super("Auto Tool", "Automatically switch tools", FeatureCategory.UTILITY); }
    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onTick() {
        if (mc == null || mc.player == null || mc.crosshairTarget == null) return;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.isDamageable()) {
                mc.player.getInventory().setSelectedSlot(i);
                break;
            }
        }
    }
    @Override public void onRender(DrawContext context, float tickDelta) {}
}
