package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

public class RegistrationTest extends BaseTest {

    private String emailForDelete;
    private String passwordForDelete;

    @Test
    public void successRegistrationTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var registerPage = new RegisterPage(driver);

        String name = "TestName" + (int)(Math.random() * 10);

        // Записываем в поля класса, чтобы метод @After имел к ним доступ
        emailForDelete = "truepass" + (int)(Math.random() * 10) + "@yandex.ru";
        passwordForDelete = "qwerty123124";

        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();

        registerPage.register(name, emailForDelete, passwordForDelete);

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void registrationWithShortPasswordErrorTest() {
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        var registerPage = new RegisterPage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.register("TestName", "badpass" + (int) (Math.random() * 1000) + "@yandex.ru", "12345");

        Assert.assertTrue(registerPage.isPasswordErrorDisplayed());
    }


    @After
    public void teardown() {
        // Проверяем, что в тесте успели сгенерироваться email и пароль
        if (emailForDelete != null && passwordForDelete != null) {
            try {
                // Авторизуемся по ручке /api/auth/login
                // Передаем email в качестве логина
                String jsonBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", emailForDelete, passwordForDelete);

                var loginResponse = io.restassured.RestAssured.given()
                        .header("Content-Type", "application/json")
                        .body(jsonBody)
                        .when()
                        .post("https://qa-stellarburgers.education-services.ru/api/auth/login");

                // ШАГ 2: Если авторизация успешна (статус 200), забираем токен и удаляем юзера
                if (loginResponse.getStatusCode() == 200) {
                    // Извлекаем строку accessToken из ответа сервера
                    String token = loginResponse.then().extract().path("accessToken");

                    // Отправляем DELETE запрос на удаление с полученным токеном
                    io.restassured.RestAssured.given()
                            .header("Authorization", token)
                            .when()
                            .delete("https://qa-stellarburgers.education-services.ru/api/auth/user")
                            .then()
                            .statusCode(202); // Код 202 означает успешное удаление аккаунта

                    System.out.println("=== Пользователь успешно авторизован и удален после теста ===");
                } else {
                    System.out.println("Не удалось авторизовать пользователя для удаления. Статус: " + loginResponse.getStatusCode());
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка при очистке данных: " + e.getMessage());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

