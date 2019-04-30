import com.machinepublishers.jbrowserdriver.Timezone
import com.machinepublishers.jbrowserdriver.Settings

// import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import scala.util.{Success, Failure}
import scala.io.StdIn.readLine

import jbrowserutil._

object Main extends App {

  implicit val settings: Settings = Settings.builder()
    .timezone(Timezone.ASIA_TOKYO)
    .loggerLevel(java.util.logging.Level.WARNING)
    .build()

  def loop(): Unit = {
    print("> ")
    readLine match {
      case x if x == null || x == "" => println("Bye!")
      case url => {
        val result: Future[WrappedPageResult] = WrappedPageResult.fromUrl(url)
        Await.ready(result, Duration.Inf)
        result.value.get match {
          case Success(wp) => {
            println("url: %s".format(wp.url))
            println("statusCode: %s".format(wp.statusCode))
            println("title: %s".format(wp.title))
            // println(wp.pageSource)
            
            val imgsrcs: List[String] = wp.findElementsByXPath("//img").map(_.getAttribute("src"))
            imgsrcs foreach println
            
            wp.quit()
          }
          case Failure(f) => println(f)
        }
        loop()
      }
    }
  }

  loop()
  
}
