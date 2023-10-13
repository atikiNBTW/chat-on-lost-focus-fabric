package com.atikinbtw.chatOnLostFocus.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

@Config(name = "chatonlostfocus")
public class ClothConfigClass implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static ClothConfigClass config;

    public static void init() {
        AutoConfig.register(ClothConfigClass.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ClothConfigClass.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip
    public String textInChat = "";

    public boolean isChatOnLostFocusEnabled = true;
}