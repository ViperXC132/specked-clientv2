package com.spekedclient;

import com.spekedclient.account.AccountManager;
import com.spekedclient.config.ConfigManager;
import com.spekedclient.crosshair.CrosshairManager;
import com.spekedclient.features.FeatureManager;
import com.spekedclient.hosting.ServerHostingManager;
import com.spekedclient.hud.HudManager;
import com.spekedclient.input.KeybindManager;
import com.spekedclient.notification.NotificationManager;
import com.spekedclient.settings.SettingsManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpekedClient implements ClientModInitializer {
    public static final String MOD_ID = "spekedclient";
    public static final String VERSION = "2.0.0";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static SpekedClient INSTANCE;
    
    private ConfigManager configManager;
    private SettingsManager settingsManager;
    private FeatureManager featureManager;
    private KeybindManager keybindManager;
    private AccountManager accountManager;
    private HudManager hudManager;
    private CrosshairManager crosshairManager;
    private NotificationManager notificationManager;
    private ServerHostingManager hostingManager;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;
        LOGGER.info("Initializing Speked Client {}", VERSION);
        
        try {
            // Initialize core systems in order
            this.configManager = new ConfigManager();
            this.settingsManager = new SettingsManager();
            this.featureManager = new FeatureManager();
            this.keybindManager = new KeybindManager();
            this.accountManager = new AccountManager();
            this.hudManager = new HudManager();
            this.crosshairManager = new CrosshairManager();
            this.notificationManager = new NotificationManager();
            this.hostingManager = new ServerHostingManager();
            
            // Load all configurations
            configManager.loadAll();
            settingsManager.load();
            featureManager.initialize();
            accountManager.load();
            hudManager.load();
            crosshairManager.load();
            hostingManager.load();
            
            LOGGER.info("Speked Client loaded successfully");
            
            // Register client tick event
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                if (client != null && client.player != null) {
                    featureManager.tick();
                    notificationManager.tick();
                    hostingManager.tick();
                }
            });
            
            // Register HUD render event
            HudRenderCallback.EVENT.register((graphics, tickCounter) -> {
                hudManager.render(graphics, tickCounter.getTickProgress(false));
                crosshairManager.render(graphics);
                notificationManager.render(graphics);
            });
            
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Speked Client", e);
        }
    }
    
    public static SpekedClient getInstance() {
        return INSTANCE;
    }
    
    public ConfigManager getConfigManager() { return configManager; }
    public SettingsManager getSettingsManager() { return settingsManager; }
    public FeatureManager getFeatureManager() { return featureManager; }
    public KeybindManager getKeybindManager() { return keybindManager; }
    public AccountManager getAccountManager() { return accountManager; }
    public HudManager getHudManager() { return hudManager; }
    public CrosshairManager getCrosshairManager() { return crosshairManager; }
    public NotificationManager getNotificationManager() { return notificationManager; }
    public ServerHostingManager getHostingManager() { return hostingManager; }
}
