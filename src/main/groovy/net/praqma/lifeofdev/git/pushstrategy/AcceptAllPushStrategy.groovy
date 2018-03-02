package net.praqma.lifeofdev.git.pushstrategy

import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.git.Commit
import net.praqma.lifeofdev.git.Repository

class AcceptAllPushStrategy implements PushStrategy {
    private Repository repository

    def AcceptAllPushStrategy(Repository repository) {

        this.repository = repository
    }

    @Override
    boolean push() {

        repository.commits.values().each { Commit commit ->

            // if the commit is already on origin, continue (return inside each = continue)
            if(GitGame.gameState.ORIGIN.commits.containsKey(commit.sha)) return

            GitGame.gameState.ORIGIN.addCommit(commit)
        }

        return true
    }
}
