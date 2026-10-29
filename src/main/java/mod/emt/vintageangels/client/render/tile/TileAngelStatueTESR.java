package mod.emt.vintageangels.client.render.tile;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.client.model.ModelWeepingAngel;
import mod.emt.vintageangels.tileentity.TileEntityAngelStatue;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class TileAngelStatueTESR extends TileEntitySpecialRenderer<TileEntityAngelStatue> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel.png");
    private static final ResourceLocation TEXTURE_XMAS = new ResourceLocation(VintageAngels.MOD_ID, "textures/entity/weeping_angel_xmas.png");

    private final ModelWeepingAngel model = new ModelWeepingAngel();

    private final boolean isChristmas;

    public TileAngelStatueTESR() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        this.isChristmas = month == 12 && day >= 24 && day <= 26; // December 24-26
    }

    @Override
    public void render(@NotNull TileEntityAngelStatue tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (tile == null) {
            return;
        }

        ResourceLocation statueTexture = this.isChristmas ? TEXTURE_XMAS : TEXTURE;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5D, y + 1.5D, z + 0.5D);
        GlStateManager.scale(1.0F, -1.0F, -1.0F);
        GlStateManager.rotate(tile.getRotation() * 22.5F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(statueTexture);
        this.model.renderStatue(0.0625F);
        GlStateManager.popMatrix();
    }
}
