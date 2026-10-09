PROYECTO
Sofka QA - Automatización E2E de compra en Demoblaze

DESCRIPCIÓN
Este proyecto implementa una prueba automatizada básica del proceso de compra en https://www.demoblaze.com/. Utiliza Java, Maven, Serenity BDD, JUnit 5 y Selenium WebDriver, siguiendo el patrón Page Object Model. Las clases y los métodos propios se nombraron en español para facilitar su lectura.

REQUISITOS
Se requiere un JDK compatible con la configuración del proyecto, Maven y Google Chrome. El proyecto fue ejecutado satisfactoriamente en Windows desde Eclipse y mediante Maven. Las dependencias y los plugins están definidos en pom.xml.

ESTRUCTURA DEL PROYECTO
Las clases principales se encuentran en src/test/java:
- pages/PaginaInicio.java: navegación por el catálogo y acceso al carrito.
- pages/PaginaProducto.java: incorporación del producto al carrito.
- pages/PaginaCarrito.java: consulta del carrito, formulario y confirmación de compra.
- tests/PruebaCompraDemoblaze.java: escenario de prueba y validaciones.

ESCENARIO AUTOMATIZADO
1. Abrir Demoblaze y agregar Samsung galaxy s6 al carrito.
2. Regresar al catálogo y agregar Sony xperia z5.
3. Abrir el carrito y verificar que aparezcan ambos productos y un total positivo.
4. Completar el formulario con datos de prueba y finalizar la compra.
5. Comprobar el mensaje "Thank you for your purchase!".

EJECUCIÓN DESDE MAVEN
Después de clonar o descargar el repositorio, abrir PowerShell en la carpeta que contiene pom.xml y ejecutar:

mvn clean verify "-Dit.test=PruebaCompraDemoblaze"

Maven ejecuta el escenario mediante Failsafe y genera el informe agregado de Serenity. El resultado de la ejecución aparece en la consola. El reporte HTML se encuentra en target/site/serenity/index.html.

EJECUCIÓN DESDE ECLIPSE
Abrir tests.PruebaCompraDemoblaze y seleccionar Run As > JUnit Test. Esta opción permite ejecutar el caso directamente; para generar el reporte agregado se utiliza Maven. Para observar Chrome durante la ejecución, configurar headless.mode = false en la configuración de Serenity.

ALCANCE
Esta es una implementación deliberadamente básica para cubrir el escenario solicitado. Emplea dos productos y datos de formulario definidos en el código. No realiza selección aleatoria de productos, no verifica matemáticamente la suma de precios y no valida un procesamiento bancario real. Las limitaciones se detallan en conclusiones.txt.
