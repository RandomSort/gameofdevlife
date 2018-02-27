package net.praqma.lifeofdev

import net.praqma.lifeofdev.git.*
import net.praqma.lifeofdev.game.Game
import net.praqma.lifeofdev.game.GitGame

class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {

        Game g = new GitGame()

        g.start()

/*
        Developer a = new Developer()
        Work w = new Work()
        def changes = [w]

        Commit c = new Commit([], changes, a, "this is a random string")
        println(c)
*/



    }
}
