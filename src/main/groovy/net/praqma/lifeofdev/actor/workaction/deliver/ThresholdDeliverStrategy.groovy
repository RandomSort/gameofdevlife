package net.praqma.lifeofdev.actor.workaction.deliver

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class ThresholdDeliverStrategy implements DeliverStrategy {
    private int localRepoValueThreshold

    ThresholdDeliverStrategy(int localRepoValueThreshold) {
        this.localRepoValueThreshold = localRepoValueThreshold
    }

    @Override
    boolean deliver(Developer developer) {

        // if we reach the threshold, deliver!
        int localInventoryValue = developer.localRepository.getInventoryValue()
        if(localInventoryValue >= localRepoValueThreshold){
            if(developer.doWorkAction(WorkAction.ACTION.DELIVER)){
                // if we succeeded delivering, continue
                return true
            }
        }

        return false

    }
}
