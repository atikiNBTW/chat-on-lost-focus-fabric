package com.atikinbtw.chatOnLostFocus.mixins;

import com.atikinbtw.chatOnLostFocus.config.ClothConfigClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class modifyFocusLossBehavior {

    @Inject(method = "onWindowFocusChanged", at = @At("HEAD"))
    private void onWindowFocusChanged(CallbackInfo info) {
        final MinecraftClient mc = MinecraftClient.getInstance();

        // Set to first to prevent minecraft from crashing on start!
        if (mc.world == null) {
            return;
        }

        if (mc.currentScreen == null && mc.isWindowFocused() && ClothConfigClass.config.isChatOnLostFocusEnabled) {
            mc.options.pauseOnLostFocus = false;
            mc.setScreen(new ChatScreen(ClothConfigClass.config.textInChat));
        }
    }
}