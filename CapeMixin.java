package pt.drx.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.Cape;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class CapeMixin {
    // Troca só a capa do teu jogador (corpo/elytra/modelo ficam iguais)
    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void drx$cape(CallbackInfoReturnable<SkinTextures> cir) {
        Cape cape = Cape.current();
        if (cape.asset == null) return;
        if ((Object) this != MinecraftClient.getInstance().player) return;
        SkinTextures o = cir.getReturnValue();
        if (o == null) return;
        cir.setReturnValue(new SkinTextures(o.body(), cape.asset, o.elytra(), o.model(), o.secure()));
    }
}
