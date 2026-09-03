package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Direction;

public class DirectionFeature extends Feature {
    public DirectionFeature() {
        super("Direction", "Show facing direction", FeatureCategory.MOVEMENT);
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
        
        int rotation = (int) ((mc.player.getYaw() + 180) % 360);
        String direction = getDirection(rotation);
        String text = "Dir: " + direction + " (" + rotation + "°)";
        context.drawText(mc.textRenderer, text, 10, 70, 0xFFFFFF, true);
    }

    private String getDirection(int rotation) {
        if (rotation < 45 || rotation >= 315) return "S";
        if (rotation < 135) return "W";
        if (rotation < 225) return "N";
        return "E";
    }
}
