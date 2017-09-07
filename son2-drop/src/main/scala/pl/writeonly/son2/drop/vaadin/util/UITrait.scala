package pl.writeonly.son2.drop.vaadin.util

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.{CheckBoxGroup, _}
import pl.writeonly.son2.core.config.Config
import pl.writeonly.son2.core.converters.Converter
import pl.writeonly.son2.core.glue.{Piper, Streamers}

import scala.collection.JavaConverters._


trait UITrait extends UI with UIUtil {

  val natives = List("Print", "String")

  def jacksonOutputFormat(selected: String) = radioButtonGroup("Output formats:", Mappings.jacksonFormatsMapping, selected)

  def components: List[Component]

  def nativeGroup = new CheckBoxGroup("Native:", natives.asJavaCollection)

  def debug(configLabel: Label, config: Config, set: Set[String]) = configLabel.setValue(config.toString + "\n" + set)

  def convert2(converter: Converter, input: TextArea, output: Label, items: Set[String]) = {
    val inputValue = input.getValue
    val streamer = new Piper(null, converter).print(items.contains("Print"))
    val outputValue = streamer.convertString(items.contains("String"), inputValue)
    output.setValue(outputValue)
  }

  def optionsPanel(components: List[Component]): Panel = {
    val result = new Panel("Options", optionsHorizontalLayout(components))
    setWidth(result)
    result
  }

  def inputTextArea: TextArea = inputTextArea(inputJson)

  def inputJson = "Input Json"

  def convertButton(listener: Button.ClickListener): Button = {
    val result = new Button("Convert", listener)
    setWidth(result)
    result
  }

  @Override
  override protected def init(vaadinRequest: VaadinRequest): Unit = {
    val layout = layoutVerticalLayout
    setContent(layout)
    layout.addComponents(components: _*)
  }


}
