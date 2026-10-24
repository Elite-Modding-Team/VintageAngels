package mod.emt.vintageangels.client.render;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.client.model.ModelWeepingAngel;
import mod.emt.vintageangels.entity.EntityWeepingAngel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RenderWeepingAngel extends RenderLiving<EntityWeepingAngel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel_angry.png");

    public RenderWeepingAngel(RenderManager renderManager) {
        super(renderManager, new ModelWeepingAngel(), 0.5F);
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(@NotNull EntityWeepingAngel entity) {
        if (entity.getAngry()) {
            return TEXTURE_ANGRY;
        }

        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityWeepingAngel> {
        @Override
        public Render<? super EntityWeepingAngel> createRenderFor(RenderManager manager) {
            return new RenderWeepingAngel(manager);
        }
    }
}
