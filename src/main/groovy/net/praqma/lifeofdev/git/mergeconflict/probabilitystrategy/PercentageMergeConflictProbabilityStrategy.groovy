package net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy

import net.praqma.lifeofdev.git.Repository

class PercentageMergeConflictProbabilityStrategy implements MergeConflictProbabilityStrategy {
    private int chanceInPercent
    private Repository repository
    Random random

    def PercentageMergeConflictProbabilityStrategy(Repository repository, int chanceInPercent) {

        this.repository = repository
        this.chanceInPercent = chanceInPercent

        this.random = new Random()
    }

    @Override
    boolean isMergeConflict() {

        // get random number from 0-99, if it's is strictly lower than the chance in percentage; there is a mergeconflict
        // (100 is not included.)
        int choice = random.nextInt(100)
        return choice < chanceInPercent

    }
}
