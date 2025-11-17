import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void escreve(String id_campo, String texto) {

        driver.findElement(By.id(id_campo)).sendKeys(texto);
    }

    public void clicarBotao(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clicarBotaoByCssSelector(String cssClass) {
        driver.findElement(By.cssSelector(cssClass)).click();
    }

    public String obterTexto(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String obterTextoByCssSelector(String CssSelector) {
        return driver.findElement(new By.ByCssSelector(CssSelector)).getText();
    }

    public String extrairCampoSenha() {
        WebElement campoSenha = driver.findElement(By.id("password"));

        String mensagem = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", campoSenha);

        return mensagem;
    }
    public String extrairCampoEmail(){
        WebElement campoEmail = driver.findElement(By.id("email"));

        String mensagem = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", campoEmail);
        return mensagem;
    }

}
