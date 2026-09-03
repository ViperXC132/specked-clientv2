package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class XrayFeature extends Feature {
    public XrayFeature() {
        super("X-Ray", "See through blocks", FeatureCategory.VISUAL);
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
