import org.openqa.selenium.WebDriver;

public class TestPraticoPage {

    private DSL dsl;

    public TestPraticoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }
    public void setEmail (String email){
        dsl.escreve("email",email);
    }
    public void setSenha (String senha){
        dsl.escreve("password",senha);
    }
    public void setConfirmarSenha(String confirmarSenha){
        dsl.escreve("password_confirmation",confirmarSenha);
    }
}
