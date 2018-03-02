package net.praqma.lifeofdev.utils

class PrintLogger extends Logger {
  public void output(String prefix, String msg) {
    if(usePrefix) {
      msg = "${prefix} ${msg}"
    }
    println(msg)
  }

}
