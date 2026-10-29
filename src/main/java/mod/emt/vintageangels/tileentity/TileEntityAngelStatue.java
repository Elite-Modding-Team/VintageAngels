package mod.emt.vintageangels.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.jetbrains.annotations.NotNull;

public class TileEntityAngelStatue extends TileEntity {
    private int rotation;
    private int statueType;

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation & 15;
        this.markDirty();

        if (world != null && !world.isRemote) {
            IBlockState state = this.world.getBlockState(pos);
            this.world.notifyBlockUpdate(pos, state, state, 3);
        }
    }

    public void setInitialRotation(int rotation) {
        this.rotation = rotation & 15;
        this.markDirty();
    }

    public int getStatueType() {
        return this.statueType;
    }

    public void setStatueType(int statueType) {
        this.statueType = statueType;
        this.markDirty();
    }

    @Override
    public @NotNull NBTTagCompound writeToNBT(@NotNull NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Rotation", rotation);
        compound.setInteger("StatueType", statueType);
        return compound;
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.rotation = compound.getInteger("Rotation");
        this.statueType = compound.getInteger("StatueType");
    }

    @Override
    public @NotNull NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void handleUpdateTag(@NotNull NBTTagCompound tag) {
        this.readFromNBT(tag);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
    }

    @Override
    public void onDataPacket(@NotNull NetworkManager net, SPacketUpdateTileEntity packet) {
        this.handleUpdateTag(packet.getNbtCompound());
    }
}
