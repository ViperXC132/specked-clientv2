package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class StepHeightFeature extends Feature {
    public StepHeightFeature() { super("Step Height", "Adjust step height", FeatureCategory.MOVEMENT); }
    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onTick() {}
    @Override public void onRender(DrawContext context, float tickDelta) {}
}
