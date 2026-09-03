package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class MemoryCleanerFeature extends Feature {
    private long lastClean = System.currentTimeMillis();

    public MemoryCleanerFeature() {
        super("Memory Cleaner", "Clean memory leaks", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        long now = System.currentTimeMillis();
        if (now - lastClean > 10000) { // Clean every 10 seconds
            System.gc();
            lastClean = now;
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
