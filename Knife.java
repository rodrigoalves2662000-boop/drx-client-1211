package pt.drx.client;

import net.minecraft.util.Identifier;

public enum Knife {
    NONE("Desligado", null),
    BAYONET("Bayonet", "bayonet"),
    KARAMBIT("Karambit", "karambit"),
    BUTTERFLY("Butterfly", "butterfly");

    public final String label;
    public final Identifier itemModel; // definido em assets/drxclient/items/<nome>.json

    Knife(String label, String path) {
        this.label = label;
        this.itemModel = path == null ? null : Identifier.of("drxclient", path);
    }

    public static Knife current() {
        Knife[] v = values();
        return v[Math.floorMod(DrxConfig.I.knife, v.length)];
    }
}
