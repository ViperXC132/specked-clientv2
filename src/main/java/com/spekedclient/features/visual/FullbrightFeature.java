package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class FullbrightFeature extends Feature {
    private float originalGamma = 0;

    public FullbrightFeature() {
        super("Fullbright", "Full brightness everywhere", FeatureCategory.VISUAL);
    }

    @Override
    public void onEnable() {
        if (mc != null) {
            originalGamma = mc.options.gamma.getValue().floatValue();
            mc.options.gamma.setValue(16.0);
        }
    }

    @Override
    public void onDisable() {
        if (mc != null) {
            mc.options.gamma.setValue((double) originalGamma);
        }
    }

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
