package net.praqma.lifeofdev.git

class Branch implements Treeish {

  // Could store the commit.sha instead of a ref to the commit if we could guarantee they were unique
  Commit commit
  String name
}
