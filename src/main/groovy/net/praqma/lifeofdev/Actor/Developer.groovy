package net.praqma.lifeofdev.actor

import net.praqma.lifeofdev.actor.workaction.*
import net.praqma.lifeofdev.actor.workaction.commit.LastInStepCommitStrategy
import net.praqma.lifeofdev.actor.workaction.commit.NeverCommitStrategy
import net.praqma.lifeofdev.actor.workaction.commit.ThresholdCommitStrategy
import net.praqma.lifeofdev.actor.workaction.deliver.NeverDeliverStrategy
import net.praqma.lifeofdev.actor.workaction.deliver.ThresholdDeliverStrategy
import net.praqma.lifeofdev.actor.workingstrategy.WorkingStrategy
import net.praqma.lifeofdev.actor.workingstrategy.WorkingStrategyFactory
import net.praqma.lifeofdev.game.GameState
import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.git.Repository

class Developer implements Actor {
    Work workInProgress
    int skillLevel = 995

    Repository localRepository = new Repository()


    //WorkingStrategy ws = new WorkingStrategyFactory(this, new NeverCommitStrategy(), new NeverDeliverStrategy())// NeverCommitWorkingStrategy(this)
    // WorkingStrategy ws = new NeverCommitWorkingStrategy(this)

    //WorkingStrategy ws = new WorkingStrategyFactory(this, new LastInStepCommitStrategy(), new NeverDeliverStrategy()) // CommitLastWorkingStrategy(this)

    //WorkingStrategy ws = new WorkingStrategyFactory(this, new LastInStepCommitStrategy(), new ThresholdDeliverStrategy(10)) // DeliverOnValueThresholdWorkingStrategy(10)
    WorkingStrategy ws = new WorkingStrategyFactory(this, new ThresholdCommitStrategy(3), new ThresholdDeliverStrategy(10))


    int actionsLeftThisStep
    final int ACTIONS_PER_STEP = 5 //allow some developers to be more productive #scopecreep #morefeatures

    HashMap<WorkAction.ACTION, WorkAction> workActions = new HashMap<WorkAction.ACTION, WorkAction>()

    public void act(GameState gameState) {

        // A developer can perform exactly ACTIONS_PER_STEP-actions each step
        // this is because, you don't get to do more, by first doing less. See non-fatigue-bonus #featurecreep
        actionsLeftThisStep = ACTIONS_PER_STEP

        println "Developer acts"
        //Do five units of work per act

        ws.work()

    }

    public Developer () {
        workInProgress = new Work()
        workActions.put(WorkAction.ACTION.DEVELOP, new DevelopWorkAction())
        workActions.put(WorkAction.ACTION.COMMIT_WIP, new CommitWIPWorkAction())
        workActions.put(WorkAction.ACTION.DELIVER, new DeliverWorkAction())
    }

    /**
     * Perform a workaction action, returns true if the action was successful
     * @param wa the WorkAction to perform
     * @return
     */
    public boolean doWorkAction(WorkAction.ACTION action){

        if(!workActions.containsKey(action)){
            println "Incapable of performing " + action.toString()
            return false
        }

        WorkAction wa = workActions.get(action)

        // TODO defer to logging
        println "Action: " + wa.getName()

        if(actionsLeftThisStep < wa.getActionCost()){
            println "Couldn't " + wa.getName().toLowerCase() + ", not enough actions left"
            return false
        }

        actionsLeftThisStep -= wa.getActionCost() // constant cost of "one" action

        return wa.doAction(this)

    }

    public String toString() {
        return "Developer: WIP ${workInProgress.value}(${workInProgress.bugs})"
    }
}
