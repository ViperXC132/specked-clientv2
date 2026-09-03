package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class StepHeightFeature extends Feature {
    public StepHeightFeature() {
        super("Step Height", "Adjust step height", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc != null && mc.player != null) {
            mc.player.stepHeight = 1.5f;
        }
    }

    @Override
    public void onDisable() {
        if (mc != null && mc.player != null) {
            mc.player.stepHeight = 0.6f;
        }
    }

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
