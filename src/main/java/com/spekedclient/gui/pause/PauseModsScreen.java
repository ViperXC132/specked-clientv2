package com.spekedclient.gui.pause;

import com.spekedclient.SpekedClient;
import com.spekedclient.features.Feature;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PauseModsScreen extends Screen {
    private final Screen parent;
    private int scroll = 0;

    public PauseModsScreen(Screen parent) {
        super(Text.literal("Modules"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context);
        drawCenteredTextShadow(context, this.textRenderer, "Enabled Modules", this.width / 2, 20, 0xFFFFFF);

        int y = 50;
        for (Feature feature : SpekedClient.getInstance().getFeatureManager().getFeatures()) {
            if (feature.isEnabled()) {
                String status = "[ON]";
                drawString(context, this.textRenderer, "• " + feature.getName() + " " + status, 20, y, 0xFF00FF00);
                y += 12;
            }
        }
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldPause() {
        return true;
    }
}
