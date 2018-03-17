package pl.writeonly.son2.core.notation

import pl.writeonly.son2.core.pcreators.PCreatorConfig
import pl.writeonly.sons.utils.ops.Pipe._

abstract class PartialCreatorConfigSymbol(f: Symbol) extends PCreatorConfig {

  override def isDefinedAt(s: String): Boolean = s && format.name.startsWith(s)

  def format: Symbol = f

}
