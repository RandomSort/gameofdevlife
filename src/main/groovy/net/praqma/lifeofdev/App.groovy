package net.praqma.lifeofdev

import net.praqma.lifeofdev.game.NDeveloperBasicGameState
import net.praqma.lifeofdev.game.OneDeveloperBasicGameState
import net.praqma.lifeofdev.game.Game
import net.praqma.lifeofdev.game.GitGame

class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {

        Game g = new GitGame(new OneDeveloperBasicGameState())
        g.start()
        g.report()

        g = new GitGame(new NDeveloperBasicGameState(2))
        g.start()
        g.report()

/*
        Developer a = new Developer()
        Work w = new Work()
        def changes = [w]

        Commit c = new Commit([], changes, a, "this is a random string")
        println(c)
*/

    }
}
