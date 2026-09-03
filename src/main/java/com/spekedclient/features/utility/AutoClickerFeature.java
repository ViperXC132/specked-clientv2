package com.spekedclient.features.utility;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class AutoClickerFeature extends Feature {
    private long lastClick = 0;
    private int clickDelay = 50;

    public AutoClickerFeature() {
        super("Auto Clicker", "Automatic clicking", FeatureCategory.UTILITY);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null) return;
        long now = System.currentTimeMillis();
        if (now - lastClick > clickDelay && mc.options.attackKey.isPressed()) {
            lastClick = now;
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
