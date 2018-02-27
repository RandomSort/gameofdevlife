package net.praqma.lifeofdev.actor

import net.praqma.lifeofdev.game.GameState

interface Actor {
  public void act(GameState gameState)
}
