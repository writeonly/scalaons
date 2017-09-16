package pl.writeonly.son2.drop.vaadin.composites
import com.vaadin.ui.Component
import pl.writeonly.son2.drop.vaadin.util.{UITrait}
import com.jayway.jsonpath.{Option => jOption}

class CompositePathOptions extends Composite {
  private val component = CompositePathOptions.apply
  override def components: List[Component] = List(component)
  def selectedItem = CompositePathOptions.selectedItem(component, CompositePathOptions.mapping)
}

object CompositePathOptions extends UITrait {
  private val mapping = Map[String, Symbol](
    "As path list" -> toSymbol(jOption.AS_PATH_LIST),
    "Always return list" -> toSymbol(jOption.ALWAYS_RETURN_LIST),
    "default path leaf to null" -> toSymbol(jOption.DEFAULT_PATH_LEAF_TO_NULL),
    "requite properties" -> toSymbol(jOption.REQUIRE_PROPERTIES),
    "suppress exception" -> toSymbol(jOption.SUPPRESS_EXCEPTIONS))

  private def toSymbol(option : jOption) = Symbol(option.name())

  private def apply = checkBoxGroup("Native:", CompositePathOptions.mapping)
}
