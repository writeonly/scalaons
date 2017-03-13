package pl.writeonly.json2yaml.test

import java.io.{File, FileNotFoundException, FileOutputStream}
import java.nio.file.Paths

import org.scalatest.{FeatureSpec, GivenWhenThen}

import pl.writeonly.json2yaml.core._
import pl.writeonly.json2yaml.main._

class FileJson2YamlFeature extends FeatureSpec with GivenWhenThen {

  info("FileJson2YamlImpl with Json2YamlJackson")

  val given = () => new FileJson2YamlImpl(new Json2YamlJackson)

  val outName = (name: String) => Features.outputPathname("file", name)

  feature("Json2Yaml convert") {
    scenario("Apply with null pathname") {
      Given("converter FileJson2Yaml")
      val file = given()
      When("should produce null when consume null")
      val name: String = null
      val caught = intercept[NullPointerException] {
        file.convertFile(name, name)
      }
      Then("null == messag")
      val message = caught.getMessage
      assert(null == message)
    }

    scenario("Apply with empty pathname") {
      Given("converter FileJson2Yaml")
      val file = given()

      When("should produce empty when consume empty")
      assertThrows[FileNotFoundException] {
        file.convertFile("", "")
      }
    }

    scenario("Apply with pathname") {
      Given("converter FileJson2Yaml")
      val file = given()

      val in = Features.inputPathname
      val out = outName("pathname")

      When("should produce null when consume null")
      file.convertFile(in, out)
    }

    scenario("Apply with uri") {
      Given("converter FileJson2Yaml")
      val file = given()

      val in = Features.inputURI
      val out = new File(outName("uri")).toURI

      When("should produce null when consume null")
      file.convertFile(in, out)
    }

    scenario("Apply with file") {
      Given("converter FileJson2Yaml")
      val file = given()

      val in = new File(Features.inputURI)
      val out = new File(outName("file"))

      When("should produce out when consume in")
      file.convertFile(in, out)
    }

//    scenario("Apply with stream") {
//      Given("converter FileJson2Yaml")
//      val file = given()
//      val in = getClass.getResourceAsStream(PATH_LOG)
//      val out = new FileOutputStream(outName("stream"))
//
//      When("should produce null when consume null")
//      file.convertStream(in, out)
//    }

  }
}

