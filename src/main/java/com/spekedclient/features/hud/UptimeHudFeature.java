package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class UptimeHudFeature extends Feature {
    private long startTime = System.currentTimeMillis();

    public UptimeHudFeature() {
        super("Uptime Display", "Show play session time", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled) return;
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        long hours = elapsed / 3600;
        long minutes = (elapsed % 3600) / 60;
        long seconds = elapsed % 60;
        String text = String.format("Uptime: %02d:%02d:%02d", hours, minutes, seconds);
        context.drawText(mc.textRenderer, text, 10, 190, 0xFFFFFF, true);
    }
}
