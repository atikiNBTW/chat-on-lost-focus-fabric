package com.atikinbtw.chatonlostfocus.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

@Config(name = "chatonlostfocus")
public class ConfigScreen implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static ConfigScreen config;

    public static void init() {
        AutoConfig.register(ConfigScreen.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigScreen.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip
    public String textInChat = "paused";

    public boolean enabled = true;
}