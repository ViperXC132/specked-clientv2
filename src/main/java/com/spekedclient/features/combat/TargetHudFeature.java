package com.spekedclient.features.combat;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.LivingEntity;

public class TargetHudFeature extends Feature {
    public TargetHudFeature() {
        super("Target HUD", "Display current target info", FeatureCategory.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.targetedEntity == null) return;
        if (mc.targetedEntity instanceof LivingEntity entity) {
            String name = entity.getName().getString();
            float health = entity.getHealth();
            String text = name + " HP: " + (int)health;
            context.drawText(mc.textRenderer, text, 10, 115, 0xFFFFFF, true);
        }
    }
}
