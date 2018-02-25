package net.praqma.lifeofdev

import org.apache.commons.lang3.RandomUtils

class Developer implements Actor {
  Work workInProgress
  int skillLevel = 995

  public void act() {
    println "Developing"
    //Do five units of work per act
    (0..4).each {
      doUnitOfWork()
    }
  }

  Developer () {
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
  }

  public String toString() {
    return "Developer: WIP ${workInProgress.value}(${workInProgress.bugs})"
  }
}
