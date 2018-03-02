package net.praqma.lifeofdev.actor.workaction.deliver

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.deliver.DeliverStrategy

class NeverDeliverStrategy implements DeliverStrategy {
    @Override
    boolean deliver(Developer developer) {
        return false // never deliver
    }
}
