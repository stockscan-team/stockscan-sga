# Memoria del Sprint 03 · Desarrollo de Interfaces

## 3.2. Implementación del modelo de eventos y respuestas

### Índice del apartado 3.2

- [3.2.1. Captura de eventos de bajo nivel](#321-captura-de-eventos-de-bajo-nivel)
  - [3.2.1.1. Objetivo e investigación](#3211-objetivo-e-investigación)
  - [3.2.1.2. Registro y control de eventos](#3212-registro-y-control-de-eventos)
  - [3.2.1.3. Catálogo de interacciones por vista](#3213-catálogo-de-interacciones-por-vista)
  - [3.2.1.4. Evidencias y revisión](#3214-evidencias-y-revisión)
- [3.2.2. Mecanismo de notificación propio](#322-mecanismo-de-notificación-propio)
  - [3.2.2.1. Elección del contrato](#3221-elección-del-contrato)
  - [3.2.2.2. Implementación](#3222-implementación)
  - [3.2.2.3. Pruebas del mecanismo](#3223-pruebas-del-mecanismo)
  - [3.2.2.4. Evidencias y documentación Javadoc](#3224-evidencias-y-documentación-javadoc)
- [3.2.3. Disparo y feedback visual](#323-disparo-y-feedback-visual)
  - [3.2.3.1. Objetivo y mecanismo elegido](#3231-objetivo-y-mecanismo-elegido)
  - [3.2.3.2. Estados visuales](#3232-estados-visuales)
  - [3.2.3.3. Emisión y recepción de notificaciones](#3233-emisión-y-recepción-de-notificaciones)
  - [3.2.3.4. Integración en las pantallas](#3234-integración-en-las-pantallas)
  - [3.2.3.5. Accesibilidad básica](#3235-accesibilidad-básica)
  - [3.2.3.6. Evidencias visuales](#3236-evidencias-visuales)
  - [3.2.3.7. Plan de verificación](#3237-plan-de-verificación)
- [Estado de revisión del apartado 3.2](#estado-de-revisión-del-apartado-32)
- [Referencias del apartado 3.2](#referencias-del-apartado-32)

### 3.2.1. Captura de eventos de bajo nivel

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

#### 3.2.1.2. Registro y control de eventos

En el trabajo de captura de eventos se describe un método privado `configurarEventos()`, llamado después de `initComponents()`, para separar el registro de escuchadores del código generado por NetBeans.

En la captura manual se contemplan dos controles:

- **`clicIniciado`:** registra una pulsación iniciada dentro de un botón habilitado y permite cancelar la acción cuando el puntero sale antes de soltar.
- **`teclaPulsada`:** permite controlar la repetición del teclado y ejecutar la acción al liberar la tecla.

Estos controles corresponden a la implementación manual descrita para este apartado.

En la integración posterior del feedback sobre los botones, descrita en el apartado **3.2.3**, se utiliza `ButtonModel` y `ActionListener`. No deben registrarse ambos mecanismos para ejecutar la misma acción, porque podrían producir notificaciones duplicadas.

Los escuchadores deben ejecutar tareas breves para no bloquear la respuesta de la interfaz [1].

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

### 3.2.2. Mecanismo de notificación propio

#### 3.2.2.1. Elección del contrato

Se analizaron dos alternativas para comunicar las acciones del componente a otras clases:

- Utilizar `ActionListener` y `ActionEvent`.
- Definir un evento propio y una interfaz de escucha.

`ActionEvent` representa una acción semántica y proporciona información como el origen, el identificador, el comando, los modificadores y la marca temporal [6].

Se eligió un evento propio para representar explícitamente los datos del proyecto mediante los campos `codigo` y `mensaje`, evitando depender de la interpretación de una única cadena de comando.

El contrato se compone de `StockScanEvent` y `StockScanListener`. Permite que un controlador reciba la notificación sin que el componente necesite conocer su implementación concreta.

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

### 3.2.3. Disparo y feedback visual

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

#### 3.2.3.5. Accesibilidad básica

Los botones se configuran para poder recibir el foco y muestran un borde naranja cuando lo tienen.

Un escuchador de foco actualiza la apariencia al ganarlo o perderlo.

Se conservan las asociaciones de teclado de `JButton`, gestionadas por Swing mediante `InputMap` y `ActionMap` [12].

La navegación mediante Tab y la activación mediante Espacio deben comprobarse en ejecución. Su comportamiento depende también de las asociaciones establecidas por la apariencia gráfica utilizada.

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

### Estado de revisión del apartado 3.2

| Apartado | Situación |
|---|---|
| 3.2.1. Captura de eventos de bajo nivel | Documentación y ejemplos aportados; pendiente contrastar el catálogo con la versión final. |
| 3.2.2. Mecanismo de notificación propio | Implementación documentada y resultados de pruebas aportados. |
| 3.2.3. Disparo y feedback visual | Implementación y grabaciones aportadas; pendiente completar el registro individual de pruebas. |
| Integración conjunta | Pendiente comprobar que los mecanismos no duplican acciones sobre los mismos controles. |

Los resultados pendientes deben actualizarse después de ejecutar las comprobaciones.

Las fuentes técnicas justifican los mecanismos utilizados, pero no sustituyen las pruebas del proyecto.

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
