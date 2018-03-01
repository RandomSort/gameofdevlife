package net.praqma.lifeofdev.actor

import net.praqma.lifeofdev.actor.workaction.*
import net.praqma.lifeofdev.actor.workingstrategy.CommitLastWorkingStrategy
import net.praqma.lifeofdev.actor.workingstrategy.DeliverOnValueThresholdWorkingStrategy
import net.praqma.lifeofdev.actor.workingstrategy.NeverCommitWorkingStrategy
import net.praqma.lifeofdev.actor.workingstrategy.WorkingStrategy
import org.apache.commons.lang3.RandomUtils

import net.praqma.lifeofdev.game.GameState
import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.git.Repository

import javax.swing.Action

class Developer implements Actor {
    Work workInProgress
    int skillLevel = 995

    Repository localRepository = new Repository()

    //WorkingStrategy ws = new NeverCommitWorkingStrategy(this)
    //WorkingStrategy ws = new CommitLastWorkingStrategy(this)
    WorkingStrategy ws = new DeliverOnValueThresholdWorkingStrategy(this, 10)

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
    }

    /**
     * Perform a workaction action, returns true if the action was successful
     * @param wa the WorkAction to perform
     * @return
     */
    public boolean doWorkAction(WorkAction.ACTION action){

        if(!workActions.containsKey(action)){
            println "Incapable of performing " + Action.toString()
            return false
        }

        WorkAction wa = workActions.get(action)

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


