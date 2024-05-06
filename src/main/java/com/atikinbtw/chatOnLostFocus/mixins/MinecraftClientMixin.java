package com.atikinbtw.chatOnLostFocus.mixins;

import com.atikinbtw.chatOnLostFocus.config.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(method = "onWindowFocusChanged", at = @At("HEAD"))
    private void onWindowFocusChanged(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();

        // checked first to prevent crash
        if (mc.world == null) return;

        if (mc.currentScreen == null && mc.isWindowFocused() && Config.config.isChatOnLostFocusEnabled) {
            mc.options.pauseOnLostFocus = false;
            mc.setScreen(new ChatScreen(Config.config.textInChat));
        }
    }
}