package pl.writeonly.son2.text.glue

import pl.writeonly.scalaops.specs.fixture.GrayScalarSpec
import pl.writeonly.scalaops.pipe.Pipe

class CreatorConverterOrTextScalarSpec extends GrayScalarSpec with Pipe {

  override type FixtureParam = CreatorConverterOrText

  override protected def withFixture(test: OneArgTest) =
    new CreatorConverterOrText |> test

  it should "when create provider with empty format" in { creator =>
    creator converterOr "" should be an 'isBad
  }

}
