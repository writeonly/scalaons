package pl.writeonly.son2.jack.converters

import pl.writeonly.son2.apis.converters.Converter
import pl.writeonly.son2.funs.liners.{Liner, LinerOpt}
import pl.writeonly.son2.funs.streamers.{Streamer, StreamerPipeForeach}
import pl.writeonly.son2.jack.core.FormatsJack
import pl.writeonly.son2.jack.glue.CreatorConverterJack
import pl.writeonly.scalaops.specs.GrayVectorSpec

class YamlVectorSpec extends GrayVectorSpec {

  val toSuccess = Table(
    ("in", "out"),
    ("0", "--- 0\n"),
    ("\"a\"", "--- \"a\"\n"),
    ("[]", "--- []\n"),
    ("[0]", "---\n- 0\n"),
    ("[0,1]", "---\n- 0\n- 1\n"),
    ("{}", "--- {}\n"),
    ("{\"a\":0}", "---\na: 0\n"),
    ("{\"a\":0, \"b\":1}", "---\na: 0\nb: 1\n"),
    ("[{}]", "---\n- {}\n"),
    ("{\"a\":[]}", "---\na: []\n")
  )

  val toFailure = Table("in", "a")

  val converter: Converter = CreatorConverterJack(FormatsJack.YAML).get
  property("convert son to yaml by provider") {
    forAll(toSuccess) { (in, out) =>
      converter convert in shouldBe out
    }
  }

  val liner: Liner = new LinerOpt(converter)
  property("convert son to yaml by liner") {
    forAll(toSuccess) { (in, out) =>
      liner.apply(in) should be(out + "\n")
    }
  }
  property("fail convert son to yaml by liner") {
    forAll(toFailure) { in =>
      liner.apply(in) should be(converter.comment(in) + "\n")
    }
  }

  val streamer: Streamer = new StreamerPipeForeach(liner)
  property("convert son to yaml by streamer") {
    forAll(toSuccess) { (in, out) =>
      streamer.convertString(in) should be(out + "\n")
    }
  }
  property("fail convert son to yaml by streamer") {
    forAll(toFailure) { in =>
      streamer.convertString(in) should be(converter.comment(in) + "\n")
    }
  }

  property("convert son to yaml by native streamer") {
    forAll(toSuccess) { (in, out) =>
      streamer.convertStringNative(in) should be(out + "\n")
    }
  }
  property("fail convert son to yaml by native streamer") {
    forAll(toFailure) { in =>
      streamer.convertStringNative(in) should be(converter.comment(in) + "\n")
    }
  }
}
