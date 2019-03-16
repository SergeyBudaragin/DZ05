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

        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        driver = new ChromeDriver();
        String url = "https://www.rgs.ru/";
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        findByXpathAndClick("//*[@id=\"main-navbar-collapse\"]/ol[1]/li[2]/a");
        findByXpathAndClick("//*[@id=\"rgs-main-menu-insurance-dropdown\"]/div[1]/div[1]/div/div[2]/div[2]/ul/li[2]/a");

        scrollByXpath("/html/body/div[6]/div/div[2]/div/div[4]/div[1]/div/div[2]/a");
        findByXpathAndClick("/html/body/div[6]/div/div[2]/div/div[4]/div[1]/div/div[2]/a");

        textCompare("/html/body/div[4]/div[1]/div/span", "Страхование выезжающих за рубеж");

        findByXpathAndClick("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[1]/btn-radio-group/div/button[2]");
        fillFormCountries("Countries", "Испания");

        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[4]/div[2]/div[1]/input");
        fillFormXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[4]/div[2]/div[1]/input", dateGenerate());
        driver.findElement(By.xpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[4]/div[2]/div[1]/input")).sendKeys(Keys.RETURN);

        findByXpathAndClick("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[4]/div[2]/div[2]/btn-radio-group/div/label[2]");

        fillFormXpathName("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[7]/div/div[2]/div[1]/div/div[1]/div/input", "IVANOV", "IVAN");

        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[7]/div/div[2]/div[2]/input");
        fillFormXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[7]/div/div[2]/div[2]/input", "11121998");

        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[10]/div[1]/div[1]/div[1]/div/input");
        clickCheckBox("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[2]/div[10]/div[1]/div[1]/div[1]/div");

        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[3]/adaptive-checkbox/label/input");
        clickCheckBox("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[3]/adaptive-checkbox/label/input");

        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[4]/button");
        findByXpathAndClick("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[2]/div/div[1]/div/form/div[4]/button");

        scrollToTop();
        Wait<WebDriver> wait = new WebDriverWait(driver, 100, 2000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[1]/h2"))));
        scrollByXpath("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[1]/h2");
        findByXpathAndClick("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[1]/h2");

        textCompare("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[3]/div/div/div/div[1]/span[2]/span", "Многократные поездки в течение года");
        textCompare("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[3]/div/div/div/div[5]/span[2]/span[1]/strong", "Шенген");
        textCompare("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[3]/div/div/div/div[6]/div/span[2]/span/strong", "IVANOV IVAN");
        textCompare("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[3]/div/div/div/div[6]/div/span[2]/span/span/strong", "11.12.1998");
        textCompare("//*[@id=\"calc-vzr-steps\"]/myrgs-steps-partner-auth/div[1]/div/div/div[1]/div[3]/div/div/div/div[7]/div[1]/div", "Включен");


        driver.close();
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

    public static void textCompare(String xPath, String expect) {

        System.out.println((driver.findElement(By.xpath(xPath)).getText().contains(expect)) ? "Искомый текст найден: " + expect : "Искомый текст не найден: " + expect +
                " Вместо него:  " + driver.findElement(By.xpath(xPath)).getText());
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
     * Метод скроллящий наверх страницы
     */
    public static void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
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
        Long date = (new Date().getTime()) + (random.nextInt(14) * 24 * 3600 * 1000);
        Date Date = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM y");
        return dateFormat.format(Date);

    }
}

