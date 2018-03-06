package net.praqma.lifeofdev

import net.praqma.lifeofdev.game.NDeveloperBasicGameState
import net.praqma.lifeofdev.game.OneDeveloperBasicGameState
import net.praqma.lifeofdev.game.Game
import net.praqma.lifeofdev.game.GitGame
import net.praqma.lifeofdev.game.ThresholdNineSimulation
import net.praqma.lifeofdev.game.ThresholdTenSimulation
import net.praqma.lifeofdev.utils.FileLogger
import net.praqma.lifeofdev.utils.PrintLogger
class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {

        Game g = new GitGame(new OneDeveloperBasicGameState(),new PrintLogger(usePrefix: true))
        // Game g = new GitGame(new NDeveloperBasicGameState(5),new PrintLogger(usePrefix: true))


        /* Curious things: even though simulation two spends less time
            explicitly developing, we sometimes experience that simulation one
            will spend more time trying to deliver, because "merge conflicts"
            get more frequent / costly, when we deliver less frequently, i.e.
            with bigger workloads, against bigger differences on Origin

               */

        // SIMULATION ONE: Developers have threshold = 10
        // Game g = new GitGame(new ThresholdTenSimulation(5),new PrintLogger(usePrefix: true))

        // SIMULATION TWO: Developers have threshold = 9
        //Game g = new GitGame(new ThresholdNineSimulation(5),new PrintLogger(usePrefix: true))

        g.reportAfterEachStep = true
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
