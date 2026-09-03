package com.spekedclient.features.movement;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class SafeWalkFeature extends Feature {
    public SafeWalkFeature() { super("Safe Walk", "Prevent edge walking", FeatureCategory.MOVEMENT); }
    @Override public void onEnable() {}
    @Override public void onDisable() {}
    @Override public void onTick() {
        if (mc == null || mc.player == null || mc.world == null) return;
        if (mc.player.isOnGround()) {
            var blockBelow = mc.world.getBlockState(mc.player.getBlockPos().down());
            if (blockBelow.isReplaceable()) mc.player.setOnGround(true);
        }
    }
    @Override public void onRender(DrawContext context, float tickDelta) {}
}
