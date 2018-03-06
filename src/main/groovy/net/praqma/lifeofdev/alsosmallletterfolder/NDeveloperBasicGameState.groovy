package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Developer

/**
 * A scalable setup with n developers.
 */

class NDeveloperBasicGameState extends GameState {

    public NDeveloperBasicGameState(int n){

        n.times{
            actors.add(new Developer())
        }
    }

}