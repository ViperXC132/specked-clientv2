package com.spekedclient.features;

import com.spekedclient.features.combat.*;
import com.spekedclient.features.movement.*;
import com.spekedclient.features.visual.*;
import com.spekedclient.features.hud.*;
import com.spekedclient.features.performance.*;
import com.spekedclient.features.utility.AutoClickerFeature;
import com.spekedclient.features.utility.AutoReconnectFeature;
import com.spekedclient.features.utility.AutoToolFeature;
import com.spekedclient.features.utility.AntiAFKFeature;
import com.spekedclient.features.utility.BetterChatFeature;
import com.spekedclient.features.utility.SayFeature;
import com.spekedclient.features.utility.ScreenshotManagerFeature;
import com.spekedclient.features.utility.TimeWarperFeature;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureManager {
    private final List<Feature> features = new ArrayList<>();

    public void initialize() {
        // Register all combat features
        register(new CpsCounterFeature());
        register(new HitColorFeature());
        register(new AttackIndicatorFeature());
        register(new ComboCounterFeature());
        register(new ReachDisplayFeature());
        register(new BlockHitFeature());
        register(new HitParticlesFeature());
        register(new CriticalHitsFeature());
        register(new TargetHudFeature());
        register(new CooldownIndicatorFeature());

        // Register all movement features
        register(new ToggleSprintFeature());
        register(new ToggleSneakFeature());
        register(new SpeedDisplayFeature());
        register(new DirectionFeature());
        register(new JumpBoostFeature());
        register(new StepHeightFeature());
        register(new VelocityFeature());
        register(new SafeWalkFeature());
        register(new AirStrafingFeature());
        register(new BhopFeature());

        // Register all visual features
        register(new FullbrightFeature());
        register(new ZoomFeature());
        register(new FovCustomizerFeature());
        register(new CustomCrosshairFeature());
        register(new XrayFeature());
        register(new NoHurtCamFeature());
        register(new NametabFeature());
        register(new BlockOutlineFeature());
        register(new NightVisionFeature());
        register(new SkyFeature());

        // Register all HUD features
        register(new FpsDisplayFeature());
        register(new PingDisplayFeature());
        register(new CoordinatesFeature());
        register(new ArmorHudFeature());
        register(new PotionHudFeature());
        register(new KeystrokesFeature());
        register(new MemoryHudFeature());
        register(new TimeHudFeature());
        register(new UptimeHudFeature());
        register(new ServerHudFeature());
        register(new BiomeHudFeature());
        register(new EnchantHudFeature());

        // Register all performance features
        register(new DynamicFpsFeature());
        register(new EntityCullingFeature());
        register(new ReduceParticlesFeature());
        register(new FpsLimiterFeature());
        register(new ChunkPreloadFeature());
        register(new LagReducerFeature());
        register(new FastRenderFeature());
        register(new MemoryCleanerFeature());
        register(new SmartRenderFeature());

        // Register utility features
        register(new BetterChatFeature());
        register(new ScreenshotManagerFeature());
        register(new AutoToolFeature());
        register(new AutoClickerFeature());
        register(new AntiAFKFeature());
        register(new AutoReconnectFeature());
        register(new SayFeature());
        register(new TimeWarperFeature());
    }

    public void register(Feature feature) {
        features.add(feature);
    }

    public void tick() {
        for (Feature feature : features) {
            if (feature.isEnabled()) {
                try {
                    feature.onTick();
                } catch (Exception e) {
                    feature.setEnabled(false);
                }
            }
        }
    }

    public void render(DrawContext context, float tickDelta) {
        for (Feature feature : features) {
            if (feature.isEnabled()) {
                try {
                    feature.onRender(context, tickDelta);
                } catch (Exception e) {
                    feature.setEnabled(false);
                }
            }
        }
    }

    public List<Feature> getFeatures() {
        return new ArrayList<>(features);
    }

    public List<Feature> getFeaturesByCategory(Feature.FeatureCategory category) {
        return features.stream()
            .filter(f -> f.getCategory() == category)
            .collect(Collectors.toList());
    }

    public Feature getFeature(String name) {
        return features.stream()
            .filter(f -> f.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
}
