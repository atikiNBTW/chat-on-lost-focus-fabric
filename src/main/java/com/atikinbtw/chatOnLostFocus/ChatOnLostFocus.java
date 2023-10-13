package com.atikinbtw.chatOnLostFocus;

import com.atikinbtw.chatOnLostFocus.config.ClothConfigClass;
import me.shedaniel.autoconfig.AutoConfig;
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
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        ClothConfigClass.init();
        Logger logger = Logger.getLogger("ChatOnLostFocus");
        logger.log(Level.INFO, "ChatOnLostFocus initialized!");

        KeyBinding toggleChatOnLostFocusBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.chatonlostfocus.onOffBind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F24, "category.chatonlostfocus.chatonlostfocus"));
        KeyBinding openConfigScreenBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "keybind.chatonlostfocus.openConfigScreenBind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "category.chatonlostfocus.chatonlostfocus"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleChatOnLostFocusBind.wasPressed()) {
                ClothConfigClass.config.isChatOnLostFocusEnabled = !ClothConfigClass.config.isChatOnLostFocusEnabled;
                sendActionBarMessage();
            }

            while (openConfigScreenBind.wasPressed()) {
                mc.setScreen(AutoConfig.getConfigScreen(ClothConfigClass.class, mc.currentScreen).get());
            }
        });
    }

    private void sendActionBarMessage() {
        Text message = Text.translatable(ClothConfigClass.config.isChatOnLostFocusEnabled ? "overlay.chatonlostfocus.on" : "overlay.chatonlostfocus.off");

        MinecraftClient.getInstance().inGameHud.setOverlayMessage(
                message,
                true
        );
    }
}