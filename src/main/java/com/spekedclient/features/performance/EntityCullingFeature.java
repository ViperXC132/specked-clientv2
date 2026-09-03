package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class EntityCullingFeature extends Feature {
    public EntityCullingFeature() {
        super("Entity Culling", "Hide distant entities", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onTick() {
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {
    }
}
