package mekanism.common.tile;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.api.TileNetworkList;
import mekanism.api.energy.IStrictEnergyStorage;
import mekanism.api.providers.IBlockProvider;
import mekanism.common.block.basic.BlockInductionCell;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.tier.InductionCellTier;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class TileEntityInductionCell extends TileEntityMekanism implements IStrictEnergyStorage {

    public InductionCellTier tier;

    public TileEntityInductionCell(IBlockProvider blockProvider) {
        super(blockProvider);
    }

    @Override
    protected void setSupportedTypes(Block block) {
        super.setSupportedTypes(block);
        //TODO: Do this in a better way, but currently we need to hijack this to set our tier earlier
        this.tier = ((BlockInductionCell) block).getTier();
    }

    public double electricityStored;

    @Override
    public void onUpdate() {
    }

    @Override
    public void handlePacketData(PacketBuffer dataStream) {
        super.handlePacketData(dataStream);
        if (isRemote()) {
            electricityStored = dataStream.readDouble();
        }
    }

    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(electricityStored);
        return data;
    }

    @Override
    public void read(CompoundNBT nbtTags) {
        super.read(nbtTags);
        electricityStored = nbtTags.getDouble("electricityStored");
    }

    @Nonnull
    @Override
    public CompoundNBT write(CompoundNBT nbtTags) {
        super.write(nbtTags);
        nbtTags.putDouble("electricityStored", electricityStored);
        return nbtTags;
    }

    @Override
    public double getEnergy() {
        return electricityStored;
    }

    @Override
    public void setEnergy(double energy) {
        electricityStored = Math.min(energy, getMaxEnergy());
    }

    @Override
    public double getMaxEnergy() {
        return tier.getMaxEnergy();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
        if (capability == Capabilities.ENERGY_STORAGE_CAPABILITY) {
            return Capabilities.ENERGY_STORAGE_CAPABILITY.orEmpty(capability, LazyOptional.of(() -> this));
        }
        return super.getCapability(capability, side);
    }
}
