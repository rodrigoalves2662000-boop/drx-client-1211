package pt.drx.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.DrxConfig;

@Mixin(EntityRenderManager.class)
public abstract class EntityCullMixin {
    // Não desenha entidades (exceto jogadores) para lá da distância configurada
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void drx$cull(Entity entity, Frustum frustum, double x, double y, double z,
                          CallbackInfoReturnable<Boolean> cir) {
        DrxConfig c = DrxConfig.I;
        if (!c.entityCull || entity instanceof PlayerEntity) return;
        PlayerEntity p = MinecraftClient.getInstance().player;
        if (p == null) return;
        double d = p.squaredDistanceTo(x, y, z);
        if (d > (double) c.cullDistance * c.cullDistance) cir.setReturnValue(false);
    }
}
