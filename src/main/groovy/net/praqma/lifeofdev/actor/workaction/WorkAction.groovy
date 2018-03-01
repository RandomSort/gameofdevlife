package net.praqma.lifeofdev.actor.workaction

import net.praqma.lifeofdev.actor.Developer

interface WorkAction {

    public enum ACTION {
        DEVELOP,
        COMMIT_WIP
    }


    boolean doAction(Developer developer)

    int getActionCost()

    String getName()
}
