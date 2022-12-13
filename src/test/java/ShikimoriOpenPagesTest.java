import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Field;

public class ShikimoriOpenPagesTest {
    WebDriver driver;
    ShikimoriPage page;

    @BeforeEach
    public void beforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new ShikimoriPage(driver);
        driver.get(page.URL);
    }
    @AfterEach
    public void afterMethod(){
        driver.quit();
    }


    // 1. Тест: Открытие домашней страницы интернет-приложения.
    @Test
    public void testOpenWebsite() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на футер домашней страницы
        Field xpathField = page.getClass().getDeclaredField("xpathToCheckWebsiteIsOpened");
        xpathField.setAccessible(true);
        String xpathToCheckWebsiteIsOpened = (String) xpathField.get(page);

        String actual = page.getResultText(xpathToCheckWebsiteIsOpened);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_WEBSITE_IS_OPENED, actual, page.errorMessage);
    }

    // 2. Тест: Открытие страницы с формой логина.
    @Test
    public void testOpenLoginPage() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath на h1 "Авторизация" страницы авторизации
        Field xpathToCheckLoginPageIsOpenedField = page.getClass().getDeclaredField("xpathToCheckLoginPageIsOpened");
        xpathToCheckLoginPageIsOpenedField.setAccessible(true);
        String xpathToCheckLoginPageIsOpened = (String) xpathToCheckLoginPageIsOpenedField.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(3000);
        String actual = page.getResultText(xpathToCheckLoginPageIsOpened);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_LOGIN_PAGE_IS_OPENED, actual, page.errorMessage);
        driver.quit();
    }
}
