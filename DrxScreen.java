package pt.drx.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class DrxScreen extends Screen {
    public DrxScreen() { super(Text.literal("DRX CLIENT")); }

    @Override
    protected void init() {
        int w = 200, x = this.width / 2 - w / 2, y = this.height / 4, step = 24;
        DrxConfig c = DrxConfig.I;

        addDrawableChild(ButtonWidget.builder(Text.literal("Faca: " + Knife.current().label), b -> {
            c.knife = (c.knife + 1) % Knife.values().length;
            b.setMessage(Text.literal("Faca: " + Knife.current().label));
        }).dimensions(x, y, w, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Capa: " + Cape.current().label), b -> {
            c.cape = (c.cape + 1) % Cape.values().length;
            b.setMessage(Text.literal("Capa: " + Cape.current().label));
        }).dimensions(x, y + step, w, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Reduzir partículas: " + onOff(c.reduceParticles)), b -> {
            c.reduceParticles = !c.reduceParticles;
            b.setMessage(Text.literal("Reduzir partículas: " + onOff(c.reduceParticles)));
        }).dimensions(x, y + step * 2, w, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Culling de entidades: " + onOff(c.entityCull)), b -> {
            c.entityCull = !c.entityCull;
            b.setMessage(Text.literal("Culling de entidades: " + onOff(c.entityCull)));
        }).dimensions(x, y + step * 3, w, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Distância culling: " + c.cullDistance), b -> {
            c.cullDistance = c.cullDistance >= 96 ? 16 : c.cullDistance + 16;
            b.setMessage(Text.literal("Distância culling: " + c.cullDistance));
        }).dimensions(x, y + step * 4, w, 20).build());

        addDrawableChild(ButtonWidget.builder(Text.literal("Fechar"), b -> close())
                .dimensions(x, y + step * 6, w, 20).build());
    }

    private static String onOff(boolean v) { return v ? "ON" : "OFF"; }

    @Override
    public void render(DrawContext ctx, int mx, int my, float delta) {
        super.render(ctx, mx, my, delta); // já desenha o fundo
        ctx.drawCenteredTextWithShadow(textRenderer, Text.literal("§c§lDRX CLIENT"), width / 2, height / 4 - 24, 0xFFFFFFFF);
    }

    @Override
    public void close() {
        DrxConfig.save();
        super.close();
    }
}
