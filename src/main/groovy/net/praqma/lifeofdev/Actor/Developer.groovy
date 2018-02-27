package net.praqma.lifeofdev.actor

import org.apache.commons.lang3.RandomUtils

import net.praqma.lifeofdev.game.GameState
import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.git.Repository

class Developer implements Actor {
  Work workInProgress
  int skillLevel = 995

  Repository localRepository = new Repository()

  public void act(GameState gameState) {
    println "Developing"
    //Do five units of work per act
    5.times {
      doUnitOfWork()
    }
  }

  public Developer () {
    workInProgress = new Work()
  }

  public void doUnitOfWork() {
    //Add a unit of value to the WIP
    //If we miss our skill test also add a bug
    def skillRoll = RandomUtils.nextInt(0,1000)
    workInProgress.value++
    if(skillRoll > skillLevel) {
      workInProgress.bugs++
    }

    // assume the developer is basic fantastic and commits after every piece of work.
    CommitWIP()

  }

  public CommitWIP(){
    localRepository.makeCommit(workInProgress, this, "did stuff")
    workInProgress = new Work()
  }

  public String toString() {
    return "Developer: WIP ${workInProgress.value}(${workInProgress.bugs})"
  }
}
