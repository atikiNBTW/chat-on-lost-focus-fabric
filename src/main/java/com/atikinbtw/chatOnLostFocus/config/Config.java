package com.atikinbtw.chatOnLostFocus.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

@me.shedaniel.autoconfig.annotation.Config(name = "chatonlostfocus")
public class Config implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static Config config;

    public static void init() {
        AutoConfig.register(Config.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip
    public String textInChat = "paused";

    public boolean isChatOnLostFocusEnabled = true;
}