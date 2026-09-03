package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class BiomeHudFeature extends Feature {
    public BiomeHudFeature() {
        super("Biome Display", "Show current biome", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.world == null || mc.player == null) return;
        String biome = mc.world.getBiome(mc.player.getBlockPos()).value().getTranslationKey();
        context.drawText(mc.textRenderer, "Biome: " + biome, 10, 160, 0xFFFFFF, true);
    }
}
