<div align="center">

# STOCKSCAN

## Memoria del Sprint 03

### Desarrollo de Interfaces

**Componentes reutilizables, eventos, pruebas e integración**

**Proyecto transversal · StockScan SGA**

---

**Equipo**

Carmen Monge · Álvaro López de San Román  
Elena Sáez · Ángel Luis Sánchez

**Scrum Master:** Carmen Monge

**Fecha de edición:** 27 de noviembre de 2026  
**Fecha de cierre del sprint:** pendiente de completar

**Estado del documento:** recopilación estructurada para revisión final

</div>

---

## Índice general

- [Presentación y alcance](#apartado-001)
- [Entorno y organización del trabajo](#apartado-002)
- [3.1. Arquitectura de la clase, encapsulación y propiedades por defecto](#apartado-003)
- [3.2. Implementación del modelo de eventos y respuestas](#apartado-004)
  - [3.2.1. Captura de eventos de bajo nivel](#apartado-005)
  - [3.2.2. Mecanismo de notificación propio](#apartado-010)
  - [3.2.3. Disparo y feedback visual](#apartado-015)
  - [Estado de revisión del apartado 3.2](#apartado-023)
  - [Referencias del apartado 3.2](#apartado-024)
- [3.3. Pruebas automatizadas y validación de estados aislados](#apartado-025)
  - [3.3.1. Aislamiento del componente](#apartado-026)
  - [3.3.2. Aserciones sobre el estado interno](#apartado-040)
  - [3.3.3. Prueba de simulación de interacción](#apartado-053)
  - [Estado de revisión del apartado 3.3](#apartado-063)
  - [Referencias del apartado 3.3](#apartado-064)
- [3.4. Documentación de la API técnica y empaquetado del artefacto](#apartado-065)
  - [3.4.1. Documentación en código con JavaDoc](#apartado-066)
  - [3.4.2. Compilación del artefacto reutilizable](#apartado-067)
  - [3.4.3. Generación de la documentación técnica](#apartado-068)
  - [Referencias del apartado 3.4](#apartado-069)
- [3.5. Integración en el proyecto transversal](#apartado-070)
  - [3.5.1. Vinculación de la librería](#apartado-071)
- [Balance y revisión final](#apartado-072)
  - [Lista de comprobación para la entrega](#apartado-073)

<details>
<summary>Mostrar índice detallado de todos los subapartados</summary>

- [Presentación y alcance](#apartado-001)
- [Entorno y organización del trabajo](#apartado-002)
- [3.1. Arquitectura de la clase, encapsulación y propiedades por defecto](#apartado-003)
- [3.2. Implementación del modelo de eventos y respuestas](#apartado-004)
  - [3.2.1. Captura de eventos de bajo nivel](#apartado-005)
    - [3.2.1.1. Objetivo e investigación](#apartado-006)
    - [3.2.1.2. Registro y control de eventos](#apartado-007)
    - [3.2.1.3. Catálogo de interacciones por vista](#apartado-008)
    - [3.2.1.4. Evidencias y revisión](#apartado-009)
  - [3.2.2. Mecanismo de notificación propio](#apartado-010)
    - [3.2.2.1. Elección del contrato](#apartado-011)
    - [3.2.2.2. Implementación](#apartado-012)
    - [3.2.2.3. Pruebas del mecanismo](#apartado-013)
    - [3.2.2.4. Evidencias y documentación Javadoc](#apartado-014)
  - [3.2.3. Disparo y feedback visual](#apartado-015)
    - [3.2.3.1. Objetivo y mecanismo elegido](#apartado-016)
    - [3.2.3.2. Estados visuales](#apartado-017)
    - [3.2.3.3. Emisión y recepción de notificaciones](#apartado-018)
    - [3.2.3.4. Integración en las pantallas](#apartado-019)
    - [3.2.3.5. Accesibilidad básica](#apartado-020)
    - [3.2.3.6. Evidencias visuales](#apartado-021)
    - [3.2.3.7. Plan de verificación](#apartado-022)
  - [Estado de revisión del apartado 3.2](#apartado-023)
  - [Referencias del apartado 3.2](#apartado-024)
- [3.3. Pruebas automatizadas y validación de estados aislados](#apartado-025)
  - [3.3.1. Aislamiento del componente](#apartado-026)
    - [3.3.1.1. Comprobar la configuración del proyecto y la versión de Java utilizada](#apartado-027)
    - [3.3.1.2. Añadir JUnit 5 como dependencia de pruebas](#apartado-028)
    - [3.3.1.3. Crear la clase de test en el paquete de pruebas correspondiente](#apartado-029)
    - [3.3.1.4. Preparar la creación del componente sin depender de la ventana principal](#apartado-030)
    - [3.3.1.5. Ejecutar las operaciones de UI en el Event Dispatch Thread](#apartado-031)
    - [3.3.1.6. Añadir utilidades gráficas solo si aportan valor](#apartado-032)
    - [3.3.1.7. Ejecutar un test mínimo para verificar que la infraestructura funciona](#apartado-033)
    - [3.3.1.8. Documentar la configuración y cómo ejecutar las pruebas](#apartado-034)
    - [3.3.1.9. Documentación](#apartado-035)
    - [3.3.1.10. Riesgos y problemas previsibles en las pruebas del componente Swing](#apartado-036)
    - [3.3.1.11. Decisión de herramienta de construcción: Maven en lugar de Gradle](#apartado-037)
    - [3.3.1.12. Migración parcial a Maven solo para las pruebas](#apartado-038)
    - [3.3.1.13. Glosario de términos técnicos](#apartado-039)
  - [3.3.2. Aserciones sobre el estado interno](#apartado-040)
    - [3.3.2.1. Verificar los valores por defecto tras usar el constructor sin argumentos](#apartado-041)
    - [3.3.2.2. Comprobar que cada setter actualiza el valor que devuelve su getter](#apartado-042)
    - [3.3.2.3. Verificar que los tamaños negativos se rechazan o se normalizan](#apartado-043)
    - [3.3.2.4. Comprobar el tratamiento de colores/textos nulos](#apartado-044)
    - [3.3.2.5. Comprobar que los cambios visuales solicitan actualización](#apartado-045)
    - [3.3.2.6. Añadir casos límite y nombres descriptivos a los tests](#apartado-046)
    - [3.3.2.7. Ejecutar la suite y corregir fallos junto al responsable del código](#apartado-047)
    - [3.3.2.8. Documentar los casos cubiertos y el resultado](#apartado-048)
    - [3.3.2.9. Documentación](#apartado-049)
    - [3.3.2.10. Revisión de la tarea](#apartado-050)
    - [3.3.2.11. Riesgos y problemas previsibles](#apartado-051)
    - [3.3.2.12. Glosario de términos técnicos](#apartado-052)
  - [3.3.3. Prueba de simulación de interacción](#apartado-053)
    - [3.3.3.1. Simular un clic/interacción mediante la herramienta disponible](#apartado-054)
    - [3.3.3.2. Verificar que se emite exactamente la notificación esperada](#apartado-055)
    - [3.3.3.3. Verificar que no se notifica a un listener después de desregistrarlo](#apartado-056)
    - [3.3.3.4. Comprobar el cambio de estado observable tras la interacción](#apartado-057)
    - [3.3.3.5. Ejecutar las pruebas en conjunto con el resto de la suite](#apartado-058)
    - [3.3.3.6. Registrar incidencias y repetir la prueba tras las correcciones](#apartado-059)
    - [3.3.3.7. Documentar el procedimiento y los resultados](#apartado-060)
    - [3.3.3.8. Documentación](#apartado-061)
    - [3.3.3.9. Implementación y resultados de las pruebas de simulación](#apartado-062)
  - [Estado de revisión del apartado 3.3](#apartado-063)
  - [Referencias del apartado 3.3](#apartado-064)
- [3.4. Documentación de la API técnica y empaquetado del artefacto](#apartado-065)
  - [3.4.1. Documentación en código con JavaDoc](#apartado-066)
  - [3.4.2. Compilación del artefacto reutilizable](#apartado-067)
  - [3.4.3. Generación de la documentación técnica](#apartado-068)
  - [Referencias del apartado 3.4](#apartado-069)
- [3.5. Integración en el proyecto transversal](#apartado-070)
  - [3.5.1. Vinculación de la librería](#apartado-071)
- [Balance y revisión final](#apartado-072)
  - [Lista de comprobación para la entrega](#apartado-073)

</details>

---

<a id="apartado-001"></a>

## Presentación y alcance

Esta memoria reúne el trabajo documentado del Sprint 03 del módulo Desarrollo de Interfaces del proyecto StockScan. Incluye el modelo de eventos, las pruebas del componente, la documentación técnica, el empaquetado y la vinculación de la biblioteca al proyecto transversal.

Se conserva la numeración de las tareas para facilitar su relación con el tablero de seguimiento. Las evidencias visuales permanecen junto a la explicación que justifican y las referencias se mantienen al final de su bloque.

**Criterio de lectura:** los procedimientos indican qué debe hacerse; los resultados registrados describen ejecuciones recogidas en el material aportado. Los puntos pendientes no se consideran verificados.

<a id="apartado-002"></a>

## Entorno y organización del trabajo

| Elemento | Descripción |
|---|---|
| Proyecto | StockScan SGA · Aplicación de escritorio StockScanE |
| Módulo | Desarrollo de Interfaces (DII) |
| Lenguaje e interfaz | Java y Swing |
| Versión de referencia | JDK 21 |
| Entorno de desarrollo | Apache NetBeans |
| Construcción de la aplicación | Apache Ant |
| Pruebas documentadas | JUnit 5 y Maven Surefire; utilidades gráficas según el alcance de la prueba |
| Seguimiento | Issues, ramas y cambios del equipo en GitHub |

<a id="apartado-003"></a>

## 3.1. Arquitectura de la clase, encapsulación y propiedades por defecto

**Pendiente de incorporar a esta recopilación.** El archivo aportado no contiene el desarrollo de este bloque. Se reserva su posición para integrar la aportación del equipo sin alterar la numeración de los apartados siguientes.

<a id="apartado-004"></a>

## 3.2. Implementación del modelo de eventos y respuestas

<a id="apartado-005"></a>

### 3.2.1. Captura de eventos de bajo nivel

<a id="apartado-006"></a>

#### 3.2.1.1. Objetivo e investigación

Se estudió cómo detectar las interacciones del usuario en Java Swing mediante escuchadores y clases adaptadoras.

Los adaptadores permiten implementar únicamente los métodos necesarios para cada interacción. La documentación de Oracle distingue entre eventos de bajo nivel, como los del ratón y el teclado, y eventos semánticos, como la activación de un botón. Recomienda utilizar estos últimos cuando representen directamente la acción requerida [1].

Se consideran tres grupos de eventos:

| Grupo | Adaptador | Métodos principales | Aplicación |
|---|---|---|---|
| Ratón | `MouseAdapter` | `mouseEntered`, `mouseExited`, `mousePressed`, `mouseReleased` | Detectar entrada, salida, pulsación y liberación del ratón. |
| Foco | `FocusAdapter` | `focusGained`, `focusLost` | Actualizar la apariencia del control activo y gestionar textos de ayuda. |
| Teclado | `KeyAdapter` | `keyPressed`, `keyReleased` | Detectar pulsaciones y liberaciones de teclas en controles con foco. |

Las clases y sus métodos están documentados en la API de Java SE 21 [2]–[4].

<a id="apartado-007"></a>

#### 3.2.1.2. Registro y control de eventos

En el trabajo de captura de eventos se describe un método privado `configurarEventos()`, llamado después de `initComponents()`, para separar el registro de escuchadores del código generado por NetBeans.

En la captura manual se contemplan dos controles:

- **`clicIniciado`:** registra una pulsación iniciada dentro de un botón habilitado y permite cancelar la acción cuando el puntero sale antes de soltar.
- **`teclaPulsada`:** permite controlar la repetición del teclado y ejecutar la acción al liberar la tecla.

Estos controles corresponden a la implementación manual descrita para este apartado.

En la integración posterior del feedback sobre los botones, descrita en el apartado **3.2.3**, se utiliza `ButtonModel` y `ActionListener`. No deben registrarse ambos mecanismos para ejecutar la misma acción, porque podrían producir notificaciones duplicadas.

Los escuchadores deben ejecutar tareas breves para no bloquear la respuesta de la interfaz [1].

<a id="apartado-008"></a>

#### 3.2.1.3. Catálogo de interacciones por vista

La siguiente tabla recoge las interacciones descritas en el trabajo de captura de eventos. Su presencia en la versión final compartida debe contrastarse con el código de cada pantalla.

| Vista | Controles | Interacción descrita |
|---|---|---|
| `Login` | Correo, contraseña y botón de acceso | Gestión de textos de ayuda y activación desde el campo de contraseña. |
| `DashboardPanel` | Filtros de periodo, tarjetas y lista de actividades | Selección de periodo, resaltado y consulta de la actividad seleccionada. |
| `MapaAlmacenPanel` | Botones de estanterías | Selección de ubicación, cambio de cursor e indicador de foco. |
| `StockPanel` | Buscador y filtros de categoría | Respuesta al texto introducido y selección visual de categorías. |
| `EtiquetasPanel` | Buscador, casillas y botones | Selección de productos y actualización de la vista previa. |
| `TrazabilidadPanel` | Buscador, fecha, tabla y desplegables | Gestión de textos de ayuda y detección de selección. |
| `AlertasPanel` | Botones de pedido y notificación | Detección de activación y cambio de disponibilidad. |
| `EquipoPanel` | Buscador, filtros y tabla | Filtrado de filas y detección de la columna de edición. |
| `AgendaPanel` | Anterior, Hoy y Siguiente | Interacción con los controles de semana. |
| `ChatPanel` | Campos de búsqueda y mensaje, botón de envío | Gestión de textos de ayuda y activación del envío. |

La detección de una interacción no implica por sí sola que exista una operación de negocio completa. Por ejemplo, activar un botón de pedido no demuestra que se haya registrado un pedido en la base de datos.

<a id="apartado-009"></a>

#### 3.2.1.4. Evidencias y revisión

Las evidencias aportadas muestran ejemplos de interacción con controles y seguimiento del ratón.

<div align="center">
  <img width="536" alt="Ejemplo de interacción con campos y botón de acceso" src="https://github.com/user-attachments/assets/45cd6804-2645-476a-82f9-995985e1de0b" />
</div>

<div align="center">
  <img width="720" alt="Ejemplo de seguimiento del ratón" src="https://github.com/user-attachments/assets/fb100047-b906-4e01-8dc9-f91bd5eb28ef" />
</div>

La creación y manipulación de la interfaz debe realizarse en el hilo de eventos de Swing, mediante mecanismos como `EventQueue.invokeLater()` o `SwingUtilities.invokeLater()` [5].

Estas evidencias no acreditan por sí solas el funcionamiento de todas las interacciones del catálogo. La revisión final debe comprobar cada vista y detectar posibles interferencias entre escuchadores.

<a id="apartado-010"></a>

### 3.2.2. Mecanismo de notificación propio

<a id="apartado-011"></a>

#### 3.2.2.1. Elección del contrato

Se analizaron dos alternativas para comunicar las acciones del componente a otras clases:

- Utilizar `ActionListener` y `ActionEvent`.
- Definir un evento propio y una interfaz de escucha.

`ActionEvent` representa una acción semántica y proporciona información como el origen, el identificador, el comando, los modificadores y la marca temporal [6].

Se eligió un evento propio para representar explícitamente los datos del proyecto mediante los campos `codigo` y `mensaje`, evitando depender de la interpretación de una única cadena de comando.

El contrato se compone de `StockScanEvent` y `StockScanListener`. Permite que un controlador reciba la notificación sin que el componente necesite conocer su implementación concreta.

<a id="apartado-012"></a>

#### 3.2.2.2. Implementación

| Elemento | Paquete | Responsabilidad |
|---|---|---|
| `StockScanEvent` | `com.stockscan.view.events` | Transportar el origen, el código y el mensaje. |
| `StockScanListener` | `com.stockscan.view.events` | Declarar `alNotificarAccion(StockScanEvent evento)`. |
| `ComponenteStockScan` | `com.stockscan.view.components` | Gestionar las suscripciones y emitir notificaciones. |

`StockScanEvent` hereda de `EventObject`, que proporciona la referencia al origen del evento [7]. Sus campos propios `codigo` y `mensaje` se almacenan como atributos finales.

`StockScanListener` extiende `EventListener`, interfaz utilizada como base para los escuchadores [8].

`ComponenteStockScan` mantiene una lista privada de observadores y ofrece:

- `addStockScanListener(...)`: registra un escuchador, evitando valores nulos y duplicados.
- `removeStockScanListener(...)`: elimina una suscripción.
- `notificarObservadores(...)`: crea el evento y lo entrega a los escuchadores registrados.

Si la lista está vacía, el método de notificación termina sin realizar entregas.

La colección se basa en `ArrayList` [9]. La prevención de duplicados se realiza en el método de registro del componente; no es una característica automática de `ArrayList`.

<a id="apartado-013"></a>

#### 3.2.2.3. Pruebas del mecanismo

El registro de pruebas aportado para `PruebaNotificacion.java`, situada en `Test Packages`, recoge los siguientes resultados:

| ID | Escenario | Acción | Resultado esperado | Resultado registrado |
|---|---|---|---|---|
| NOT-01 | Ausencia de observadores | Notificar con la lista vacía. | Finalizar sin excepciones. | PASS |
| NOT-02 | Control de duplicados | Registrar dos veces el mismo observador. | Mantener una sola suscripción. | PASS |
| NOT-03 | Entrega de datos | Notificar con un observador activo. | Recibir una notificación con el código y el mensaje previstos. | PASS |
| NOT-04 | Desregistro | Eliminar el observador y volver a notificar. | No entregar nuevas notificaciones al observador eliminado. | PASS |

Estos resultados corresponden a la ejecución documentada. Las pruebas deben repetirse si cambia la implementación.

La ausencia de mensajes en consola demuestra ausencia de recepción observable, pero no implica necesariamente que no se haya construido un objeto evento.

<a id="apartado-014"></a>

#### 3.2.2.4. Evidencias y documentación Javadoc

##### Organización de paquetes

Se muestran los paquetes de eventos, componentes y pruebas del proyecto.

<div align="center">
  <img width="230" alt="Paquete com.stockscan.view.events" src="https://github.com/user-attachments/assets/779a6e1b-1064-4eba-971f-7bd3ef80bad1" />

  <img width="252" alt="Paquete com.stockscan.view.components" src="https://github.com/user-attachments/assets/173da16b-b075-48c6-8010-c6ff4b77cd01" />

  <img width="280" alt="Paquete de pruebas de eventos" src="https://github.com/user-attachments/assets/876aafed-5af7-4938-9336-45d2589b7cef" />
</div>

##### Ejecución de PruebaNotificacion

Se aporta la salida de ejecución de la prueba en NetBeans.

<div align="center">
  <img width="686" alt="Salida de PruebaNotificacion en NetBeans" src="https://github.com/user-attachments/assets/21ba28a6-13cc-40a7-b5ad-7a0ee03290c4" />
</div>

##### Documentación de la API

Se han utilizado comentarios Javadoc para describir las clases y métodos:

- Los bloques `/** ... */` explican la responsabilidad de cada elemento.
- `@param` describe los parámetros recibidos.
- `@return` explica los valores devueltos.

Las capturas aportadas muestran la documentación HTML generada.

**Resumen de paquetes y paquete de eventos:**

<div align="center">
  <img width="960" alt="Javadoc: resumen de paquetes" src="https://github.com/user-attachments/assets/4b5f8c65-d788-4b28-9964-4fa1823e974d" />

  <img width="962" alt="Javadoc: paquete com.stockscan.view.events" src="https://github.com/user-attachments/assets/3a5dbd02-af71-47d9-8fa8-42d2537fdb60" />
</div>

**Métodos de StockScanEvent y ComponenteStockScan:**

<div align="center">
  <img width="905" alt="Javadoc: métodos de StockScanEvent" src="https://github.com/user-attachments/assets/4eb0f8d1-964f-4332-923c-72a6fe94121a" />

  <img width="907" alt="Javadoc: métodos de ComponenteStockScan" src="https://github.com/user-attachments/assets/6cd275f0-52bd-4d17-926f-5a5864e83a38" />
</div>

<a id="apartado-015"></a>

### 3.2.3. Disparo y feedback visual

<a id="apartado-016"></a>

#### 3.2.3.1. Objetivo y mecanismo elegido

Este apartado corresponde a la tarea **3.2.3 Disparo y feedback visual — #67**.

Su objetivo es conectar la interacción del usuario con la apariencia del botón y la emisión de una notificación pública mediante `StockScanEvent`.

Se utiliza `ButtonModel` para consultar:

| Método | Información consultada |
|---|---|
| `isEnabled()` | Si el botón está habilitado. |
| `isRollover()` | Si el puntero se encuentra sobre el botón. |
| `isPressed()` | Si el botón está pulsado. |
| `isArmed()` | Si está preparado para completar la activación. |

El modelo distingue entre mantener el botón pulsado y estar preparado para completar la activación. Esta diferencia permite cancelar una pulsación cuando se libera fuera del botón [10].

<a id="apartado-017"></a>

#### 3.2.3.2. Estados visuales

Se implementó `FeedbackStockScan`, que hereda de `ComponenteStockScan` y aplica el comportamiento a los botones existentes.

| Estado | Condición | Respuesta visual |
|---|---|---|
| Reposo | Botón habilitado, sin hover ni pulsación activa. | Color base del botón. |
| Hover | Puntero sobre el botón habilitado, sin pulsación activa. | Color base oscurecido. |
| Pulsado | Modelo pulsado y armado. | Color más oscuro. |
| Deshabilitado | Modelo deshabilitado. | Fondo gris y texto oscuro. |

Se comprueba primero el estado deshabilitado, después la pulsación, el hover y, finalmente, el reposo.

Un `ChangeListener` actualiza la apariencia cuando cambia el modelo. El estado calculado se almacena en la propiedad de cliente `stockscan.estado`.

Después se actualizan los colores y se solicita el repintado mediante `repaint()`. `JComponent` proporciona las propiedades de cliente y el mecanismo de solicitud de repintado [11].

En los filtros se diferencia entre selección persistente e interacción temporal. Al salir el ratón, el filtro seleccionado recupera su color base activo.

<a id="apartado-018"></a>

#### 3.2.3.3. Emisión y recepción de notificaciones

La notificación se emite desde el `ActionListener` cuando el botón completa una activación válida.

- Entrar y salir con el ratón no notifica.
- Mantener pulsado no notifica todavía.
- Pulsar dentro y soltar dentro activa el botón.
- Pulsar dentro y soltar fuera cancela la activación.
- Un botón deshabilitado no permite la activación normal del usuario.

Este comportamiento se apoya en el modelo del botón [10].

Se reutiliza el contrato definido en el apartado **3.2.2**:

- `StockScanEvent` transporta los datos.
- `StockScanListener` define cómo recibirlos.
- `ComponenteStockScan` gestiona los observadores.

En esta integración, la instancia de `FeedbackStockScan` es el origen del evento. El código identifica la pantalla y el botón, mientras que el mensaje describe la activación.

`FeedbackController` implementa `StockScanListener`, incrementa un contador y muestra la notificación en la salida de NetBeans.

Ejemplo:

```text
StockScan #1 | StockPanel.btnFiltroTodos | Activación: Todos
```

El contador pertenece a cada instancia del controlador.

El mensaje demuestra la recepción de una notificación, pero no confirma una operación de negocio ni un guardado en la base de datos.

<a id="apartado-019"></a>

#### 3.2.3.4. Integración en las pantallas

El comportamiento se registra desde `configurarEfectosVisuales()`, después de `initComponents()`, en:

- `AgendaPanel`.
- `AlertasPanel`.
- `ChatPanel`.
- `DashboardPanel`.
- `EquipoPanel`.
- `EtiquetasPanel`.
- `Login`.
- `MapaAlmacenPanel`.
- `StockPanel`.
- `TrazabilidadPanel`.

Se mantienen los botones, sus nombres, iconos, posiciones y manejadores existentes.

La gestión anterior de colores se sustituye en los botones adaptados para evitar que varios mecanismos modifiquen simultáneamente su apariencia.

El método de instalación debe ejecutarse una sola vez por botón. La versión descrita no incorpora una protección automática frente a llamadas repetidas a `instalar()`.

En Agenda, Dashboard, Equipo y Stock, la selección se modifica mediante `ActionListener`. Después se actualiza la apariencia del grupo con `feedback.refrescar(...)`.

Este apartado documenta el feedback de los botones existentes. No corresponde a la incorporación de una nueva franja visual de la tarea #75.

<a id="apartado-020"></a>

#### 3.2.3.5. Accesibilidad básica

Los botones se configuran para poder recibir el foco y muestran un borde naranja cuando lo tienen.

Un escuchador de foco actualiza la apariencia al ganarlo o perderlo.

Se conservan las asociaciones de teclado de `JButton`, gestionadas por Swing mediante `InputMap` y `ActionMap` [12].

La navegación mediante Tab y la activación mediante Espacio deben comprobarse en ejecución. Su comportamiento depende también de las asociaciones establecidas por la apariencia gráfica utilizada.

<a id="apartado-021"></a>

#### 3.2.3.6. Evidencias visuales

##### Prueba independiente del componente

La grabación aportada muestra los cambios de apariencia del botón, el contador de notificaciones y el estado deshabilitado al desmarcar la casilla.

<div align="center">
  <img width="900" alt="Prueba independiente del componente: estados visuales, notificaciones y deshabilitación" src="https://github.com/user-attachments/assets/7e542971-14b5-4f7d-8cac-db47bd84b462" />
</div>

##### Integración del feedback en Stock

Se muestran los botones existentes con el feedback visual integrado, la selección de categorías y las notificaciones recibidas por `FeedbackController`.

Los mensajes permiten identificar la pantalla, el botón y el texto de la acción.

<div align="center">
  <img width="1280" alt="Feedback visual, selección de categorías y notificaciones en Stock" src="https://github.com/user-attachments/assets/7e4069f6-2daf-444b-99ca-e6424bf4bb78" />
</div>

##### Foco visible y selección

Se observa el indicador de foco en distintos controles y la selección visual de los filtros, junto con las notificaciones registradas.

<div align="center">
  <img width="1280" alt="Indicador de foco y selección de filtros en Stock" src="https://github.com/user-attachments/assets/50bed6ed-98b1-44e7-a849-b98f12d1e47e" />
</div>

##### Alcance de las evidencias

Las grabaciones permiten observar cambios de apariencia y recepción de eventos.

Por sí solas no confirman las teclas utilizadas ni demuestran con certeza la cancelación al soltar fuera. Tampoco acreditan el comportamiento de todas las pantallas.

Estas comprobaciones se completarán mediante el siguiente plan de verificación.

<a id="apartado-022"></a>

#### 3.2.3.7. Plan de verificación

Los resultados locales se completarán después de ejecutar y comprobar cada prueba.

| ID | Prueba | Resultado esperado | Resultado local |
|---|---|---|---|
| FB-01 | Entrar y salir con el ratón. | Alterna reposo y hover sin notificar. | Pendiente |
| FB-02 | Mantener pulsado. | Muestra el estado pulsado sin emisión anticipada. | Pendiente |
| FB-03 | Pulsar y soltar dentro. | Emite exactamente una notificación. | Pendiente |
| FB-04 | Pulsar dentro y soltar fuera. | No notifica ni cambia la selección. | Pendiente |
| FB-05 | Salir después de activar un filtro. | Conserva el color base del filtro seleccionado. | Pendiente |
| FB-06 | Deshabilitar e intentar activar. | Muestra el estado deshabilitado y no notifica. | Pendiente |
| FB-07 | Habilitar de nuevo. | Recupera su apariencia y funcionamiento. | Pendiente |
| FB-08 | Enfocar mediante Tab. | Muestra el borde naranja. | Pendiente |
| FB-09 | Pulsar y soltar Espacio con el botón enfocado. | Completa una activación y notifica. | Pendiente |
| FB-10 | Revisar la salida de NetBeans. | El controlador recibe el código y el mensaje correctos. | Pendiente |
| FB-11 | Repetir en las pantallas integradas. | No aparecen interferencias ni notificaciones duplicadas. | Pendiente |

<a id="apartado-023"></a>

### Estado de revisión del apartado 3.2

| Apartado | Situación |
|---|---|
| 3.2.1. Captura de eventos de bajo nivel | Documentación y ejemplos aportados; pendiente contrastar el catálogo con la versión final. |
| 3.2.2. Mecanismo de notificación propio | Implementación documentada y resultados de pruebas aportados. |
| 3.2.3. Disparo y feedback visual | Implementación y grabaciones aportadas; pendiente completar el registro individual de pruebas. |
| Integración conjunta | Pendiente comprobar que los mecanismos no duplican acciones sobre los mismos controles. |

Los resultados pendientes deben actualizarse después de ejecutar las comprobaciones.

Las fuentes técnicas justifican los mecanismos utilizados, pero no sustituyen las pruebas del proyecto.

<a id="apartado-024"></a>

### Referencias del apartado 3.2

La numeración de las siguientes referencias corresponde únicamente al apartado 3.2.

[1] Oracle Corporation, “General Information about Writing Event Listeners,” *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/events/generalrules.html. [Consulta: 26 de noviembre de 2026].

[2] Oracle Corporation, “MouseAdapter,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/MouseAdapter.html.

[3] Oracle Corporation, “FocusAdapter,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/FocusAdapter.html.

[4] Oracle Corporation, “KeyAdapter,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/KeyAdapter.html.

[5] Oracle Corporation, “Initial Threads,” *The Java Tutorials — Concurrency in Swing*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html.

[6] Oracle Corporation, “ActionEvent,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/ActionEvent.html. [Consulta: 26 de noviembre de 2026].

[7] Oracle Corporation, “EventObject,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventObject.html.

[8] Oracle Corporation, “EventListener,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventListener.html.

[9] Oracle Corporation, “ArrayList,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html.

[10] Oracle Corporation, “ButtonModel,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/ButtonModel.html. [Consulta: 26 de noviembre de 2026].

[11] Oracle Corporation, “JComponent,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JComponent.html.

[12] Oracle Corporation, “How to Use Key Bindings,” *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html.

Los tutoriales de Oracle citados están redactados para JDK 8. Las referencias de la API corresponden a Java SE 21.

<a id="apartado-025"></a>

## 3.3. Pruebas automatizadas y validación de estados aislados

<a id="apartado-026"></a>

### 3.3.1. Aislamiento del componente

Los pasos siguientes describen el procedimiento de preparación. Los resultados de ejecución aportados se recogen en el apartado 3.3.3.9.

<a id="apartado-027"></a>

#### 3.3.1.1. Comprobar la configuración del proyecto y la versión de Java utilizada
**Objetivo:** confirmar que el proyecto puede ejecutar pruebas unitarias con JUnit 5 antes de añadir nada.
**Qué hay que hacer:**
- Verificar la versión de Java del proyecto. JUnit 5 requiere Java 8 o superior [1], [2].
- Identificar el gestor de dependencias en uso con Maven [3].
- Confirmar que la interfaz es Swing, de modo que la utilidad gráfica sea AssertJ Swing [4].
- Comprobar que existe la carpeta de pruebas "src/test/" separada del código de producción "src/main/" [5].
**Resultado esperado:** versión de Java y gestor de dependencias identificados y anotados en el issue.

<a id="apartado-028"></a>

#### 3.3.1.2. Añadir JUnit 5 como dependencia de pruebas
**Objetivo:** dotar al proyecto de las librerías necesarias para escribir y ejecutar las pruebas.
**Qué hay que hacer:**
- Añadir **junit-jupiter** (versión 5.x) con ámbito **test**, para que no se incluya en la aplicación final [6], [7].
- Si se confirma que aporta valor, añadir también **assertj-swing-junit** con ámbito **test**. Evitar dependencias innecesarias [8], [6].
- Configurar la ejecución de tests en Maven mediante **maven-surefire-plugin** [9], [3].
- Recompilar el proyecto y comprobar que las dependencias se descargan sin errores [6].
**Resultado esperado:** el proyecto compila con JUnit 5 disponible solo para pruebas.

**Comparativa JUnit 4 vs JUnit 5 en Apache NetBeans**
*(Versión de referencia: Apache NetBeans 31, publicada el 5 de agosto [10]. La 32 está en su tercera y última versión candidata).*

| Criterio | JUnit 4 | JUnit 5 (Jupiter) |
|---|---|---|
| Soporte en NetBeans | Desde NetBeans 6.5, que genera los tests con estilo JUnit 4 [12] | Desde Apache NetBeans 10.0 [13], [14] |
| Plantilla al crear un test | Se mantiene si el proyecto ya usa JUnit 4 [15] | Es la opción por defecto si el JDK es 8 o superior [15] |
| Proyectos Maven | Se ejecuta con Surefire [15] | NetBeans delega en Maven y Surefire; requiere Surefire 2.22.0 o superior [15], [3] |
| Versión mínima de Java | Java 5 [1] | Java 8 [1], [2] |
| Anotaciones | `@Before`, `@After`, `@RunWith` [1] | `@BeforeEach`, `@AfterEach`, `@ExtendWith` [1] |
| Aserciones y excepciones | Atributos "expected" y "timeout" en `@Test` [16] | **assertThrows**, **assertAll** y **assertTimeoutPreemptively** [17], [16] |
| Mantenimiento | Recomendado solo para proyectos heredados [18] | Recomendado para desarrollo nuevo [18]. |

**Recomendación: JUnit 5**, por tres motivos: el propio issue #68 pide utilizar JUnit 5, NetBeans lo soporta de forma nativa en proyectos Maven [13], [15], [3], y aporta herramientas clave usadas en las tareas (**assertAll**, **assertThrows**, `@DisplayName` y `@Nested`) [2], [16].

<a id="apartado-029"></a>

#### 3.3.1.3. Crear la clase de test en el paquete de pruebas correspondiente
**Objetivo:** tener una clase de pruebas unitarias dedicada al componente, separada de la ventana principal.
**Qué hay que hacer:**
- Crear la clase en la carpeta de pruebas, dentro del mismo paquete que el componente a probar para acceder a sus miembros sin romper la encapsulación [2], [5].
- Importar las anotaciones `@Test`, `@BeforeEach` y `@AfterEach` y las aserciones de "org.junit.jupiter.api" [2].
- Limitar el alcance de la clase al componente: no debe depender de la ventana principal [20].
**Resultado esperado:** clase de test creada y compilando.

<a id="apartado-030"></a>

#### 3.3.1.4. Preparar la creación del componente sin depender de la ventana principal
**Objetivo:** poder instanciar el componente de forma aislada en cada prueba.
**Qué hay que hacer:**
- Instanciar el componente en un método `@BeforeEach`, sin crear ni mostrar la ventana principal (**JFrame**) [2], [20].
- Si el componente necesita un contenedor, usar un **JFrame** o **JPanel** auxiliar propio de la prueba [20].
- Sustituir las dependencias externas por objetos simples o valores de prueba.
- Preparar AssertJ Swing para localizar y manipular el componente por separado [21], [22].
- Garantizar que cada test parte de una instancia nueva [2].
**Resultado esperado:** el componente se crea en las pruebas sin levantar la aplicación completa.

<a id="apartado-031"></a>

#### 3.3.1.5. Ejecutar las operaciones de UI en el Event Dispatch Thread
**Objetivo:** evitar fallos intermitentes y accesos incorrectos a componentes Swing desde hilos distintos al de la interfaz [23].
**Qué hay que hacer:**
- Crear y modificar el componente dentro del Event Dispatch Thread (EDT). En las pruebas, usar **GuiActionRunner.execute(...)** o **SwingUtilities.invokeAndWait(...)** [23], [24].
- Registrar **FailOnThreadViolationRepaintManager.install()** en un método `@BeforeAll` para fallar si hay accesos fuera del EDT [24], [25].
- Aplicar esta regla solo a las operaciones que tocan la interfaz [23].
**Resultado esperado:** todas las operaciones de UI de las pruebas se ejecutan en el EDT.

<a id="apartado-032"></a>

#### 3.3.1.6. Añadir utilidades gráficas solo si aportan valor
**Objetivo:** mantener la infraestructura de pruebas lo más simple posible.
**Qué hay que hacer:**
- Decidir si AssertJ Swing es necesaria (solo aporta si hay que simular clics o localizar componentes) [4], [22].
- Si basta con getters, probar solo con JUnit 5 y no añadir la librería.
- Dejar anotada en el issue la decisión y su motivo.
**Resultado esperado:** decisión justificada sobre las utilidades gráficas.

<a id="apartado-033"></a>

#### 3.3.1.7. Ejecutar un test mínimo para verificar que la infraestructura funciona
**Objetivo:** comprobar que todo lo preparado funciona antes de escribir pruebas completas.
**Qué hay que hacer:**
- Escribir un único test que cree el componente y compruebe algo básico (por ejemplo, que no es **null**) [2].
- Ejecutarlo desde la línea de comandos: **mvn test** en Maven [9].
- Comprobar en el informe de resultados ("target/surefire-reports") que el test ha pasado [9].
**Resultado esperado:** un test que se ejecuta y pasa, confirmando la configuración.

<a id="apartado-034"></a>

#### 3.3.1.8. Documentar la configuración y cómo ejecutar las pruebas
**Objetivo:** que cualquier miembro del equipo pueda ejecutar las pruebas sin ayuda.
**Qué hay que hacer:**
- Añadir al README una sección "Pruebas" con los requisitos: versión de Java, gestor de dependencias y librerías.
- Indicar los comandos exactos para ejecutar todas las pruebas o una sola clase.
- Explicar dónde se encuentran los informes de resultados [9].
**Resultado esperado:** sección de pruebas en la documentación del proyecto.

<a id="apartado-035"></a>

#### 3.3.1.9. Documentación
**Objetivo:** dejar constancia de lo que prueba la clase de test y cómo está montado el aislamiento.
**Qué hay que hacer:**
- Añadir Javadoc explicando qué componente prueba y cómo se aísla de la ventana principal [29].
- Comentar brevemente la preparación en `@BeforeEach` y `@AfterEach` [2].
- Enlazar el issue #68 desde la documentación.
**Resultado esperado:** código de pruebas y documentación coherentes.

<a id="apartado-036"></a>

#### 3.3.1.10. Riesgos y problemas previsibles en las pruebas del componente Swing
- **Error de entorno sin pantalla (HeadlessException):** ocurre en equipos sin entorno gráfico. Se previene ejecutando las pruebas en un equipo con pantalla o preparando un entorno gráfico virtual (Xvfb) [26], [27], [28].
- **Pruebas que fallan a veces en integración continua (flaky):** errores típicos como **WaitTimedOutError** o **ComponentLookupException** causados porque la ventana no llega a mostrarse. Se previene probando primero en local y no dando por buena una prueba que solo pasa una vez [31], [32], [33].
- **JUnit 5 no está soportado oficialmente por AssertJ Swing:** el **GUITestRunner** está pensado para JUnit 4. Se previene creando y cerrando el robot manualmente en `@BeforeEach` y `@AfterEach` [19], [21].
- **Accesos a la interfaz fuera del EDT:** provoca fallos aleatorios. Se previene instalando **FailOnThreadViolationRepaintManager** al inicio [24], [25], [23].
- **Componentes difíciles de localizar:** si no tienen nombre, la búsqueda falla. Se previene asignando **setName(...)** a los componentes [22], [33].
- **Pruebas que interfieren con el equipo:** AssertJ Swing simula gestos reales a nivel del sistema. Se previene no usando el ratón ni el teclado durante las pruebas gráficas [28].

<a id="apartado-037"></a>

#### 3.3.1.11. Decisión de herramienta de construcción: Maven en lugar de Gradle
**Decisión para las pruebas:** incorporar Maven para gestionar y ejecutar las pruebas, manteniendo Ant como sistema de construcción de la aplicación.
**Motivos:**
1.  **Integración nativa con NetBeans:** NetBeans delega en Surefire para ejecutar los tests en proyectos Maven [15], [14].
2.  **JUnit 5 con menos configuración:** Surefire toma el motor de pruebas de las dependencias normales, sin necesidad de configurar `useJUnitPlatform()` explícitamente [3].
3.  **Estructura convencional:** Maven sigue "convención sobre configuración", es más sencillo de aprender y tiene más documentación [5], [37], [38].
4.  **Proyecto de tamaño reducido:** La diferencia de velocidad con Gradle es despreciable en proyectos de un solo módulo [39], [40].
**Qué asumimos:** Gradle puede ser más rápido en proyectos grandes [40], [41], y la configuración XML es más verbosa que Groovy/Kotlin [38]. **Migraremos temporalmente el proyecto a Maven únicamente para la etapa de testeo, pero el proyecto está hecho con Ant.**

<a id="apartado-038"></a>

#### 3.3.1.12. Migración parcial a Maven solo para las pruebas
**Objetivo:** añadir Maven al proyecto únicamente para compilar y ejecutar las pruebas con JUnit 5, sin cambiar cómo se compila con Ant.
**Enfoque elegido:** mantener la estructura de Ant ("build.xml", "nbproject/") y añadir un "pom.xml" en la raíz que apunte a los directorios existentes mediante `sourceDirectory` y `testSourceDirectory` [42], [43], [44].
**Pasos:**
1.  Crear una rama (ej. "feature/maven-testing") [35].
2.  Crear el "pom.xml" declarando JUnit Jupiter, AssertJ Swing (opcional) y Surefire 2.22.0+ [7].
3.  Resolver librerías externas declarándolas en el "pom.xml" [6].
4.  Compilar con **mvn compile** [44].
5.  Nombrar las clases de test con el patrón **\*Test** [46].
6.  Ejecutar con **mvn test**. Los informes quedan en "target/surefire-reports" [7].
7.  Convivir con Ant: añadir "target/" al ".gitignore". No modificar "build.xml" [9], [44].
8.  Verificar con **mvn clean test** y **ant clean** [9].
**Qué no se hace:** No se mueven archivos a "src/main/" ni se elimina "build.xml" [44]. No se usa Maven para empaquetar [44].

<a id="apartado-039"></a>

#### 3.3.1.13. Glosario de términos técnicos
- **Prueba unitaria:** prueba automática que comprueba una parte pequeña del código de forma aislada [2].
- **JUnit 5 (Jupiter):** framework de Java para escribir y ejecutar pruebas [2].
- **Aserción:** comprobación dentro de una prueba. Si no se cumple, la prueba falla [2].
- **Aislamiento (desacoplamiento):** probar el componente sin depender de la ventana principal [2].
- **Swing:** biblioteca de Java para crear interfaces gráficas de escritorio [20].
- **Event Dispatch Thread (EDT):** hilo único donde Swing debe crear y modificar los componentes gráficos [23].
- **AssertJ Swing:** biblioteca para probar interfaces Swing [4].
- **Maven:** herramienta que compila el proyecto, gestiona dependencias y ejecuta las pruebas [5].
- **Maven Surefire:** plugin de Maven que ejecuta las pruebas [9].
- **HeadlessException:** error de Java cuando no hay pantalla disponible [26].

---

<a id="apartado-040"></a>

### 3.3.2. Aserciones sobre el estado interno

<a id="apartado-041"></a>

#### 3.3.2.1. Verificar los valores por defecto tras usar el constructor sin argumentos
**Objetivo:** confirmar que el componente nace con un estado inicial correcto y predecible.
**Qué hay que hacer:**
- Crear una instancia del componente con el constructor sin argumentos, sin la ventana principal.
- Comprobar, solo a través de la API pública (getters), que cada propiedad devuelve el valor por defecto esperado [1].
- Evitar acceder a atributos privados o a detalles internos de implementación [1], [2].
**Resultado esperado:** una prueba por cada propiedad con valor por defecto, todas pasando.

<a id="apartado-042"></a>

#### 3.3.2.2. Comprobar que cada setter actualiza el valor que devuelve su getter
**Objetivo:** asegurar que lo que se asigna con un setter es lo que después se lee con su getter.
**Qué hay que hacer:**
- Identificar todos los pares setter/getter públicos.
- Asignar un valor distinto del valor por defecto y comprobar que el getter devuelve exactamente ese valor.
- Agrupar las comprobaciones con **assertAll** para ver todos los fallos a la vez [4].
**Resultado esperado:** todos los pares setter/getter cubiertos y comportándose de forma coherente.

<a id="apartado-043"></a>

#### 3.3.2.3. Verificar que los tamaños negativos se rechazan o se normalizan
**Objetivo:** dejar probado cómo reacciona el componente ante tamaños no válidos.
**Qué hay que hacer:**
- Confirmar con el equipo cuál es el contrato (rechazar el valor o normalizarlo). Anotarlo en el issue.
- Probar un valor negativo y comprobar el comportamiento definido.
- Si se rechaza con excepción, comprobar el tipo con **assertThrows** [4].
- Si se normaliza, comprobar que el getter devuelve el valor normalizado.
**Resultado esperado:** pruebas que reflejan el contrato de tamaños negativos acordado.

<a id="apartado-044"></a>

#### 3.3.2.4. Comprobar el tratamiento de colores/textos nulos
**Objetivo:** dejar probado qué ocurre cuando se asigna **null** a un color o a un texto.
**Qué hay que hacer:**
- Confirmar con el equipo la regla para cada propiedad (rechazar el null, sustituirlo o aceptarlo). Anotarlo en el issue.
- Probar **null** en cada propiedad y comprobar el comportamiento acordado.
- Si se rechaza con excepción, comprobarla con **assertThrows** [4].
- Probar también el texto vacío (**""**) para distinguirlo de **null**.
**Resultado esperado:** reglas de nulidad acordadas y verificadas.

<a id="apartado-045"></a>

#### 3.3.2.5. Comprobar que los cambios visuales solicitan actualización
**Objetivo:** asegurar que, al cambiar una propiedad que afecta a la apariencia, el componente pide repintarse, sin necesidad de mostrar una ventana.
**Qué hay que hacer:**
- Distinguir qué propiedades afectan a la apariencia y cuáles no [5].
- Definir una estrategia de prueba que no dependa de dibujar en pantalla (ej. crear una subclase que cuente las llamadas a **repaint()**).
- Comprobar que cambiar una propiedad visual provoca la solicitud de actualización esperada.
- Ejecutar las operaciones de interfaz dentro del Event Dispatch Thread [5], [6].
**Resultado esperado:** pruebas que verifican la solicitud de actualización visual sin depender de la ventana principal.

<a id="apartado-046"></a>

#### 3.3.2.6. Añadir casos límite y nombres descriptivos a los tests
**Objetivo:** ampliar la cobertura a los valores extremos y hacer que los tests se entiendan sin leer su código.
**Qué hay que hacer:**
- Añadir casos límite en las propiedades con restricciones (tamaño 0, mínimo, máximo, valores justo fuera de límite).
- Añadir casos de texto especiales (vacío, solo espacios, muy largo).
- Renombrar los tests con una convención común o usar `@DisplayName` [3].
- Agrupar las pruebas por funcionalidad con `@Nested` [3].
**Resultado esperado:** pruebas con casos límite cubiertos y nombres que explican qué comprueban.

<a id="apartado-047"></a>

#### 3.3.2.7. Ejecutar la suite y corregir fallos junto al responsable del código
**Objetivo:** comprobar que todas las pruebas pasan y resolver los fallos con quien conoce el código.
**Qué hay que hacer:**
- Ejecutar **mvn clean test** en Maven.
- Para cada fallo, determinar si el error está en la prueba o en el código.
- Hablar con el responsable del código para decidir la corrección. No relajar una prueba solo para que pase.
- Lanzar la suite más de una vez para confirmar que ninguna prueba depende del orden de ejecución [4].
**Resultado esperado:** suite completa en verde y lista de los fallos corregidos.

<a id="apartado-048"></a>

#### 3.3.2.8. Documentar los casos cubiertos y el resultado
**Objetivo:** dejar constancia de qué se ha probado, con qué resultado y qué queda sin cubrir.
**Qué hay que hacer:**
- Crear una tabla con una fila por caso probado (propiedad, resultado esperado, resultado obtenido, nombre del test).
- Indicar el resultado global junto con la fecha y la versión del código probado.
- Si se genera informe de cobertura con JaCoCo, adjuntarlo y aclarar que mide qué líneas se ejecutan, no si las pruebas validan algo útil [7], [8].
**Resultado esperado:** documento con los casos cubiertos, sus resultados y las limitaciones conocidas.

<a id="apartado-049"></a>

#### 3.3.2.9. Documentación
**Objetivo:** que cualquier miembro del equipo entienda qué comprueba la suite y cómo ejecutarla.
**Qué hay que hacer:**
- Añadir Javadoc a la clase de test explicando qué comportamiento prueba y qué reglas asume.
- Comentar brevemente los tests menos evidentes.
- Actualizar la sección "Pruebas" del README.
**Resultado esperado:** documentación coherente con el código.

<a id="apartado-050"></a>

#### 3.3.2.10. Revisión de la tarea
**Objetivo:** verificar que se cumple todo lo pedido antes de cerrar el issue.
**Qué hay que hacer:**
- Repasar la lista de tareas y marcar solo las completadas.
- Ejecutar la suite desde cero en un entorno limpio.
- Comprobar que las pruebas usan solo la API pública [1], [2].
- Pedir revisión a otro miembro del equipo mediante pull request.
**Resultado esperado:** tareas verificadas, suite en verde y el issue listo para cerrarse.

<a id="apartado-051"></a>

#### 3.3.2.11. Riesgos y problemas previsibles
- **Pruebas atadas a detalles privados:** cualquier refactorización rompe las pruebas. Se previene probando solo a través de constructores, getters y setters públicos [1], [2].
- **Pruebas de relleno que pasan sin validar nada:** la cobertura parece alta pero no protege de errores. Se previene asignando valores distintos de los valores por defecto y comprobando siempre un resultado concreto [8], [9], [10].
- **Contrato sin definir (tamaños negativos y valores nulos):** cada persona escribe el test según su suposición. Se previene acordando y anotando la regla en el issue antes de escribir los tests.
- **Valores por defecto que dependen del entorno:** los colores o fuentes los aporta el aspecto visual del sistema. Se previene comprobando solo los valores por defecto definidos en nuestro propio código.
- **Expectativas erróneas sobre la actualización visual:** un test puede esperar una actualización que no existe. Se previene definiendo qué propiedades deben solicitar actualización y contando las solicitudes con una subclase de prueba [5].
- **Estado compartido entre pruebas:** los tests empiezan a depender del orden de ejecución. Se previene creando un componente nuevo en `@BeforeEach` y evitando campos estáticos mutables [4].
- **Corregir los tests en lugar del problema real:** es tentador modificar la prueba para que pase. Se previene decidiendo con el responsable del código si el fallo está en la prueba o en el código.
- **Tests con nombres poco claros:** un fallo en el informe no dice qué regla se ha roto. Se previene acordando una convención de nombres única y agrupando con `@Nested` [3].

<a id="apartado-052"></a>

#### 3.3.2.12. Glosario de términos técnicos
- **API pública:** conjunto de constructores y métodos públicos del componente [1].
- **Detalle de implementación:** parte interna del componente que puede cambiar sin afectar al comportamiento [1], [2].
- **Contrato:** reglas acordadas sobre cómo debe comportarse el componente.
- **Getter / setter:** métodos públicos para leer y asignar el valor de una propiedad [9].
- **Valor nulo (null):** ausencia de valor. No es lo mismo que un texto vacío ("") .
- **assertEquals:** aserción que comprueba que un valor obtenido coincide con el esperado [4].
- **assertThrows:** aserción que comprueba que un código lanza la excepción esperada [4].
- **assertAll:** agrupa varias aserciones y las ejecuta todas antes de informar de cuáles han fallado [4].
- **repaint():** solicita que el componente se vuelva a dibujar [5].
- **Cobertura de código:** porcentaje de líneas del código que se ejecutan durante las pruebas [8].

---

<a id="apartado-053"></a>

### 3.3.3. Prueba de simulación de interacción

<a id="apartado-054"></a>

#### 3.3.3.1. Simular un clic/interacción mediante la herramienta disponible  
**Objetivo:** provocar en la prueba la acción del usuario sin intervención manual.
**Qué se hace:**
- **Opción A:** sin librerías gráficas: invocar el método de acción público del componente o, si es un botón, **doClick()**, que realiza un clic por programa [4]. El botón dispara entonces el evento de acción y se invoca **actionPerformed** en sus listeners [5].
- **Opción B:** con AssertJ Swing (solo si se ha decidido añadirla): localizar el componente por nombre y simular el clic [6].
- Las operaciones sobre componentes Swing se ejecutan en el Event Dispatch Thread [7].
- La opción elegida y su motivo se anotan en el issue.
**Resultado esperado:** la interacción se simula de forma repetible en la prueba.

<a id="apartado-055"></a>

#### 3.3.3.2. Verificar que se emite exactamente la notificación esperada
**Objetivo:** comprobar que, tras la interacción, el listener ha recibido una única notificación y con el contenido correcto.
**Qué se hace:**
- Comprobar el número de notificaciones recibidas con **assertEquals(esperado, actual)** [8].
- Comprobar el contenido del evento recibido [1].
- Agrupar varias comprobaciones con **assertAll** [8].
- Si se espera una lista de eventos, compararla con **assertIterableEquals** [8].
**Resultado esperado:** el test falla si no se notifica, si se notifica más de una vez o si el contenido no es el previsto.

<a id="apartado-056"></a>

#### 3.3.3.3. Verificar que no se notifica a un listener después de desregistrarlo
**Objetivo:** comprobar que un listener desregistrado deja de recibir notificaciones.
**Qué se hace:**
- Registrar el listener, desregistrarlo con el método **remove<Evento>Listener** [2], [4] y repetir la interacción.
- Comprobar con **assertEquals** que el número de notificaciones del listener no ha cambiado [8].
- Opcionalmente, comprobar que ya no figura entre los listeners del componente [4].
**Resultado esperado:** tras desregistrarlo, el listener no recibe ninguna notificación nueva.

<a id="apartado-057"></a>

#### 3.3.3.4. Comprobar el cambio de estado observable tras la interacción
**Objetivo:** confirmar que, además de notificar, el componente actualiza su estado observable.
**Qué se hace:**
- Leer el estado del componente tras la interacción y compararlo con el esperado mediante **assertEquals** [8].
- Leer el estado dentro del Event Dispatch Thread cuando se acceda directamente al componente [7].
- Agrupar con **assertAll** las comprobaciones de varios elementos [8].
**Resultado esperado:** el estado observable coincide con el previsto después de la interacción.

<a id="apartado-058"></a>

#### 3.3.3.5. Ejecutar las pruebas en conjunto con el resto de la suite
**Objetivo:** comprobar que las nuevas pruebas funcionan junto con las demás y no se afectan entre sí.
**Qué se hace:**
- Ejecutar todas las pruebas con **mvn test**: Surefire ejecuta los tests en la fase **test** [9].
- Comprobar que la clase de test se detecta por su nombre (**\*Test**) [11].
- Ejecutar solo esta clase con **mvn -Dtest=NombreClase test** [10].
- Revisar los informes en **target/surefire-reports** [9].
**Resultado esperado:** las pruebas pasan tanto solas como junto al resto de la suite.

<a id="apartado-059"></a>

#### 3.3.3.6. Registrar incidencias y repetir la prueba tras las correcciones
**Objetivo:** dejar constancia de los fallos encontrados y comprobar que quedan corregidos.
**Qué se hace:**
- Registrar cada problema encontrado como issue de GitHub [12].
- Tras corregir, repetir la prueba con **mvn test** y comprobar el informe de resultados [9].
- Anotar en el issue qué falló, qué se corrigió y el resultado de la repetición.
**Resultado esperado:** incidencias registradas y pruebas repetidas tras cada corrección.

<a id="apartado-060"></a>

#### 3.3.3.7. Documentar el procedimiento y los resultados
**Objetivo:** que cualquier miembro del equipo pueda repetir las pruebas y ver qué resultados dieron.
**Qué se hace:**
- Describir el procedimiento seguido.
- Indicar los comandos para ejecutar las pruebas y dónde están los informes [9].
- Recoger los resultados y las incidencias registradas [12].

| Prueba | Resultado |
|---|---|
| Notificación esperada | *Comprobado* |
| Sin notificación tras desregistrar | *Comprobado* |
| Cambio de estado observable | *Comprobado* |
| Ejecución en conjunto con la suite | *Comprobado* |

<a id="apartado-061"></a>

#### 3.3.3.8. Documentación
**Objetivo:** dejar el código de pruebas comprensible para quien no ha participado en la tarea.
**Qué se hace:**
- Añadir Javadoc a la clase de test [13].
- Comentar brevemente la preparación en `@BeforeEach` y la limpieza en `@AfterEach` [3].
- Enlazar este issue desde la documentación [12].
**Resultado esperado:** código de pruebas y documentación coherentes entre sí.

<a id="apartado-062"></a>

#### 3.3.3.9. Implementación y resultados de las pruebas de simulación
Se ha completado con éxito la fase de pruebas unitarias para el componente **ComponenteStockScan**. El objetivo principal era validar el patrón Observer implementado, asegurando que la comunicación entre el componente y sus suscriptores funciona de manera fiable y sin efectos secundarios no deseados.

**Enfoque de las pruebas realizadas:**
Para garantizar la calidad del componente, se ha diseñado una suite de pruebas automatizadas con **JUnit 5** (integrada en Java 21). Dado que el componente no cuenta con un elemento gráfico nativo para simular clics, se optó por invocar el método **notificarObservadores** desde la prueba con el acceso permitido por la clase y el paquete, lo que permite un control total y repetible sobre el escenario de prueba.

Los escenarios clave que han sido validados son los siguientes:
- **Verificación de la notificación:** Se comprobó que, al simular la interacción, el listener registrado recibe exactamente un evento. Además, se validó que el evento transporta el código correcto, el mensaje esperado y que el objeto origen es, efectivamente, la instancia del componente.
- **Gestión del ciclo de vida (Desregistro):** Se verificó que al eliminar un listener mediante el método correspondiente, este deja de recibir notificaciones en futuras interacciones, garantizando que no queden "suscriptores fantasma".
- **Comprobación del estado observable:** Se aseguró que los datos reflejados en el evento emitido coinciden de manera exacta con el estado esperado tras la interacción.
- **Robustez del componente:** Se incluyeron comprobaciones para asegurar que el componente no lanza excepciones ni genera eventos si se intenta notificar cuando no hay ningún listener registrado, o si se intentan registrar listeners nulos o duplicados.

**Ejecución y Gestión con Maven:**
La ejecución de las pruebas se ha gestionado a través de **Maven**, cumpliendo con los requisitos de integración en la suite del proyecto:
- **Detección de pruebas:** El plugin **Maven Surefire** se encarga de ejecutar los tests en la fase **test** del ciclo de vida de Maven. Surefire detecta automáticamente la clase de prueba gracias a que su nombre sigue la convención establecida (**ComponenteStockScanTest**), cumpliendo con los patrones **\*Test**, **\*Tests** o **\*TestCase**.
- **Ejecución en conjunto:** Se ha ejecutado el comando **mvn test** desde la raíz del proyecto. Esto ha permitido comprobar que las nuevas pruebas funcionan correctamente junto con el resto de la suite, sin interferir entre sí.
- **Ejecución aislada:** Adicionalmente, se comprobó el resultado aislado de esta clase mediante el comando **mvn -Dtest=ComponenteStockScanTest test**, verificando que el comportamiento es idéntico tanto en aislamiento como en conjunto.
- **Informes generados:** Los resultados detallados de la ejecución han quedado registrados en los ficheros generados automáticamente por Surefire en el directorio **target/surefire-reports**.

**Resultados obtenidos (Salida de Maven):**
A continuación se muestra la salida exacta de la consola al ejecutar las pruebas mediante Maven, confirmando el éxito total de la suite:

```text
[INFO] -------------------------------------------------------
[INFO] T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.stockscan.view.components.ComponenteStockScanTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.145 s - in com.stockscan.view.components.ComponenteStockScanTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.345 s
[INFO] ------------------------------------------------------------------------
```


**Conclusión del registro aportado:**
La salida documentada recoge tres pruebas ejecutadas sin fallos ni errores. Este resultado respalda los casos incluidos en esa ejecución; no acredita por sí solo todos los estados gráficos, la interacción física del ratón ni el conjunto de la aplicación.

---

<a id="apartado-063"></a>

### Estado de revisión del apartado 3.3

| Apartado | Situación |
|---|---|
| 3.3.1. Aislamiento del componente | Documentado. Configuración de Maven, JUnit 5 y directrices de aislamiento revisadas. |
| 3.3.2. Aserciones sobre el estado interno | Documentado. Casos límite, valores por defecto y validaciones definidos. |
| 3.3.3. Prueba de simulación de interacción | Documentado. Implementación y resultados de pruebas aportados. |
| Integración conjunta | Comprobado que los mecanismos de notificación y los tests funcionan en la suite sin interferencias. |

Los resultados pendientes deben actualizarse después de ejecutar las comprobaciones.

Las fuentes técnicas justifican los mecanismos utilizados, pero no sustituyen las pruebas del proyecto.

<a id="apartado-064"></a>

### Referencias del apartado 3.3

> **Revisión bibliográfica pendiente:** se conserva la bibliografía del documento original. Algunas llamadas numéricas de 3.3.1 y 3.3.2 no coinciden con la entrada correspondiente de esta lista. Deben corregirse antes de la entrega; esta reorganización no equivale a una validación de las fuentes.

La numeración de las siguientes referencias corresponde únicamente al apartado 3.3.

[1] Oracle, "Introduction to Event Listeners," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html (accessed Nov. 19, 2026).

[2] Oracle, "Writing Bean Events," The Java Tutorials: JavaBeans. [Online]. Available: https://docs.oracle.com/javase/tutorial/javabeans/writing/events.html (accessed Nov. 19, 2026).

[3] JUnit Team, "JUnit 5 User Guide." [Online]. Available: https://junit.org/junit5/docs/current/user-guide/

[4] Oracle, "AbstractButton," Java Platform, Standard Edition 8 API Specification. [Online]. Available: https://docs.oracle.com/javase/8/docs/api/javax/swing/AbstractButton.html (accessed Nov. 19, 2026).

[5] Oracle, "How to Write an Action Listener," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html (accessed Nov. 19, 2026).

[6] AssertJ Swing, "Assertj Swing basics," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-basics/ (accessed Nov. 19, 2026).

[7] AssertJ Swing, "Assertj Swing EDT," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-edt/ (accessed Nov. 19, 2026).

[8] "JUnit Assertions: assertEquals, assertAll and More (JUnit 5)," HowToDoInJava. [Online]. Available: https://howtodoinjava.com/junit5/junit-5-assertions-examples/ (accessed Nov. 19, 2026).

[9] Apache Software Foundation, "Maven Surefire Plugin," Apache Maven. [Online]. Available: https://maven.apache.org/surefire/maven-surefire-plugin/ (accessed Nov. 19, 2026).

[10] Apache Software Foundation, "Running a Single Test," Apache Maven Surefire Plugin. [Online]. Available: https://maven.apache.org/plugins/maven-surefire-plugin/examples/single-test.html (accessed Nov. 19, 2026).

[11] Apache Software Foundation, "Inclusions and Exclusions of Tests," Apache Maven Surefire Plugin. [Online]. Available: https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html (accessed Nov. 19, 2026).

[12] GitHub, "About issues," GitHub Docs. [Online]. Available: https://docs.github.com/issues/tracking-your-work-with-issues/about-issues (accessed Nov. 19, 2026).

[13] Oracle, "Javadoc FAQ," Oracle Java Technologies. [Online]. Available: https://www.oracle.com/java/technologies/javase/javadoc-faq.html (accessed Nov. 19, 2026).

[14] Apache NetBeans, "Apache NetBeans 10.0 New and Noteworthy," Apache Software Foundation. [Online]. Available: https://cwiki.apache.org/confluence/display/NETBEANS/Apache+NetBeans+10.0+New+and+Noteworthy

[15] J. McDonnell, "[NETBEANS-6] Adding Support for JUnit 5 #892," GitHub, apache/netbeans. [Online]. Available: https://github.com/apache/netbeans/pull/892

[16] "Migrating from JUnit 4 to JUnit 5," Baeldung. [Online]. Available: https://www.baeldung.com/junit-5-migration

[17] nipafx, "JUnit 5 Basics: @Test, Lifecycle, Assertions, Assumptions, And More." [Online]. Available: https://nipafx.dev/junit-5-basics/

[18] "JUnit 4 vs JUnit 5: Key Differences and When to Use Each," Leyaa. [Online]. Available: https://leyaa.ai/codefly/learn/junit/qna/junit-4-vs-junit-5-difference

[19] "Support for JUnit 5 #259," GitHub, assertj/assertj-swing. [Online]. Available: https://github.com/assertj/assertj-swing/issues/259

[20] Oracle, "Using Top-Level Containers," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html (accessed Nov. 14, 2026).

[21] AssertJ Swing, "Assertj Swing basics," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-basics/ (accessed Nov. 14, 2026).

[22] AssertJ Swing, "Assertj Swing lookup," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-lookup/ (accessed Nov. 14, 2026).

[23] Oracle, "Initial Threads," The Java Tutorials: Concurrency in Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html (accessed Nov. 14, 2026).

[24] AssertJ Swing, "Assertj Swing EDT," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-edt/ (accessed Nov. 14, 2026).

[25] AssertJ Swing, "FailOnThreadViolationRepaintManager," AssertJ Swing 2.0.1 API. [Online]. Available: https://javadoc.io/static/org.assertj/assertj-swing/2.0.1/org/assertj/swing/edt/FailOnThreadViolationRepaintManager.html (accessed Nov. 14, 2026).

[26] Oracle, "Using Headless Mode in the Java SE Platform," Oracle Technical Resources. [Online]. Available: https://www.oracle.com/technical-resources/articles/javase/headless.html (accessed Nov. 14, 2026).

[27] X.Org Foundation, "Xvfb: virtual framebuffer X server for X Version 11," X.Org documentation. [Online]. Available: https://www.x.org/X11R6.8.2/doc/Xvfb.1.html (accessed Nov. 14, 2026).

[28] AssertJ Swing, "Assertj Swing running," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-running/ (accessed Nov. 14, 2026).

[29] Oracle, "Javadoc FAQ," Oracle Java Technologies. [Online]. Available: https://www.oracle.com/java/technologies/javase/javadoc-faq.html (accessed Nov. 14, 2026).

[30] "Testing Swing elements in a headless environment (GitLab CI)," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/58963414/testing-swing-elements-in-a-headless-environment-gitlab-ci (accessed Nov. 14, 2026).

[31] "Swing UI tests on GitHub Actions," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/70758763/swing-ui-tests-on-github-actions (accessed Nov. 14, 2026).

[32] AssertJ Swing, "WaitTimedOutError," AssertJ Swing 1.2.0 API. [Online]. Available: https://www.javadoc.io/static/org.assertj/assertj-swing/1.2.0/org/assertj/swing/exception/WaitTimedOutError.html (accessed Nov. 14, 2026).

[33] AssertJ Swing, "ComponentLookupException," AssertJ Swing 1.2.0 API. [Online]. Available: https://www.javadoc.io/static/org.assertj/assertj-swing/1.2.0/org/assertj/swing/exception/ComponentLookupException.html (accessed Nov. 14, 2026).

[34] GitHub, "Understanding GitHub Actions," GitHub Docs. [Online]. Available: https://docs.github.com/en/actions/get-started/understand-github-actions (accessed Nov. 14, 2026).

[35] GitHub, "About pull requests," GitHub Docs. [Online]. Available: https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests (accessed Nov. 14, 2026).

[36] Apache NetBeans, "Apache NetBeans (incubating) 11.0 Features." [Online]. Available: https://netbeans.apache.org/download/nb110/index.html

[37] "Apache Maven vs Gradle," StackShare. [Online]. Available: https://stackshare.io/gradle/vs/maven

[38] R. Singh, "Gradle vs Maven: Differences, Performance, and Which Build Tool Should You Choose?," upGrad. [Online]. Available: https://www.upgrad.com/blog/gradle-vs-maven/

[39] "Java Netbeans: java with maven vs java with gradle," Feb. 18, 2026. [Online]. Available: https://dedetoknotes.blogspot.com/2026/02/java-netbeans-java-with-maven-vs-java.html

[40] K. Brzezinski, "Maven vs Gradle," DEV Community. [Online]. Available: https://dev.to/kuba_brzezinski_2d3356a2b/maven-vs-gradle-536b

[41] Gradle, "Maven and Gradle," Gradle Inc. [Online]. Available: https://gradle.org/maven-and-gradle

[42] "Changing an Ant project to Maven," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/20289118/changing-an-ant-project-to-maven

[43] "Non-standard sourceDirectory and testSourceDirectory in Apache Commons POMs," Apache Commons Developers mailing list. [Online]. Available: https://www.mail-archive.com/dev@commons.apache.org/msg25313.html

[44] G. Ingham, "Convert Ant based NetBeans project to a Maven project," IDR Solutions. [Online]. Available: https://blog.idrsolutions.com/convert-ant-based-netbeans-project-to-a-maven-project/

[45] "How do I add local jar files (not yet part of the Maven repository) to a Maven project?," Stack Overflow. [Online]. Available: https://kaweah.freedombox.rocks/kiwix/content/stackoverflow.com_en_all_2023-11/a/31023523

[46] "How do I configure Maven Surefire for JUnit tests?," Kodejava. [Online]. Available: https://kodejava.org/?p=18456

[47] "JUnit 5 Maven dependency pom.xml example," HowToDoInJava. [Online]. Available: https://howtodoinjava.com/junit-5/junit-5-maven-dependency-pom-xml-example/


<a id="apartado-065"></a>

## 3.4. Documentación de la API técnica y empaquetado del artefacto

El objetivo de esta actividad fue preparar el componente de escaneo de StockScan para que otro desarrollador pudiera utilizarlo en su propio proyecto Java. El trabajo se dividió en tres partes: explicar en el código cómo se utiliza el componente, generar una biblioteca con las clases necesarias y crear una guía que se pudiera consultar desde el navegador [1].

<a id="apartado-066"></a>

### 3.4.1. Documentación en código con JavaDoc

Para preparar esta tarea, realicé el curso *Documentación del código con Javadoc* de OpenWebinars, con una duración de 43 minutos. En él aprendí a escribir comentarios de documentación y a generar una guía a partir del código Java. Al terminar, adjunté el certificado a la actividad del sprint [2]–[4].

JavaDoc permite documentar una clase, un constructor o un método mediante un comentario que empieza con `/**` y termina con `*/`. El comentario se coloca justo encima del elemento que describe. Además de explicar para qué sirve, se pueden utilizar etiquetas para aclarar qué información recibe, qué devuelve y en qué situaciones puede fallar [5].

| Etiqueta | Qué explica |
|---|---|
| `@param` | El significado de cada parámetro de un método o constructor. |
| `@return` | El valor que devuelve un método. |
| `@throws` | La excepción que puede producirse y la condición que la provoca. |
| `@author` | La autoría del elemento documentado. |
| `@version` | La versión del elemento documentado. |
| `@since` | La versión desde la que está disponible. |
| `@see` | Una referencia a otra clase, método o recurso relacionado. |

En StockScan documenté la **API pública**, es decir, las clases y los métodos que puede utilizar quien incorpore el componente a su programa. En `ComponenteStockScan` expliqué para qué sirve el panel, cómo se crea mediante su constructor sin argumentos y cómo se registra o se retira un oyente. Un oyente es el objeto que quiere recibir los avisos del componente [6].

También expliqué la relación entre `StockScanEvent` y `StockScanListener`. El primero contiene los datos de la notificación, como el código del artículo y el mensaje. El segundo define el método que recibe el aviso. Por ejemplo, si el componente comunica `ART-02` y `Stock actualizado`, el oyente recibe esos dos datos en el mismo evento [6].

En los métodos de consulta y modificación de propiedades expliqué qué valor se obtiene, qué valores se admiten y qué ocurre cuando un dato es incorrecto. Un caso concreto es el constructor de `StockScanEvent`: si el programa intenta crear un evento con un origen nulo, se produce una `IllegalArgumentException`. Documentar esa condición ayuda a utilizar la clase correctamente [6].

Por último, revisé los comentarios existentes, completé las explicaciones que faltaban y publiqué los cambios en la rama `main` de StockScan SGA. También se comprobó que JavaDoc podía generar la documentación pública con Java 21 sin emitir avisos [6], [7].

<a id="apartado-067"></a>

### 3.4.2. Compilación del artefacto reutilizable

El objetivo de esta tarea fue entregar el componente de escaneo en un **archivo JAR** que pudiera añadirse como biblioteca a otro proyecto. Un JAR agrupa en un único archivo las clases Java ya compiladas y los recursos que necesitan. Trabajé con Apache NetBeans, el JDK 21 y Apache Ant 1.10.17. Comprobé la versión de Ant mediante `ant -version` [8]–[12].

**Primera compilación.** Ejecuté **Clean and Build** en NetBeans para limpiar los resultados anteriores y volver a compilar. El proceso terminó con el mensaje `BUILD SUCCESSFUL` y generó `StockScanE.jar` en la carpeta `dist`. Este archivo contiene la aplicación completa. Para entregar únicamente el componente reutilizable preparé un segundo JAR [12].

**Incidencia con Java 21.** Durante las pruebas apareció una advertencia relacionada con las opciones `-source 21` y `-target 21`. Al añadir `--release 21` como argumento adicional, NetBeans seguía enviando las opciones anteriores y la compilación falló. La documentación de `javac` indica que `--release` no se puede combinar con `--source` ni con `--target`. Retiré el argumento adicional y, en la configuración final de Ant, utilicé el atributo `release` para compilar con la versión 21 [12]–[15].

**Generación de la biblioteca.** Añadí manualmente al archivo `build.xml` un objetivo de Ant llamado `jar-componente`. Al ejecutarlo, Ant crea una carpeta temporal, compila las clases necesarias y las empaqueta en `stockscan-componente-1.0.0.jar`. El número `1.0.0` identifica la versión de la biblioteca. El archivo se guarda en `StockScanE/dist/` [14]–[17].

| Elemento incluido en el JAR | Función |
|---|---|
| `BasePanel` | Es la clase base de la que hereda el componente. |
| `ComponenteStockScan` | Permite registrar oyentes y enviarles notificaciones. |
| `StockScanEvent` | Contiene los datos de cada aviso. |
| `StockScanListener` | Define cómo recibe el aviso el programa que utiliza la biblioteca. |

Para generar la biblioteca desde la raíz del repositorio se ejecuta el siguiente comando [15]:

```powershell
ant -f StockScanE/build.xml jar-componente
```

**Prueba realizada.** Primero comprobé qué clases contenía el archivo. Para ello utilicé la opción `t` de la herramienta `jar`, que permite listar el contenido del paquete [8], [12]:

```powershell
jar tf StockScanE/dist/stockscan-componente-1.0.0.jar
```

Después compilé un programa de prueba en una carpeta independiente utilizando el JAR como única dependencia de StockScan. Registré dos veces el mismo oyente y envié un aviso con el código `ART-02` y el mensaje `Stock actualizado`. La consola mostró una sola notificación. A continuación, retiré el oyente y envié otro aviso. Esta vez no apareció ningún mensaje nuevo. Con esta prueba comprobé que la biblioteca funcionaba fuera del proyecto original y que el registro y la retirada de oyentes se comportaban como indicaba su documentación [12].

<a id="apartado-068"></a>

### 3.4.3. Generación de la documentación técnica

El objetivo de esta tarea fue convertir los comentarios del apartado 3.4.1 en una guía que pudiera abrirse desde el navegador. La herramienta `javadoc`, incluida en el JDK, lee los comentarios del código fuente y genera páginas HTML con la descripción de las clases, los constructores y los métodos [18].

**Cómo generé la documentación.** NetBeans ya tenía configurada una tarea para generar JavaDoc. Lo que añadí manualmente en `build.xml` fue un objetivo llamado `javadoc-zip`. Este objetivo ejecuta la generación de JavaDoc, guarda las páginas y sus recursos en una carpeta temporal y después los comprime en `stockscan-javadoc.zip`. El ZIP se guarda en `StockScanE/dist/`. La configuración también establece que un aviso de documentación detenga el proceso para poder revisarlo antes de entregar el archivo [15], [18]–[20].

Para repetir el proceso después de modificar los comentarios del código se ejecuta este comando desde la raíz del repositorio [15]:

```powershell
ant -f StockScanE/build.xml javadoc-zip
```

**Prueba realizada.** La generación terminó sin avisos. Para comprobar la guía tal como la recibiría otro desarrollador, descomprimí el ZIP completo y abrí `index.html` en Chrome. Desde el índice accedí al paquete del componente de escaneo y comprobé los enlaces entre sus páginas. En `ComponenteStockScan` revisé cómo crear el panel y registrar un oyente. En `StockScanEvent` comprobé la descripción del código del artículo y del mensaje. En `StockScanListener` localicé el método que recibe la notificación [21], [22].

Como resultado, el apartado 3.4 produjo dos archivos complementarios. **`stockscan-componente-1.0.0.jar`** permite incorporar el componente a otro proyecto Java. **`stockscan-javadoc.zip`** contiene la guía para aprender a utilizarlo [17], [22].

<a id="apartado-069"></a>

### Referencias del apartado 3.4

La numeración de estas referencias corresponde únicamente al apartado 3.4.

[1] StockScan Team, “3.4 Documentación de la API técnica y empaquetado del artefacto,” actividad n.º 59, GitHub, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-dii/issues/59>.

[2] OpenWebinars, “Documentación del código con Javadoc,” curso en línea. Disponible en: <https://openwebinars.net/cursos/documentacion-codigo-javadoc/>.

[3] OpenWebinars, *Formaciones Becas Despega*, catálogo de cursos, entrada “Documentación del código con Javadoc”, 43 min. [En línea]. Disponible en: <https://cdn.openwebinars.net/recursos/FORMACIONES-BECAS-DESPEGA.pdf>.

[4] OpenWebinars, “Certificado de Documentación del código con Javadoc,” certificado de Ángel Luis Sánchez Pérez, 2026. [En línea]. Disponible en: <https://github.com/user-attachments/files/34800580/certificado_documentacion_del_codigo_con_javadoc.pdf>.

[5] Oracle, “Documentation Comment Specification for the Standard Doclet (JDK 21),” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: <https://docs.oracle.com/en/java/javase/21/docs/specs/javadoc/doc-comment-spec.html>.

[6] StockScan Team, “3.4.1 Documentación en código (Javadoc),” actividad n.º 71, con ejemplo de comentario, certificado y revisión del componente, GitHub, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-dii/issues/71>.

[7] Á. L. Sánchez Pérez, “Actualizar la documentación JavaDoc,” *StockScan SGA*, commit `57fdf01`, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-sga/commit/57fdf016ed779e2904b1826221a1236199a8ddf3>.

[8] Oracle, “The jar Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: <https://docs.oracle.com/en/java/javase/21/docs/specs/man/jar.html>.

[9] Oracle, “Oracle Java SE Support Roadmap.” [En línea]. Disponible en: <https://www.oracle.com/java/technologies/java-se-support-roadmap.html>.

[10] Apache Software Foundation, *Apache Ant User Manual*. [En línea]. Disponible en: <https://ant.apache.org/manual/>.

[11] Apache Software Foundation, “Apache Ant 1.10.17 Released,” *Apache Ant Project News*, 2026. [En línea]. Disponible en: <https://ant.apache.org/antnews.html>.

[12] StockScan Team, “3.4.2 Compilación del artefacto reutilizable,” actividad n.º 72, con las capturas de NetBeans y los resultados de las pruebas, GitHub, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-dii/issues/72>.

[13] Oracle, “The javac Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: <https://docs.oracle.com/en/java/javase/21/docs/specs/man/javac.html>.

[14] Apache Software Foundation, “Javac Task,” *Apache Ant User Manual*. [En línea]. Disponible en: <https://ant.apache.org/manual/Tasks/javac.html>.

[15] StockScan Team, “Configuración de compilación y empaquetado de StockScanE,” archivo `build.xml`, *StockScan SGA*, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/build.xml>.

[16] Apache Software Foundation, “Jar Task,” *Apache Ant User Manual*. [En línea]. Disponible en: <https://ant.apache.org/manual/Tasks/jar.html>.

[17] StockScan Team, “Componente de escaneo de StockScan,” biblioteca Java, versión 1.0.0, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/dist/stockscan-componente-1.0.0.jar>.

[18] Oracle, “The javadoc Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: <https://docs.oracle.com/en/java/javase/21/docs/specs/man/javadoc.html>.

[19] Apache Software Foundation, “Javadoc Task,” *Apache Ant User Manual*. [En línea]. Disponible en: <https://ant.apache.org/manual/Tasks/javadoc.html>.

[20] Apache Software Foundation, “Zip Task,” *Apache Ant User Manual*. [En línea]. Disponible en: <https://ant.apache.org/manual/Tasks/zip.html>.

[21] StockScan Team, “3.4.3 Generación de la documentación técnica,” actividad n.º 73, con el procedimiento y las comprobaciones realizadas, GitHub, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-dii/issues/73>.

[22] StockScan Team, “Documentación JavaDoc de StockScan SGA,” sitio HTML distribuido en `stockscan-javadoc.zip`, 2026. [En línea]. Disponible en: <https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/dist/stockscan-javadoc.zip>.


<a id="apartado-070"></a>

## 3.5. Integración en el proyecto transversal

Este bloque recoge la vinculación de la biblioteca al proyecto StockScanE. Los demás subapartados de integración no se incluyen en el texto aportado.

<a id="apartado-071"></a>

### 3.5.1. Vinculación de la librería

Para que el proyecto transversal `StockScanE` pudiera utilizar el componente de escaneo, incorporé el JAR generado en la tarea 3.4.2 siguiendo estos pasos:

1. Copié `stockscan-componente-1.0.0.jar` en la carpeta `lib` del proyecto.
2. En `nbproject/project.properties`, añadí la ruta relativa `lib/stockscan-componente-1.0.0.jar` a las bibliotecas de compilación (`javac.classpath`). Así, NetBeans puede localizar el JAR en cualquier equipo que descargue el proyecto.
3. En `build.xml`, excluí de la compilación de la aplicación las cuatro clases e interfaces que ya contiene el JAR. De esta forma, la aplicación utiliza la biblioteca y evita duplicar esas clases.

**Comprobación.** Compilé el proyecto desde cero y ejecuté la prueba de notificaciones. Verifiqué que Java cargaba `ComponenteStockScan`, `StockScanEvent` y `StockScanListener` desde el JAR. El programa recibió una única notificación con el código `ART-02` y el mensaje `Stock actualizado`. [Ver el cambio en GitHub](https://github.com/stockscan-team/stockscan-sga/commit/f0987a69d2bf4c6f2bb7864bd9e0188dda7d793f)

<a id="apartado-072"></a>

## Balance y revisión final

La documentación recoge la conexión entre interacción, notificación y respuesta visual; el procedimiento de pruebas; la preparación del JAR y de Javadoc; y la vinculación de la biblioteca al proyecto transversal.

| Bloque | Contenido disponible | Revisión pendiente |
|---|---|---|
| 3.1 | Posición reservada. | Incorporar la documentación del equipo. |
| 3.2 | Implementación, capturas, grabaciones y pruebas de notificación. | Contrastar el catálogo por pantalla y completar FB-01 a FB-11. |
| 3.3 | Procedimientos y registro de tres pruebas sin fallos. | Corregir la correspondencia de citas y asociar los resultados a la versión probada. |
| 3.4 | Documentación de API, JAR, Javadoc y enlaces a entregables. | Comprobar la accesibilidad de los entregables en la revisión de entrega. |
| 3.5 | Vinculación del JAR documentada. | Incorporar los demás subapartados que correspondan al alcance del sprint. |

<a id="apartado-073"></a>

### Lista de comprobación para la entrega

- [ ] Incorporar el apartado 3.1.
- [ ] Completar la fecha de cierre acordada por el equipo.
- [ ] Identificar la versión o commit final al que corresponde la memoria.
- [ ] Revisar las referencias y su correspondencia con las citas, especialmente en 3.3.
- [ ] Completar las pruebas pendientes sin sustituir los resultados esperados por resultados obtenidos.
- [ ] Comprobar que las imágenes y grabaciones se visualizan en GitHub.
- [ ] Revisar los enlaces al código, JAR y documentación Javadoc.
- [ ] Incorporar las aportaciones restantes de integración, si forman parte de la entrega.
- [ ] Realizar la revisión conjunta del equipo antes de cerrar la memoria.
