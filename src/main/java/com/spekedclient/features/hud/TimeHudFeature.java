package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class TimeHudFeature extends Feature {
    public TimeHudFeature() {
        super("Time Display", "Show in-game time", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.world == null) return;
        long time = mc.world.getTimeOfDay();
        int hours = (int)((time / 1000 + 6) % 24);
        int minutes = (int)((time % 1000) * 60 / 1000);
        String text = String.format("Time: %02d:%02d", hours, minutes);
        context.drawText(mc.textRenderer, text, 10, 175, 0xFFFFFF, true);
    }
}
