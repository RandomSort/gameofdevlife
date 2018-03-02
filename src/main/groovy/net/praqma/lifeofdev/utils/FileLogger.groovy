package net.praqma.lifeofdev.utils

class FileLogger extends Logger {
  File outputFile
  FileLogger(String filePath) {
    File logDir = new File("logs")
    if(!logDir.exists()) {
        logDir.mkdirs()
    }
    outputFile = new File("logs/${filePath}")
    outputFile.text = ""
  }
  public void output(String prefix, String msg) {
    if (usePrefix) {
      msg = "${prefix} ${msg}"
    }
    outputFile << "${msg}\n"
  }

}
