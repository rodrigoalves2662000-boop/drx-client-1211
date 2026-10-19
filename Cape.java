package pt.drx.client;

import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;

public enum Cape {
    NONE("Padrão", null),
    DRX("DRX Vermelha", "drx"),
    NEON("DRX Neon", "neon"),
    DARK("DRX Dark", "dark");

    public final String label;
    public final AssetInfo.TextureAsset asset;

    Cape(String label, String name) {
        this.label = label;
        this.asset = name == null ? null : new AssetInfo.TextureAssetInfo(
                Identifier.of("drxclient", "cape/" + name),
                Identifier.of("drxclient", "textures/cape/" + name + ".png"));
    }

    public static Cape current() {
        Cape[] v = values();
        return v[Math.floorMod(DrxConfig.I.cape, v.length)];
    }
}
