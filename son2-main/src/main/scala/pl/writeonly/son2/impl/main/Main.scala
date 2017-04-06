package pl.writeonly.son2.impl.main

import pl.writeonly.son2.core.formats.{Config, FormatProvider}
import pl.writeonly.son2.core.providers._
import pl.writeonly.son2.core.util.AppLogging

object Main extends AppLogging {

  val providerOpt = args.length match {
    case 0 => Option.empty
    case _ => provider(Config(o = args(0).toLowerCase))
  }

  def provider(arg: Config): Option[Provider] = new FormatProvider(arg).apply(arg.o)

  providerOpt.map { p =>
    val main = new Piper(p)
    args.length match {
      case 1 => main.convertStream();
      case 2 => main.convertFile(args(1));
      case _ => main.convertFile(args(1), args(2));
    }
  }.getOrElse {
    val main = new Piper(new ProviderImpl)
    main.convertResource("README.md")
  }
}
