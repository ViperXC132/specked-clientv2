package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ReachDisplayFeature extends Feature {
    public ReachDisplayFeature() {
        super("Reach Display", "Show attack reach", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.crosshairTarget == null) return;
        double reach = mc.player.distanceTo(mc.crosshairTarget.getPos());
        String text = String.format("Reach: %.2f", reach);
        context.drawText(mc.textRenderer, text, 10, 85, 0xFFFFFF, true);
    }
}
