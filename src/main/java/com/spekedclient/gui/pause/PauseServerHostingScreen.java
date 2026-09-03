package com.spekedclient.gui.pause;

import com.spekedclient.SpekedClient;
import com.spekedclient.hosting.ServerHostingManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class PauseServerHostingScreen extends Screen {
    private final Screen parent;

    public PauseServerHostingScreen(Screen parent) {
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
            int color = switch (server.getStatus()) {
                case ONLINE -> 0xFF00FF00;
                case OFFLINE -> 0xFFFF0000;
                case STARTING -> 0xFFFFFF00;
                case STOPPING -> 0xFFFFFF00;
                case CRASHED -> 0xFFFF0000;
            };
            drawString(context, this.textRenderer, "Status: " + server.getStatus().getDisplayName(), 20, y + 10, color);
            y += 25;
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
