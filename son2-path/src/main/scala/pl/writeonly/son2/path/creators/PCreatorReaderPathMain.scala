package pl.writeonly.son2.path.creators

import com.jayway.jsonpath.Configuration.Defaults
import pl.writeonly.son2.core.config.RConfig
import pl.writeonly.son2.core.notation.NotationReader
import pl.writeonly.son2.core.pcreators.PCreatorReader
import pl.writeonly.son2.path.notation.{DefaultsSmart, NotationReaderPath}

class PCreatorReaderPathMain extends PCreatorReader {

  override def isDefinedAt(c: RConfig): Boolean = isDefinedAt(c.query)

  def isDefinedAt(s: Option[String]): Boolean = s != null && s.isDefined && isDefinedAt(s.get)

  def isDefinedAt(s: String): Boolean = s != null && s.startsWith("$")

  override def apply(c: RConfig): NotationReader = new NotationReaderPath(defaults(c), c.query)

  def defaults(c: RConfig): Defaults = new DefaultsSmart(c)
}
