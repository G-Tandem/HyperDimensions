package com.elisis.att.init;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class KeyInputHandler {
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		
		if (Keybinds.hello.isPressed()) {
			
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Hello!"));
		}
	}
}
