package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class PotionHudFeature extends Feature {
    public PotionHudFeature() {
        super("Potion HUD", "Show active effects", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.player == null) return;
        int y = 10;
        for (var effect : mc.player.getStatusEffects()) {
            String text = effect.getEffectType().getName().getString() + " " + (effect.getAmplifier() + 1);
            context.drawText(mc.textRenderer, text, 10, this.mc.getWindow().getHeight() - 50 - y, 0xFFFFFF, true);
            y += 12;
        }
    }
}
