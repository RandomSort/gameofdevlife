package net.praqma.lifeofdev.utils

abstract class Logger {
  Boolean usePrefix = false

  public void info(String msg) {
    output("[INFO]:", msg)
  }

  public void debug(String msg) {
    output("[DEBUG]:", msg)
  }

  public void error(String msg) {
    output("[ERROR]:", msg)
  }

  public void warning(String msg) {
    output("[WARNING]:", msg)
  }

  abstract void output(String prefix, String msg)
}
