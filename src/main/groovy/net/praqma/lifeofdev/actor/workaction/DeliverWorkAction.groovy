package net.praqma.lifeofdev.actor.workaction

import net.praqma.lifeofdev.actor.Developer

class DeliverWorkAction implements WorkAction {
    @Override
    boolean doAction(Developer developer) {

        // Push the local to "its upstream" - abstraction: there only exists one remote = must be upstream
        developer.localRepository.push()
        // Reset the inventory value, as we've clearly just pushed everything we have
        developer.localRepository.inventoryValueAtLastCalculation = 0

        return true
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
