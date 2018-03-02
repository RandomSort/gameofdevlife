package net.praqma.lifeofdev.actor.workaction.deliver

import net.praqma.lifeofdev.actor.Developer

interface DeliverStrategy {

    boolean deliver(Developer developer)
}