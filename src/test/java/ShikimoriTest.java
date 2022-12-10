import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Field;


public class ShikimoriTest {
    WebDriver driver;
    ShikimoriPage page;

    @Before
    public void beforeMethod(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new ShikimoriPage(driver);
        driver.get(page.URL);
    }
    @After
    public void afterMethod(){
        driver.quit();
    }


    // 1. Тест: Открытие домашней страницы интернет-приложения.
    @Test
    public void testOpenWebsite() throws NoSuchFieldException, IllegalAccessException {
        //WHEN
        Field xpathField = page.getClass().getDeclaredField("xpathToCheckWebsiteIsOpened");
        xpathField.setAccessible(true);
        String xpathToCheckWebsiteIsOpened = (String) xpathField.get(page);

        String actual = page.getResultText(xpathToCheckWebsiteIsOpened);

        //THEN
        Assert.assertEquals(page.TEXT_TO_CHECK_WEBSITE_IS_OPENED, actual);
    }

    // 2. Тест: Открытие страницы с формой логина.
    @Test
    public void testOpenLoginPage() throws NoSuchFieldException, IllegalAccessException {
        //WHEN
        //Обращаемся к приватным полям класса ShikimoriPage
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        Field xpathToCheckLoginPageIsOpenedField = page.getClass().getDeclaredField("xpathToCheckLoginPageIsOpened");
        xpathToCheckLoginPageIsOpenedField.setAccessible(true);
        String xpathToCheckLoginPageIsOpened = (String) xpathToCheckLoginPageIsOpenedField.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter();
        String actual = page.getResultText(xpathToCheckLoginPageIsOpened);

        //THEN
        Assert.assertEquals(page.TEXT_TO_CHECK_LOGIN_PAGE_IS_OPENED, actual);
        driver.quit();
    }
}
