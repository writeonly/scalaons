package pl.writeonly.son2.main

import java.io.FileNotFoundException

import pl.writeonly.son2.impl.{Features, Types}
import pl.writeonly.son2.jack.core.Formats
import pl.writeonly.son2.spec.BlackSpec

class MainSon2ObjectFeature extends BlackSpec {

  val outName = (name: String) => Features.outputPathname(Types.MAIN, name, Formats.OBJECT)

  feature(classOf[MainSon2ObjectFeature].getSimpleName) {
    scenario("Apply with null pathname") {
      Given("converter FileJson2Yaml")
      val name: String = null

      When("should produce null when consume null")
      val caught = intercept[NullPointerException] {
        Main.main(Array(Formats.OBJECT, name, name))
      }

      Then("null == messag")
      val message = caught.getMessage
      assert(null == message)
    }

    scenario("Apply with empty pathname") {
      Given("converter FileJson2Yaml")

      When("should produce empty when consume empty")
      assertThrows[FileNotFoundException] {
        Main.main(Array(Formats.OBJECT, "", ""))
      }
    }

    scenario("Apply with pathname") {
      Given("converter FileJson2Yaml")
      val in = Features.inputPathname
      val out = outName("pathname")

      When("should produce null when consume null")
      Main.main(Array(Formats.OBJECT, in, out))
    }
  }
}
