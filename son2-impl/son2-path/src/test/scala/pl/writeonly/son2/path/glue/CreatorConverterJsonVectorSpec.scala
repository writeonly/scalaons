package pl.writeonly.son2.path.glue

import pl.writeonly.son2.apis.config.Meta
import pl.writeonly.son2.jack.core.FormatsJack
import pl.writeonly.son2.path.core.ProvidersPath
import pl.writeonly.scalaops.specs.GrayVectorSpec

class CreatorConverterPathVectorSpec extends GrayVectorSpec {

  val providers = Table("format", ProvidersPath.ALL: _*)

  val pf = new ChainNotationConfigPath().get
  val chain = new ChainNotationRWTPath
  val reader = chain.r
  val writer = chain.w
  property("PF should isDefinedAt for provider") {
    forAll(providers) { (provider) =>
      pf.isDefinedAt(provider.name) shouldBe true
      val config = pf.apply(provider.name)
      reader.isDefinedAt(config.read) shouldBe true
      writer.isDefinedAt(config.write) shouldBe true

      reader.apply(config.read)
      writer.apply(config.write)
    }
  }
  property("Apply creatorConverterPath with format") {
    forAll(providers) { (provider) =>
      val converter = CreatorConverterPath.apply(provider)
      withClue(s"$provider ${converter}") {
        converter.isGood shouldBe true
        converter.get.metas._1 shouldBe Meta(provider, FormatsJack.OBJECT)
        converter.get.metas._2 shouldBe Meta(provider, FormatsJack.OBJECT)
      }

    }
  }

}
