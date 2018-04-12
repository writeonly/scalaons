package pl.writeonly.son2.path.notation

import com.jayway.jsonpath.spi.json.JsonSmartJsonProvider
import com.jayway.jsonpath.spi.mapper.JsonSmartMappingProvider
import com.typesafe.scalalogging.StrictLogging
import net.minidev.json.parser.JSONParser
import net.minidev.json.writer.JsonReaderI
import net.minidev.json.{JSONStyle, JSONValue}
import pl.writeonly.son2.apis.config.{MetaImpl, RConfig, WConfig}
import pl.writeonly.son2.apis.core.Formats
import pl.writeonly.son2.apis.notation.NotationWriter
import pl.writeonly.son2.path.core.{DefaultsPath, ProvidersPath}

case class NotationCaseSmart()
    extends NotationCasePath(
      MetaImpl(ProvidersPath.SMART, Formats.OBJECT),
      c => new NotationReaderSmart(c),
      c => new NotationWriterSmart(c)
    )

class DefaultsSmart(c: RConfig, parseMode: Int, mapper: JsonReaderI[_])
    extends DefaultsPath(
      c,
      new JsonSmartJsonProvider(parseMode, mapper),
      new JsonSmartMappingProvider(mapper.base)
    )
    with StrictLogging {
  //  logger.info(this.toString, new Exception)

  def this(c: RConfig) =
    this(c, JSONParser.MODE_PERMISSIVE, JSONValue.defaultReader.DEFAULT_ORDERED)
}

class NotationReaderSmart(c: RConfig)
    extends NotationReaderPath(
      MetaImpl(ProvidersPath.SMART, Formats.OBJECT),
      new DefaultsSmart(c)
    ) {

  override def isDefinedAt(content: String): Boolean =
    JSONValue.isValidJson(content)
}

class NotationWriterSmart(c: WConfig)
    extends NotationWriter(MetaImpl(ProvidersPath.SMART, Formats.OBJECT), c) {

  override def writePretty(value: Any): String =
    JSONValue.toJSONString(value, JSONStyle.MAX_COMPRESS)

  override def writeRaw(value: Any): String =
    JSONValue.toJSONString(value, JSONStyle.NO_COMPRESS)
}
