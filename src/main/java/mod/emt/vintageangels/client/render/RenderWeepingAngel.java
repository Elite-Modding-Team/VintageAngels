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

import java.util.Calendar;

public class RenderWeepingAngel extends RenderLiving<EntityWeepingAngel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel.png");
    private static final ResourceLocation TEXTURE_XMAS = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel_xmas.png");
    private static final ResourceLocation TEXTURE_ANGRY = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel_angry.png");
    private static final ResourceLocation TEXTURE_ANGRY_XMAS = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel_angry_xmas.png");

    private final boolean isChristmas;

    public RenderWeepingAngel(RenderManager renderManager) {
        super(renderManager, new ModelWeepingAngel(), 0.5F);
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        this.isChristmas = month == 12 && day >= 24 && day <= 26; // December 24-26
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(@NotNull EntityWeepingAngel entity) {
        if (entity.getAngry()) {
            return this.isChristmas ? TEXTURE_ANGRY_XMAS : TEXTURE_ANGRY;
        }

        return this.isChristmas ? TEXTURE_XMAS : TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityWeepingAngel> {
        @Override
        public Render<? super EntityWeepingAngel> createRenderFor(RenderManager manager) {
            return new RenderWeepingAngel(manager);
        }
    }
}
