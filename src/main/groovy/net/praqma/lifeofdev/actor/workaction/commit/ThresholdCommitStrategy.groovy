package net.praqma.lifeofdev.actor.workaction.commit

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class ThresholdCommitStrategy implements CommitStrategy {
    private int WIPValueThreshold

    ThresholdCommitStrategy(int WIPValueThreshold) {
        this.WIPValueThreshold = WIPValueThreshold
    }

    @Override
    boolean commit(Developer developer) {


        // if we exceed the threshold, commit!
        if(developer.workInProgress.value > WIPValueThreshold){

            if(developer.doWorkAction(WorkAction.ACTION.COMMIT_WIP)){
                // if we succeeded committing, return true
                return true
            }

        }

        return false

    }
}
