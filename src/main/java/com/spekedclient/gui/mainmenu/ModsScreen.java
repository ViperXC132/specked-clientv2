package com.spekedclient.gui.mainmenu;

import com.spekedclient.SpekedClient;
import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModsScreen extends Screen {
    private final Screen parent;
    public ModsScreen(Screen parent) { super(Text.literal("Mods & Features")); this.parent = parent; }
    @Override public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context, mouseX, mouseY, partialTick);
        context.drawCenteredTextWithShadow(this.textRenderer, "Modules", this.width / 2, 20, 0xFFFFFF);
        int y = 50;
        for (Feature feature : SpekedClient.getInstance().getFeatureManager().getFeatures()) {
            String status = feature.isEnabled() ? "ON" : "OFF";
            int color = feature.isEnabled() ? 0xFF00FF00 : 0xFFFFFF00;
            context.drawTextWithShadow(this.textRenderer, feature.getName() + ": " + status, 20, y, color);
            y += 12;
        }
    }
    @Override public void close() { this.client.setScreen(this.parent); }
    @Override public boolean shouldPause() { return false; }
}
