package net.praqma.lifeofdev.git

import static org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_224
import org.apache.commons.lang3.RandomUtils
import org.apache.commons.codec.digest.DigestUtils

import net.praqma.lifeofdev.actor.Developer
import net.praqma.lifeofdev.Work

class Commit implements Treeish {
  String sha
  ArrayList<Commit> parents
  ArrayList<Work> changes
  Developer author
  String message

  Commit(ArrayList<Commit> parents, ArrayList<Work> changes, Developer author, String message) {
      sha = new DigestUtils(SHA_224).digestAsHex("${RandomUtils.nextInt(0,1000000)}")
      this.parents = parents
      this.changes = changes
      this.author = author
      this.message = message
  }

  public String toString() {
    return "${sha} ${message}"
  }

}
