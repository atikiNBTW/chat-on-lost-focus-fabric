package com.atikinbtw.chatonlostfocus;

import com.atikinbtw.chatonlostfocus.config.ConfigScreen;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.LoggerFactory;

public class ChatOnLostFocus implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ConfigScreen.init();
        KeyBinding toggleBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.chatonlostfocus.onOffBind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.chatonlostfocus.chatonlostfocus"));
        KeyBinding openConfigScreenBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.chatonlostfocus.openConfigScreenBind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "category.chatonlostfocus.chatonlostfocus"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleBind.wasPressed()) {
                ConfigScreen.config.enabled = !ConfigScreen.config.enabled;
                this.sendActionBarMessage();
            }

            if (openConfigScreenBind.wasPressed())
                MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(ConfigScreen.class, MinecraftClient.getInstance().currentScreen).get());
        });

        LoggerFactory.getLogger("chatonlostfocus").info("[Chat On Lost Focus] Initialized successfully!");
    }

    private void sendActionBarMessage() {
        Text message = Text.translatable(ConfigScreen.config.enabled ? "overlay.chatonlostfocus.on" : "overlay.chatonlostfocus.off");

        MinecraftClient.getInstance().inGameHud.setOverlayMessage(message, true);
    }
}