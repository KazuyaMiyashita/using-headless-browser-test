import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.machinepublishers.jbrowserdriver.Timezone
import com.machinepublishers.jbrowserdriver.JBrowserDriver
import com.machinepublishers.jbrowserdriver.Settings

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import jbrowserutil._

object Main extends App {

  implicit val settings: Settings = Settings.builder()
    .timezone(Timezone.ASIA_TOKYO)
    .build()

  print("> ")
  val url = readLine

  val result: Future[WrappedPageResult] = WrappedPageResult.fromUrl(url)
  result.onSuccess { case wp =>
    println(wp.url)
    println(wp.statusCode)
    println(wp.title)
    println(wp.pageSource)

    val imgsrcs: List[String] = wp.findElementsByXPath("//img").map(_.getAttribute("src"))
    imgsrcs foreach println

    wp.quit()
  }
  
}
