package com.spekedclient.features.hud;

import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;

public class ServerHudFeature extends Feature {
    public ServerHudFeature() {
        super("Server Info", "Show server name/type", FeatureCategory.HUD);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void onTick() {}

    @Override
    public void onRender(DrawContext context, float tickDelta) {
        if (!enabled || mc.getNetworkHandler() == null) return;
        String serverName = mc.getNetworkHandler().getServerInfo() != null ? 
                           mc.getNetworkHandler().getServerInfo().name : "Singleplayer";
        context.drawText(mc.textRenderer, "Server: " + serverName, 10, 205, 0xFFFFFF, true);
    }
}
