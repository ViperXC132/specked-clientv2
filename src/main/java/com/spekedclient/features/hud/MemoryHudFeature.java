package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class MemoryHudFeature extends Feature {
    public MemoryHudFeature() {
        super("Memory Display", "Show RAM usage", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled) return;
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        String text = "Memory: " + usedMemory + "MB / " + totalMemory + "MB";
        context.drawText(mc.textRenderer, text, 10, 85, 0xFFFFFF, true);
    }
}
