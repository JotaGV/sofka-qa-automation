
package tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;

import pages.PaginaInicio;
import pages.PaginaProducto;
import pages.PaginaCarrito;

// Integra la ejecución de JUnit 5 con Serenity BDD.
@ExtendWith(SerenityJUnit5Extension.class)
public class PruebaCompraDemoblaze {

    // Serenity administra la instancia del navegador Chrome.
    @Managed(driver = "chrome")
    WebDriver navegador;

    @Test
    void debeComprarDosProductosCorrectamente() {

        // Todos los Page Objects comparten el mismo navegador.
        PaginaInicio inicio = new PaginaInicio(navegador);
        PaginaProducto producto = new PaginaProducto(navegador);
        PaginaCarrito carrito = new PaginaCarrito(navegador);

        // PASO 1: Agregar el primer producto al carrito.
        inicio.abrirInicio();
        inicio.seleccionarProducto("Samsung galaxy s6");
        producto.agregarAlCarrito();

        // PASO 2: Agregar el segundo producto al carrito.
        inicio.abrirInicio();
        inicio.seleccionarProducto("Sony xperia z5");
        producto.agregarAlCarrito();

        // PASO 3: Visualizar y comprobar el contenido del carrito.
        inicio.abrirCarrito();

        // Verifica que estén presentes los dos productos elegidos.
        assertThat(carrito.contieneProducto("Samsung galaxy s6")).isTrue();
        assertThat(carrito.contieneProducto("Sony xperia z5")).isTrue();

        // Comprueba que el importe total sea positivo.
        // No verifica que la suma de los precios sea exacta.
        assertThat(carrito.obtenerTotal()).isGreaterThan(0);

        // PASO 4: Abrir y completar el formulario de compra.
        carrito.abrirFormularioCompra();
        carrito.completarFormularioCompra();

        // PASO 5: Finalizar la compra.
        carrito.finalizarCompra();

        // VALIDACIÓN FINAL:
        // Confirma que aparezca el mensaje esperado de compra.
        assertThat(carrito.obtenerMensajeConfirmacion())
                .contains("Thank you for your purchase!");
    }
}
