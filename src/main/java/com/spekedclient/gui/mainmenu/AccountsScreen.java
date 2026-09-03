package com.spekedclient.gui.mainmenu;

import com.spekedclient.SpekedClient;
import com.spekedclient.account.Account;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class AccountsScreen extends Screen {
    private final Screen parent;

    public AccountsScreen(Screen parent) {
        super(Text.literal("Accounts"));
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(context);
        drawCenteredTextShadow(context, this.textRenderer, "Account Manager", this.width / 2, 20, 0xFFFFFF);

        int y = 50;
        for (Account account : SpekedClient.getInstance().getAccountManager().getAccounts()) {
            String active = account.isActive() ? " [ACTIVE]" : "";
            drawString(context, this.textRenderer, account.getUsername() + " (" + account.getType().getDisplayName() + ")" + active, 20, y, 0xFFFFFF);
            y += 12;
        }
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
