package net.praqma.lifeofdev.actor.workaction.commit

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class LastInStepCommitStrategy implements CommitStrategy {
    @Override
    boolean commit(Developer developer) {

        // if we have exactly one action left, commit!
        if(developer.actionsLeftThisStep == 1){

            // if we have only one action left, commit our work
            if(developer.doWorkAction(WorkAction.ACTION.COMMIT_WIP)){
                // if we succeeded committing, continue
                return true
            }

        }

        return false

    }
}
