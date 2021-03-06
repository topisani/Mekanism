package mekanism.common.inventory.container.tile;

import mekanism.common.registries.MekanismContainerTypes;
import mekanism.common.tile.TileEntityEnergyCube;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;

public class EnergyCubeContainer extends MekanismTileContainer<TileEntityEnergyCube> {

    public EnergyCubeContainer(int id, PlayerInventory inv, TileEntityEnergyCube tile) {
        super(MekanismContainerTypes.ENERGY_CUBE, id, inv, tile);
    }

    public EnergyCubeContainer(int id, PlayerInventory inv, PacketBuffer buf) {
        this(id, inv, getTileFromBuf(buf, TileEntityEnergyCube.class));
    }
}