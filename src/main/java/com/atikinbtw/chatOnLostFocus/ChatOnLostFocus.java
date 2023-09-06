package com.atikinbtw.chatOnLostFocus;

import net.fabricmc.api.ClientModInitializer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatOnLostFocus implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Logger logger = Logger.getLogger("ChatOnLostFocus");
        logger.log(Level.INFO, "ChatOnLostFocus initialized!");
    }
}