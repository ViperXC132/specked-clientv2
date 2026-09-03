package com.spekedclient.gui.pause;

import com.spekedclient.SpekedClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;

public class PauseMenuScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40;
    private static final int PANEL_COLOR = 0xD0101424;
    private static final int BORDER_COLOR = 0xFF1E2540;
    private static final int ACCENT_COLOR = 0xFF6D7CFF;
    private static final int TEXT_COLOR = 0xFFEAF0FF;

    public PauseMenuScreen() {
        super(Text.literal("Pause Menu"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        // Semi-transparent background
        context.fill(0, 0, this.width, this.height, 0x88000000);

        // Title and server info
        int centerX = this.width / 2;
        drawCenteredTextShadow(context, this.textRenderer, "GAME PAUSED", centerX, 60, TEXT_COLOR);

        // Server hosting info if active
        var hostingManager = SpekedClient.getInstance().getHostingManager();
        int activeServers = (int) hostingManager.getServers().stream()
            .filter(s -> s.getStatus() == com.spekedclient.hosting.ServerHostingManager.ServerStatus.ONLINE)
            .count();
        
        if (activeServers > 0) {
            drawCenteredTextShadow(context, this.textRenderer, activeServers + " server(s) running", centerX, 100, 0xFF00FF00);
        }

        // Buttons
        int buttonY = 140;
        int buttonX = centerX - BUTTON_WIDTH / 2;

        buttonY = drawButton(context, buttonX, buttonY, "Resume", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Server Hosting", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Settings", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Mods", mouseX, mouseY) + 10;
        drawButton(context, buttonX, buttonY, "Save & Quit", mouseX, mouseY);

        super.render(context, mouseX, mouseY, partialTick);
    }

    private int drawButton(DrawContext context, int x, int y, String label, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + BUTTON_WIDTH && mouseY >= y && mouseY <= y + BUTTON_HEIGHT;
        int bgColor = hovered ? 0x503B5BDB : 0x30101828;
        
        context.fill(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, bgColor);
        context.fill(x, y, x + BUTTON_WIDTH, y + 1, hovered ? ACCENT_COLOR : BORDER_COLOR);
        context.fill(x, y, x + 1, y + BUTTON_HEIGHT, hovered ? ACCENT_COLOR : BORDER_COLOR);

        drawCenteredTextShadow(context, this.textRenderer, label, x + BUTTON_WIDTH / 2, y + BUTTON_HEIGHT / 2 - 4, hovered ? TEXT_COLOR : 0xFF8892A8);

        return y + BUTTON_HEIGHT;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return false;

        int centerX = this.width / 2;
        int buttonY = 140;
        int buttonX = centerX - BUTTON_WIDTH / 2;

        // Resume
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) {
            this.client.setScreen(null);
            return true;
        }
        buttonY += BUTTON_HEIGHT + 10;
        
        // Server Hosting
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) {
            this.client.setScreen(new PauseServerHostingScreen(this));
            return true;
        }
        buttonY += BUTTON_HEIGHT + 10;
        
        // Settings
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) {
            this.client.setScreen(new OptionsScreen(this, this.client.options));
            return true;
        }
        buttonY += BUTTON_HEIGHT + 10;
        
        // Mods
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) {
            this.client.setScreen(new PauseModsScreen(this));
            return true;
        }
        buttonY += BUTTON_HEIGHT + 10;
        
        // Save & Quit
        if (isMouseOverButton((int) mouseX, (int) mouseY, buttonX, buttonY)) {
            this.client.world.close();
            this.client.disconnect();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean isMouseOverButton(int mouseX, int mouseY, int buttonX, int buttonY) {
        return mouseX >= buttonX && mouseX <= buttonX + BUTTON_WIDTH && 
               mouseY >= buttonY && mouseY <= buttonY + BUTTON_HEIGHT;
    }

    @Override
    public boolean shouldPause() {
        return true;
    }
}
