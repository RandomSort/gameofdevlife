package net.praqma.lifeofdev

class Work {
  // This class represents _"some amount of work"_
  // It has some value, may have some bugs and should be contained in a commit ( eventually )
  // In this incarnation a bug is something that "maybe works on my machine" or similar.
  // It could break tests or something like that
  int value = 0
  int bugs = 0
  int technicalDebt = 0
}
