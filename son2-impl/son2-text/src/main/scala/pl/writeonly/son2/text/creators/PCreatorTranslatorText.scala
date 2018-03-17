package pl.writeonly.son2.text.creators

import pl.writeonly.son2.core.config.{RWTConfig, TConfig}
import pl.writeonly.son2.core.notation.NotationTranslator
import pl.writeonly.son2.core.pcreators.PCreatorTranslator

import scala.util.control.Exception.catching
import pl.writeonly.sons.utils.ops.Pipe._

class PCreatorTranslatorText extends PCreatorTranslator {
  private val matcher = new MatcherStringEscape()

  override def isDefinedAt(c: RWTConfig): Boolean =
    catching(classOf[Exception])
      .opt(apply(c))
      .isDefined

  //  override def isDefinedAt(c: Config): Boolean = isDefinedAt(c.translate)

  override def apply(c: RWTConfig): NotationTranslator =
    new NotationTranslator(c.write, translatorMatch(c.translate))

  def translatorMatch(p: TConfig) = matcher.apply(p)

  def isDefinedAt(c: TConfig): Boolean = c && c.action && c.format.s

  def isDefinedAt(s: Symbol): Boolean = s && s != Symbol("")
}
