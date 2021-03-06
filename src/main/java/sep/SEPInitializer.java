package sep;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import sep.config.SEPConfig;
import sep.config.screen.SEPConfigGui;
import sep.config.screen.SEPConfigScreen;

@Environment(EnvType.CLIENT)
public class SEPInitializer implements ClientModInitializer {
    private static KeyBinding configKeyBinding;

    @Override
    public void onInitializeClient() {
        SEPConfig.init();
        registerKeyBind();

    }

    private void registerKeyBind(){
        configKeyBinding = new KeyBinding("key.sep.config", InputUtil.Type.KEYSYM, 0, "category.sep");
        KeyBindingHelper.registerKeyBinding(configKeyBinding);
        ClientTickEvents.START_CLIENT_TICK.register(client ->{
            if(configKeyBinding.wasPressed()) {
                    MinecraftClient.getInstance().openScreen(new SEPConfigScreen(new SEPConfigGui()));
            }
        });
    }

}