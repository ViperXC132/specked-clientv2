package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

public class SpeedDisplayFeature extends Feature {
    private double lastX, lastY, lastZ;

    public SpeedDisplayFeature() {
        super("Speed Display", "Show movement speed", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {
        if (mc != null && mc.player != null) {
            lastX = mc.player.getX();
            lastY = mc.player.getY();
            lastZ = mc.player.getZ();
        }
    }

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc == null || mc.player == null) return;
        
        double dx = mc.player.getX() - lastX;
        double dz = mc.player.getZ() - lastZ;
        double speed = MathHelper.sqrt((float) (dx * dx + dz * dz)) * 20; // multiply by 20 for ticks
        
        lastX = mc.player.getX();
        lastZ = mc.player.getZ();
        
        String text = String.format("Speed: %.2f", speed);
        context.drawText(mc.textRenderer, text, 10, 55, 0xFFFFFF, true);
    }
}
