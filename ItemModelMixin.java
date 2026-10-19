package pt.drx.client.mixin;

import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.HeldItemContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pt.drx.client.Knife;

@Mixin(ItemModelManager.class)
public abstract class ItemModelMixin {
    // Qualquer espada é desenhada com o modelo da faca escolhida (só visual, client-side)
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void drx$knife(ItemRenderState renderState, ItemStack stack, ItemDisplayContext displayContext,
                           @Nullable World world, @Nullable HeldItemContext heldItemContext, int seed,
                           CallbackInfo ci) {
        Knife k = Knife.current();
        if (k.itemModel == null || !stack.isIn(ItemTags.SWORDS)) return;
        ItemStack copy = stack.copy();
        copy.set(DataComponentTypes.ITEM_MODEL, k.itemModel);
        ((ItemModelManager) (Object) this).update(renderState, copy, displayContext, world, heldItemContext, seed);
        ci.cancel();
    }
}
