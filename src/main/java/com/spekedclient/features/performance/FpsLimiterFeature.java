package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class FpsLimiterFeature extends Feature {
    private int fpsLimit = 60;

    public FpsLimiterFeature() {
        super("FPS Limiter", "Cap FPS at custom value", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {
        if (mc != null) {
            mc.options.maxFps.setValue(fpsLimit);
        }
    }

    @Override
    public void onDisable() {
        if (mc != null) {
            mc.options.maxFps.setValue(260);
        }
    }

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
