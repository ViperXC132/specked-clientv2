package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class CriticalHitsFeature extends Feature {
    public CriticalHitsFeature() {
        super("Critical Hits", "Show critical hit indicator", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.player == null) return;
        if (!mc.player.isOnGround() && mc.player.getVelocity().y < 0) {
            String text = "CRITICAL!";
            int x = mc.getWindow().getWidth() / 2 - mc.textRenderer.getWidth(text) / 2;
            int y = mc.getWindow().getHeight() / 2 - 50;
            context.drawText(mc.textRenderer, text, x, y, 0xFFFF00FF, true);
        }
    }
}
