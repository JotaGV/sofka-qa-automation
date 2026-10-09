
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

// Representa la página de detalle de un producto.
public class PaginaProducto extends PageObject {

    public PaginaProducto(WebDriver navegador) {
        super(navegador);
    }

    // Agrega al carrito el producto que estamos visualizando.
    public void agregarAlCarrito() {

        // Esperamos hasta que el enlace Add to cart sea clicable.
        find(By.linkText("Add to cart"))
                .waitUntilClickable().click();

        // Demoblaze muestra una alerta nativa de JavaScript.
        // Esperamos un máximo de 15 segundos y la aceptamos.
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.alertIsPresent())
                .accept();

        // La presencia real del producto se comprobará
        // posteriormente en la página del carrito.
    }
}
