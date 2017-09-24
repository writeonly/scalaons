package pl.writeonly.son2.drop.vaadin.complexes

import pl.writeonly.son2.drop.vaadin.util.{ItemSymbol, UITrait}

class GsonOptionsComp extends Complex {
  setCaption("Gson")
  private val component = GsonOptionsComp.apply
  setCompositionRoot(component)

  def selectedItem = PathOptionsComp.selectedItem(component).map(_.value)
}

object GsonOptionsComp extends UITrait {
  private val items = Set(
    ItemSymbol('disableHtmlEscaping),
    ItemSymbol('disableInnerClassSerialization),
    ItemSymbol('enableComplexMapKeySerialization),
    ItemSymbol('excludeFieldsWithoutExposeAnnotation),
    ItemSymbol('generateNonExecutableJson),
    ItemSymbol('serializeNulls),
    ItemSymbol('serializeSpecialFloatingPointValues),
    ItemSymbol('setLenient)
  )

  private def apply = checkBoxGroup("Gson options:", items)
}
