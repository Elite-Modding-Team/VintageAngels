
package mod.emt.vintageangels.entity;

import mod.emt.vintageangels.config.VAConfig;
import mod.emt.vintageangels.init.VASoundEvents;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityWeepingAngel extends EntityMob {
    private static final DataParameter<Byte> ANGRY = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> ARM_MOVEMENT = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BYTE);

    private int spawnTimer = 5;
    private int attackCooldown;
    private int armMovementCooldown;

    public EntityWeepingAngel(World world) {
        super(world);
        this.setSize(0.6F, 1.95F);
        this.isImmuneToFire = true;
        this.stepHeight = (float) VAConfig.WEEPING_ANGEL.stepHeight;
        this.setPathPriority(PathNodeType.WATER, 8.0F);
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
        this.tasks.addTask(2, new AIAngelStalk());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(VAConfig.WEEPING_ANGEL.armor);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(VAConfig.WEEPING_ANGEL.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(VAConfig.WEEPING_ANGEL.followRange);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(VAConfig.WEEPING_ANGEL.knockbackResistance);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(VAConfig.WEEPING_ANGEL.maxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(VAConfig.WEEPING_ANGEL.movementSpeed);
        this.getEntityAttribute(SWIM_SPEED).setBaseValue(VAConfig.WEEPING_ANGEL.swimSpeed);
    }

    @Override
    protected EntityBodyHelper createBodyHelper() {
        return new EntityBodyHelper(this) {
            @Override
            public void updateRenderAngles() {
                EntityWeepingAngel entity = EntityWeepingAngel.this;
                entity.renderYawOffset = entity.rotationYaw;
                entity.prevRenderYawOffset = entity.rotationYaw;
                entity.rotationYawHead = entity.rotationYaw;
                entity.prevRotationYawHead = entity.rotationYaw;
            }
        };
    }

    @Override
    public boolean attackEntityAsMob(@NotNull Entity entity) {
        if (!super.attackEntityAsMob(entity)) {
            return false;
        }

        if (this.isEntityAlive() && entity instanceof EntityLivingBase) {
            double teleportChance = VAConfig.WEEPING_ANGEL.teleportChanceTarget;
            if (VAConfig.WEEPING_ANGEL.jumpscareSound && this.rand.nextFloat() <= (float) Math.min(VAConfig.WEEPING_ANGEL.jumpscareSoundChance, 1.0F)) {
                this.playJumpscareSound();
            }
            if (this.rand.nextFloat() <= (float) Math.min(teleportChance, 1.0F)) {
                teleportEntity(entity.world, entity, true, (float) VAConfig.WEEPING_ANGEL.teleportRangeTarget);
            }
        }

        return true;
    }

    @Override
    public boolean attackEntityFrom(@NotNull DamageSource source, float amount) {
        // Pickaxes (or other similar tools) will deal normal damage, otherwise damage dealt is reduced to only 25%
        if (source.getImmediateSource() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) source.getImmediateSource();
            ItemStack heldItem = entity.getHeldItemMainhand();

            // TODO: Add a config list for additional classes that can work here
            // Surprisingly some mods don't extend ItemPickaxe...
            if (heldItem.getItem().canHarvestBlock(Blocks.STONE.getDefaultState())) {
                return super.attackEntityFrom(source, amount);
            }
        }

        return super.attackEntityFrom(source, amount * 0.25F);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote && spawnTimer > 0) {
            spawnTimer--;
        }
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
        this.dataManager.set(ARM_MOVEMENT, (byte) (moving ? 1 : 0));
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }


    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return VASoundEvents.ENTITY_WEEPING_ANGEL_CRUMBLE.getSoundEvent();
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull Block block) {
    }

    private void playJumpscareSound() {
        this.world.playSound(null, this.getPosition(), SoundEvents.ENTITY_GHAST_HURT, this.getSoundCategory(), 2.0F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
    }

    private void playStalkingSound() {
        this.world.playSound(null, this.getPosition(), VASoundEvents.ENTITY_WEEPING_ANGEL_MOVE.getSoundEvent(), this.getSoundCategory(), 0.5F, 1.0F);
    }

    @Override
    public boolean isPotionApplicable(@NotNull PotionEffect effect) {
        return false;
    }

    public static void teleportEntity(@NotNull World world, @NotNull Entity entity, boolean dropToGround, float range) {
        if (entity instanceof FakePlayer) {
            return;
        }

        double origX = entity.posX, origY = MathHelper.clamp(entity.posY, 1, 255), origZ = entity.posZ;

        for (int i = 0; i < 15; i++) {
            double targetX = origX + world.rand.nextGaussian() * range;
            double targetY = -1;

            while (targetY < 1.1) {
                targetY = origY + world.rand.nextGaussian() * (range / 2);
            }

            double targetZ = origZ + world.rand.nextGaussian() * range;

            if (dropToGround) {
                targetY = MathHelper.floor(targetY) + .05;
                while (targetY >= 2f && !(hasGround(world, targetX, targetY, targetZ) && isClear(world, entity, targetX, targetY, targetZ))) {
                    targetY -= 1f;
                }
            }

            if (targetY >= 2f && isClear(world, entity, targetX, targetY, targetZ) && doTeleport(world, entity, targetX, targetY, targetZ)) {
                world.playSound(null, origX, origY, origZ, VASoundEvents.ENTITY_WEEPING_ANGEL_TELEPORT_ACTIVATE.getSoundEvent(), SoundCategory.NEUTRAL, 0.6F, 1.0F);
                world.playSound(null, targetX, targetY, targetZ, VASoundEvents.ENTITY_WEEPING_ANGEL_TELEPORT_ACTIVATE.getSoundEvent(), SoundCategory.NEUTRAL, 0.6F, 1.0F);
                world.playSound(null, origX, origY, origZ, VASoundEvents.ENTITY_WEEPING_ANGEL_TELEPORT_ACTIVATE.getSoundEvent(), SoundCategory.NEUTRAL, 0.8F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                world.playSound(null, targetX, targetY, targetZ, VASoundEvents.ENTITY_WEEPING_ANGEL_TELEPORT_ACTIVATE.getSoundEvent(), SoundCategory.NEUTRAL, 0.8F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
                entity.timeUntilPortal = 5;
                return;
            }
        }
    }

    private static boolean isClear(@NotNull World world, @NotNull Entity entity, double targetX, double targetY, double targetZ) {
        double origX = entity.posX, origY = entity.posY, origZ = entity.posZ;
        try {
            entity.setPosition(targetX, targetY, targetZ);
            return world.checkNoEntityCollision(entity.getEntityBoundingBox(), entity) && world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(entity.getEntityBoundingBox());
        } finally {
            entity.setPosition(origX, origY, origZ);
        }
    }

    private static boolean hasGround(@NotNull World world, double targetX, double targetY, double targetZ) {
        int xInt = MathHelper.floor(targetX);
        int yInt = MathHelper.floor(targetY);
        int zInt = MathHelper.floor(targetZ);
        return yInt > 1 && world.getBlockState(new BlockPos(xInt, yInt - 1, zInt)).getMaterial().blocksMovement();
    }


    private static boolean doTeleport(@NotNull World world, @NotNull Entity entity, double targetX, double targetY, double targetZ) {
        if (entity.isRiding()) {
            entity.dismountRidingEntity();
        }

        if (entity.isBeingRidden()) {
            entity.removePassengers();
        }

        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) entity;
            player.connection.setPlayerLocation(targetX, targetY, targetZ, entity.rotationYaw, entity.rotationPitch);
        } else {
            entity.setPositionAndRotation(targetX, targetY, targetZ, entity.rotationYaw, entity.rotationPitch);
        }

        // TODO: Refactor to packet
        if (FMLLaunchHandler.side().isClient() && entity instanceof EntityPlayer) {
            Minecraft.getMinecraft().player.timeInPortal = 1.0F;
        }

        return true;
    }

    private boolean isInFieldOfVision(EntityLivingBase player, float yawLimit, float pitchLimit) {
        double dx = this.posX - player.posX;
        double dz = this.posZ - player.posZ;
        double targetY = this.posY + this.getEyeHeight();
        double playerY = player.posY + player.getEyeHeight();
        double dy = targetY - playerY;
        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);
        float targetYaw = (float) (Math.atan2(dz, dx) * (180.0D / Math.PI) - 90.0D);
        float targetPitch = (float) (-Math.atan2(dy, horizontalDistance) * (180.0D / Math.PI));
        float yawDifference = MathHelper.wrapDegrees(targetYaw - player.rotationYaw);
        float pitchDifference = MathHelper.wrapDegrees(targetPitch - player.rotationPitch);
        return Math.abs(yawDifference) <= yawLimit && Math.abs(pitchDifference) <= pitchLimit;
    }

    private boolean isAngelInDarkness() {
        BlockPos pos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        return this.world.getLight(pos) < 1;
    }

    private boolean canAngelSeePlayer(EntityLivingBase player) {
        if (player == null || !player.isEntityAlive()) {
            return false;
        }

        Vec3d start = new Vec3d(this.posX, this.posY + this.getEyeHeight(), this.posZ);
        Vec3d end = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        return this.world.rayTraceBlocks(start, end, false, true, false) == null;
    }

    private boolean canAngelBeSeen(EntityPlayer player) {
        if (player == null || isAngelInDarkness()) {
            return false;
        }

        if (!canAngelSeePlayer(player)) {
            return false;
        }

        return isInFieldOfVision(player, 70.0F, 65.0F);
    }

    private boolean canAngelBeSeenMultiplayer() {
        if (isAngelInDarkness()) {
            return false;
        }

        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(VAConfig.WEEPING_ANGEL.scanRadiusX, VAConfig.WEEPING_ANGEL.scanRadiusY, VAConfig.WEEPING_ANGEL.scanRadiusZ));

        for (EntityPlayer player : players) {
            if (!player.isSpectator() && canAngelBeSeen(player)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPlayerWatchingAngel(EntityPlayer player) {
        if (player == null || !canAngelSeePlayer(player)) {
            return false;
        }

        Vec3d look = player.getLookVec();
        Vec3d direction = new Vec3d(posX - player.posX, posY + getEyeHeight() - (player.posY + player.getEyeHeight()), posZ - player.posZ);

        if (direction.lengthSquared() < 1.0E-6D) {
            return true;
        }

        direction = direction.normalize();
        return look.dotProduct(direction) > 0.7071D;
    }

    private boolean isValidStalkTarget(EntityPlayer player) {
        return player != null && player.isEntityAlive() && !player.isSpectator() && !player.isCreative() && getDistanceSq(player) <= VAConfig.WEEPING_ANGEL.scanRadiusX * VAConfig.WEEPING_ANGEL.scanRadiusZ;
    }


    private boolean canNavigateToTarget(EntityPlayer player) {
        if (player == null || !player.isEntityAlive()) {
            return false;
        }

        Path path = getNavigator().getPathToEntityLiving(player);

        if (path == null || path.getFinalPathPoint() == null) {
            return false;
        }

        PathPoint end = path.getFinalPathPoint();
        BlockPos targetPos = player.getPosition();

        int dx = end.x - targetPos.getX();
        int dy = end.y - targetPos.getY();
        int dz = end.z - targetPos.getZ();

        return dx * dx + dz * dz <= 4 && Math.abs(dy) <= 2;
    }

    private class AIAngelStalk extends EntityAIBase {
        private EntityPlayer target;
        private boolean startedMoving;
        private int unreachableTicks;

        public AIAngelStalk() {
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            if (spawnTimer > 0) {
                return false;
            }

            if (isValidStalkTarget(target) && (canAngelSeePlayer(target) || canNavigateToTarget(target))) {
                return true;
            }

            target = null;

            if (getAttackTarget() instanceof EntityPlayer) {
                EntityPlayer current = (EntityPlayer) getAttackTarget();
                if (isValidStalkTarget(current) && (canAngelSeePlayer(current) || canNavigateToTarget(current))) {
                    target = current;
                    return true;
                }
            }

            setAttackTarget(null);

            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(VAConfig.WEEPING_ANGEL.scanRadiusX, VAConfig.WEEPING_ANGEL.scanRadiusY, VAConfig.WEEPING_ANGEL.scanRadiusZ));
            EntityPlayer closest = null;
            double closestDistance = Double.MAX_VALUE;

            for (EntityPlayer player : players) {
                if (!isValidStalkTarget(player)) {
                    continue;
                }

                if (!canAngelSeePlayer(player) && !canNavigateToTarget(player)) {
                    continue;
                }

                if (isPlayerWatchingAngel(player)) {
                    continue;
                }

                double distance = getDistanceSq(player);

                if (distance < closestDistance) {
                    closest = player;
                    closestDistance = distance;
                }
            }

            if (closest != null) {
                target = closest;
                setAttackTarget(closest);
                return true;
            }

            return false;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return isValidStalkTarget(target);
        }

        @Override
        public void startExecuting() {
            attackCooldown = 0;
            unreachableTicks = 0;
        }

        @Override
        public void resetTask() {
            target = null;
            unreachableTicks = 0;
            getNavigator().clearPath();
            setAttackTarget(null);
            setAngry(false);
            setArmMovement(false);
            startedMoving = false;
        }

        @Override
        public void updateTask() {
            if (!isValidStalkTarget(target)) {
                getNavigator().clearPath();
                setAttackTarget(null);
                target = null;
                setAngry(false);
                setArmMovement(false);
                startedMoving = false;
                return;
            }

            double distance = getDistanceSq(target);
            boolean canSeeTarget = canAngelSeePlayer(target);
            boolean canReachTarget = canNavigateToTarget(target);

            if (!canSeeTarget && !canReachTarget) {
                unreachableTicks++;
                if (unreachableTicks >= 100) {
                    getNavigator().clearPath();
                    motionX = 0.0D;
                    motionZ = 0.0D;
                    target = null;
                    setAttackTarget(null);
                }
                return;
            }

            unreachableTicks = 0;
            faceEntity(target, 30.0F, 30.0F);

            boolean watched = canAngelBeSeenMultiplayer();

            if (watched) {
                getNavigator().clearPath();
                motionX = 0.0D;
                motionZ = 0.0D;
                startedMoving = false;
                return;
            }

            setAngry(distance <= 25.0D);

            if (!startedMoving) {
                playStalkingSound();
                startedMoving = true;
            }

            getNavigator().tryMoveToEntityLiving(target, 1.0D);

            if (distance > 25.0D && --armMovementCooldown <= 0) {
                setArmMovement(!getArmMovement());
                armMovementCooldown = 10 + rand.nextInt(15);
            }

            if (distance <= 4.0D) {
                if (attackCooldown <= 0) {
                    if (attackEntityAsMob(target)) {
                        attackCooldown = 20;
                    }
                } else {
                    attackCooldown--;
                }
            }
        }
    }
}
