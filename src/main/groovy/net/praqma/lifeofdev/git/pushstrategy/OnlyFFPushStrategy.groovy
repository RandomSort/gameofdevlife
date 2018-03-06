package net.praqma.lifeofdev.git.pushstrategy

import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.git.Commit
import net.praqma.lifeofdev.git.Repository

class OnlyFFPushStrategy implements PushStrategy {
    private Repository repository

    OnlyFFPushStrategy(Repository repository) {
        this.repository = repository
    }

    @Override
    boolean push() {

        // does the remote contain work we do not have locally?
        if(repository.ORIGINvalue != GitGame.gameState.ORIGIN.getValue()){

            return false // there's something on the remote we don't have, cant commit
        }

        repository.commits.values().each { Commit commit ->

            // if the commit is already on origin, continue (return inside each = continue)
            if(GitGame.gameState.ORIGIN.commits.containsKey(commit.sha)) return

            GitGame.gameState.ORIGIN.addCommit(commit)
        }

        // we just made changes to the remote, we should update our idea of it
        repository.ORIGINvalue = GitGame.gameState.ORIGIN.getValue()

        return true

    }
}
