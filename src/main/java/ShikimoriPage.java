import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShikimoriPage {
    WebDriver driver;

    public final String URL = "https://shikimori.one/";

    // 1. Данные: Открытие домашней страницы интернет-приложения.
    private String xpathToCheckWebsiteIsOpened = "//div[@class='copyright']";
    public final String TEXT_TO_CHECK_WEBSITE_IS_OPENED = "© shikimori.one 2011-2022";

    // 2. Данные: Открытие страницы с формой логина.

    private String xpathToLoginButton = "//a[@class='menu-icon sign_in']";
    private String xpathToCheckLoginPageIsOpened = "//header[@class='head m15']/h1";
    public final String TEXT_TO_CHECK_LOGIN_PAGE_IS_OPENED = "Авторизация";

    // private String xpathInputName = "//input[@name='name']";
    // private String xpathButtonSubmit = "//input[@type='submit']";

    public ShikimoriPage(WebDriver driver){
        this.driver = driver;
    }

    public String getResultText(String xpath) {
        By byResultText = By.xpath(xpath);
        WebElement elementResultText = driver.findElement(byResultText);
        return elementResultText.getText();
    }
    public void clickButton(String xpath) {
        By byButtonClick = By.xpath(xpath);
        WebElement elementButtonClick = driver.findElement(byButtonClick);
        elementButtonClick.click();
    }

    //    public void typeName (String name) {
//        By byInputName = By.xpath(xpathInputName);
//        WebElement elementInputName = driver.findElement(byInputName);
//        elementInputName.sendKeys(name);
//    }


//    public void fillFormAndClickSubmit(String name, String height, String weight){
//        typeName(name);
//        clickSubmit();
//        getResultText();
//    }
}
