package net.praqma.lifeofdev.actor.workingstrategy

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.WorkAction
import net.praqma.lifeofdev.actor.workaction.commit.CommitStrategy
import net.praqma.lifeofdev.actor.workaction.deliver.DeliverStrategy

class WorkingStrategyFactory implements WorkingStrategy {
    public Developer developer
    private CommitStrategy commitStrategy
    private DeliverStrategy deliverStrategy

    public WorkingStrategyFactory(Developer developer, CommitStrategy commitStrategy, DeliverStrategy deliverStrategy) {

        this.deliverStrategy = deliverStrategy
        this.commitStrategy = commitStrategy
        this.developer = developer
    }


    @Override
    void work() {

        // While we have actions left, work
        while(developer.actionsLeftThisStep > 0){

            if(deliverStrategy.deliver(developer)){
                continue
            }

            if(commitStrategy.commit(developer)){
                continue
            }

            // if we've done nothing else, develop!
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
