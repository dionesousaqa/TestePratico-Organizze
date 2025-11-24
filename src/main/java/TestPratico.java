import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;


public class TestPratico {
    private WebDriver driver;
    private DSL dsl;
    private TestPraticoPage page;

    @Before
    public void inicializa() {
        WebDriverManager.firefoxdriver().clearDriverCache().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("intl.accept_languages", "pt-BR,pt");
        options.addPreference("intl.locale.requested", "pt-BR");
        options.addArguments("--headless=new"); // 🆕 modo headless do Firefox 120+

        driver = new FirefoxDriver(options);

        driver.get("https://www.organizze.com.br/");
        driver.findElement(By.xpath("//*[text()='Login']")).click();

        dsl = new DSL(driver);
        page = new TestPraticoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void loginVazio()  {


        WebElement campoEmail = driver.findElement(By.cssSelector("input[type='email']"));
        WebElement campoSenha = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement btnEntrar = driver.findElement(By.cssSelector("button[type='submit']"));

        campoEmail.clear();
        campoSenha.clear();

        btnEntrar.click();

        String valorEmail = campoEmail.getAttribute("value");

        Assert.assertTrue(valorEmail.isEmpty());




    }

    @Test
    public void loginSenhaVazio()  {
        page.setEmail("teste@teste");

        WebDriverWait waitCss = new WebDriverWait(driver ,Duration.ofSeconds(30));
        waitCss.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".h-\\[50px\\]")));

        dsl.clicarBotaoByCssSelector(".h-\\[50px\\]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("password")));

        String mensagem = dsl.extrairCampoSenha();
        System.out.println("Mensagem exibida: " + mensagem);

        assertThat(mensagem, anyOf(
                is("Preencha este campo."),
                is("Please fill out this field.")
        ));

    }

    @Test
    public void loginEmailVazio()  {

        page.setSenha("test");

        WebDriverWait waitCss = new WebDriverWait(driver ,Duration.ofSeconds(30));
        waitCss.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".h-\\[50px\\]")));

        dsl.clicarBotaoByCssSelector(".h-\\[50px\\]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));

        String mensagem = dsl.extrairCampoEmail();

        System.out.println("Mensagem exibida: " + mensagem);

        assertThat(mensagem, anyOf(
                is("Preencha este campo."),
                is("Please fill out this field.")
        ));


    }

    @Test
    public void esqueceuSenha()  {

        dsl.clicarBotao("//a[normalize-space(text())='Esqueci minha senha']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[normalize-space(text())='Recuperar senha']")));

        String RecupSenha = dsl.obterTexto("//h1[normalize-space(text())='Recuperar senha']");
        Assert.assertEquals("Recuperar senha", RecupSenha);

        WebDriverWait waitPath = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitPath.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//label[contains(text(), 'Seu')]")));

        String SeuE = dsl.obterTexto("//label[contains(text(), 'Seu')]");
        Assert.assertEquals("Seu email", SeuE);

        WebDriverWait waitXpath = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitXpath.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Recuperar')]")));
        String Button = dsl.obterTexto("//span[contains(text(), 'Recuperar')]");
        Assert.assertEquals("Recuperar senha", Button);

    }

    @Test
    public void logarComFacebook()  {
        dsl.clicarBotao("//button//p[normalize-space(text())='Entre com o Facebook']");

        WebDriverWait wats = new WebDriverWait(driver, Duration.ofSeconds(30));
        wats.until(ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector("#header_block ._9axz")));

        String entreface = dsl.obterTextoByCssSelector("#header_block ._9axz");
        Assert.assertEquals("Entrar no Facebook", entreface);

    }

    @Test
    public void logarComGoogle()  {
        dsl.clicarBotao("//form[2]//button//p[normalize-space(text())='Entre com o Google']");

        WebDriverWait wats = new WebDriverWait(driver, Duration.ofSeconds(30));
        wats.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//c-wiz/main//c-wiz/div/div/div[2]")));

        String entregoogle = dsl.obterTexto("//c-wiz/main//c-wiz/div/div/div[2]");
        Assert.assertEquals("Fazer Login com o Google", entregoogle);
    }

    @Test
    public void logarDadosInvalidos()  {
        page.setEmail("teste01@gmail.com");
        page.setSenha("12345");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//main//form//button)[3]")));

        driver.findElement(By.xpath("(//main//form//button)[3]")).submit();

        WebDriverWait wats = new WebDriverWait(driver, Duration.ofSeconds(30));
        wats.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//main//div//p[2])[1]")));

        String error = dsl.obterTexto("(//main//div//p[2])[1]");
        Assert.assertEquals("email ou senha inválidos", error);

    }

    @Test
    public void testTelaCadastro()  {
        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        WebDriverWait waitXpath = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitXpath.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//main//form//div[1]/label)[1]")));

        String Semail = dsl.obterTexto("(//main//form//div[1]/label)[1]");
        Assert.assertEquals("Seu email", Semail);

        WebDriverWait waitPath = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitPath.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//main//form//div[1]/label)[2]")));

        String Ssenha = dsl.obterTexto("(//main//form//div[1]/label)[2]");
        Assert.assertEquals("Sua senha", Ssenha);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//form//div[2]//div[2]/label")));

        String Rsenha = dsl.obterTexto("//form//div[2]//div[2]/label");
        Assert.assertEquals("Repetir senha", Rsenha);
    }

    @Test
    public void emailInvalido()  {
        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        WebDriverWait waitId = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitId.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
        WebElement campoEmail = driver.findElement(By.cssSelector("input[type='email']"));
        campoEmail.sendKeys("123");

        page.setSenha("123456");
        WebDriverWait waitIdConfirm = new WebDriverWait(driver, Duration.ofSeconds(30));
        waitIdConfirm.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("password_confirmation")));
        page.setConfirmarSenha("123456");

        driver.findElement(By.id("terms_of_use")).click();
        dsl.clicarBotao("(//form//button)[3]");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Boolean valido = (Boolean) js.executeScript(
                "return arguments[0].checkValidity();", campoEmail);
        Assert.assertFalse(valido);



    }

    @Test
    public void campoSenhaVazio()  {
        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
        page.setEmail("teste@gmail.com");

        driver.findElement(By.id("terms_of_use")).click();
        dsl.clicarBotao("(//form//button)[3]");

        WebElement campoEmail = driver.findElement(By.id("password"));

        String mensagem = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", campoEmail);

        System.out.println("Mensagem exibida: " + mensagem);

        assertThat(mensagem, anyOf(
                is("Preencha este campo."),
                is("Please fill out this field.")
        ));


    }

    @Test
    public void cadastrarEmailJaCadastrado()  {

        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        WebDriverWait waitId = new WebDriverWait(driver,Duration.ofSeconds(30));
        waitId.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));

        page.setEmail("teste25@gmail.com");

        page.setSenha("123456");

        page.setConfirmarSenha("123456");

        driver.findElement(By.id("terms_of_use")).click();
        dsl.clicarBotao("(//form//button)[3]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("(//form//div[1]/span)[1]")));

        String error = dsl.obterTexto("(//form//div[1]/span)[1]");

        assertThat(error, anyOf(
                is("já está em uso"),
                is("this está em uso")
        ));

    }

    @Test()
    public void cadastrarComRadioButtonDesmarcado()  {

        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));

        page.setEmail("teste29856@teste");

        page.setSenha("123456");

        page.setConfirmarSenha("123456");
        dsl.clicarBotao("(//form//button)[3]");

        WebElement termoUso = driver.findElement(By.id("terms_of_use"));

        String mensagem = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", termoUso);

        System.out.println("Mensagem exibida: " + mensagem);

        assertThat(mensagem, anyOf(
                is("Marque esta opção se quiser continuar."),
                is("Please check this box if you want to proceed.")
        ));



    }

    @Test
    public void cadastrarUsandoFacebook()  {

        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        dsl.clicarBotao("(//form[1]//button)[1]");

        String entrarface = dsl.obterTexto("//div/span/div");

        driver.findElement(By.id("email"));
        driver.findElement(By.id("pass"));

        Assert.assertEquals("Entrar no Facebook", entrarface);

    }

    @Test
    public void cadastrarUsandoGoogle()  {

        dsl.clicarBotao("/html/body/div[1]/main/div[3]/footer/p/a");

        dsl.clicarBotao("//form[2]//button");

        String fazerlog = dsl.obterTexto("//c-wiz/main//c-wiz/div/div/div[2]");

        driver.findElement(By.id("identifierId"));
        driver.findElement(By.xpath("(//c-wiz/main//button/div[3])[2]"));

        Assert.assertEquals("Fazer Login com o Google", fazerlog);

    }
}
