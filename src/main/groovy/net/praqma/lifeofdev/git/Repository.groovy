package net.praqma.lifeofdev.git

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.Work

class Repository {
  ArrayList<Branch> branches = new ArrayList<Branch>()
  Treeish HEAD

  public void makeCommit(Work w, Developer author, String message) {
    commit c = new Commit([HEAD], [w], author, message )

    HEAD.commit = c
  }

  public Repository() {
    HEAD = new Branch(name: "master", commit: null)
  }

}
