package com.atikinbtw.chatOnLostFocus.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.atikinbtw.chatOnLostFocus.ChatOnLostFocus;

@Mixin(MinecraftClient.class)
public abstract class modifyFocusLossBehavior {

    @Inject(method = "onWindowFocusChanged", at = @At("HEAD"))
    private void onWindowFocusChanged(CallbackInfo info) {
        MinecraftClient mc = MinecraftClient.getInstance();

        // First to prevent minecraft from crashing on start!
        if (mc.world == null) {
            return;
        }

        if (!mc.options.pauseOnLostFocus && mc.currentScreen == null && mc.isWindowFocused() && ChatOnLostFocus.isChatOnLostFocusEnabled()) {
            mc.setScreen(new ChatScreen(""));
        }
    }
}