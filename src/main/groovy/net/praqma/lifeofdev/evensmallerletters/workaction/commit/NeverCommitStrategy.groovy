package net.praqma.lifeofdev.actor.workaction.commit

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.actor.workaction.commit.CommitStrategy

class NeverCommitStrategy implements CommitStrategy {
    @Override
    boolean commit(Developer developer) {
        return false // never commit
    }
}
