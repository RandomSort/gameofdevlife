package net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy

import net.praqma.lifeofdev.git.Repository

class InventoryMergeConflictProbabilityStrategy implements MergeConflictProbabilityStrategy {
    private Repository repository
    private int InventoryMultiplier
    Random random

    def InventoryMergeConflictProbabilityStrategy(Repository repository, int InventoryMultiplier) {

        this.InventoryMultiplier = InventoryMultiplier
        this.repository = repository

        this.random = new Random()
    }

    @Override
    boolean isMergeConflict() {


        // get random number from 0-99, if it's is strictly lower than the chance in percentage; there is a mergeconflict
        // (100 is not included.)
        int choice = random.nextInt(100)
        return choice < InventoryMultiplier*repository.inventoryValue

        return false
    }
}
