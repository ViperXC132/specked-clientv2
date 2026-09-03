package com.spekedclient.gui.mainmenu;

import com.spekedclient.SpekedClient;
import com.spekedclient.hosting.ServerHostingManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ServerHostingScreen extends Screen {
    private final Screen parent;

    public ServerHostingScreen(Screen parent) {
        super(Text.literal("Server Hosting"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context);
        drawCenteredTextShadow(context, this.textRenderer, "Server Hosting", this.width / 2, 20, 0xFFFFFF);

        ServerHostingManager manager = SpekedClient.getInstance().getHostingManager();
        int y = 50;
        for (ServerHostingManager.ManagedServer server : manager.getServers()) {
            drawString(context, this.textRenderer, server.getName(), 20, y, 0xFFFFFF);
            drawString(context, this.textRenderer, "Status: " + server.getStatus().getDisplayName(), 20, y + 10, 0xFF00FF00);
            y += 25;
        }

        if (this.shouldShowPauseMenu() && this.client.player != null) {
            this.client.setScreen(this.parent);
        }
    }

    private boolean shouldShowPauseMenu() {
        return this.client == null || !this.client.isRunning();
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
