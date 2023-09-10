package com.atikinbtw.chatOnLostFocus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatOnLostFocus implements ClientModInitializer {

    private static boolean chatOnLostFocusEnabled = true;

    public static boolean isChatOnLostFocusEnabled() {
        return chatOnLostFocusEnabled;
    }

    @Override
    public void onInitializeClient() {
        Logger logger = Logger.getLogger("ChatOnLostFocus");
        logger.log(Level.INFO, "ChatOnLostFocus initialized!");

        KeyBinding toggleBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.chatonlostfocus.onOffBind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "category.chatonlostfocus.chatonlostfocus"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleBind.wasPressed()) {
                chatOnLostFocusEnabled = !chatOnLostFocusEnabled;
                sendActionBarMessage(chatOnLostFocusEnabled);
            }
        });
    }

    private void sendActionBarMessage(boolean isEnabled) {
        Text message = Text.translatable(isEnabled ? "overlay.chatonlostfocus.on" : "overlay.chatonlostfocus.off");

        MinecraftClient.getInstance().inGameHud.setOverlayMessage(
                message,
                true
        );
    }
}