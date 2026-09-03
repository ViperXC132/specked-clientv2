package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class NoHurtCamFeature extends Feature {
    public NoHurtCamFeature() {
        super("No Hurt Cam", "Disable hurt camera shake", FeatureCategory.VISUAL);
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
