package mekanism.common.inventory.slot;

import java.util.function.Predicate;
import mekanism.api.annotations.NonNull;
import mekanism.api.inventory.IMekanismInventory;
import mekanism.common.item.ItemCraftingFormula;
import net.minecraft.item.ItemStack;

public class FormulaInventorySlot extends BasicInventorySlot {

    private static final Predicate<@NonNull ItemStack> validator = stack -> stack.getItem() instanceof ItemCraftingFormula;

    public static FormulaInventorySlot at(IMekanismInventory inventory, int x, int y) {
        return new FormulaInventorySlot(inventory, x, y);
    }

    private FormulaInventorySlot(IMekanismInventory inventory, int x, int y) {
        super(manualOnly, alwaysTrueBi, validator, inventory, x, y);
    }
}