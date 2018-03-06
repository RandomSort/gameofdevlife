package net.praqma.lifeofdev.actor.workingstrategy

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class CommitLastWorkingStrategy implements WorkingStrategy{
    private Developer developer

    CommitLastWorkingStrategy(Developer developer) {
        this.developer = developer
    }

    @Override
    void work() {

        while(developer.actionsLeftThisStep > 1){
            developer.doWorkAction(WorkAction.ACTION.DEVELOP)
        }

        developer.doWorkAction(WorkAction.ACTION.COMMIT_WIP)

    }
}
