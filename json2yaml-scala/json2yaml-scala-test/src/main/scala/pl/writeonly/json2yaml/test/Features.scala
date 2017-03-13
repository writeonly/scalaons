package pl.writeonly.json2yaml.test

import java.nio.file.Paths

object Features {
  val NAME = "writeonly-init"
  val PREFIX = NAME + "."
  val PATH_LOG = "/" + PREFIX + "log"
  val YAML = ".yaml"
  val ONML = "java -jar ../json2yaml-scala-main-2.8.3-SNAPSHOT.jar"
  val CAT = "cat"
  val DIR = "yaml"

  def outputPathname(feature: String, scenario: String) = DIR +"/"+ PREFIX + feature + "." + scenario + YAML

  def inputURI = getClass.getResource(PATH_LOG).toURI

  def inputPathname = Paths.get(inputURI).toString


}
