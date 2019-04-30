package jbrowserutil

import org.openqa.selenium.WebElement
import com.machinepublishers.jbrowserdriver.JBrowserDriver
import com.machinepublishers.jbrowserdriver.Settings

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class WrappedPageResult(
  url: String,
  statusCode: Int,
  title: String,
  pageSource: String,
  private val driver: JBrowserDriver
) {

  // see another method
  // cf. http://machinepublishers.github.io/jBrowserDriver/com/machinepublishers/jbrowserdriver/JBrowserDriver.html
  def findElementsByXPath(xpath: String): List[WebElement] = {
    import collection.JavaConverters._
    driver.findElementsByXPath(xpath).asScala.toList
  }

  // Close the browser. Allows this thread to terminate.
  def quit(): Unit = {
    driver.quit() // FIX ME
  }

}

object WrappedPageResult {

  def fromUrl(url: String)(implicit settings: Settings): Future[WrappedPageResult] = Future {
    import com.machinepublishers.jbrowserdriver.Timezone
    import com.machinepublishers.jbrowserdriver.Settings

    val driver: JBrowserDriver = new JBrowserDriver(settings)

    driver.get(url)

    WrappedPageResult(
      url = url,
      statusCode = driver.getStatusCode,
      title = driver.getTitle,
      pageSource = driver.getPageSource,
      driver
    )
  }

}
