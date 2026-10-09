
package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Representa la página principal de Demoblaze.
// Contiene las operaciones de navegación hacia productos y carrito.
public class PaginaInicio extends PageObject {

    // Utilizamos el mismo navegador administrado por Serenity.
    public PaginaInicio(WebDriver navegador) {
        super(navegador);
    }

    // Abre el catálogo principal de productos.
    public void abrirInicio() {
        getDriver().get("https://www.demoblaze.com/");

        // Maximiza la ventana para facilitar la visualización.
        // Se ejecuta cada vez que llamamos a abrirInicio().
        getDriver().manage().window().maximize();
    }

    // Selecciona un producto mediante el nombre visible en el catálogo.
    public void seleccionarProducto(String nombreProducto) {

        // Espera a que el enlace pueda recibir un clic.
        // linkText busca una coincidencia exacta con el texto del enlace.
        find(By.linkText(nombreProducto))
                .waitUntilClickable().click();
    }

    // Accede a la página del carrito.
    public void abrirCarrito() {

        // cartur es el identificador HTML del enlace Cart.
        find(By.id("cartur"))
                .waitUntilClickable().click();
    }
}
