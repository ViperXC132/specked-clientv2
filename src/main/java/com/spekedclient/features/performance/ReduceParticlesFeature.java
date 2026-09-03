package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.particle.ParticleTextureSheet;

public class ReduceParticlesFeature extends Feature {
    public ReduceParticlesFeature() {
        super("Reduce Particles", "Minimize particles", FeatureCategory.PERFORMANCE);
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
