package net.praqma.lifeofdev.git.mergeconflict.costStrategy

class ConstantMergeConflictCostStrategy implements MergeConflictCostStrategy {
    private int mergeCost

    ConstantMergeConflictCostStrategy(int mergeCost) {
        this.mergeCost = mergeCost
    }

    @Override
    int getCost() {
        return mergeCost
    }
}
