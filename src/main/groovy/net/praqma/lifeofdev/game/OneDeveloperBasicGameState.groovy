package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Developer

/**
 * The most basic setup with one developer.
 */

class OneDeveloperBasicGameState extends GameState {

    public OneDeveloperBasicGameState(){
        actors.add(new Developer())
    }

}