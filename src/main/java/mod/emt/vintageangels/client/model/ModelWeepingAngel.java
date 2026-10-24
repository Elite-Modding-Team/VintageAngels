package mod.emt.vintageangels.client.model;

import mod.emt.vintageangels.entity.EntityWeepingAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

@SideOnly(Side.CLIENT)
public class ModelWeepingAngel extends ModelBase {
    ModelRenderer leftfoot;
    ModelRenderer rightfoot;
    ModelRenderer leftwing1;
    ModelRenderer leftwing2;
    ModelRenderer leftwing3;
    ModelRenderer leftwing4;
    ModelRenderer rightwing1;
    ModelRenderer rightwing2;
    ModelRenderer rightwing3;
    ModelRenderer rightwing4;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    private float angleX;
    private float angleY;
    private float angleZ;

    public ModelWeepingAngel() {
        this.textureHeight = 32;
        this.textureWidth = 64;
        leftfoot = new ModelRenderer(this, 32, 0);
        leftfoot.addBox(-2F, 7F, -4F, 6, 5, 8);
        leftfoot.setRotationPoint(2.0F, 12F, 0.0F);
        leftfoot.rotateAngleX = 0.0F;
        leftfoot.rotateAngleY = 0.0F;
        leftfoot.rotateAngleZ = 0.0F;
        leftfoot.mirror = false;
        rightfoot = new ModelRenderer(this, 32, 0);
        rightfoot.addBox(-4F, 7F, -4F, 6, 5, 8);
        rightfoot.setRotationPoint(-2F, 12F, 0.0F);
        rightfoot.rotateAngleX = 0.0F;
        rightfoot.rotateAngleY = 0.0F;
        rightfoot.rotateAngleZ = 0.0F;
        rightfoot.mirror = false;
        leftwing1 = new ModelRenderer(this, 40, 25);
        leftwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        leftwing1.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing1.rotateAngleX = 0.20944F;
        leftwing1.rotateAngleY = 0.61087F;
        leftwing1.rotateAngleZ = 0.0F;
        leftwing1.mirror = false;
        leftwing2 = new ModelRenderer(this, 46, 19);
        leftwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        leftwing2.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing2.rotateAngleX = 0.20944F;
        leftwing2.rotateAngleY = 0.61087F;
        leftwing2.rotateAngleZ = 0.01745F;
        leftwing2.mirror = false;
        leftwing3 = new ModelRenderer(this, 58, 12);
        leftwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        leftwing3.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing3.rotateAngleX = 0.20944F;
        leftwing3.rotateAngleY = 0.61087F;
        leftwing3.rotateAngleZ = 0.0F;
        leftwing3.mirror = false;
        leftwing4 = new ModelRenderer(this, 52, 16);
        leftwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        leftwing4.setRotationPoint(1.0F, 1.0F, 1.0F);
        leftwing4.rotateAngleX = 0.20944F;
        leftwing4.rotateAngleY = 0.61087F;
        leftwing4.rotateAngleZ = 0.0F;
        leftwing4.mirror = false;
        rightwing1 = new ModelRenderer(this, 40, 25);
        rightwing1.addBox(-0.5F, -1F, 1.0F, 1, 5, 2);
        rightwing1.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing1.rotateAngleX = 0.20944F;
        rightwing1.rotateAngleY = -0.61087F;
        rightwing1.rotateAngleZ = 0.0F;
        rightwing1.mirror = false;
        rightwing2 = new ModelRenderer(this, 46, 19);
        rightwing2.addBox(-0.5F, -2F, 3F, 1, 11, 2);
        rightwing2.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing2.rotateAngleX = 0.20944F;
        rightwing2.rotateAngleY = -0.61087F;
        rightwing2.rotateAngleZ = 0.0F;
        rightwing2.mirror = false;
        rightwing3 = new ModelRenderer(this, 58, 12);
        rightwing3.addBox(-0.5F, -2F, 5F, 1, 18, 2);
        rightwing3.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing3.rotateAngleX = 0.20944F;
        rightwing3.rotateAngleY = -0.61087F;
        rightwing3.rotateAngleZ = 0.0F;
        rightwing3.mirror = false;
        rightwing4 = new ModelRenderer(this, 52, 16);
        rightwing4.addBox(-0.5F, 0.0F, 7F, 1, 14, 2);
        rightwing4.setRotationPoint(-2F, 1.0F, 1.0F);
        rightwing4.rotateAngleX = 0.20944F;
        rightwing4.rotateAngleY = -0.61087F;
        rightwing4.rotateAngleZ = 0.0F;
        rightwing4.mirror = false;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.rotateAngleX = 0.24435F;
        head.rotateAngleY = 0.0F;
        head.rotateAngleZ = 0.0F;
        head.mirror = false;
        body = new ModelRenderer(this, 0, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        rightarm = new ModelRenderer(this, 24, 19);
        rightarm.addBox(-3F, 0.0F, -2F, 4, 9, 4);
        rightarm.setRotationPoint(-5F, 0.0F, 0.0F);
        rightarm.rotateAngleX = -1.74533F;
        rightarm.rotateAngleY = -0.55851F;
        rightarm.rotateAngleZ = 0.0F;
        rightarm.mirror = false;
        leftarm = new ModelRenderer(this, 24, 19);
        leftarm.addBox(-1F, 0.0F, -2F, 4, 9, 4);
        leftarm.setRotationPoint(5F, 0.0F, 0.0F);
        leftarm.rotateAngleX = -1.74533F;
        leftarm.rotateAngleY = 0.55851F;
        leftarm.rotateAngleZ = 0.0F;
        leftarm.mirror = false;
        rightleg = new ModelRenderer(this, 24, 19);
        rightleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        rightleg.setRotationPoint(-2F, 12F, 0.0F);
        rightleg.rotateAngleX = 0.0F;
        rightleg.rotateAngleY = 0.0F;
        rightleg.rotateAngleZ = 0.0F;
        rightleg.mirror = false;
        leftleg = new ModelRenderer(this, 24, 19);
        leftleg.addBox(-2F, 0.0F, -2F, 4, 9, 4);
        leftleg.setRotationPoint(2.0F, 12F, 0.0F);
        leftleg.rotateAngleX = 0.0F;
        leftleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleZ = 0.0F;
        leftleg.mirror = false;
    }

    public void render(@NotNull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        leftfoot.render(scale);
        rightfoot.render(scale);
        leftwing1.render(scale);
        leftwing2.render(scale);
        leftwing3.render(scale);
        leftwing4.render(scale);
        rightwing1.render(scale);
        rightwing2.render(scale);
        rightwing3.render(scale);
        rightwing4.render(scale);
        head.render(scale);
        body.render(scale);
        rightarm.render(scale);
        leftarm.render(scale);
        rightleg.render(scale);
        leftleg.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, @NotNull Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        if (entity instanceof EntityWeepingAngel) {
            EntityWeepingAngel angel = (EntityWeepingAngel) entity;

            if (angel.getAngry()) {
                angleX = toRadians(20F);
                angleY = toRadians(60F);
                angleZ = toRadians(5F);
            } else {
                angleX = toRadians(10F);
                angleY = toRadians(30F);
                angleZ = toRadians(5F);
            }

            if (angel.getAngry()) {
                float swing = this.swingProgress;
                float f6 = MathHelper.sin(swing * (float) Math.PI);
                float f7 = MathHelper.sin((1.0F - (1.0F - swing) * (1.0F - swing)) * (float) Math.PI);
                rightarm.rotateAngleZ = 0.0F;
                leftarm.rotateAngleZ = 0.0F;
                rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
                leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
                rightarm.rotateAngleX = -(float) Math.PI / 2.0F;
                leftarm.rotateAngleX = -(float) Math.PI / 2.0F;
            } else if (angel.getArmMovement()) {
                rightarm.rotateAngleX = -1.04533F;
                rightarm.rotateAngleY = -0.55851F;
                rightarm.rotateAngleZ = 0.0F;
                leftarm.rotateAngleX = -1.04533F;
                leftarm.rotateAngleY = 0.55851F;
                leftarm.rotateAngleZ = 0.0F;
            } else {
                rightarm.rotateAngleX = -1.74533F;
                rightarm.rotateAngleY = -0.55851F;
                rightarm.rotateAngleZ = 0.0F;
                leftarm.rotateAngleX = -1.74533F;
                leftarm.rotateAngleY = 0.55851F;
                leftarm.rotateAngleZ = 0.0F;
            }

            rightwing1.rotateAngleX = angleX;
            rightwing2.rotateAngleX = angleX;
            rightwing3.rotateAngleX = angleX;
            rightwing4.rotateAngleX = angleX;
            leftwing1.rotateAngleX = angleX;
            leftwing2.rotateAngleX = angleX;
            leftwing3.rotateAngleX = angleX;
            leftwing4.rotateAngleX = angleX;
            rightwing1.rotateAngleY = -angleY;
            rightwing2.rotateAngleY = -angleY;
            rightwing3.rotateAngleY = -angleY;
            rightwing4.rotateAngleY = -angleY;
            leftwing1.rotateAngleY = angleY;
            leftwing2.rotateAngleY = angleY;
            leftwing3.rotateAngleY = angleY;
            leftwing4.rotateAngleY = angleY;
            rightwing1.rotateAngleZ = angleZ;
            rightwing2.rotateAngleZ = angleZ;
            rightwing3.rotateAngleZ = angleZ;
            rightwing4.rotateAngleZ = angleZ;
            leftwing1.rotateAngleZ = -angleZ;
            leftwing2.rotateAngleZ = -angleZ;
            leftwing3.rotateAngleZ = -angleZ;
            leftwing4.rotateAngleZ = -angleZ;
        } else {
            rightarm.rotateAngleX = -1.74533F;
            rightarm.rotateAngleY = -0.55851F;
            rightarm.rotateAngleZ = 0.0F;
            leftarm.rotateAngleX = -1.74533F;
            leftarm.rotateAngleY = 0.55851F;
            leftarm.rotateAngleZ = 0.0F;
        }
    }

    private float toRadians(float f) {
        return f / 57.29578F;
    }

    private float toDegrees(float f) {
        return f * 57.29578F;
    }
}