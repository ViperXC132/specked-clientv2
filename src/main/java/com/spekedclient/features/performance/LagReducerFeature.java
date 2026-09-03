package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class LagReducerFeature extends Feature {
    private int tickCounter = 0;

    public LagReducerFeature() {
        super("Lag Reducer", "Reduce server lag effects", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        tickCounter++;
        if (tickCounter % 10 == 0) {
            System.gc();
            tickCounter = 0;
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
