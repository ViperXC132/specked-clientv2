package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class BhopFeature extends Feature {
    private boolean lastGrounded = false;

    public BhopFeature() {
        super("Bunny Hop", "Automatic bunny hopping", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        if (mc.player.isOnGround() && !lastGrounded) {
            if (mc.options.forwardKey.isPressed()) {
                mc.player.jump();
                mc.player.setSprinting(true);
            }
        }
        lastGrounded = mc.player.isOnGround();
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
