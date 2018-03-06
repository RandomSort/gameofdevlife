package net.praqma.lifeofdev.git.mergeconflict.costStrategy

import net.praqma.lifeofdev.git.Repository

class InventoryMergeConflictCostStrategy implements MergeConflictCostStrategy {
    private Repository repository
    private double inventoryCostMultiplier

    def InventoryMergeConflictCostStrategy(Repository repository, Double InventoryCostMultiplier) {

        this.inventoryCostMultiplier = InventoryCostMultiplier
        this.repository = repository
    }

    @Override
    int getCost() {

        Double rawCost = repository.inventoryValue * inventoryCostMultiplier

        // midway long-placeholder for readable code.
        Long l = Math.round(rawCost)

        return l.intValue()
    }
}
