package pt.drx.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class DrxClient implements ClientModInitializer {
    public static KeyBinding MENU_KEY;

    @Override
    public void onInitializeClient() {
        DrxConfig.load();

        KeyBinding.Category category = KeyBinding.Category.create(Identifier.of("drxclient", "main"));
        MENU_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.drxclient.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, category));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (MENU_KEY.wasPressed()) mc.setScreen(new DrxScreen());
        });
    }
}
