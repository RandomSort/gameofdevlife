package net.praqma.lifeofdev.actor.workingstrategy

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction

class DeliverOnValueThresholdWorkingStrategy implements WorkingStrategy {
    private Developer developer
    private int localRepoValueThreshold

    def DeliverOnValueThresholdWorkingStrategy(Developer developer, int localRepoValueThreshold) {

        this.localRepoValueThreshold = localRepoValueThreshold
        this.developer = developer
    }

    boolean printChoice = false

    @Override
    void work() {

        // While we have actions left, work
        while(developer.actionsLeftThisStep > 0){

            // if the threshold is reached, deliver
            int localInventoryValue = developer.localRepository.getInventoryValue()
            if(localInventoryValue >= localRepoValueThreshold){
                // TODO defer to logging
                if(printChoice) println "CHOICE: delivering since local InventoryValue is: " + localInventoryValue + ", above threshold: " + localRepoValueThreshold
                if(developer.doWorkAction(WorkAction.ACTION.DELIVER)){
                    // if we succeeded delivering, continue
                    continue
                }
            }

            // if we have exactly one action left, commit!
            if(developer.actionsLeftThisStep == 1){

                if(printChoice) println "CHOICE: committing since we only have 1 action left"

                // if we have only one action left, commit our work
                if(developer.doWorkAction(WorkAction.ACTION.COMMIT_WIP)){
                    // if we succeeded committing, continue
                    continue
                }

            }

            // when we've not delivered or committed, just progress with some development
            // TODO defer to logging
            if(printChoice) println "CHOICE: developing since we have more than 1 (" + developer.actionsLeftThisStep + ") actions left left"
            if(developer.doWorkAction(WorkAction.ACTION.DEVELOP)){
                // if we succeeded developing, continue
                continue
            }

            // We "continue" after each action,
            //  so if we ever reach this point,
            //  none of the actions are workable
            //  and we just break working
            if(developer.actionsLeftThisStep > 0){
                break
            }
        }

    }
}
