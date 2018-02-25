package net.praqma.lifeofdev

class GameLoop {
  GameState gameState = new GameState()

  public void step() {
    gameState.actors.each { Actor actor ->
      actor.act()

    }
    gameState.step++
    println(gameState.step)
  }

  public void addActor(Actor a) {
    gameState.actors.add(a)
  }

  public void print_state() {
    println("------------------------")
    println("Actors:")
    gameState.actors.each { actor ->
      println(actor)
    }
  }
}
