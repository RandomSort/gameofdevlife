package net.praqma.lifeofdev.utils

class FileLogger extends Logger {
  File outputFile
  FileLogger(File outputFile) {
    this.outputFile = outputFile
    this.outputFile.text = ""
  }
  public void output(String prefix, String msg) {
    if (usePrefix) {
      msg = "${prefix} ${msg}"
    }
    outputFile << "${msg}\n"
  }

}
