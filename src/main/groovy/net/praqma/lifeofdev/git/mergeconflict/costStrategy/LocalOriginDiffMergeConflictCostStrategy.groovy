package net.praqma.lifeofdev.git.mergeconflict.costStrategy

import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.git.Repository

class LocalOriginDiffMergeConflictCostStrategy implements MergeConflictCostStrategy {

    private double costMultiplier
    Repository repository

    def LocalOriginDiffMergeConflictCostStrategy(Repository repository, Double costMultiplier) {

        this.costMultiplier = costMultiplier
        this.repository = repository
    }

    @Override
    int getCost() {

        int valueDiffOnOrigin = 0

        if(repository.ORIGINvalue < GitGame.gameState.ORIGIN.getValue()){
            valueDiffOnOrigin = GitGame.gameState.ORIGIN.getValue() - repository.ORIGINvalue
        }

        Double rawCost = costMultiplier * (repository.inventoryValue + valueDiffOnOrigin)


        // midway long-placeholder for readable code.
        Long l = Math.round(rawCost)

        return l.intValue()
    }
}

