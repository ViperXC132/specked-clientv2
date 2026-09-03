package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class FpsDisplayFeature extends Feature {
    public FpsDisplayFeature() {
        super("FPS Display", "Show frames per second", FeatureCategory.HUD);
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
        String text = "FPS: " + mc.getCurrentFps();
        context.drawText(mc.textRenderer, text, 10, 10, 0xFFFFFF, true);
    }
}
