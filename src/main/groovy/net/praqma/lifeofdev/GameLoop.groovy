package net.praqma.lifeofdev

class GameLoop {
  ArrayList<Actor> actors = new ArrayList<Actor>()
  int step = 0

  public void step() {
    actors.each { Actor actor ->
      actor.act()

    }
    step++
    println(step)
  }

  public void addActor(Actor a) {
    actors.add(a)
  }

}
