package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class DynamicFpsFeature extends Feature {
    public DynamicFpsFeature() {
        super("Dynamic FPS", "Adjust FPS dynamically", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null) return;
        
        if (mc.isWindowFocused()) {
            mc.options.maxFps.setValue(240);
        } else {
            mc.options.maxFps.setValue(30);
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
