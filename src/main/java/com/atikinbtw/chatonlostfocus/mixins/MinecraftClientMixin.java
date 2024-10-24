package com.atikinbtw.chatonlostfocus.mixins;

import com.atikinbtw.chatonlostfocus.config.ConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Nullable public Screen currentScreen;
    @Shadow public abstract void setScreen(@Nullable Screen screen);

    @Shadow @Final public GameOptions options;

    @Inject(method = "onWindowFocusChanged", at = @At("HEAD"))
    private void onWindowFocusChanged(boolean focused, CallbackInfo ci) {
        if (!focused && ConfigScreen.config.enabled && this.currentScreen == null) {
            this.setScreen(new ChatScreen(ConfigScreen.config.textInChat));
            this.options.pauseOnLostFocus = false;
        }
    }
}