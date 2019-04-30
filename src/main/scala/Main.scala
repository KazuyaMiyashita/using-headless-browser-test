import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.machinepublishers.jbrowserdriver.Timezone
import com.machinepublishers.jbrowserdriver.JBrowserDriver
import com.machinepublishers.jbrowserdriver.Settings



object Main extends App {
  // You can optionally pass a Settings object here,
  // constructed using Settings.Builder
  val driver: JBrowserDriver = new JBrowserDriver(Settings.builder()
    .timezone(Timezone.ASIA_TOKYO).build())

  print("> ")
  val url = readLine

  // This will block for the page load and any
  // associated AJAX requests
  driver.get(url)

  // You can get status code unlike other Selenium drivers.
  // It blocks for AJAX requests and page loads after clicks 
  // and keyboard events.
  System.out.println(driver.getStatusCode())

  // Returns the page source in its current state, including
  // any DOM updates that occurred after page load
  System.out.println(driver.getPageSource())

  val imgsrcs: List[String] = {
    import collection.JavaConverters._
    driver.findElementsByXPath("//img").asScala.map(_.getAttribute("src")).toList
  }

  imgsrcs foreach println
  
  // Close the browser. Allows this thread to terminate.
  driver.quit()
}
