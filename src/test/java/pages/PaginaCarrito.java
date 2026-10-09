
package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.pages.PageObject;

// Representa el carrito y el formulario de compra de Demoblaze.
public class PaginaCarrito extends PageObject {

    // Localiza las filas que contienen los productos del carrito.
    private final By filasCarrito = By.cssSelector("#tbodyid tr");

    public PaginaCarrito(WebDriver navegador) {
        super(navegador);
    }

    // Comprueba si el carrito contiene el producto indicado.
    public boolean contieneProducto(String nombreProducto) {

        WebDriverWait espera =
                new WebDriverWait(getDriver(), Duration.ofSeconds(15));

        // Esperamos hasta que existan al menos dos filas.
        // Esta condición corresponde al escenario solicitado.
        // Si aparece un solo producto, se producirá un timeout.
        espera.until(navegador ->
                navegador.findElements(filasCarrito).size() >= 2);

        // Recuperamos las filas que representan los productos.
        List<WebElement> filas =
                getDriver().findElements(filasCarrito);

        // Devuelve true si alguna fila contiene el nombre buscado.
        return filas.stream()
                .anyMatch(fila ->
                        fila.getText().contains(nombreProducto));
    }

    // Obtiene el importe total que muestra el carrito.
    public int obtenerTotal() {

        // Espera a que aparezca el elemento con el importe.
        String valor = new WebDriverWait(
                getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("totalp")))
                .getText().trim();

        // Convierte el texto del importe en un número entero.
        return Integer.parseInt(valor);
    }

    // Abre el formulario para registrar los datos de compra.
    public void abrirFormularioCompra() {

        // Localiza el botón Place Order por su texto visible.
        find(By.xpath("//button[normalize-space()='Place Order']"))
                .waitUntilClickable().click();

        // Espera hasta que el formulario emergente sea visible.
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("orderModal")));
    }

    // Completa los campos solicitados por Demoblaze.
    public void completarFormularioCompra() {

        // Utilizamos datos fijos para este escenario de prueba.
        find(By.id("name")).type("QA Demo");
        find(By.id("country")).type("Ecuador");
        find(By.id("city")).type("Cuenca");

        // Número ficticio utilizado exclusivamente en la demo.
        find(By.id("card")).type("4111111111111111");

        find(By.id("month")).type("10");
        find(By.id("year")).type("2026");
    }

    // Envía el formulario para finalizar la compra.
    public void finalizarCompra() {

        // Selecciona Purchase dentro del formulario de compra.
        find(By.xpath(
                "//div[@id='orderModal']//button[normalize-space()='Purchase']"))
                .waitUntilClickable().click();
    }

    // Obtiene el mensaje mostrado después de finalizar la compra.
    public String obtenerMensajeConfirmacion() {

        // Espera el encabezado de la ventana de confirmación.
        // Devuelve su texto para validarlo desde la prueba.
        return new WebDriverWait(
                getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".sweet-alert h2")))
                .getText();
    }
}
