package net.praqma.lifeofdev.actor.workaction.commit

import net.praqma.lifeofdev.actor.Developer

interface CommitStrategy {

    boolean commit(Developer developer)
}