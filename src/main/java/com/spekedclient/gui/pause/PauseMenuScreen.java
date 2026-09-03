package com.spekedclient.gui.pause;

import com.spekedclient.SpekedClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;

public class PauseMenuScreen extends Screen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BORDER_COLOR = 0xFF1E2540;
    private static final int ACCENT_COLOR = 0xFF6D7CFF;
    private static final int TEXT_COLOR = 0xFFEAF0FF;
    public PauseMenuScreen() { super(Text.literal("Pause Menu")); }
    @Override protected void init() { super.init(); }
    @Override public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        context.fill(0, 0, this.width, this.height, 0x88000000);
        int centerX = this.width / 2;
        context.drawCenteredTextWithShadow(this.textRenderer, "GAME PAUSED", centerX, 60, TEXT_COLOR);
        var hostingManager = SpekedClient.getInstance().getHostingManager();
        int activeServers = (int) hostingManager.getServers().stream().filter(s -> s.getStatus() == com.spekedclient.hosting.ServerHostingManager.ServerStatus.ONLINE).count();
        if (activeServers > 0) context.drawCenteredTextWithShadow(this.textRenderer, activeServers + " server(s) running", centerX, 100, 0xFF00FF00);
        int buttonY = 140, buttonX = centerX - BUTTON_WIDTH / 2;
        buttonY = drawButton(context, buttonX, buttonY, "Resume", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Server Hosting", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Settings", mouseX, mouseY) + 10;
        buttonY = drawButton(context, buttonX, buttonY, "Mods", mouseX, mouseY) + 10;
        drawButton(context, buttonX, buttonY, "Save & Quit", mouseX, mouseY);
        super.render(context, mouseX, mouseY, partialTick);
    }
    private int drawButton(DrawContext context, int x, int y, String label, int mouseX, int mouseY) {
        boolean hovered = mouseX >= x && mouseX <= x + BUTTON_WIDTH && mouseY >= y && mouseY <= y + BUTTON_HEIGHT;
        context.fill(x, y, x + BUTTON_WIDTH, y + BUTTON_HEIGHT, hovered ? 0x503B5BDB : 0x30101828);
        context.fill(x, y, x + BUTTON_WIDTH, y + 1, hovered ? ACCENT_COLOR : BORDER_COLOR);
        context.fill(x, y, x + 1, y + BUTTON_HEIGHT, hovered ? ACCENT_COLOR : BORDER_COLOR);
        context.drawCenteredTextWithShadow(this.textRenderer, label, x + BUTTON_WIDTH / 2, y + BUTTON_HEIGHT / 2 - 4, hovered ? TEXT_COLOR : 0xFF8892A8);
        return y + BUTTON_HEIGHT;
    }
    @Override public boolean mouseClicked(Click click, boolean doubled) {
        if (click.button() != 0) return false;
        int centerX = this.width / 2, buttonY = 140, buttonX = centerX - BUTTON_WIDTH / 2;
        if (isMouseOverButton((int) click.x(), (int) click.y(), buttonX, buttonY)) { this.client.setScreen(null); return true; }
        buttonY += BUTTON_HEIGHT + 10;
        if (isMouseOverButton((int) click.x(), (int) click.y(), buttonX, buttonY)) { this.client.setScreen(new PauseServerHostingScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 10;
        if (isMouseOverButton((int) click.x(), (int) click.y(), buttonX, buttonY)) { this.client.setScreen(new OptionsScreen(this, this.client.options)); return true; }
        buttonY += BUTTON_HEIGHT + 10;
        if (isMouseOverButton((int) click.x(), (int) click.y(), buttonX, buttonY)) { this.client.setScreen(new PauseModsScreen(this)); return true; }
        buttonY += BUTTON_HEIGHT + 10;
        if (isMouseOverButton((int) click.x(), (int) click.y(), buttonX, buttonY)) {
            if (this.client.world != null) this.client.world.close();
            this.client.disconnect(Text.literal("Saved and quit"));
            return true;
        }
        return super.mouseClicked(click, doubled);
    }
    private boolean isMouseOverButton(int mouseX, int mouseY, int buttonX, int buttonY) { return mouseX >= buttonX && mouseX <= buttonX + BUTTON_WIDTH && mouseY >= buttonY && mouseY <= buttonY + BUTTON_HEIGHT; }
    @Override public boolean shouldPause() { return true; }
}
