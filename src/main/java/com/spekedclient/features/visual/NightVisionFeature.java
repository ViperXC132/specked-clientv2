package com.spekedclient.features.visual;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;

public class NightVisionFeature extends Feature {
    public NightVisionFeature() {
        super("Night Vision", "Always see in dark", FeatureCategory.VISUAL);
    }

    @Override
    public void onEnable() {
        if (mc != null && mc.player != null) {
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 999999, 0, false, false));
        }
    }

    @Override
    public void onDisable() {
        if (mc != null && mc.player != null) {
            mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
        }
    }

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
