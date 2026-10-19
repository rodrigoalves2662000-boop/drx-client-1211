package pt.drx.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class DrxConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("drxclient.json");
    public static DrxConfig I = new DrxConfig();

    public int knife = 0;              // índice em Knife
    public int cape = 1;               // índice em Cape (0 = desligada)
    public boolean reduceParticles = true;
    public boolean entityCull = true;
    public int cullDistance = 48;      // blocos

    public static void load() {
        try {
            if (Files.exists(FILE)) I = GSON.fromJson(Files.readString(FILE), DrxConfig.class);
        } catch (Exception e) { I = new DrxConfig(); }
    }

    public static void save() {
        try { Files.writeString(FILE, GSON.toJson(I)); } catch (Exception ignored) {}
    }
}
