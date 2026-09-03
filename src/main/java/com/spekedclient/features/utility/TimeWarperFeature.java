package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class TimeWarperFeature extends Feature {
    public TimeWarperFeature() {
        super("Time Warper", "Show time until sleep", FeatureCategory.UTILITY);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
