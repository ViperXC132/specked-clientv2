package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class FastRenderFeature extends Feature {
    public FastRenderFeature() { super("Fast Render", "Optimize render pipeline", FeatureCategory.PERFORMANCE); }
    @Override public void onEnable() { if (mc != null && mc.options != null) mc.options.getMaxFps().setValue(300); }
    @Override public void onDisable() {}
    @Override public void onTick() {}
    @Override public void onRender(DrawContext context, float tickDelta) {}
}
