
package mod.emt.vintageangels.block;

import mod.emt.vintageangels.VintageAngels;
import mod.emt.vintageangels.init.VASoundEvents;
import mod.emt.vintageangels.tileentity.TileEntityAngelStatue;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class BlockAngelStatue extends Block {
    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);

    public enum Half implements IStringSerializable {
        LOWER, UPPER;

        @Override
        public @NotNull String getName() {
            return this.name().toLowerCase();
        }
    }

    public static final PropertyEnum<Half> HALF = PropertyEnum.create("half", Half.class);

    private boolean removingPair = false;

    public BlockAngelStatue() {
        super(Material.ROCK);
        this.setRegistryName(VintageAngels.MOD_ID, "angel_statue");
        this.setTranslationKey(Objects.requireNonNull(this.getRegistryName()).toString());
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(HALF, Half.LOWER));
    }

    @Override
    protected @NotNull BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, HALF);
    }

    @Override
    public @NotNull IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(HALF, meta == 1 ? Half.UPPER : Half.LOWER);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(HALF) == Half.UPPER ? 1 : 0;
    }

    @Override
    public boolean canPlaceBlockAt(World world, @NotNull BlockPos pos) {
        return world.isAirBlock(pos) && world.isAirBlock(pos.up()) && super.canPlaceBlockAt(world, pos);
    }

    @Override
    public void onBlockPlacedBy(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull EntityLivingBase placer, @NotNull ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        int rotation = Math.floorMod(Math.round(placer.rotationYaw * 16.0F / 360.0F) + 8, 16);
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityAngelStatue) {
            ((TileEntityAngelStatue) tile).setInitialRotation(rotation);
        }

        if (!world.isRemote && world.isAirBlock(pos.up())) {
            world.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, Half.UPPER), 3);
        }
    }

    @Override
    public boolean onBlockActivated(@NotNull World world, @NotNull BlockPos pos, @NotNull IBlockState state, EntityPlayer player, @NotNull EnumHand hand, @NotNull EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!player.getHeldItemMainhand().isEmpty() || !player.getHeldItemOffhand().isEmpty()) {
            return false;
        }

        BlockPos lowerPos = state.getValue(HALF) == Half.UPPER ? pos.down() : pos;
        TileEntity tile = world.getTileEntity(lowerPos);

        if (!(tile instanceof TileEntityAngelStatue)) {
            return false;
        }

        if (!world.isRemote) {
            TileEntityAngelStatue plinth = (TileEntityAngelStatue) tile;
            int direction = player.isSneaking() ? -1 : 1;
            plinth.setRotation(Math.floorMod(plinth.getRotation() + direction, 16));
            world.playSound(null, pos, VASoundEvents.ENTITY_WEEPING_ANGEL_MOVE.getSoundEvent(), SoundCategory.BLOCKS, 0.5F, 2.0F);
        }

        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return state.getValue(HALF) == Half.LOWER;
    }

    @Override
    public TileEntity createTileEntity(@NotNull World world, IBlockState state) {
        return state.getValue(HALF) == Half.LOWER ? new TileEntityAngelStatue() : null;
    }

    @Override
    public void breakBlock(World world, @NotNull BlockPos pos, @NotNull IBlockState state) {
        if (!world.isRemote && !removingPair) {
            removingPair = true;
            try {
                BlockPos otherPos = state.getValue(HALF) == Half.LOWER ? pos.up() : pos.down();
                if (world.getBlockState(otherPos).getBlock() == this) {
                    world.setBlockToAir(otherPos);
                }
            } finally {
                removingPair = false;
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean isOpaqueCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@NotNull IBlockState state) {
        return false;
    }

    @Override
    public @NotNull AxisAlignedBB getBoundingBox(@NotNull IBlockState state, @NotNull IBlockAccess source, @NotNull BlockPos pos) {
        return AABB;
    }

    @Override
    public @NotNull EnumBlockRenderType getRenderType(@NotNull IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
}
