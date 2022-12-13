import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShikimoriPage {
    WebDriver driver;
    public final String URL = "https://shikimori.one/";
    public final String errorMessage = "Тест упал у вас... Соболезнуем.";


    // 1. Данные: Открытие домашней страницы интернет-приложения.
    private String xpathToCheckWebsiteIsOpened = "//span[@class='submenu-triangle icon-home']/span";
    public final String TEXT_TO_CHECK_WEBSITE_IS_OPENED = "Главная";

    // 2. Данные: Открытие страницы с формой логина.
    private String xpathToLoginButton = "//a[@class='menu-icon sign_in']";
    private String xpathToCheckLoginPageIsOpened = "//header[@class='head m15']/h1";
    public final String TEXT_TO_CHECK_LOGIN_PAGE_IS_OPENED = "Авторизация";

    // 3. Данные: Заполнение формы логина корректными данными и вход на домашнюю страницу для зарегистрированного пользователя.
     private String xpathInputLogin = "//input[@id='user_nickname']";
     private String xpathInputPassword = "//input[@id='user_password']";
     private String xpathToCaptcha = "//iframe[@title='reCAPTCHA']";
     private String xpathToSpanInCaptcha = "//div[@class='recaptcha-checkbox-border']";
     private String xpathToSubmitButton = "//input[@class='btn-primary btn-submit btn']";
     private String xpathToCheckWebsiteIsOpenedForRegistered = "//span[@class='nickname']";
    public final String TEXT_TO_FILL_LOGIN_INPUT = "palswick";
    public final String TEXT_TO_FILL_PASSWORD_INPUT = "Ghyuas32o2";
    public final String TEXT_TO_CHECK_WEBSITE_IS_OPENED_FOR_REGISTERED = TEXT_TO_FILL_LOGIN_INPUT;

    // 4. Данные: Заполнение формы логина некорректными данными (пустые поля).
    private String xpathToCheckIfSignInCanceled = "//div[@class='toastify on error toastify-right toastify-top']";
    public final String TEXT_TO_CHECK_ERROR_MESSAGE_IS_SHOWN = "Неверный Логин или пароль.";

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
    public void typeSomething(String xpath, String stringToBeTyped) {
        By byInputName = By.xpath(xpath);
        WebElement elementInputName = driver.findElement(byInputName);
        elementInputName.sendKeys(stringToBeTyped);
    }

    public void fillFormAndClickSubmit(String xpathForLogin, String login,
                                       String xpathForPassword, String password,
                                       String xpathForSubmitButton)
    {
        typeSomething(xpathForLogin, login);
        Utilities.waiter(3000);

        typeSomething(xpathForPassword, password);
        Utilities.waiter(3000);

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
//                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.recaptcha.net/recaptcha/api2/')]")));
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();

        System.out.println("Clicked the checkbox");

        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='btn-primary btn-submit btn']"))).click();

        //wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//input[@class='btn-primary btn-submit btn']")));

        clickButton(xpathForSubmitButton);
    }
}
