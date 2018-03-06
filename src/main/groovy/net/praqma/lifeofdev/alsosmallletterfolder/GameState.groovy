package net.praqma.lifeofdev.game

import net.praqma.lifeofdev.actor.Actor
import net.praqma.lifeofdev.git.Repository

abstract class GameState {

  int step
  ArrayList<Actor> actors = new ArrayList<Actor>()

  boolean gameOver = false

  // The basic game has one repository
  Repository ORIGIN = new Repository()

}

