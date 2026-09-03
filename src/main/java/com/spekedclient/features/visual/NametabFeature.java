package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

public class NametabFeature extends Feature {
    public NametabFeature() {
        super("Name Tag", "Better name tag display", FeatureCategory.VISUAL);
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
        for (var entity : mc.world.getEntities()) {
            if (entity instanceof PlayerEntity && entity != mc.player) {
                float dist = mc.player.distanceTo(entity);
                if (dist < 50) {
                    String name = entity.getName().getString();
                    int y = 10;
                    context.drawText(mc.textRenderer, name, 10, y, 0xFFFFFF, true);
                    y += 12;
                }
            }
        }
    }
}
