package mekanism.common.capabilities.basic;

import mekanism.api.energy.IStrictEnergyAcceptor;
import mekanism.common.capabilities.basic.DefaultStorageHelper.DefaultStorage;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Created by ben on 30/04/16.
 */
public class DefaultStrictEnergyAcceptor implements IStrictEnergyAcceptor {

    public static void register() {
        CapabilityManager.INSTANCE.register(IStrictEnergyAcceptor.class, new DefaultStorage<>(), DefaultStrictEnergyAcceptor::new);
    }

    @Override
    public double acceptEnergy(Direction side, double amount, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canReceiveEnergy(Direction side) {
        return true;
    }
}