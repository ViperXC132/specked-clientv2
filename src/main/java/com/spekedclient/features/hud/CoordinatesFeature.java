package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class CoordinatesFeature extends Feature {
    public CoordinatesFeature() {
        super("Coordinates", "Show player position", FeatureCategory.HUD);
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
        int x = (int) mc.player.getX();
        int y = (int) mc.player.getY();
        int z = (int) mc.player.getZ();
        String text = "Pos: " + x + " " + y + " " + z;
        context.drawText(mc.textRenderer, text, 10, 40, 0xFFFFFF, true);
    }
}
