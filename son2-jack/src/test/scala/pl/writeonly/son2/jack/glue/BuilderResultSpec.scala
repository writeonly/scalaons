package pl.writeonly.son2.jack.glue

import pl.writeonly.son2.core.notation.Config
import pl.writeonly.son2.core.streamers.{StreamerPipeAll, StreamerPipeForeach, StreamerSourceAll, StreamerSourceForeach}
import pl.writeonly.son2.spec.WhiteResultSpec

class BuilderResultSpec extends WhiteResultSpec {

  "A Builder" when {
    "config s is true" should {
      val builder = new Builder(new Config(s = true))
      "return s equals true" in {
        val stream = builder.pipe
        assert(stream.isInstanceOf[StreamerPipeForeach])
      }
      "return p equals true" in {
        val stream = builder.source
        assert(stream.isInstanceOf[StreamerSourceForeach])
      }
    }
    "config s is false" should {
      val builder = new Builder(new Config(s = false))
      "return i equals 'object'" in {
        val stream = builder.pipe
        assert(stream.isInstanceOf[StreamerPipeAll])
      }
      "return o equals 'yaml'" in {
        val stream = builder.source
        assert(stream.isInstanceOf[StreamerSourceAll])
      }
    }
  }
}
