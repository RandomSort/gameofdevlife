package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.commit.ThresholdCommitStrategy
import net.praqma.lifeofdev.actor.workaction.deliver.ThresholdDeliverStrategy
import net.praqma.lifeofdev.git.Repository
import net.praqma.lifeofdev.git.mergeconflict.costStrategy.LocalOriginDiffMergeConflictCostStrategy
import net.praqma.lifeofdev.git.mergeconflict.probabilitystrategy.LocalOriginDiffMergeConflictProbabilityStrategy

class ThresholdTenSimulation extends GameState {
    def ThresholdTenSimulation(int numberOfDevelopers) {

        int deliveryThreshold = 10

        numberOfDevelopers.times{

            // the repo config I want
            Repository repo = new Repository()

            // coupling is a bit strong with a repo reference on the strategies, but they mutate the object, so..
            repo.mcps = new LocalOriginDiffMergeConflictProbabilityStrategy(repo, 2)
            repo.mccs = new LocalOriginDiffMergeConflictCostStrategy(repo, 0.05) // merging costs 0.05 per difference in InventoryValue locally + the Value only on the remote

            actors.add(new Developer(
                    repo,
                    new ThresholdCommitStrategy(3),
                    new ThresholdDeliverStrategy(deliveryThreshold)))
        }

    }
}
