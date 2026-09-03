package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class AutoReconnectFeature extends Feature {
    private long disconnectTime = 0;

    public AutoReconnectFeature() {
        super("Auto Reconnect", "Automatically rejoin", FeatureCategory.UTILITY);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null) return;
        if (mc.getNetworkHandler() == null && disconnectTime > 0) {
            long now = System.currentTimeMillis();
            if (now - disconnectTime > 5000) {
                disconnectTime = 0;
            }
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
