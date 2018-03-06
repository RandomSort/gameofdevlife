package net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy

import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.git.Repository

class LocalOriginDiffMergeConflictProbabilityStrategy implements MergeConflictProbabilityStrategy {
    private int valueMultiplier
    Repository repository
    Random random

    def LocalOriginDiffMergeConflictProbabilityStrategy(Repository repository, int valueMultiplier) {

        this.valueMultiplier = valueMultiplier
        this.repository = repository

        this.random = new Random()
    }

    @Override
    boolean isMergeConflict() {

        // get random number from 0-99, if it's is strictly lower than the chance in percentage; there is a mergeconflict
        // (100 is not included.)
        int choice = random.nextInt(100)

        int valueDiffOnOrigin = 0

        if(repository.ORIGINvalue < GitGame.gameState.ORIGIN.getValue()){
            valueDiffOnOrigin = GitGame.gameState.ORIGIN.getValue() - repository.ORIGINvalue
        }

        int chance = valueMultiplier * (repository.inventoryValue + valueDiffOnOrigin)

        return choice < chance

        return false
    }
}
