package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class SmartRenderFeature extends Feature {
    public SmartRenderFeature() { super("Smart Render", "Intelligent render distance", FeatureCategory.PERFORMANCE); }
    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onTick() {
        if (mc == null || mc.options == null || mc.world == null) return;
        if (mc.isWindowFocused()) mc.world.setSimulationDistance(Math.max(8, mc.world.getSimulationDistance()));
    }
    @Override public void onRender(DrawContext context, float tickDelta) {}
}
