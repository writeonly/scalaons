package pl.writeonly.son2.jack.converters

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import pl.writeonly.son2.core.config.WPretty
import pl.writeonly.son2.core.converters.{Converter, Converter2}
import pl.writeonly.son2.core.liners.{Liner, LinerOpt}
import pl.writeonly.son2.jack.core.{ConfigJack, FormatsJack}
import pl.writeonly.son2.jack.glue.CreatorConverterJack
import pl.writeonly.sons.specs.WhiteAssertSpec

class ObjectAssertSpec extends WhiteAssertSpec {

  val provider: Converter = CreatorConverterJack(FormatsJack.OBJECT)
  "A Provider" should {
    "produce JsonParseException when convert a" in {
      assertThrows[JsonParseException] {
        provider.convert("a")
      }
    }
    "produce JsonMappingException when convert empty string" in {
      assertThrows[JsonMappingException] {
        provider.convert("")
      }
    }
  }

  val liner: Liner = new LinerOpt(provider)
  "A Liner" should {
    "return empty comment" in {
      assertResult(provider.comment("") + "\n")(liner.apply(""))
    }
  }

  val providerRaw: Converter = CreatorConverterJack(
    ConfigJack(FormatsJack.OBJECT))
  "A ProviderRaw" should {
    "have pretty == true" in {
      assertResult(WPretty)(providerRaw.config.write.style)
    }
    "be  Provider2" in {
      assertResult(true)(providerRaw.isInstanceOf[Converter2])
    }
  }
  val provider2Raw = providerRaw.asInstanceOf[Converter2]
  "A Provider2Raw" should {
    //    "have pretty == false" in {
    //      assertResult(false)(provider2Raw.out.config.style)
    //    }
    //    "be not pretty" in {
    //      assertResult(false)(provider2Raw.out.pretty)
    //    }
  }
}
