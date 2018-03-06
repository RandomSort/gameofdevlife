package net.praqma.lifeofdev.actor.workingstrategy

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class NeverCommitWorkingStrategy implements WorkingStrategy {
    private Developer developer

    NeverCommitWorkingStrategy(Developer developer) {

        this.developer = developer
    }

    @Override
    void work() {

        // Try developing as much as possible!
        while(developer.doWorkAction(WorkAction.ACTION.DEVELOP)){}

    }
}
