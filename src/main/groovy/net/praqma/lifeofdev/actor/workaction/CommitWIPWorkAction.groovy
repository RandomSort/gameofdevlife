package net.praqma.lifeofdev.actor.workaction

import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class CommitWIPWorkAction implements WorkAction {

    boolean doAction(Developer developer) {

        developer.localRepository.makeCommit(developer.workInProgress, developer, "did stuff")
        developer.workInProgress = new Work()

        return true
    }

    int getActionCost() {
        return 1
    }

    @Override
    String getName() {
        return "Committing"
    }
}
