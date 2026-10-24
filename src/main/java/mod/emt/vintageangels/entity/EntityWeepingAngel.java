
package mod.emt.vintageangels.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityWeepingAngel extends EntityMob {
    private static final DataParameter<Byte> ANGRY = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> ARM_MOVEMENT = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BYTE);

    public EntityWeepingAngel(World world) {
        super(world);
        this.setSize(0.6F, 1.95F);
        this.isImmuneToFire = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ANGRY, (byte) 0);
        this.dataManager.register(ARM_MOVEMENT, (byte) 0);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
    }

    public boolean getAngry() {
        return this.dataManager.get(ANGRY) == 1;
    }

    public void setAngry(boolean angry) {
        this.dataManager.set(ANGRY, (byte) (angry ? 1 : 0));
    }

    public boolean getArmMovement() {
        return this.dataManager.get(ARM_MOVEMENT) == 1;
    }

    public void setArmMovement(boolean moving) {
        this.dataManager.set(
                ARM_MOVEMENT, (byte) (moving ? 1 : 0)
        );
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.BLOCK_STONE_HIT;
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }
}
