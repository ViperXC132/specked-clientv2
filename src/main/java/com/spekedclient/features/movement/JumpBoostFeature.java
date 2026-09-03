package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class JumpBoostFeature extends Feature {
    public JumpBoostFeature() {
        super("Jump Boost", "Enhanced jump tracking", FeatureCategory.MOVEMENT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.player == null) return;
        if (mc.options.jumpKey.isPressed() && mc.player.isOnGround()) {
            mc.player.setVelocity(mc.player.getVelocity().x, 0.6, mc.player.getVelocity().z);
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
