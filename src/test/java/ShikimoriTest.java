import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Field;


public class ShikimoriTest {
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

    // 3. Тест: Заполнение формы логина корректными данными и вход на домашнюю страницу для зарегистрированного пользователя.
    @Test
    public void testFillAuthorizationFormWithValidData() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath на input логина
        Field xpathToInputLogin = page.getClass().getDeclaredField("xpathInputLogin");
        xpathToInputLogin.setAccessible(true);
        String xpathToLoginInput = (String) xpathToInputLogin.get(page);

        // Обращаемся к приватному полю с xpath на input пароля
        Field xpathToInputPassword = page.getClass().getDeclaredField("xpathInputPassword");
        xpathToInputPassword.setAccessible(true);
        String xpathToPasswordInput = (String) xpathToInputPassword.get(page);

        // Обращаемся к приватному полю с xpath для кнопки "Войти"
        Field xpathToSubmitButton = page.getClass().getDeclaredField("xpathToSubmitButton");
        xpathToSubmitButton.setAccessible(true);
        String xpathToSubmitInput = (String) xpathToSubmitButton.get(page);

        // Обращаемся к приватному полю с xpath на никнейм профиля (он будет равен никнейму из логина)
        Field xpathToCheckWebsiteIsOpenedForRegistered = page.getClass().getDeclaredField("xpathToCheckWebsiteIsOpenedForRegistered");
        xpathToCheckWebsiteIsOpenedForRegistered.setAccessible(true);
        String xpathToCheckWebsiteIsOpened = (String) xpathToCheckWebsiteIsOpenedForRegistered.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(5000);

        page.fillFormAndClickSubmit(xpathToLoginInput, page.TEXT_TO_FILL_LOGIN_INPUT,
                                    xpathToPasswordInput, page.TEXT_TO_FILL_PASSWORD_INPUT,
                                    xpathToSubmitInput);

        String actual = page.getResultText(xpathToCheckWebsiteIsOpened);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_WEBSITE_IS_OPENED_FOR_REGISTERED, actual, page.errorMessage);
        driver.quit();
    }

    // 4. Тест: Заполнение формы логина некорректными данными (пустые поля).
    @Test
    public void testFillAuthorizationFormWithNoData() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath для кнопки "Войти"
        Field xpathToSubmitButton = page.getClass().getDeclaredField("xpathToSubmitButton");
        xpathToSubmitButton.setAccessible(true);
        String xpathToSubmitInput = (String) xpathToSubmitButton.get(page);

        // Обращаемся к приватному полю с xpath на сообщение об ошибке
        Field xpathToCheckIfSignInCanceled = page.getClass().getDeclaredField("xpathToCheckIfSignInCanceled");
        xpathToCheckIfSignInCanceled.setAccessible(true);
        String xpathToCheckErrorMessageIsShown = (String) xpathToCheckIfSignInCanceled.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(10000);
        page.clickButton(xpathToSubmitInput);
        Utilities.waiter(1000);

        String actual = page.getResultText(xpathToCheckErrorMessageIsShown);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_ERROR_MESSAGE_IS_SHOWN, actual, page.errorMessage);
        driver.quit();
    }

    // 4.1 Тест: Заполнение формы логина некорректными данными (пустое поле пароля).
    @Test
    public void testFillAuthorizationFormWithNoPassword() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath на input логина
        Field xpathToInputLogin = page.getClass().getDeclaredField("xpathInputLogin");
        xpathToInputLogin.setAccessible(true);
        String xpathToLoginInput = (String) xpathToInputLogin.get(page);

        // Обращаемся к приватному полю с xpath для кнопки "Войти"
        Field xpathToSubmitButton = page.getClass().getDeclaredField("xpathToSubmitButton");
        xpathToSubmitButton.setAccessible(true);
        String xpathToSubmitInput = (String) xpathToSubmitButton.get(page);

        // Обращаемся к приватному полю с xpath на сообщение об ошибке
        Field xpathToCheckIfSignInCanceled = page.getClass().getDeclaredField("xpathToCheckIfSignInCanceled");
        xpathToCheckIfSignInCanceled.setAccessible(true);
        String xpathToCheckErrorMessageIsShown = (String) xpathToCheckIfSignInCanceled.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(2000);
        page.typeSomething(xpathToLoginInput, page.TEXT_TO_FILL_LOGIN_INPUT);
        Utilities.waiter(10000);
        page.clickButton(xpathToSubmitInput);
        Utilities.waiter(1000);

        String actual = page.getResultText(xpathToCheckErrorMessageIsShown);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_ERROR_MESSAGE_IS_SHOWN, actual, page.errorMessage);
        driver.quit();
    }

    // 4.2 Тест: Заполнение формы логина некорректными данными (пустое поле логина).
    @Test
    public void testFillAuthorizationFormWithNoLogin() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath на input пароля
        Field xpathToInputPassword = page.getClass().getDeclaredField("xpathInputPassword");
        xpathToInputPassword.setAccessible(true);
        String xpathToPasswordInput = (String) xpathToInputPassword.get(page);

        // Обращаемся к приватному полю с xpath для кнопки "Войти"
        Field xpathToSubmitButton = page.getClass().getDeclaredField("xpathToSubmitButton");
        xpathToSubmitButton.setAccessible(true);
        String xpathToSubmitInput = (String) xpathToSubmitButton.get(page);

        // Обращаемся к приватному полю с xpath на сообщение об ошибке
        Field xpathToCheckIfSignInCanceled = page.getClass().getDeclaredField("xpathToCheckIfSignInCanceled");
        xpathToCheckIfSignInCanceled.setAccessible(true);
        String xpathToCheckErrorMessageIsShown = (String) xpathToCheckIfSignInCanceled.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(2000);
        page.typeSomething(xpathToPasswordInput, page.TEXT_TO_FILL_PASSWORD_INPUT);
        Utilities.waiter(10000);
        page.clickButton(xpathToSubmitInput);
        Utilities.waiter(1000);

        String actual = page.getResultText(xpathToCheckErrorMessageIsShown);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_ERROR_MESSAGE_IS_SHOWN, actual, page.errorMessage);
        driver.quit();
    }

    // 5. Тест: Заполнение формы логина некорректными данными (рандомный логин, пароль).
    @Test
    public void testFillAuthorizationFormWithInValidData() throws NoSuchFieldException, IllegalAccessException {
        //WHEN

        // Обращаемся к приватному полю с xpath на кнопку "Вход"
        Field xpathToLoginButtonField = page.getClass().getDeclaredField("xpathToLoginButton");
        xpathToLoginButtonField.setAccessible(true);
        String xpathToLoginButton = (String) xpathToLoginButtonField.get(page);

        // Обращаемся к приватному полю с xpath на input логина
        Field xpathToInputLogin = page.getClass().getDeclaredField("xpathInputLogin");
        xpathToInputLogin.setAccessible(true);
        String xpathToLoginInput = (String) xpathToInputLogin.get(page);

        // Обращаемся к приватному полю с xpath на input пароля
        Field xpathToInputPassword = page.getClass().getDeclaredField("xpathInputPassword");
        xpathToInputPassword.setAccessible(true);
        String xpathToPasswordInput = (String) xpathToInputPassword.get(page);

        // Обращаемся к приватному полю с xpath для кнопки "Войти"
        Field xpathToSubmitButton = page.getClass().getDeclaredField("xpathToSubmitButton");
        xpathToSubmitButton.setAccessible(true);
        String xpathToSubmitInput = (String) xpathToSubmitButton.get(page);

        // Обращаемся к приватному полю с xpath на сообщение об ошибке
        Field xpathToCheckIfSignInCanceled = page.getClass().getDeclaredField("xpathToCheckIfSignInCanceled");
        xpathToCheckIfSignInCanceled.setAccessible(true);
        String xpathToCheckErrorMessageIsShown = (String) xpathToCheckIfSignInCanceled.get(page);


        page.clickButton(xpathToLoginButton);
        Utilities.waiter(3000);

        page.typeSomething(xpathToLoginInput, PasswordGenerator.generateRandomString("abcdefghijklmnopqrstuvwxyz", 12));
        Utilities.waiter(3000);

        page.typeSomething(xpathToPasswordInput, PasswordGenerator.generateStrongPassword());
        Utilities.waiter(5000);

        page.clickButton(xpathToSubmitInput);
        Utilities.waiter(1000);

        String actual = page.getResultText(xpathToCheckErrorMessageIsShown);

        //THEN
        Assertions.assertEquals(page.TEXT_TO_CHECK_ERROR_MESSAGE_IS_SHOWN, actual, page.errorMessage);
        driver.quit();
    }
}
