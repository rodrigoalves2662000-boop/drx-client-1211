package pt.drx.client.mixin;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pt.drx.client.DrxConfig;

@Mixin(ParticleManager.class)
public abstract class ParticleMixin {
    @Unique private int drx$counter = 0;

    // Deixa passar só 1 em cada 3 partículas
    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)Lnet/minecraft/client/particle/Particle;",
            at = @At("HEAD"), cancellable = true)
    private void drx$limit(ParticleEffect effect, double x, double y, double z, double vx, double vy, double vz,
                           CallbackInfoReturnable<Particle> cir) {
        if (DrxConfig.I.reduceParticles && (drx$counter++ % 3) != 0) cir.setReturnValue(null);
    }
}
