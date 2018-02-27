package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Actor
import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.game.GameState

class GitGame implements Game {

    GameState gameState

    // perform setup of game
    public GitGame(){

        gameState = new GameState()

        // hardcoded setup

        // a gitgame has 1 developer
        addActor(new Developer())

    }

    public void start(){

        // gameLoop
        while(!gameState.gameOver){
            // step

            gameState.actors.each { Actor actor ->
                actor.act(gameState) // allow actors to modify gamestate
            }
            gameState.step++

            println(gameState.step)

            // if some condition is satisfied set gameover = true
            if(gameState.step > 10){ // hardcoded game-ending condition

                // A gitgame stops after 10 rounds
                gameState.gameOver = true

            }

        }

        // print some final state

    }

    public void addActor(Actor a) {
        gameState.actors.add(a)
    }

    // TODO move to logging
    public void print_state() {
        println("------------------------")
        println("Actors:")

        gameState.actors.each { actor ->
            println(actor)
        }
    }

}