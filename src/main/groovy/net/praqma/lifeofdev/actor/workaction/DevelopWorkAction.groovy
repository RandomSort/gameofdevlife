package net.praqma.lifeofdev.actor.workaction

import net.praqma.lifeofdev.actor.Developer
import org.apache.commons.lang3.RandomUtils

class DevelopWorkAction implements WorkAction {

    boolean doAction(Developer developer) {

        // TODO defer to logging
        println "Action: Developing"

        //Add a unit of value to the WIP
        //If we miss our skill test also add a bug
        def skillRoll = RandomUtils.nextInt(0, 1000)
        developer.workInProgress.value++
        if (skillRoll > developer.skillLevel) {
            developer.workInProgress.bugs++
        }
        return true
    }

    int getActionCost() {
        return 1
    }

    @Override
    String getName() {
        return "Developing"
    }
}
