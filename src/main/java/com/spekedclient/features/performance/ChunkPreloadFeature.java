package com.spekedclient.features.performance;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ChunkPreloadFeature extends Feature {
    public ChunkPreloadFeature() {
        super("Chunk Preload", "Pre-load nearby chunks", FeatureCategory.PERFORMANCE);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {
        if (mc == null || mc.world == null || mc.player == null) return;
        var chunkPos = mc.player.getChunkPos();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                mc.world.getChunk(chunkPos.x + x, chunkPos.z + z);
            }
        }
    }

    @Override
    public void onRender(DrawContext context, float tickDelta) {}
}
