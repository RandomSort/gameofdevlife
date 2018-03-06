package net.praqma.lifeofdev.actor.workaction

import net.praqma.lifeofdev.actor.Developer

class DeliverWorkAction implements WorkAction {
    @Override
    boolean doAction(Developer developer) {

        // Push the local to "its upstream" - abstraction: there only exists one remote = must be upstream
        if(developer.localRepository.push()){

            // Reset the inventory value, as we've clearly just pushed everything we have
            developer.localRepository.inventoryValueAtLastCalculation = 0
        } else {
            // sth went wrong. What? - right now, only a mergeconflict can go wrong..
            // and that the gamestate is NULL but then everything dies so..
            // NOTGONNADO implement HTTPresponse-like error messaging system

            // we might be able to pull, depending on the state of our developer
            developer.localRepository.pull(developer)

        }

        return true // we've done work..

    }

    @Override
    int getActionCost() {
        return 1
    }

    @Override
    String getName() {
        return "Delivering local to remote"
    }
}
