package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.HitResult;

public class BlockOutlineFeature extends Feature {
    public BlockOutlineFeature() {
        super("Block Outline", "Highlight target block", FeatureCategory.VISUAL);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.crosshairTarget == null) return;
        if (mc.crosshairTarget.getType() == HitResult.Type.BLOCK) {
            context.fill(mc.getWindow().getWidth()/2 - 15, mc.getWindow().getHeight()/2 - 15, 
                        mc.getWindow().getWidth()/2 + 15, mc.getWindow().getHeight()/2 + 15, 0x5500FF00);
        }
    }
}
