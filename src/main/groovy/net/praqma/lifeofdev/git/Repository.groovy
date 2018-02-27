package net.praqma.lifeofdev.git

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.Work

class Repository {
  ArrayList<Branch> branches = new ArrayList<Branch>()

  // dictionary of commits, order-less-"LinkedHashMap" for 1-to-1 get/put
  HashMap<String, Commit> commits = new HashMap<String, Commit>()

  Treeish HEAD

  public void makeCommit(Work w, Developer author, String message) {
    Commit c = new Commit([HEAD], [w], author, message )

    // add commit to repository
    commits.put(c.sha, c)

    // update HEAD
    if(HEAD.getClass() == Branch){ // is the HEAD currently pointing to a branch?
      // set the branch-pointer to the new commit
      HEAD.commit = c
    } else {
      // assume it's pointing to a commit, update it to point to the new commit
      HEAD = c
    }
  }

  public Repository() {
    HEAD = new Branch(name: "master", commit: null)
  }

  public int getValue() {

    int value = 0
    ArrayList<String> workSeen = new ArrayList<>()

    for (Commit c in commits.values()){

      if(!workSeen.contains(c.sha)){
        workSeen.add(c.sha)
        c.changes.each { Work w ->
          value += w.value
        }
      }

    }

    return value
  }
}
