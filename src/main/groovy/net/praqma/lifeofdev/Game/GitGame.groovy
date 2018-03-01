package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Actor
import net.praqma.lifeofdev.actor.Developer

class GitGame implements Game {

    GameState gameState

    // perform setup of game
    public GitGame(gameState){

        this.gameState = gameState

    }

    public void start(){

        // gameLoop
        while(!gameState.gameOver){
            // step

            gameState.actors.each { Actor actor ->
                actor.act(gameState) // allow actors to modify gamestate
            }
            gameState.step++

            // TODO defer to logging
            println("Step: " + gameState.step)

            // if some condition is satisfied set gameover = true
            if(gameState.step >= 5){ // hardcoded game-ending condition

                // A gitgame stops after 5 rounds
                gameState.gameOver = true

            }

        }

    }

    /**
     * Wrapper for reporting some gamestate, before/during/after
     *  - so the game can be run silently
     * Might want to defer to GameState
     */
    public void report(){

        // q0: how much value is in the developers local repository?
        gameState.actors.each {
            Actor actor ->
                if(actor.getClass() == Developer){
                    Developer d = (Developer) actor

                    // TODO defer to logging
                    println "Value of Developer: " + d.toString() + ". LocalRepoValue: " +  d.localRepository.getValue()

                }

        }

        // q1: how much value is in the global repository?
        // TODO defer to logging
        println "Value of remote: " + gameState.remote.getValue()

        // print some final state
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
