/**
 * Тестирование сайта Росгорстрах
 *
 * @autor Сергей Бударагин
 */

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
    private static WebDriver driver;

    public static void main(String[] args) {

        try {
            System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
            driver = new ChromeDriver();
            String url = "https://www.rgs.ru/";
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);

            // Выбор пункта Страхование
            findByXpathAndClick("//ol/li/a[contains(text(),'Страхование')]");

            // Выбор пункта Страхование выезжающих за рубеж
            findByXpathAndClick("//*[contains(text(),'Выезжающим')]");

            // Нажатие кнопки рассчитат онйлан
            scrollByXpath("//a[contains(text(),'Рассчитать')]");
            findByXpathAndClick("//a[contains(text(),'Рассчитать')]");

            // Сравнение текста в заголовке
            Wait<WebDriver> wait = new WebDriverWait(driver, 10, 1000);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(@class,'page-header')]/span"))));
            textCompare("//*[contains(@class,'page-header')]/span", "Страхование выезжающих за рубеж");

            // Выбор нескольких поездок в течении года
            scrollByXpath("//*[contains(text(), 'Несколько')]");
            findByXpathAndClick("//*[contains(text(), 'Несколько')]");

            // Ввод страны
            fillFormCountries("Countries", "Испания");

            // Заполнение поля дата первой поездки
            scrollByXpath("//*[contains(@data-bind,'FirstD')]");
            fillFormXpath("//*[contains(@data-bind,'FirstD')]", dateGenerate());

            // Выбор времени нахождени не боле 90 дней
            scrollByXpath("//*[contains(text(), 'Не более 90')]");
            findByXpathAndClick("//*[contains(text(), 'Не более 90')]");

            // Заполнение ФИО
            scrollByXpath("//input[contains(@class,'form-control')][@data-test-name='FullName']");
            fillFormXpathName("//input[contains(@class,'form-control')][@data-test-name='FullName']", "IVANOV", "IVAN");

            // Заполнение даты рождения
            scrollByXpath("//*[@data-test-name='BirthDate']");
            fillFormXpath("//*[@data-test-name='BirthDate']", "11121998");

            // Чек-бокс планируется ли активный отдых
            scrollByXpath("//div[contains(@data-bind,'active')]/div[contains(@class,'toggle')]");
            clickCheckBox("//div[contains(@data-bind,'active')]/div[contains(@class,'toggle')]");

            // Чек-бокс согласие на обработку персональных данных
            scrollByXpath("//input[contains(@data-test-name , 'IsProcessingPersonalDataTo')]");
            clickCheckBox("//input[contains(@data-test-name , 'IsProcessingPersonalDataTo')]");

            // Нажатие кнопки рассчитать
            scrollByXpath("//*[@data-test-name='NextButton'][contains(@data-bind,'Misc.NextButton')]");
            findByXpathAndClick("//*[@data-test-name='NextButton'][contains(@data-bind,'Misc.NextButton')]");

            Thread.sleep(5000);

            // Сравение ожидаемых и актуальных данных
            textCompare("//*[contains(@class,'summary-value')][contains(@data-bind,'Trips')]", "Многократные поездки в течение года");
            textCompare("//span/span/strong[contains(@data-bind, 'text: Name')]", "Шенген");
            textCompare("//strong[contains(@data-bind, 'text: Last')]", "IVANOV IVAN");
            textCompare("//strong[contains(@data-bind, 'text: Birth')]", "11.12.1998");
            textCompare("//div[contains(@data-bind, 'Актив')]/div[@class='summary-row']/span[@class='summary-value']/span", "Включен");

            Thread.sleep(1000);
            driver.close();

        } catch (Exception e) {
            driver.close();
            System.out.println("Ошибка " + e);

        }
    }

    /**
     * Метод нажимающий чек-бокс если он не активен
     *
     * @param xPath - xPath чек-бокса
     */
    private static void clickCheckBox(String xPath) {
        if (!driver.findElement(By.xpath(xPath)).isSelected()) {
            driver.findElement(By.xpath(xPath)).click();
        }
    }

    /**
     * Метод сравнивающий ожидаемый и реальный тексты
     *
     * @param xPath  xPath проверяемого текста
     * @param expect - ожидаемый текст
     */

    public static void textCompare(String xPath, String expect) throws Exception {
        scrollByXpath(xPath);
        if (driver.findElement(By.xpath(xPath)).getText().contains(expect)) {
            System.out.println("Искомый текст найден: " + expect);
        } else {
            throw new Exception("Искомый текст не найден: " + expect + " Вместо него:  " + driver.findElement(By.xpath(xPath)).getText());
        }
    }

    /**
     * Метод находящий элемент и кликающий на него
     *
     * @param xPath xPath элемента
     */
    public static void findByXpathAndClick(String xPath) {
        driver.findElement(By.xpath(xPath)).click();
    }

    /**
     * Метод проскролливающий до элемента
     *
     * @param xPath xPath элемента
     */
    public static void scrollByXpath(String xPath) {
        WebElement ScrollLocation = driver.findElement(By.xpath(xPath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ScrollLocation);
    }

    /**
     * Метод заполнения поля по его ID
     *
     * @param formId Id элемента
     * @param text   текст для заполнения
     */
    public static void fillFormCountries(String formId, String text) {
        driver.findElement(By.id(formId)).click();
        driver.findElement(By.id(formId)).clear();
        driver.findElement(By.id(formId)).sendKeys(text);
        driver.findElement(By.id(formId)).sendKeys(Keys.DOWN, Keys.RETURN);
    }

    /**
     * Метод заполнения поля по его xPath
     *
     * @param xPath xPath элемента
     * @param text  текст для заполнения
     */

    public static void fillFormXpath(String xPath, String text) {
        driver.findElement(By.xpath(xPath)).click();
        driver.findElement(By.xpath(xPath)).clear();
        driver.findElement(By.xpath(xPath)).sendKeys(text);
    }

    /**
     * Метод для заполнения фио
     *
     * @param xPath     xPath элемента
     * @param lastName  фамилия
     * @param firstName имя
     */
    public static void fillFormXpathName(String xPath, String lastName, String firstName) {
        driver.findElement(By.xpath(xPath)).click();
        driver.findElement(By.xpath(xPath)).clear();
        driver.findElement(By.xpath(xPath)).sendKeys(lastName, Keys.SPACE, firstName);
    }

    /**
     * Метод генерирующий дату в промежутке от завтра до двух недель вперед
     *
     * @return сгенерированная дата
     */
    public static String dateGenerate() {
        Random random = new Random();
        Long date = (new Date().getTime()) + ((random.nextInt(13) + 1) * 24 * 3600 * 1000);
        Date Date = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM y");
        String dateString = dateFormat.format(Date);
        dateString = dateString.replace(" ", "");
        return dateString;
    }
}

