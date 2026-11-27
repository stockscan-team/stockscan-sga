<div align="center">

# STOCKSCAN

## Memoria del Sprint 03 · Modularidad y Componentes Propios

### Desarrollo de Interfaces

**Componentes reutilizables, eventos, pruebas e integración**

**Proyecto transversal · StockScan SGA**

---

**Equipo**

Carmen Monge · Álvaro López de San Román  
Elena Sáez · Ángel Luis Sánchez

**Scrum Master:** Carmen Monge

**Fecha de edición:** 27 de noviembre de 2026  
**Fecha de cierre del sprint:** 27 de noviembre de 2026

**Estado del documento:** finalizado

</div>

---

## Índice

- [1. Arquitectura de la clase, encapsulación y propiedades por defecto](#1-arquitectura-de-la-clase-encapsulación-y-propiedades-por-defecto)
  - [1.1. Definición de la jerarquía de herencia](#11-definición-de-la-jerarquía-de-herencia)
    - [1.1.1. Decisión de jerarquía de herencia](#111-decisión-de-jerarquía-de-herencia)
    - [1.1.2. Estructura actual identificada](#112-estructura-actual-identificada)
    - [1.1.3. Decisión sobre la clase padre](#113-decisión-sobre-la-clase-padre)
    - [1.1.4. Organización de paquetes propuesta](#114-organización-de-paquetes-propuesta)
    - [1.1.5. Diseño de la clase BasePanel](#115-diseño-de-la-clase-basepanel)
  - [1.2. Encapsulación estricta](#12-encapsulación-estricta)
    - [1.2.1. Análisis del proyecto actual](#121-análisis-del-proyecto-actual)
    - [1.2.2. Propiedades y atributos que debe gestionar el componente](#122-propiedades-y-atributos-que-debe-gestionar-el-componente)
    - [1.2.3. Declaración de los atributos como private](#123-declaración-de-los-atributos-como-private)
    - [1.2.4. Restricciones de los valores](#124-restricciones-de-los-valores)
    - [1.2.5. Revisión de accesos externos](#125-revisión-de-accesos-externos)
    - [1.2.6. Revisión de encapsulación ejecutada](#126-revisión-de-encapsulación-ejecutada)
    - [1.2.7. Riesgos pendientes](#127-riesgos-pendientes)
  - [1.3. Constructor y valores por defecto](#13-constructor-y-valores-por-defecto)
    - [1.3.1. Objetivo](#131-objetivo)
    - [1.3.2. Constructor sin argumentos](#132-constructor-sin-argumentos)
    - [1.3.3. Valores por defecto](#133-valores-por-defecto)
    - [1.3.4. Constructores adicionales](#134-constructores-adicionales)
    - [1.3.5. Comprobación](#135-comprobación)
    - [1.3.6. Documentación de los constructores](#136-documentación-de-los-constructores)
  - [1.4. Métodos accesores (getters y setters)](#14-métodos-accesores-getters-y-setters)
    - [1.4.1. Objetivo](#141-objetivo)
    - [1.4.2. Métodos disponibles](#142-métodos-disponibles)
    - [1.4.3. Validación de los parámetros](#143-validación-de-los-parámetros)
    - [1.4.4. Uso de repaint() y revalidate()](#144-uso-de-repaint-y-revalidate)
    - [1.4.5. Propiedades enlazadas](#145-propiedades-enlazadas)
    - [1.4.6. Comprobación](#146-comprobación)
    - [1.4.7. Revisión de la API](#147-revisión-de-la-api)
    - [1.4.8. Documentación de los accesores](#148-documentación-de-los-accesores)
- [2. Implementación del modelo de eventos y respuestas interactivas](#2-implementación-del-modelo-de-eventos-y-respuestas-interactivas)
  - [2.1. Captura de eventos de bajo nivel](#21-captura-de-eventos-de-bajo-nivel)
    - [2.1.1. Acciones del Usuario y Modelo de Interacción](#211-acciones-del-usuario-y-modelo-de-interacción)
    - [2.1.2. Registro de Listeners y Prevención de Eventos Duplicados](#212-registro-de-listeners-y-prevención-de-eventos-duplicados)
    - [2.1.3. Catálogo de Eventos de Bajo Nivel Implementados por Vista](#213-catálogo-de-eventos-de-bajo-nivel-implementados-por-vista)
    - [2.1.4. Pruebas Aisladas y Revisión de Interferencias](#214-pruebas-aisladas-y-revisión-de-interferencias)
  - [2.2. Mecanismo de notificación propio](#22-mecanismo-de-notificación-propio)
    - [2.2.1. Análisis, búsqueda y elección del contrato de notificación](#221-análisis-búsqueda-y-elección-del-contrato-de-notificación)
    - [2.2.2. Implementación de la API de eventos y gestión de observadores](#222-implementación-de-la-api-de-eventos-y-gestión-de-observadores)
    - [2.2.3. Plan de Pruebas y Verificación del Mecanismo de Notificación](#223-plan-de-pruebas-y-verificación-del-mecanismo-de-notificación)
  - [2.3. Disparo y feedback visual](#23-disparo-y-feedback-visual)
    - [2.3.1. Objetivo](#231-objetivo)
    - [2.3.2. Investigación y elección del mecanismo](#232-investigación-y-elección-del-mecanismo)
    - [2.3.3. Implementación de los estados visuales](#233-implementación-de-los-estados-visuales)
    - [2.3.4. Disparo de la notificación](#234-disparo-de-la-notificación)
    - [2.3.5. Recepción mediante un controlador externo](#235-recepción-mediante-un-controlador-externo)
    - [2.3.6. Integración en las pantallas](#236-integración-en-las-pantallas)
    - [2.3.7. Accesibilidad básica](#237-accesibilidad-básica)
    - [2.3.8. Evidencias visuales de la implementación](#238-evidencias-visuales-de-la-implementación)
    - [2.3.9. Plan de verificación](#239-plan-de-verificación)
- [3. Pruebas automatizadas y validación de estados aislados](#3-pruebas-automatizadas-y-validación-de-estados-aislados)
  - [3.1. Aislamiento del componente](#31-aislamiento-del-componente)
    - [3.1.1. Comprobar la configuración del proyecto y la versión de Java utilizada](#311-comprobar-la-configuración-del-proyecto-y-la-versión-de-java-utilizada)
    - [3.1.2. Añadir JUnit 5 como dependencia de pruebas](#312-añadir-junit-5-como-dependencia-de-pruebas)
    - [3.1.3. Crear la clase de test en el paquete de pruebas correspondiente](#313-crear-la-clase-de-test-en-el-paquete-de-pruebas-correspondiente)
    - [3.1.4. Preparar la creación del componente sin depender de la ventana principal](#314-preparar-la-creación-del-componente-sin-depender-de-la-ventana-principal)
    - [3.1.5. Ejecutar las operaciones de UI en el Event Dispatch Thread cuando corresponda](#315-ejecutar-las-operaciones-de-ui-en-el-event-dispatch-thread-cuando-corresponda)
    - [3.1.6. Añadir utilidades gráficas solo si aportan valor; evitar dependencias innecesarias](#316-añadir-utilidades-gráficas-solo-si-aportan-valor-evitar-dependencias-innecesarias)
    - [3.1.7. Ejecutar un test mínimo para verificar que la infraestructura funciona](#317-ejecutar-un-test-mínimo-para-verificar-que-la-infraestructura-funciona)
    - [3.1.8. Documentar la configuración y cómo ejecutar las pruebas](#318-documentar-la-configuración-y-cómo-ejecutar-las-pruebas)
    - [3.1.9. Documentación](#319-documentación)
    - [3.1.10. Riesgos y problemas previsibles en las pruebas del componente Swing](#3110-riesgos-y-problemas-previsibles-en-las-pruebas-del-componente-swing)
    - [3.1.11. Decisión de herramienta de construcción: Maven en lugar de Gradle](#3111-decisión-de-herramienta-de-construcción-maven-en-lugar-de-gradle)
    - [3.1.12. Migración parcial a Maven solo para las pruebas (el proyecto sigue en Ant)](#3112-migración-parcial-a-maven-solo-para-las-pruebas-el-proyecto-sigue-en-ant)
    - [3.1.13. Glosario de términos técnicos](#3113-glosario-de-términos-técnicos)
  - [3.2. Pruebas de valores por defecto, getters/setters y validación del componente](#32-pruebas-de-valores-por-defecto-getterssetters-y-validación-del-componente)
    - [3.2.1. Resumen](#321-resumen)
    - [3.2.2. Desarrollo de las tareas](#322-desarrollo-de-las-tareas)
    - [3.2.3. Riesgos y problemas previsibles](#323-riesgos-y-problemas-previsibles)
    - [3.2.4. Glosario de términos técnicos](#324-glosario-de-términos-técnicos)
  - [3.3. Prueba de simulación de interacción](#33-prueba-de-simulación-de-interacción)
    - [3.3.1. Simular un clic/interacción mediante la herramienta disponible o invocar el método de acción público](#331-simular-un-clicinteracción-mediante-la-herramienta-disponible-o-invocar-el-método-de-acción-público)
    - [3.3.2. Verificar que se emite exactamente la notificación esperada](#332-verificar-que-se-emite-exactamente-la-notificación-esperada)
    - [3.3.3. Verificar que no se notifica a un listener después de desregistrarlo](#333-verificar-que-no-se-notifica-a-un-listener-después-de-desregistrarlo)
    - [3.3.4. Comprobar el cambio de estado observable tras la interacción](#334-comprobar-el-cambio-de-estado-observable-tras-la-interacción)
    - [3.3.5. Ejecutar las pruebas en conjunto con el resto de la suite](#335-ejecutar-las-pruebas-en-conjunto-con-el-resto-de-la-suite)
    - [3.3.6. Registrar incidencias y repetir la prueba tras las correcciones](#336-registrar-incidencias-y-repetir-la-prueba-tras-las-correcciones)
    - [3.3.7. Documentar el procedimiento y los resultados](#337-documentar-el-procedimiento-y-los-resultados)
    - [3.3.8. Documentación](#338-documentación)
    - [3.3.9. Implementación y Resultados de las Pruebas de Simulación](#339-implementación-y-resultados-de-las-pruebas-de-simulación)
- [4. Documentación de la API técnica y empaquetado del artefacto](#4-documentación-de-la-api-técnica-y-empaquetado-del-artefacto)
  - [4.1. Documentación en código con JavaDoc](#41-documentación-en-código-con-javadoc)
  - [4.2. Compilación del artefacto reutilizable](#42-compilación-del-artefacto-reutilizable)
  - [4.3. Generación de la documentación técnica](#43-generación-de-la-documentación-técnica)
- [5. Integración y consumo del componente en el proyecto transversal](#5-integración-y-consumo-del-componente-en-el-proyecto-transversal)
  - [5.1. Vinculación de la librería](#51-vinculación-de-la-librería)
  - [5.2. Incorporación visual](#52-incorporación-visual)
    - [5.2.1. Objetivo y selección de la pantalla](#521-objetivo-y-selección-de-la-pantalla)
    - [5.2.2. Incorporación al formulario de NetBeans](#522-incorporación-al-formulario-de-netbeans)
    - [5.2.3. Configuración mediante la API pública](#523-configuración-mediante-la-api-pública)
    - [5.2.4. Distribución y actualización visual](#524-distribución-y-actualización-visual)
    - [5.2.5. Conservación de la pantalla existente](#525-conservación-de-la-pantalla-existente)
    - [5.2.6. Notificación y alcance de la acción](#526-notificación-y-alcance-de-la-acción)
    - [5.2.7. Revisión y resultados registrados](#527-revisión-y-resultados-registrados)
    - [5.2.8. Resultado y alcance de la entrega](#528-resultado-y-alcance-de-la-entrega)
  - [5.3. Conexión con el flujo de datos](#53-conexión-con-el-flujo-de-datos)
    - [5.3.1. Investigación](#531-investigación)
    - [5.3.2. Implementación](#532-implementación)
- [6. Balance y revisión final](#6-balance-y-revisión-final)
  - [6.1. Lista de comprobación para la entrega](#61-lista-de-comprobación-para-la-entrega)
- [7. Referencias](#7-referencias)

## 1. Arquitectura de la clase, encapsulación y propiedades por defecto

### 1.1. Definición de la jerarquía de herencia

#### 1.1.1. Decisión de jerarquía de herencia

La decisión inicial tomada es utilizar Java Swing y JPanel como clase padre, creando una clase base abstracta que sirva como fundamento para los componentes visuales reutilizables del sistema, en vez de extender directamente de JFrame. Esta representa una ventana de aplicación, mientras que JPanel está diseñado para agrupar y organizar componentes dentro de otras interfaces. Esta distinción permite separar la ventana principal del contenido funcional de cada módulo [1], [2].

##### 1.1.1.1. Justificación de la elección de JPanel como clase padre

Se ha elegido "javax.swing.JPanel" como clase padre para desarrollar el componente base de StockScan debido a su naturaleza de contenedor visual ligero y reutilizable dentro de la arquitectura Swing. Esta clase permite agrupar y organizar diferentes elementos gráficos mediante administradores de diseño, facilitando la construcción de interfaces modulares [2].

Además, su utilización permite separar la ventana principal de la aplicación, representada mediante "JFrame", del contenido visual de los distintos módulos funcionales. De esta forma, funcionalidades como la gestión de stock, las alertas y el dashboard podrán integrarse dentro de una misma ventana, favoreciendo la modularidad y el mantenimiento de la aplicación [2], [5].

Por último, mediante la creación de una clase abstracta propia que extienda de "JPanel", se establecerá una jerarquía de herencia que permitirá centralizar características visuales comunes y facilitar la ampliación futura de los módulos de StockScan [4].

#### 1.1.2. Estructura actual identificada

El proyecto por ahora presenta la siguiente organización:

```text
StockScanE/
   |__build.xml
   |
   |__manifest.mf
   |
   |__nbproject/
   |     |__project.properties
   |
   |__src/
         |__com/
               |__stockscan/
                     |__MainFrame.java
                     |
                     |__controller/
                     |     |__ChatController.java
                     |     |__MainController.java
                     |     |__StockController.java
                     |
                     |__model/
                     |     |__Alerta.java
                     |     |__Estanteria.java
                     |     |__MensajeChat.java
                     |     |__Movimiento.java
                     |     |__Operario.java
                     |     |__Producto.java
                     |     |
                     |     |__dao/
                     |           |__DatabaseConnection.java
                     |           |__ProductoDAO.java
                     |
                     |__view/
                           |__pages/
                                 |__AgendaPanel.java
                                 |__AlertasPanel.java
                                 |__ChatPanel.java
                                 |__DashboardPanel.java
                                 |__EquipoPanel.java
                                 |__EtiquetasPanel.java
                                 |__Login.java
                                 |__MapaAlmacenPanel.java
                                 |__StockPanel.java
                                 |__TrazabilidadPanel.java
```

#### 1.1.3. Decisión sobre la clase padre

##### 1.1.3.1. Comparativa de alternativas

Las dos opciones sobre las que se debate dentro de Swing son **JComponent** y **JPanel**:

| Criterio | Jcomponent | JPanel |
|---|---|---|
| Naturaleza | Clase base abstracta | Contenedor visual genérico |
| Permite agrupar componentes | Sí | Sí |
| Dispone de gestión de distribución | Heredada de Container |Sí, con gestos de distribución |
| Uso habitual | Componentes personalizados | Paneles y secciones de interfaz |
| Complejidad de implementación | Mayor | Menor |

**JComponent** es la clase base de prácticamente todos los componentes Swing, excepto los contenedores de nivel superior. Proporciona funcionalidades como bordes, accesibilidad, doble búfer y soporte para interfaces visuales personalizadas [1].

Por otra parte, **JPanel** es un contenedor ligero y genérico, pensado para agrupar componentes y gestionar su distribución mediante administradores de diseño [2].

**Utilizaremos JPanel**

##### 1.1.3.2. Justificación técnica

1. StockScan ya utiliza Swing.
2. Sus módulos funcionales están diseñados como pantallas con múltiples componentes.
3. Necesitamos que las pantallas puedan integrarse en una ventana principal.
4. JPanel permite reutilizar los módulos sin asociarlos obligatoriamente a una ventana independiente.
5. Evitamos implementar desde cero funcionalidades que JPanel ya proporciona [1], [2].

##### 1.1.3.3. Jerarquía de herencia propuesta

La idea es que exista una clase BasePanel que herede de JPanel y que sea nuestra clase base común.

Los módulos funcionales heredarían de ella, en lugar de heredar directamente de JPanel.
De esta manera, cualquier mejora transversal que incorporemos a BasePanel podrá beneficiar a todos los módulos que la extiendan.

#### 1.1.4. Organización de paquetes propuesta

```text
StockScanE/
   |__build.xml
   |
   |__manifest.mf
   |
   |__nbproject/
   |     |__project.properties
   |
   |__src/
         |__com/
               |__stockscan/
                     |__MainFrame.java
                     |
                     |__controller/
                     |     |__ChatController.java
                     |     |__MainController.java
                     |     |__StockController.java
                     |
                     |__model/
                     |     |__Alerta.java
                     |     |__Estanteria.java
                     |     |__MensajeChat.java
                     |     |__Movimiento.java
                     |     |__Operario.java
                     |     |__Producto.java
                     |     |
                     |     |__dao/
                     |           |__DatabaseConnection.java
                     |           |__ProductoDAO.java
                     |
                     |__view/
                           |__components/
                           |     |__BasePanel.java
                           |
                           |__pages/
                                 |__AgendaPanel.java
                                 |__AlertasPanel.java
                                 |__ChatPanel.java
                                 |__DashboardPanel.java
                                 |__EquipoPanel.java
                                 |__EtiquetasPanel.java
                                 |__Login.java
                                 |__MapaAlmacenPanel.java
                                 |__StockPanel.java
                                 |__TrazabilidadPanel.java
```

La separación tiene una finalidad concreta:
- **components:** componentes visuales reutilizables y clases base.
- **pages:** pantallas y módulos funcionales.

#### 1.1.5. Diseño de la clase BasePanel

##### 1.1.5.1. Responsabilidad

La clase BasePanel tendrá como responsabilidad centralizar el comportamiento visual compartido por los módulos de StockScan.

Propongo que pueda encargarse de:
- Establecer una configuración visual común.
- Centralizar colores y propiedades generales de los paneles.
- Proporcionar métodos protegidos para inicializar características comunes.
- Facilitar la evolución de los componentes visuales.
- Servir como punto de extensión para futuras funcionalidades.

No debe asumir responsabilidades de acceso a base de datos, gestión de productos ni operaciones de negocio. Estas responsabilidades pertenecen a otras capas de la aplicación.

##### 1.1.5.2. Especificación inicial

Características:
- **Nombre:** "BasePanel"
- **Paquete:** "com.stockscan.view.components"
- **Modificador:** "abstract"
- **Herencia:** "extends JPanel"
- **Visibilidad de la clase:** "public"
- **Constructor:** "protected"
- **Instanciación directa:** No permitida
- **Dependencias externas:** Ninguna
- **Compatibilidad:** Java 21

La elección de una clase abstracta es intencionada, no queremos crear instancias genéricas de "BasePanel", sino obligar a que los módulos concretos representen funcionalidades específicas del SGA.

##### 1.1.5.3. Esqueleto propuesto

```java
package com.stockscan.view.components;

import javax.swing.JPanel;

/**
 * Clase base para los componentes visuales de la aplicación StockScan.
 *
 * Centraliza las características visuales compartidas por los módulos de
 * los paneles.
 *
 * @author Equipo de desarrollo StockScan
 */
public abstract class BasePanel extends JPanel {

    /**
     * Constructor protegido de la clase base.
     */
    protected BasePanel() {
        super();
    }
}
```

### 1.2. Encapsulación estricta

#### 1.2.1. Análisis del proyecto actual

##### 1.2.1.1. Estructura actual

```text
StockScanE/
   |__build.xml
   |
   |__manifest.mf
   |
   |__nbproject/
   |     |__project.properties
   |
   |__src/
   |     |__com/
   |           |__stockscan/
   |                 |__MainFrame.java
   |                 |
   |                 |__controller/
   |                 |     |__ChatController.java
   |                 |     |__MainController.java
   |                 |     |__StockController.java
   |                 |
   |                 |__model/
   |                 |     |__Alerta.java
   |                 |     |__Estanteria.java
   |                 |     |__MensajeChat.java
   |                 |     |__Movimiento.java
   |                 |     |__Operario.java
   |                 |     |__Producto.java
   |                 |     |
   |                 |     |__dao/
   |                 |           |__DatabaseConnection.java
   |                 |           |__ProductoDAO.java
   |                 |
   |                 |__view/
   |                       |__components/
   |                       |     |__ComponenteStockScan.java
   |                       |
   |                       |__events/
   |                       |     |__StockScanEvent.java
   |                       |     |__StockScanListener.java
   |                       |
   |                       |__icons/
   |                       |
   |                       |__pages/
   |                             |__AgendaPanel.java
   |                             |__AlertasPanel.java
   |                             |__ChatPanel.java
   |                             |__DashboardPanel.java
   |                             |__EquipoPanel.java
   |                             |__EtiquetasPanel.java
   |                             |__Login.java
   |                             |__MapaAlmacenPanel.java
   |                             |__StockPanel.java
   |                             |__TrazabilidadPanel.java
   |
   |__test/
         |__com/
               |__stockscan/
                     |__view/
                           |__events/
                                 |__PruebaNotificacion.java
```

La estructura confirma que ya existe una separación entre componentes visuales, eventos y módulos funcionales. También se ha incorporado un mecanismo de notificaciones mediante el patrón **Observer**, utilizando **StockScanEvent** y **StockScanListener**.

##### 1.2.1.2. Clase que se podría mejorar a futuro

El archivo a modificar es
"src/com/stockscan/view/components/ComponenteStockScan.java".

**Actualmente contiene:**

```java
public class ComponenteStockScan extends JPanel {

    private final List<StockScanListener> observadores =
        new ArrayList<>();

    public void addStockScanListener(StockScanListener listener) {
        if (listener != null && !observadores.contains(listener)) {
            observadores.add(listener);
        }
    }

    public void removeStockScanListener(StockScanListener listener) {
        observadores.remove(listener);
    }

    public void notificarObservadores(String codigo, String mensaje) {

        if (observadores.isEmpty()) {
            return;
        }

        StockScanEvent evento =
            new StockScanEvent(this, codigo, mensaje);
        for (StockScanListener listener : observadores) {
            listener.alNotificarAccion(evento);
        }
    }
}
```

**Aspectos positivos ya implementados:**
- La clase hereda de **JPanel**.
- La lista de observadores está declarada como **private**.
- La referencia a la lista es **final**.
- No existe un método público que entregue directamente la lista interna.
- Se impide registrar dos veces un mismo observador.
- Se comprueba que el observador no sea nulo al registrarlo.

Estas características ya cumplen buena parte del principio de encapsulación [6], [7].

##### 1.2.1.3. Posibles mejoras futuras

He identificado cuatro cuestiones:
1. Dimensiones:
   - **Situación actual:** No existe validación propia.
   - **Propuesta de mejora:** Validar valores negativos.

2. Notificaciones:
   - **Situación actual:** Admiten código y mensaje nulos o vacíos.
   - **Propuesta de mejora:** Establecer restricciones explícitas.

3. Lista de observadores:
   - **Situación actual:** **private final ArrayList**.
   - **Propuesta de mejora:** Mantener acceso privado y mejorar su gestión concurrente.
   - **Justificación:**
  La lista de observadores debe seguir siendo privada, para que otras clases no puedan modificarla directamente, y su gestión debe evitar problemas cuando varios hilos la modifican o consultan al mismo tiempo.

Se realizaría de la siguiente manera:
- Declarando la colección como privada:

```java
private final CopyOnWriteArrayList<StockScanListener> observadores =
    new CopyOnWriteArrayList<>();
```

- Controlando el acceso mediante métodos:

```java
public void addStockScanListener(StockScanListener listener) {
    Objects.requireNonNull(listener);
    observadores.addIfAbsent(listener);
}

public void removeStockScanListener(StockScanListener listener) {
    Objects.requireNonNull(listener);
    observadores.remove(listener);
}
```

 - **CopyOnWriteArrayList**
Es una lista diseñada para situaciones en las que se consulta o recorre frecuentemente una colección y se modifica con menos frecuencia.
En este caso:
    - Permite recorrer los observadores mientras se registran o eliminan otros.
    - Evita errores de modificación concurrente durante la iteración.
    - No necesita que bloquees manualmente la lista con synchronized.

Con la opción anterior cada modificación crea una copia interna del array, por lo que no es la mejor opción si se añaden y eliminan observadores constantemente.

  - **Objects.requireNonNull()**
Sirve para comprobar que un parámetro no sea **null**.
Si recibe **null**, lanza inmediatamente una **NullPointerException**. Así evitas almacenar referencias nulas y errores posteriores al notificar.

  - **addIfAbsent()**
Es un método propio de **CopyOnWriteArrayList**.
Añade el observador solamente si todavía no existe en la lista. Evita registrar dos veces el mismo observador, según el criterio de igualdad de **equals()**.

4. Iteración de observadores:
   - **Situación actual:** Se recorre la lista original.
   - **Propuesta de mejora:** Evitar modificaciones concurrentes durante las notificaciones.

**Importante:** declarar una colección como **final** impide sustituir su referencia, pero no impide modificar su contenido. Por eso necesitamos controlar las operaciones que permiten añadir y eliminar observadores [6], [1].

#### 1.2.2. Propiedades y atributos que debe gestionar el componente

No vamos a crear atributos privados para todas las propiedades visuales. Primero debemos distinguir entre las propiedades heredadas de Swing y el estado propio de nuestro componente.

##### 1.2.2.1. Propiedades visuales heredadas

**ComponenteStockScan** extiende de **JPanel**, por lo que ya dispone de mecanismos para gestionar estas propiedades [8], [9].

| Propiedad | Tipo | Gestión | Restricción |
|---|---|---|---|
| Texto | **String** | No corresponde a **JPanel** | Se gestiona mediante etiquetas y botones. |
| Fondo | **Color** | **setBackground()** | Se permite **null**. |
| Primer plano | **Color** | **setForeground()** | Se permite **null**. |
| Borde | **Border** | **setBorder()** | Se permite **null** para retirar el borde. |
| Tamaño mínimo | **Dimension** | **setMinimumSize()** | Dimensiones no negativas o **null**. |
| Tamaño preferido | **Dimension** | **setPreferredSize()** | Dimensiones no negativas o **null**. |
| Estado habilitado | **boolean** | **setEnabled()** | **true** o **false**. |

No duplicaremos estas propiedades mediante nuevos atributos porque Swing ya las administra. Esto evita mantener dos fuentes de información para una misma característica [8], [9].

##### 1.2.2.2. Propiedades identificadas

La tabla resume las propiedades del componente, el atributo que las guarda y su tipo, según el código fuente:

| Propiedad | Atributo | Tipo | Dónde se guarda |
| --- | --- | --- | --- |
| Texto del botón | "texto" | **String** | **ComponenteAccionStockScan** |
| Color de fondo en reposo | "colorFondo" | **Color** | **ComponenteAccionStockScan** |
| Color del texto | "colorTexto" | **Color** | **ComponenteAccionStockScan** |
| Color del borde de foco | "colorBordeFoco" | **Color** | **ComponenteAccionStockScan** |
| Código de acción notificado | "codigoAccion" | **String** | **ComponenteAccionStockScan** |
| Tamaño mínimo, preferido y máximo | Heredados | **Dimension** | **BasePanel** / **Component** |
| Estado habilitado | Heredado | **boolean** | **Component** |
| Botón interno | "boton" | **JButton** (**final**) | **ComponenteAccionStockScan** |
| Observadores | "observadores" | **List\<StockScanListener\>** (**final**) | **ComponenteStockScan** |

Los tamaños y el estado habilitado no se duplican en atributos propios, porque Swing ya los guarda y los expone mediante métodos públicos [8].

#### 1.2.3. Declaración de los atributos como **private**

Todos los atributos de instancia del componente son privados, y solo las constantes son **public static final**, tal como recomienda Oracle [6]. Fragmento de:

```java
public static final String PROPIEDAD_TEXTO = "texto";
public static final String TEXTO_POR_DEFECTO = "Confirmar artículo";
public static final Color FONDO_POR_DEFECTO = new Color(30, 64, 175);

private final JButton boton = new JButton();
private String texto = TEXTO_POR_DEFECTO;
private Color colorFondo = FONDO_POR_DEFECTO;
private Color colorTexto = TEXTO_COLOR_POR_DEFECTO;
private Color colorBordeFoco = BORDE_FOCO_POR_DEFECTO;
private String codigoAccion = CODIGO_POR_DEFECTO;
```

#### 1.2.4. Restricciones de los valores

Las restricciones se comprueban antes de modificar el atributo, de manera que un valor rechazado no altera el estado anterior. Para los nulos se usa **Objects.requireNonNull**, pensado para validar parámetros de métodos y constructores [9].

| Propiedad | Valor rechazado | Excepción | Valor admitido |
| --- | --- | --- | --- |
| "texto" | **null** | **NullPointerException** | Texto no vacío; se eliminan los espacios laterales |
| "texto" | Vacío o en blanco | **IllegalArgumentException** | — |
| "colorFondo" | **null** | **NullPointerException** | Color opaco |
| "colorFondo" | Alfa distinto de 255 | **IllegalArgumentException** | — |
| "colorTexto", "colorBordeFoco" | **null** | **NullPointerException** | Cualquier color |
| "codigoAccion" | **null** / vacío | **NullPointerException** / **IllegalArgumentException** | Código no vacío |
| Tamaños | Ancho o alto negativo | **IllegalArgumentException** | **null** restaura el cálculo automático |

El fondo debe ser opaco porque el botón se pinta como opaco, y un alfa de 255 indica un color totalmente opaco [10]. La validación de tamaños y las copias defensivas de **Dimension** ya estaban implementadas en **BasePanel**.

#### 1.2.5. Revisión de accesos externos

La revisión buscó clases que modificaran directamente el estado de otra.

Se detectó un incumplimiento en **FeedbackStockScan**: guardaba su rutina de actualización y el estado de cada botón con **putClientProperty**, de modo que cualquier clase podía leerlos o sustituirlos con **getClientProperty** y **putClientProperty**, que son métodos públicos de **JComponent** [8].

La corrección guarda esas rutinas en un mapa privado del propio componente:

```java
private final Map<JButton, Runnable> actualizadores =
    new IdentityHashMap<>();
```

Se usa **IdentityHashMap** porque compara las claves por referencia y no por igualdad de objetos, que es lo adecuado para identificar cada botón concreto [11]. Además, **instalar** rechaza botones o códigos nulos o vacíos y lanza **IllegalStateException** si se instala dos veces el mismo botón, y **refrescar** ignora elementos nulos y botones no instalados.

#### 1.2.6. Revisión de encapsulación ejecutada

Comprobamos que todos los atributos de instancia son privados, y que los setters rechazan los valores no permitidos sin cambiar el valor anterior. Resultado obtenido:

| Comprobación | Resultado |
| --- | --- |
| Atributos privados en **BasePanel**, **ComponenteStockScan**, **ComponenteAccionStockScan**, **FeedbackStockScan**, **StockScanEvent** y **FeedbackController** | PASS |
| Valores iniciales válidos | PASS |
| Texto **null** o vacío rechazado y valor conservado | PASS |
| Fondo **null** o translúcido rechazado y valor conservado | PASS |
| Colores de texto y borde **null** rechazados | PASS |
| Solo los cambios válidos notifican **PropertyChange** | PASS |
| Tamaños negativos rechazados | PASS |
| El feedback no deja estado accesible en el botón | PASS |
| Instalar dos veces el mismo botón se rechaza | PASS |
| Botón o código no válidos rechazados | PASS |

#### 1.2.7. Riesgos pendientes

Quedan dos puntos que no se han cambiado por el momento:

1. **getComponent(0)** es un método público heredado de **Container**, por lo que una clase externa podría obtener el **JButton** interno [12]. Se recomienda no usarlo fuera de las pruebas.
2. **notificarObservadores** es público, por lo que otra clase podría disparar eventos falsos. Siguiendo la regla del acceso más restrictivo, debería pasar a **protected**, pero antes hay que adaptar **PruebaNotificacion** y **PruebaBasePanel**, que lo llaman directamente [6].

### 1.3. Constructor y valores por defecto

#### 1.3.1. Objetivo

Garantizar que **ComponenteAccionStockScan** pueda crearse mediante un constructor público sin argumentos y presente un estado visual válido desde su creación, sin necesitar una configuración adicional.

#### 1.3.2. Constructor sin argumentos

El componente dispone del constructor público **public ComponenteAccionStockScan()**.
Este constructor configura la distribución del componente, prepara el botón, registra sus escuchadores y aplica el estado visual inicial mediante **actualizarEstadoVisual()**.

Su utilización desde otra clase es sencilla:

```java
ComponenteAccionStockScan componente =
    new ComponenteAccionStockScan();
```

La organización del componente sigue las convenciones de JavaBeans, que permiten a las herramientas reconocer sus propiedades, métodos y eventos [13]. Estas convenciones exigen que un componente JavaBeans defina un constructor sin parámetros [15], requisito que cumple este constructor.

#### 1.3.3. Valores por defecto

La implementación establece los siguientes valores iniciales, definidos como constantes y atributos privados de la clase:

| Propiedad | Valor inicial |
| --- | --- |
| Texto del botón | **Confirmar artículo** ("TEXTO_POR_DEFECTO") |
| Código de acción notificado | **ART-01** ("CODIGO_POR_DEFECTO") |
| Estado visual | Reposo |
| Color de fondo en reposo | Azul: RGB **(30, 64, 175)** ("FONDO_POR_DEFECTO") |
| Colores de hover y pulsado | Derivados del fondo con **brighter()** y **darker().darker()** |
| Color del texto | Blanco ("TEXTO_COLOR_POR_DEFECTO") |
| Color del borde de foco | Ámbar: RGB **(245, 158, 11)** ("BORDE_FOCO_POR_DEFECTO") |
| Tamaño preferido del botón | **260 × 60** píxeles |
| Habilitado | Sí |
| Puede recibir el foco | Sí |
| Detección del puntero sobre el botón | Activada |
| Distribución del componente | **BorderLayout** |
| Posición del botón | **BorderLayout.CENTER** |
| Descripción accesible | **Confirma el artículo y notifica la acción.** |

El tamaño indicado corresponde al tamaño **preferido del botón**, no a un tamaño fijo de la ventana.

Ningún valor por defecto es nulo ni inválido: los atributos se inicializan en su declaración con las constantes anteriores, antes de que se ejecute el cuerpo del constructor, y cumplen las mismas restricciones que aplican los setters. Los colores derivados se obtienen con **brighter()** y **darker()**, que devuelven colores nuevos y conservan el alfa [10].

Oracle señala que la apariencia de Swing puede modificar determinadas propiedades iniciales [1]. Por ello, los valores necesarios para el diseño se establecen expresamente en el código.

#### 1.3.4. Constructores adicionales

Se ha añadido un constructor parametrizado porque, al disponer ya de **setTexto** y **setCodigoAccion**, permite reutilizar el componente para otras acciones del almacén en una sola línea:

```java
public ComponenteAccionStockScan(String texto, String codigoAccion) {
    this();
    setTexto(texto);
    setCodigoAccion(codigoAccion);
}
```

El constructor delega en el constructor sin argumentos mediante **this()**, que según Oracle es una invocación explícita a otro constructor de la misma clase y debe ser la primera instrucción. Así toda la configuración visual vive en un único constructor y no se duplica [16].

Los argumentos no se asignan directamente, sino a través de los setters, que validan los nulos con **Objects.requireNonNull** y rechazan los textos vacíos [7]. Esta comprobación temprana de los parámetros sigue la recomendación de validar los argumentos al inicio de métodos y constructores.

Ejemplo de uso:

```java
ComponenteAccionStockScan entrada =
    new ComponenteAccionStockScan("Registrar entrada", "ENT-01");
```

#### 1.3.5. Comprobación

##### 1.3.5.1. Prueba automática

La clase **PruebaConstructores** instancia el componente con ambos constructores y comprueba sus valores en el hilo de eventos de Swing. Resultado obtenido al ejecutarla:

| Prueba | Resultado |
| --- | --- |
| Constructor sin argumentos sin excepciones | PASS |
| Texto y código por defecto | PASS |
| Colores por defecto no nulos y coherentes | PASS |
| Tamaño preferido calculado desde el botón: **260x60** | PASS |
| Componente habilitado al crearse | PASS |
| Constructor parametrizado aplica y limpia los valores | PASS |
| Constructor parametrizado conserva el resto de valores | PASS |
| Constructor parametrizado rechaza valores no válidos | PASS |

##### 1.3.5.2. Ventana de prueba

Para la comprobación visual se usa la clase **PruebaConstructorStockScan**, incluida en el paquete de pruebas:

```java
package com.stockscan.view.events;

import com.stockscan.view.components.ComponenteAccionStockScan;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * Prueba visual del constructor sin argumentos.
 */
public class PruebaConstructorStockScan {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ComponenteAccionStockScan componente =
                new ComponenteAccionStockScan();

            JFrame ventana = new JFrame("Prueba del constructor");
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.add(componente);
            ventana.pack();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);

            System.out.println(
                "Componente creado y ventana abierta sin excepciones."
            );
        });
    }
}
```

La ventana se crea mediante **SwingUtilities.invokeLater**, siguiendo la recomendación de Oracle de inicializar la interfaz en el hilo de eventos de Swing [14].

##### 1.3.5.3. Pasos de comprobación

Procedimiento para repetir la prueba en NetBeans:

1. Abrir **PruebaConstructorStockScan** en "test/com/stockscan/view/events".
2. Ejecutar la clase con **Run File**.
3. Comprobar que la ventana aparece sin excepciones.
4. Revisar que el botón muestra el texto completo y los colores previstos.
5. Comprobar que está habilitado.
6. Guardar una captura de la ventana y de la salida de ejecución.

Si el botón recibe el foco al abrirse, puede aparecer el borde ámbar de foco configurado; esto no significa que esté pulsado.

##### 1.3.5.4. Registro de pruebas

Resultados de la ejecución de **PruebaConstructores**, de
**PruebaConstructorStockScan** y de un renderizado del componente en sus cuatro estados:

| Prueba | Resultado esperado | Estado |
| --- | --- | --- |
| Crear el componente sin argumentos | No se producen excepciones | Superada |
| Revisar el texto inicial | Aparece **Confirmar artículo** completo | Superada |
| Revisar la apariencia inicial | Fondo azul y texto blanco | Superada |
| Revisar las dimensiones | El botón se muestra con su tamaño preferido **260 × 60** | Superada |
| Revisar el estado inicial | Componente habilitado y sin pulsación activa | Superada |
| Constructor parametrizado | Aplica texto y código y rechaza valores no válidos | Superada |

La salida de consola confirma la creación de la ventana, pero la apariencia debe documentarse también con la captura tomada en NetBeans.

#### 1.3.6. Documentación de los constructores

Los dos constructores tienen comentario Javadoc con su descripción, sus parámetros y las excepciones que pueden lanzar, como indica la guía de Oracle [17]. Comentario del constructor sin argumentos:

```java
/**
 * Crea el componente con su configuración visual inicial.
 *
 * El botón muestra «Confirmar artículo», tiene un tamaño preferido
 * de 260 por 60 píxeles y comienza habilitado en estado de reposo,
 * con fondo azul y texto blanco. Al activarse notifica el código
 * «ART-01».
 *
 * Configura los escuchadores necesarios y aplica la apariencia inicial.
 */
```

Comentario del constructor parametrizado:

```java
/**
 * Crea el componente con un texto y un código de acción propios.
 *
 * Delega en el constructor sin argumentos y después aplica
 * los valores mediante los setters, que los validan.
 *
 * @param texto texto visible del botón
 * @param codigoAccion código que se notificará al activarlo
 * @throws NullPointerException si algún argumento es null
 * @throws IllegalArgumentException si algún argumento está vacío
 */
```

### 1.4. Métodos accesores (getters y setters)

#### 1.4.1. Objetivo

Permitir que otras clases consulten y modifiquen las propiedades del componente mediante métodos públicos, manteniendo sus datos protegidos y actualizando la interfaz cuando sea necesario.

Los métodos siguen las convenciones de JavaBeans: **getPropiedad()** para consultar, **setPropiedad(valor)** para modificar e **isPropiedad()** para consultar propiedades booleanas [18].

#### 1.4.2. Métodos disponibles

##### 1.4.2.1. Heredados de **BasePanel** y de Swing

**BasePanel** dispone de los siguientes accesores para las dimensiones, que se heredan en todos los componentes que lo extienden:

| Propiedad | Getter | Setter |
| --- | --- | --- |
| Tamaño mínimo | **getMinimumSize()** | **setMinimumSize(Dimension size)** |
| Tamaño preferido | **getPreferredSize()** | **setPreferredSize(Dimension size)** |
| Tamaño máximo | **getMaximumSize()** | **setMaximumSize(Dimension size)** |

**ComponenteAccionStockScan** permite además consultar su estado mediante **isEnabled()** y modificarlo mediante **setEnabled(boolean)**, que también actualiza el botón interno y su apariencia.

##### 1.4.2.2. Propiedades nuevas del componente

Las propiedades que estaban pendientes se han implementado en
**ComponenteAccionStockScan**:

| Propiedad | Getter | Setter | Valor por defecto |
| --- | --- | --- | --- |
| Texto del botón | **getTexto()** | **setTexto(String texto)** | **Confirmar artículo** |
| Color de fondo en reposo | **getColorFondo()** | **setColorFondo(Color color)** | RGB **(30, 64, 175)** |
| Color del texto | **getColorTexto()** | **setColorTexto(Color color)** | Blanco |
| Color del borde de foco | **getColorBordeFoco()** | **setColorBordeFoco(Color color)** | RGB **(245, 158, 11)** |
| Código de acción | **getCodigoAccion()** | **setCodigoAccion(String codigo)** | **ART-01** |

El nombre propuesto inicialmente para el color de reposo era **getColorReposo()**. Se ha usado **getColorFondo()** porque es el nombre que ya comprobaba la prueba de encapsulación del equipo, **PruebaEncapsulacion**. Los colores de **hover** y pulsado no tienen setter porque se derivan del color de fondo [10].

No se han creado getters que devuelvan el **JButton** interno, para que ninguna clase externa pueda cambiar su estado sin pasar por las validaciones.

#### 1.4.3. Validación de los parámetros

Cada setter valida el valor antes de modificar el atributo; si lo rechaza, lanza la excepción y el valor anterior se conserva. Los nulos se comprueban con **Objects.requireNonNull**, que lanza **NullPointerException** con un mensaje propio [7].

| Setter | Tratamiento de **null** | Otros valores rechazados |
| --- | --- | --- |
| **setTexto** | **NullPointerException** | Vacío o en blanco: **IllegalArgumentException**. Se eliminan espacios laterales |
| **setColorFondo** | **NullPointerException** | Alfa distinto de 255: **IllegalArgumentException** |
| **setColorTexto** | **NullPointerException** | — |
| **setColorBordeFoco** | **NullPointerException** | — |
| **setCodigoAccion** | **NullPointerException** | Vacío o en blanco: **IllegalArgumentException** |
| **setMinimumSize**, **setPreferredSize**, **setMaximumSize** | Se admite: restaura el cálculo automático | Ancho o alto negativo: **IllegalArgumentException** |

El fondo se exige opaco porque el botón se pinta como opaco, y en **Color** un alfa de 255 significa opacidad total [10]. En los tamaños se admite **null** porque Swing lo usa para volver al tamaño calculado por el componente [1].

Fragmento de un setter con validación, de:

```java
public void setColorFondo(Color colorFondo) {
    Objects.requireNonNull(
        colorFondo, "El color de fondo no puede ser null."
    );

    if (colorFondo.getAlpha() != 255) {
        throw new IllegalArgumentException(
            "El color de fondo debe ser opaco (alfa 255)."
        );
    }

    Color anterior = this.colorFondo;
    this.colorFondo = colorFondo;

    actualizarEstadoVisual();
    firePropertyChange(PROPIEDAD_COLOR_FONDO, anterior, colorFondo);
}
```

#### 1.4.4. Uso de **repaint()** y **revalidate()**

Oracle indica que, si se cambian las indicaciones de tamaño de un componente visible, hay que invocar **revalidate()** para que se vuelva a distribuir su jerarquía y después **repaint()** [19]. Además, en Swing **repaint()** no pinta en el momento, sino que registra una petición asíncrona que el **RepaintManager** atiende después en el hilo de eventos [20].

Con ese criterio, cada setter actualiza la interfaz así:

| Setter | Cambio | Llamadas |
| --- | --- | --- |
| **setTexto** | Representación y posible tamaño | **revalidate()** + **repaint()** |
| **setColorFondo**, **setColorTexto**, **setColorBordeFoco** | Solo representación | **actualizarEstadoVisual()**, que termina en **repaint()** |
| **setCodigoAccion** | Ninguno visual | Ninguna |
| **setMinimumSize**, **setPreferredSize**, **setMaximumSize** | Tamaño y distribución | **revalidate()** + **repaint()** |
| **setEnabled** | Representación | **actualizarEstadoVisual()** |

Los setters de tamaño de **BasePanel** no llamaban a **revalidate()**, por lo que se ha añadido el método privado **actualizarDistribucion()**, que invoca **revalidate()** y **repaint()** tras cada cambio válido [19].

#### 1.4.5. Propiedades enlazadas

Las cinco propiedades nuevas son propiedades enlazadas: el setter guarda el valor anterior, actualiza el atributo y notifica un **PropertyChangeEvent** a los oyentes registrados [18]. Para ello se usa **firePropertyChange**, que **JComponent** ofrece para notificar cambios de propiedades enlazadas [1].

Los nombres de las propiedades se publican como constantes para evitar errores de escritura en los oyentes:

```java
public static final String PROPIEDAD_TEXTO = "texto";
public static final String PROPIEDAD_COLOR_FONDO = "colorFondo";
public static final String PROPIEDAD_COLOR_TEXTO = "colorTexto";
public static final String PROPIEDAD_COLOR_BORDE_FOCO = "colorBordeFoco";
public static final String PROPIEDAD_CODIGO_ACCION = "codigoAccion";
```

Ejemplo de uso desde otra clase [18]:

```java
componente.addPropertyChangeListener(
    ComponenteAccionStockScan.PROPIEDAD_TEXTO,
    evento -> System.out.println("Nuevo texto: " + evento.getNewValue())
);
```

#### 1.4.6. Comprobación

La clase **PruebaAccesores** comprueba que los getters devuelven el valor actualizado, que se rechazan los valores no válidos y que se solicitan la revalidación y el repintado. La revalidación se detecta porque el componente deja de ser válido (**isValid()** devuelve **false**), y el repintado mediante un **RepaintManager** de prueba que registra las regiones marcadas por **repaint()** [20], [1].

Resultado obtenido:

| Prueba | Resultado |
| --- | --- |
| **setTexto**: getter actualizado, **revalidate()** y **repaint()** | PASS |
| **setColorFondo**: getter actualizado y **repaint()** | PASS |
| **setColorTexto** y **setColorBordeFoco**: getters y **repaint()** | PASS |
| **setCodigoAccion**: getter actualizado y sin espacios | PASS |
| **setCodigoAccion**: **null** o vacío rechazado y valor conservado | PASS |
| **setPreferredSize**: getter actualizado, **revalidate()** y **repaint()** | PASS |
| **setMinimumSize**: getter actualizado y **revalidate()** | PASS |
| **setMaximumSize**: getter actualizado y **revalidate()** | PASS |
| **setEnabled(false)** / **setEnabled(true)**: **isEnabled()** coherente | PASS |

Las validaciones de texto y colores también las comprueba **PruebaEncapsulacion**, y las de tamaños **PruebaBasePanel**, ambas siguen pasando tras los cambios.

#### 1.4.7. Revisión de la API

Revisión de consistencia, nombres y visibilidad [18]:

- Todos los getters y setters son **public**. Los métodos auxiliares (**actualizarEstadoVisual**, **calcularEstadoVisual**, **validarTexto**, **actualizarDistribucion**) son **private**.
- Los nombres siguen el patrón **get**/**set**/**is** de JavaBeans, con el mismo nombre de propiedad en getter, setter y constante [18].
- Los textos se validan con la misma regla en **setTexto** y **setCodigoAccion**, mediante un único método **validarTexto**.
- Los getters de **Dimension** devuelven copias, y los de **Color** y **String** devuelven el objeto, porque ambos tipos no ofrecen métodos para modificarlo [10].

#### 1.4.8. Documentación de los accesores

Cada getter y setter tiene Javadoc con su descripción, `@param`, `@return` y `@throws` cuando corresponde, según la guía de Oracle [17]. La generación de Javadoc con **-Xdoclint** no muestra avisos en estas clases. Ejemplo:

```java
/**
 * Cambia el texto visible del botón.
 *
 * Elimina los espacios laterales. Si el valor no es válido,
 * lanza la excepción y conserva el texto anterior.
 * Notifica la propiedad {@link #PROPIEDAD_TEXTO}.
 *
 * @param texto nuevo texto
 * @throws NullPointerException si texto es null
 * @throws IllegalArgumentException si texto está vacío o en blanco
 */
public void setTexto(String texto) {
```

## 2. Implementación del modelo de eventos y respuestas interactivas

### 2.1. Captura de eventos de bajo nivel

#### 2.1.1. Acciones del Usuario y Modelo de Interacción

Para gestionar la respuesta de la interfaz gráfica ante las acciones del usuario en Java Swing, se ha seguido el modelo de delegación de eventos mediante clases adaptadoras (*Event Adapters*) [21], [22]. Las interacciones definidas para toda la aplicación se dividen en tres bloques:
- **Ratón (MouseAdapter):** Detección de entrada (**mouseEntered**) y salida (**mouseExited**) del cursor para ofrecer retroalimentación visual inmediata (cambio a **Cursor.HAND_CURSOR** y resaltado de bordes/fondos), así como control de pulsación (**mousePressed**) y liberación (**mouseReleased**) del botón izquierdo (**MouseEvent.BUTTON1**) [23].
- **Foco (FocusAdapter):** Gestión de entrada (**focusGained**) y pérdida (**focusLost**) de foco en campos de texto para limpiar y restaurar los textos de ayuda (**placeholders**), además de remarcar con un borde azul (**RGB(27, 79, 114)**) el control activo durante la navegación con la tecla *Tab* [24].
- **Teclado (KeyAdapter):** Captura de liberación de teclas (**keyReleased**) para realizar filtrados en tiempo real en los buscadores y permitir la activación de botones y estanterías mediante **KeyEvent.VK_ENTER** o **KeyEvent.VK_SPACE** [25].

#### 2.1.2. Registro de Listeners y Prevención de Eventos Duplicados

Siguiendo las reglas generales de escritura de escuchadores en Swing para desacoplar la lógica de eventos del código autogenerado [22], se ha centralizado el registro de todos los listeners dentro de un método privado **configurarEventos()** invocado desde el constructor de cada vista justo después de **initComponents()**.

Para evitar disparos accidentales o duplicados durante la interacción del usuario, se han implementado dos mecanismos de control:
1. **Validación de clic completo (clicIniciado):** En los botones críticos se controla que la pulsación (**mousePressed**) se inicie dentro del botón habilitado (**btn.isEnabled()**).
Si el usuario desplaza el puntero fuera del área antes de soltar (**mouseExited**), la variable vuelve a "false", evitando que **mouseReleased** ejecute la acción por error [23].
2. **Control de repetición de teclado (teclaPulsada):** En controles como los filtros de **EquipoPanel**, se registra la pulsación inicial en **keyPressed** para que la acción se dispare una única vez al soltar la tecla en **keyReleased**, evitando ejecuciones repetidas si se deja pulsada la tecla *Enter* o *Espacio* [25].

#### 2.1.3. Catálogo de Eventos de Bajo Nivel Implementados por Vista

| Vista | Componentes Configurados | Adaptadores Registrados | Comportamiento

Implementado |

| :--- | :--- | :--- | :--- |
| **Login** | **txtEmail**, **jPasswordField1**, **btnIniciarSesion** | **FocusAdapter** [24], **KeyAdapter** [25], **MouseAdapter** [23] | Borrado de credenciales de ejemplo al ganar foco, envío directo pulsando *Enter* en la contraseña y cambio de estado visual en el botón de acceso. |
| **DashboardPanel** | **btnFiltroDia/Semana/Mes**, Tarjetas KPI, **lstActividades** | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Cambio de métricas por periodo (ratón/teclado), resaltado de bordes al pasar sobre las tarjetas y lectura de actividad seleccionada en la lista. |
| **MapaAlmacenPanel** | Matriz de 25 **JButton** ("estanterias") | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Selección de ubicación por clic izquierdo o teclas *Enter*/*Espacio*, cambio de cursor y resaltado de borde al navegar con *Tab*. |
| **StockPanel** | **txtBuscarStock**, **btnAñadirProducto**, Botones de categoría | **FocusAdapter** [24], **KeyAdapter** [25], **MouseAdapter** [23] | Búsqueda dinámica en el subtítulo al escribir y conmutación visual de los filtros de categoría. |
| **EtiquetasPanel** | **txtBuscarEtiquetas**, 7 **JCheckBox**, **btnExportarPDF**, **btnQuitar1/2** | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Filtrado instantáneo de productos visibles y actualización en tiempo real de las tarjetas de vista previa. |
| **TrazabilidadPanel** | **txtBuscarTraza**, **txtFechaFilter**, **tblTrazabilidad**, **JComboBox** | **FocusAdapter** [24], **KeyAdapter** [25], **MouseAdapter** [23] | Gestión de **placeholders** por lote/fecha y detección de fila seleccionada en la tabla mediante **mouseReleased**. |
| **AlertasPanel** | **btnPedidoMultiple**, **btnPedido1/3**, **btnNotif1/3** | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Tramitación de pedidos por clic o teclado y deshabilitación automática del botón tras confirmar la acción. |
| **EquipoPanel** | **txtBuscarEquipo**, Filtros de estado, **btnAñadirMiembro**, **tblOperarios** | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Filtrado mediante **TableRowSorter** insensible a mayúsculas/minúsculas y detección de clic sobre la columna de edición. |
| **AgendaPanel** | **btnAnterior**, **btnHoy**, **btnSiguiente** | **MouseAdapter** [23], **FocusAdapter** [24], **KeyAdapter** [25] | Navegación entre semanas mediante contador (**desplazamientoSemanas**) usando ratón o teclado. |
| **ChatPanel** | **txtInputMensaje**, **txtBuscarChat**, **btnEnviar** | **FocusAdapter** [24], **KeyAdapter** [25], **MouseAdapter** [23] | Limpieza automática de **placeholders** y envío/vaciado del campo de mensaje con *Enter* o clic en enviar. |

#### 2.1.4. Pruebas Aisladas y Revisión de Interferencias

- **Ejecución en entorno aislado:** Se ha probado cada vista de forma independiente ejecutando su propio método **main** sobre el hilo de despacho de eventos de Swing (**EventQueue.invokeLater**), verificando que las transiciones de cursor, bordes de foco y pulsaciones responden sin retrasos [21], [22].
- **Verificación de no interferencia:** Se ha comprobado que los listeners añadidos a los campos de texto, tablas y botones actúan únicamente sobre su propio componente sin consumir ni bloquear el funcionamiento de los botones de navegación del menú lateral (**panelSidebar**) ni el diseño generado por NetBeans [22].

<div align="center">
  <img width="536" height="496" alt="mouse_listener_txtx_btn_login" src="https://github.com/user-attachments/assets/45cd6804-2645-476a-82f9-995985e1de0b" />
</div>
<div align="center">
   <img width="720" height="480" alt="mouse_listener_siguiendo_mouse" src="https://github.com/user-attachments/assets/fb100047-b906-4e01-8dc9-f91bd5eb28ef" />
</div>

### 2.2. Mecanismo de notificación propio

#### 2.2.1. Análisis, búsqueda y elección del contrato de notificación

Para resolver esta tarea, se analizó en primer lugar cómo gestiona Java Swing la comunicación entre componentes visuales y controladores externos. Se revisaron en la documentación oficial de Java SE y en los tutoriales de Swing las opciones disponibles:
reutilizar el estándar **ActionListener** / **ActionEvent** o crear un modelo de eventos personalizado basado en **EventObject** y **EventListener** [26], [21].
En consonancia con los requisitos curriculares de diseño de interfaces modulares y componentes reutilizables [29], y tras analizar ambas alternativas, se eligió **implementar un evento y una interfaz de escucha propios** (**StockScanEvent** y **StockScanListener**) por las siguientes razones:

- **Limitación de ActionListener:** Al revisar la clase **ActionEvent**, se comprobó que solo permite enviar un texto (**String**) y un entero de modificadores. En **StockScan SGA** se requiere que el controlador reciba datos concretos y tipados cuando ocurre una acción (el código del artículo/ubicación y el mensaje o estado actualizado) sin tener que consultar métodos externos [21].
- **Estándar JavaBeans y desacoplamiento:** Siguiendo la especificación oficial de JavaBeans [28], se decidió respetar las firmas estándar **addStockScanListener** y **removeStockScanListener**. De este modo, cualquier ventana o panel puede suscribirse al componente de forma limpia y totalmente desacoplada [28], [21].

#### 2.2.2. Implementación de la API de eventos y gestión de observadores

Tras definir el contrato, se implementaron las tres piezas principales de la arquitectura en el proyecto:

- **StockScanEvent ("com.stockscan.view.events"):** Clase que hereda de "java.util.EventObject" [26] y encapsula de forma inmutable el componente origen (**source**), el **codigo** del elemento y el **mensaje** de la acción.
- **StockScanListener ("com.stockscan.view.events"):** Interfaz funcional que extiende "java.util.EventListener" [26] con el método **alNotificarAccion(StockScanEvent evento)** para que los controladores externos reciban las notificaciones [21].
- **ComponenteStockScan ("com.stockscan.view.components"):** Componente visual con una colección privada (**ArrayList\<StockScanListener\>**) [27], los métodos públicos **addStockScanListener** (con control de nulos y duplicados) y **removeStockScanListener** [28], y el método **notificarObservadores** protegido frente a listas vacías.

#### 2.2.3. Plan de Pruebas y Verificación del Mecanismo de Notificación

Para comprobar el ciclo de vida completo del patrón observador (**StockScanEvent** + **StockScanListener** + **ComponenteStockScan**), se creó la clase de prueba "PruebaNotificacion.java" dentro de **Test Packages** ("com.stockscan.view.events") y se ejecutó una batería de cuatro casos de prueba funcionales:

##### 2.2.3.1. Matriz de Casos de Prueba Ejecutados

| ID Prueba | Escenario Evaluado | Acción Ejecutada | Resultado Esperado | Resultado

Obtenido | Estado |

| :--- | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | **Ausencia de observadores** | Invocar **notificarObservadores("ART-01", "Sin oyentes")** con la lista vacía. | Ejecución segura sin lanzar **NullPointerException** ni bloquear el hilo. | Finaliza limpiamente sin errores ni salidas. | **PASS** |
| **TC-02** | **Registro y control de duplicados** | Registrar dos veces seguidas la misma instancia con **addStockScanListener(observador)**. | El componente detecta la referencia repetida y almacena una única suscripción. | Solo se registra una vez en **ArrayList** [27]. | **PASS** |
| **TC-03** | **Disparo y entrega de datos tipados** | Invocar **notificarObservadores("ART-02", "Stock actualizado")** con un oyente activo. | El oyente recibe exactamente 1 notificación con **source**, **codigo** y **mensaje** íntegros. | Imprime 1 sola vez: **Código: ART-02 \| Mensaje: Stock actualizado**. | **PASS** |
| **TC-04** | **Desregistro de observadores** | Ejecutar **removeStockScanListener(observador)** y disparar **notificarObservadores("ART-03", ...)**. | El observador deja de recibir eventos tras darse de baja. | Ninguna notificación emitida tras la baja. | **PASS** |

##### 2.2.3.2. Evidencias Visuales

###### 2.2.3.2.1. Estructura de paquetes y pruebas (**Source Packages** y **Test Packages**)

Organización modular de los paquetes de eventos, componentes y pruebas unitarias dentro del proyecto **StockScanE**:

<div align="center">

  <img width="230" height="57" alt="Paquete com.stockscan.view.events" src="https://github.com/user-attachments/assets/779a6e1b-1064-4eba-971f-7bd3ef80bad1" />

  <img width="252" height="40" alt="Paquete com.stockscan.view.components" src="https://github.com/user-attachments/assets/173da16b-b075-48c6-8010-c6ff4b77cd01" />

   <img width="280" height="56" alt="Paquete de pruebas com.stockscan.view.events" src="https://github.com/user-attachments/assets/876aafed-5af7-4938-9336-45d2589b7cef" />

</div>

---

###### 2.2.3.2.2. Ejecución de **PruebaNotificacion** en Apache NetBeans (JDK 21)

Resultado de la ejecución de los casos de prueba (**TC-01** a **TC-04**), verificando que no hay excepciones sin oyentes, que se evitan avisos duplicados y que la baja de suscriptores funciona correctamente (**BUILD SUCCESSFUL**):
<div align="center">
  <img width="686" height="76" alt="Salida en consola de PruebaNotificacion" src="https://github.com/user-attachments/assets/21ba28a6-13cc-40a7-b5ad-7a0ee03290c4" />
</div>

---

###### 2.2.3.2.3. Documentación oficial de la API generada con Javadoc

Se han documentado las clases y métodos de la API (**StockScanEvent**, **StockScanListener** y **ComponenteStockScan**) utilizando comentarios estándar de Javadoc y generado la especificación HTML desde Apache NetBeans para que cualquier miembro del equipo pueda consultar cómo utilizar el componente sin necesidad de abrir el código fuente:

- **Bloques descriptivos (**/\*\* ... \*/**):** Explican la función general de cada clase, interfaz y método público para que NetBeans los compile en formato web HTML.
- **Etiqueta `@param`:** Detalla qué dato de entrada recibe cada constructor o método (por ejemplo, **source**, **codigo**, **mensaje** y **listener**).
- **Etiqueta `@return`:** Especifica qué valor y tipo de dato devuelven los métodos de consulta (**getCodigo()** y **getMensaje()**).

**Vista general de paquetes y resumen del paquete "com.stockscan.view.events":**

<div align="center">

  <img width="960" height="378" alt="Javadoc - Resumen de paquetes" src="https://github.com/user-attachments/assets/4b5f8c65-d788-4b28-9964-4fa1823e974d" />

   <img width="962" height="311" alt="Javadoc - Paquete com.stockscan.view.events" src="https://github.com/user-attachments/assets/3a5dbd02-af71-47d9-8fa8-42d2537fdb60" />

</div>

**Detalle de métodos documentados en StockScanEvent y ComponenteStockScan:**

<div align="center">

   <img width="905" height="366" alt="Javadoc - Métodos de StockScanEvent" src="https://github.com/user-attachments/assets/4eb0f8d1-964f-4332-923c-72a6fe94121a" />
  <img width="907" height="284" alt="Javadoc - Métodos de ComponenteStockScan" src="https://github.com/user-attachments/assets/6cd275f0-52bd-4d17-926f-5a5864e83a38" />

</div>

### 2.3. Disparo y feedback visual

#### 2.3.1. Objetivo

Conectar la interacción del usuario con los cambios visuales del componente y la emisión de una notificación pública mediante **StockScanEvent**.

El componente debe distinguir los estados de reposo, hover, pulsado y deshabilitado, además de mostrar cuándo tiene el foco del teclado.

#### 2.3.2. Investigación y elección del mecanismo

Se consultó la documentación oficial de **ButtonModel** para identificar los estados que Swing mantiene durante la interacción.

Se decidió utilizar el modelo del **JButton** para consultar si está habilitado, bajo el puntero, pulsado y preparado para activar la acción. Esto permite coordinar la apariencia con el comportamiento del propio botón [30].

Los métodos utilizados son:

- **isEnabled()**: indica si el botón está habilitado.
- **isRollover()**: indica si el puntero está sobre el botón.
- **isPressed()**: indica si el botón está pulsado.
- **isArmed()**: indica si está preparado para completar la activación al soltarlo.

#### 2.3.3. Implementación de los estados visuales

Se implementó la clase **FeedbackStockScan**, que hereda de
**ComponenteStockScan** y aplica el comportamiento a los botones existentes.

| Estado | Condición | Respuesta visual |
|---|---|---|
| Reposo | Botón habilitado, sin hover ni pulsación activa. | Color base del botón. |
| Hover | Puntero sobre el botón habilitado, sin pulsación activa. | Color base oscurecido. |
| Pulsado | Modelo pulsado y preparado para activar. | Color más oscuro. |
| Deshabilitado | Botón deshabilitado. | Fondo gris y texto oscuro. |

El estado deshabilitado tiene prioridad sobre los demás. Después se comprueba la pulsación, el hover y, finalmente, el reposo.

Un **ChangeListener** detecta los cambios del modelo. El estado calculado se conserva en la propiedad de cliente **stockscan.estado**.

Después se actualizan los colores y se llama a **repaint()** para solicitar el repintado. Las propiedades de cliente permiten asociar información al componente, mientras que **repaint()** solicita actualizar su representación visual [1].

En los filtros se diferencia entre la selección persistente y el estado de interacción. Al salir el ratón, el filtro seleccionado conserva su color base activo.

#### 2.3.4. Disparo de la notificación

La notificación se emite desde el **ActionListener** añadido al botón cuando se completa una activación válida.

- Entrar o salir con el ratón no emite notificaciones.
- Mantener pulsado no emite todavía la notificación.
- Pulsar dentro y soltar dentro completa la activación.
- Pulsar dentro y soltar fuera cancela la activación.
- Un botón deshabilitado no permite la activación normal del usuario.

Este comportamiento se apoya en la distinción entre los estados **pressed** y **armed** documentados en **ButtonModel** [30].

Se reutiliza el contrato de eventos existente:

| Elemento | Responsabilidad |
|---|---|
| **StockScanEvent** | Transportar el origen, el código y el mensaje. |
| **StockScanListener** | Definir el método **alNotificarAccion()**. |
| **ComponenteStockScan** | Registrar, eliminar y notificar a los observadores. |

**StockScanEvent** hereda de **EventObject**, que proporciona la referencia al objeto origen del evento [31].

En esta integración, el origen es la instancia de **FeedbackStockScan**. El código identifica la pantalla y el botón activado.

#### 2.3.5. Recepción mediante un controlador externo

Se añadió **FeedbackController**, que implementa **StockScanListener**.

Cada pantalla registra un controlador mediante **addStockScanListener()**. Cuando recibe una notificación, el controlador incrementa su contador y muestra el código y el mensaje en la salida de NetBeans.

Ejemplo del formato de salida:

```text
StockScan #1 | StockPanel.btnFiltroTodos | Activación: Todos
```

Esta salida permite observar que la notificación llega a un receptor externo al componente.
El contador pertenece a cada instancia del controlador. El mensaje confirma la recepción del evento, pero no confirma un guardado en la base de datos ni la ejecución correcta de una operación de negocio.

#### 2.3.6. Integración en las pantallas

El comportamiento se incorpora desde **configurarEfectosVisuales()**, después de **initComponents()**, en las siguientes pantallas:

- **AgendaPanel**.
- **AlertasPanel**.
- **ChatPanel**.
- **DashboardPanel**.
- **EquipoPanel**.
- **EtiquetasPanel**.
- **Login**.
- **MapaAlmacenPanel**.
- **StockPanel**.
- **TrazabilidadPanel**.

Se conservan los botones existentes, sus nombres, iconos, posiciones y manejadores **ActionPerformed**.

Se sustituye la gestión anterior de los efectos visuales para evitar que dos mecanismos diferentes cambien simultáneamente los colores.

La instalación del feedback se realiza una sola vez por botón, evitando registrar varias veces la misma notificación.

En Agenda, Dashboard, Equipo y Stock, la selección visual se actualiza mediante **ActionListener**. De este modo, también responde a la activación por teclado y no cambia al cancelar una pulsación fuera del botón.

Después de modificar el filtro activo, **feedback.refrescar(...)** actualiza la apariencia del grupo.

#### 2.3.7. Accesibilidad básica

Se incorpora un borde naranja para identificar el botón que tiene el foco. Un **FocusListener** actualiza la apariencia cuando el botón gana o pierde ese foco.

Los botones se configuran para poder recibir el foco y se conservan sus asociaciones de teclado. Swing utiliza **InputMap** y **ActionMap** para gestionar estas asociaciones [32].
La navegación mediante **Tab** y la activación mediante Espacio deben comprobarse en la aplicación en ejecución y con su apariencia gráfica configurada.

Este tutorial está redactado para JDK 8 y se utiliza como explicación del mecanismo de teclado de Swing.

#### 2.3.8. Evidencias visuales de la implementación

##### 2.3.8.1. Prueba independiente del componente

La grabación muestra los cambios de apariencia del botón, el contador de notificaciones y el estado deshabilitado al desmarcar la casilla.

Esta prueba permite observar el comportamiento del componente de forma independiente de las pantallas de la aplicación.

<div align="center">
 <img width="900" alt="Prueba independiente del componente: feedback visual, contador de notificaciones y estado deshabilitado" src="https://github.com/user-attachments/assets/7e542971-14b5-4f7d-8cac-db47bd84b462" />
</div>

##### 2.3.8.2. Integración en la pantalla de Stock

Se muestran los botones existentes con el feedback visual integrado, la selección de categorías y las notificaciones recibidas por **FeedbackController** en la salida de NetBeans.

Los mensajes permiten identificar la pantalla, el botón activado y el texto de la acción.

<div align="center">
  <img width="1280" alt="Pantalla de Stock con feedback visual, selección de categorías y recepción de notificaciones" src="https://github.com/user-attachments/assets/7e4069f6-2daf-444b-99ca-e6424bf4bb78" />
</div>

##### 2.3.8.3. Foco visible y selección

Se observa el borde de foco en distintos controles y la selección visual de los filtros, junto con las notificaciones registradas.

<div align="center">
 <img width="1280" alt="Foco visible y selección de filtros en StockScan" src="https://github.com/user-attachments/assets/50bed6ed-98b1-44e7-a849-b98f12d1e47e" />
</div>

##### 2.3.8.4. Alcance de las evidencias

Las grabaciones muestran cambios visuales y recepción de eventos.

Por sí solas no permiten confirmar las teclas utilizadas ni verificar con certeza la cancelación al pulsar dentro y soltar fuera.

La demostración en Stock tampoco acredita por sí sola el funcionamiento de todas las demás pantallas. Estas comprobaciones se completarán mediante el siguiente plan de verificación.

#### 2.3.9. Plan de verificación

Los resultados locales se completarán después de ejecutar y comprobar individualmente cada prueba.

| ID | Prueba | Resultado esperado | Resultado local |
|---|---|---|---|
| TC-01 | Entrar y salir con el ratón. | Alterna reposo y hover sin notificar. | Pendiente |
| TC-02 | Mantener pulsado. | Muestra el estado pulsado sin emisión anticipada. | Pendiente |
| TC-03 | Soltar dentro después de pulsar dentro. | Emite exactamente una notificación. | Pendiente |
| TC-04 | Pulsar dentro y soltar fuera. | No emite notificación ni cambia la selección. | Pendiente |
| TC-05 | Salir después de activar un filtro. | Conserva el color base del filtro seleccionado. | Pendiente |
| TC-06 | Deshabilitar e intentar activar. | Muestra el estado deshabilitado y no notifica. | Pendiente |
| TC-07 | Habilitar de nuevo. | Recupera la apariencia y el funcionamiento. | Pendiente |
| TC-08 | Enfocar mediante Tab. | Muestra el borde naranja. | Pendiente |
| TC-09 | Pulsar y soltar Espacio con el botón enfocado. | Completa una activación y emite una notificación. | Pendiente |
| TC-10 | Revisar la salida de NetBeans. | El controlador recibe el código y el mensaje correspondientes. | Pendiente |
| TC-11 | Repetir la interacción en las pantallas integradas. | El comportamiento es coherente y no aparecen notificaciones duplicadas. | Pendiente |

## 3. Pruebas automatizadas y validación de estados aislados

### 3.1. Aislamiento del componente

#### 3.1.1. Comprobar la configuración del proyecto y la versión de Java utilizada

**Objetivo:** confirmar que el proyecto puede ejecutar pruebas unitarias con JUnit 5 antes de añadir nada.

**Qué hay que hacer:**

- Verificar la versión de Java del proyecto. JUnit 5 requiere Java 8 o superior [33], [34].
- Identificar el gestor de dependencias en uso con Maven [35].
- Confirmar que la interfaz es Swing, de modo que la utilidad gráfica sea AssertJ Swing [36].
- Comprobar que existe la carpeta de pruebas "src/test/" separada del código de producción "src/main/" [37].

**Resultado esperado:** versión de Java y gestor de dependencias identificados y anotados en el issue.

#### 3.1.2. Añadir JUnit 5 como dependencia de pruebas

**Objetivo:** dotar al proyecto de las librerías necesarias para escribir y ejecutar las pruebas.

**Qué hay que hacer:**

- Añadir **junit-jupiter** (versión 5.x) con ámbito **test**, para que no se incluya en la aplicación final [38], [39].
- Si se confirma que aporta valor, añadir también **assertj-swing-junit** con ámbito **test**. Evitar dependencias innecesarias [40], [38].
- Configurar la ejecución de tests:
  - En Maven, **maven-surefire-plugin** [41], [35].
- Recompilar el proyecto y comprobar que las dependencias se descargan sin errores [38].

**Resultado esperado:** el proyecto compila con JUnit 5 disponible solo para pruebas.

##### 3.1.2.1. Comparativa JUnit 4 vs JUnit 5 en Apache NetBeans

**Versión de referencia:** Apache NetBeans 31, publicada el 5 de agosto [42]. La 32 está en su tercera y última versión candidata (4 de noviembre) y se espera su lanzamiento a mediados de noviembre [43].

| Criterio | JUnit 4 | JUnit 5 (Jupiter) |
|---|---|---|
| Soporte en NetBeans | Desde NetBeans 6.5, que genera los tests con estilo JUnit 4 [44] | Desde Apache NetBeans 10.0 [45], [46] |
| Plantilla al crear un test | Se mantiene si el proyecto ya usa JUnit 4 [47] | Es la opción por defecto si el JDK es 8 o superior y no hay JUnit 4 en uso, o si JUnit 5 está en el classpath de un proyecto Maven [47] |
| Proyectos Maven | Se ejecuta con Surefire [47] | NetBeans delega en Maven y Surefire; requiere Surefire 2.22.0 o superior [47], [35] |
| Proyectos Gradle | Funciona sin configuración adicional [35] | Hay que activar **useJUnitPlatform()** en el bloque **test** [35] |
| Proyectos Ant | Integración ya existente en NetBeans [46] | En NetBeans 10.0 no se implementó integración específica de JUnit 5 con Ant [46]. Hay que comprobarlo en la versión que se use |
| Ejecutar un solo método de test | Soportado [47] | Soportado en proyectos Maven [47] |
| Versión mínima de Java | Java 5 [33] | Java 8 [33], [34] |
| Anotaciones | `@Before`, `@After`, `@BeforeClass`, `@AfterClass`, `@Ignore`, `@Category`, `@RunWith` [33] | `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, `@Disabled`, `@Tag`, `@ExtendWith` [33] |
| Aserciones y excepciones | Atributos "expected" y "timeout" en `@Test` [48] | **assertThrows**, **assertAll** y **assertTimeoutPreemptively** sustituyen a esos atributos [49], [48] |
| Extensibilidad | Runners y Rules y solo un runner a la vez [48], [50] | Extension API y varias extensiones a la vez [48], [50] |
| Tests parametrizados y dinámicos | Con un runner específico; sin tests dinámicos [50] | `@ParameterizedTest`, `@RepeatedTest` y `@TestFactory` [34] |
| Nombres y organización | Solo el nombre del método [50] | `@DisplayName` y `@Nested` [34] |
| Convivencia entre versiones | No aplica | El motor Vintage ejecuta tests de JUnit 4 y ambos pueden coexistir en el mismo proyecto [48], [50] |
| AssertJ Swing (pruebas de interfaz Swing) | Cuenta con **GUITestRunner** oficial [51] | Sin soporte oficial, hay que escribir una extensión o usar una de terceros [51] |
| Mantenimiento | Recomendado solo para proyectos heredados [50] | Recomendado para desarrollo nuevo [50]. Ya existe JUnit 6 como última versión estable [45] |

**Recomendación: JUnit 5**, por tres motivos:

- El propio issue #68 pide utilizar JUnit 5.
- NetBeans lo soporta de forma nativa en proyectos Maven y Gradle [45], [47], [35].
- Aporta **assertAll**, **assertThrows**, `@DisplayName` y `@Nested`, que son las herramientas que usan nuestras tareas de pruebas [34], [48].

**Cuándo elegir JUnit 4:**

- Si el proyecto de NetBeans es de tipo Ant y la integración de JUnit 5 no funciona en vuestra versión [46].
- Si necesitáis el **GUITestRunner** oficial de AssertJ Swing y no queréis escribir una extensión [51]. Con JUnit 5 se puede crear y cerrar el robot manualmente en `@BeforeEach` y `@AfterEach`.
- Si el proyecto ya usa JUnit 4 en todos sus tests y migrar no compensa [47].

#### 3.1.3. Crear la clase de test en el paquete de pruebas correspondiente

**Objetivo:** tener una clase de pruebas unitarias dedicada al componente, separada de la ventana principal.

**Qué hay que hacer:**

- Crear la clase en la carpeta de pruebas, dentro del mismo paquete que el componente a probar. Así se accede a sus miembros sin romper la encapsulación [34], [37].
- Importar las anotaciones `@Test`, `@BeforeEach` y `@AfterEach` y las aserciones de "org.junit.jupiter.api" [34].
- Limitar el alcance de la clase al componente: no debe depender de la ventana principal [52].

**Resultado esperado:** clase de test creada y compilando, todavía sin pruebas con contenido.

#### 3.1.4. Preparar la creación del componente sin depender de la ventana principal

**Objetivo:** poder instanciar el componente de forma aislada en cada prueba.

**Qué hay que hacer:**

- Instanciar el componente en un método `@BeforeEach`, sin crear ni mostrar la ventana principal (**JFrame**) de la aplicación [34], [52].
- Si el componente necesita un contenedor, usar un **JFrame** o **JPanel** auxiliar propio de la prueba, nunca la ventana real [52].
- Sustituir las dependencias externas (controlador, modelo, datos) por objetos simples o valores de prueba.
- Preparar AssertJ Swing para localizar y manipular el componente por separado [53], [54].
- Garantizar que cada test parte de una instancia nueva, para que sean independientes entre sí [34].

**Resultado esperado:** el componente se crea en las pruebas sin levantar la aplicación completa.

#### 3.1.5. Ejecutar las operaciones de UI en el Event Dispatch Thread cuando corresponda

**Objetivo:** evitar fallos intermitentes y accesos incorrectos a componentes Swing desde hilos distintos al de la interfaz [14].

**Qué hay que hacer:**

- Crear y modificar el componente dentro del Event Dispatch Thread (EDT). En las pruebas, usar **GuiActionRunner.execute(...)** de AssertJ Swing o **SwingUtilities.invokeAndWait(...)** [14], [55].
- Registrar **FailOnThreadViolationRepaintManager.install()** en un método `@BeforeAll`, para que las pruebas fallen si algún acceso a la UI se hace fuera del EDT [55], [56].
- Aplicar esta regla solo a las operaciones que tocan la interfaz (crear, leer o cambiar propiedades de componentes). La lógica sin UI no necesita el EDT [14].
- Evitar **Thread.sleep** para esperar a la UI. Esperar con las utilidades de AssertJ Swing.

**Resultado esperado:** todas las operaciones de UI de las pruebas se ejecutan en el EDT y cualquier violación de hilo hace fallar el test.

#### 3.1.6. Añadir utilidades gráficas solo si aportan valor; evitar dependencias innecesarias

**Objetivo:** mantener la infraestructura de pruebas lo más simple posible.

**Qué hay que hacer:**

- Decidir si AssertJ Swing es necesaria. Solo aporta valor si hay que simular interacción (clics, escritura, selección) o localizar componentes por nombre [36], [54].
- Si basta con comprobar el estado del componente mediante sus métodos (getters), probarlo solo con JUnit 5 y no añadir la librería.
- Si se añade, asignar nombres a los componentes con **setName(...)** para localizarlos en las pruebas sin depender de su posición [54].
- Dejar anotada en el issue la decisión y su motivo.
- Comprobar que ninguna dependencia nueva se filtra a la aplicación final (para **test**) [38].

**Resultado esperado:** decisión justificada sobre las utilidades gráficas y ninguna dependencia de pruebas que no se use.

#### 3.1.7. Ejecutar un test mínimo para verificar que la infraestructura funciona

**Objetivo:** comprobar que todo lo preparado funciona antes de escribir pruebas completas.

**Qué hay que hacer:**

- Escribir un único test que cree el componente, sin la ventana principal, y compruebe algo básico (por ejemplo, que no es **null** o que su estado inicial es el esperado) [34].
- Ejecutarlo desde la línea de comandos: **mvn test** en Maven [41].
- Comprobar en el informe de resultados ("target/surefire-reports" en Maven) que el test se ha ejecutado y ha pasado [41].
- Si falla por falta de entorno gráfico, revisar el modo headless o ejecutar en una máquina con pantalla [57].

**Resultado esperado:** un test que se ejecuta y pasa, lo que confirma que JUnit 5, la configuración y el aislamiento del componente funcionan.

#### 3.1.8. Documentar la configuración y cómo ejecutar las pruebas

**Objetivo:** que cualquier miembro del equipo pueda ejecutar las pruebas sin ayuda.

**Qué hay que hacer:**

- Añadir al README (o a la wiki del repositorio) una sección "Pruebas" con los requisitos:
versión de Java, gestor de dependencias y librerías de pruebas con sus versiones.
- Indicar los comandos exactos para ejecutar todas las pruebas y una sola clase de test.
- Explicar dónde se encuentran los informes de resultados [41].
- Anotar los requisitos del entorno gráfico: Swing necesita pantalla, así que en servidores o integración continua hay que usar un entorno virtual (por ejemplo, Xvfb) [58], [59].

**Resultado esperado:** sección de pruebas en la documentación del proyecto, probada siguiendo sus propios pasos desde cero.

#### 3.1.9. Documentación

**Objetivo:** dejar constancia de lo que prueba la clase de test y de cómo está montado el aislamiento.

**Qué hay que hacer:**

- Añadir Javadoc explicando qué componente prueba y cómo se aísla de la ventana principal [60].
- Comentar brevemente la preparación en `@BeforeEach` y `@AfterEach` (qué se crea, qué se sustituye por objetos de prueba y qué se libera) [34].
- Enlazar el issue #68 desde la documentación y recoger las decisiones tomadas (por ejemplo, el uso o no de AssertJ Swing).
- Revisar la ortografía y que los nombres de clases y métodos coincidan con el código.

**Resultado esperado:** código de pruebas y documentación coherentes y comprensibles para alguien que no ha participado en la tarea.

#### 3.1.10. Riesgos y problemas previsibles en las pruebas del componente Swing

**Objetivo:** anticipar los problemas más habituales al probar componentes Swing con JUnit 5 y AssertJ Swing, para prevenirlos antes de que bloqueen la tarea.

##### 3.1.10.1. Error de entorno sin pantalla (HeadlessException)

**Qué nos puede pasar:** si las pruebas se ejecutan en un equipo o servidor sin entorno gráfico, cualquier operación con ventanas lanza **HeadlessException** o un error de "no screen devices". Es un problema que ya han tenido otros proyectos al ejecutar Swing en máquinas virtuales en la nube [57], [61].

**Cómo prevenirlo:**

- Ejecutar las pruebas en un equipo con pantalla [57].
- Si hay integración continua, preparar un entorno gráfico virtual [58], [59].
- No forzar **java.awt.headless=true** en las pruebas que usan AssertJ Swing [57].

##### 3.1.10.2. Pruebas que fallan a veces en integración continua

**Qué nos puede pasar:** hay equipos cuyas pruebas pasaban en local pero fallaban de forma aleatoria en GitHub Actions o Travis. Los errores típicos eran **WaitTimedOutError** y **ComponentLookupException**, causados porque la ventana no llegaba a mostrarse. En esos casos, el problema estaba en Xvfb [62], [63], [64].

**Cómo prevenirlo:**

- Probar primero en local y no dar por buena una prueba que solo ha pasado una vez [62].
- Si falla solo en el servidor, valorar un servidor VNC en lugar de Xvfb, que es lo que recomiendan los autores de AssertJ Swing [59].
- Repetir la ejecución varias veces antes de dar la infraestructura por estable [62].

##### 3.1.10.3. JUnit 5 no está soportado oficialmente por AssertJ Swing

**Qué nos puede pasar:** el **GUITestRunner** y las clases de apoyo de AssertJ Swing están pensadas para JUnit 4. En el repositorio hay una petición abierta de soporte para JUnit 5, y algunos equipos han tenido que escribir su propia extensión (por ejemplo, para arrancar y cerrar el robot o hacer capturas al fallar). Una extensión de terceros puede traer su propia versión de JUnit y provocar conflictos de dependencias [51].

**Cómo prevenirlo:**

- Decidir pronto si usamos una extensión de terceros o una propia, y dejarlo anotado en el issue [51].
- Comprobar que las versiones de JUnit y de la extensión son compatibles antes de escribir pruebas [51].
- Crear y cerrar el robot manualmente en `@BeforeEach` y `@AfterEach` si no se usa extensión [53].

##### 3.1.10.4. Accesos a la interfaz fuera del Event Dispatch Thread

**Qué nos puede pasar:** crear o modificar componentes fuera del EDT provoca fallos aleatorios, difíciles de reproducir. Además, el detector de violaciones puede avisar de accesos que no siempre son un fallo real, lo que genera ruido en las pruebas [55], [56], [14].

**Cómo prevenirlo:**

- Instalar **FailOnThreadViolationRepaintManager** una sola vez, al inicio de la clase de pruebas [55], [56].
- Crear y leer el componente siempre con **GuiActionRunner.execute(...)** [55].
- Revisar cada violación detectada antes de decidir si es un error real [56].

##### 3.1.10.5. Componentes difíciles de localizar

**Qué nos puede pasar:** AssertJ Swing localiza los componentes por nombre. Si no tienen nombre, la búsqueda falla con **ComponentLookupException**, y se complica aún más con componentes de librerías de terceros que no se pueden nombrar [54], [64].

**Cómo prevenirlo:**

- Asignar **setName(...)** a los componentes que se vayan a probar [54].
- Mantener una convención de nombres única en todo el proyecto [54].

##### 3.1.10.6. Pruebas que interfieren con el equipo mientras se ejecutan

**Qué nos puede pasar:** AssertJ Swing simula gestos reales a nivel del sistema operativo, así que mientras corren las pruebas el ratón y el teclado pueden moverse solos. Si tocamos el equipo durante la ejecución, el test puede fallar sin razón aparente [59].

**Cómo prevenirlo:**

- No usar el ratón ni el teclado mientras se ejecutan las pruebas gráficas [59].
- Separar las pruebas gráficas de las pruebas unitarias normales, para poder lanzar solo las que no dependen de la pantalla [59].

#### 3.1.11. Decisión de herramienta de construcción: Maven en lugar de Gradle

**Decisión:** el proyecto usa Maven como herramienta de construcción y gestor de dependencias.

##### 3.1.11.1. Motivos

**1. Integración nativa con NetBeans y sus pruebas**

- NetBeans está muy ligado a Maven. En proyectos Maven delega en Surefire para ejecutar los tests, y los resultados se muestran en su ventana de resultados [47], [46].
- Desde la versión 11.0, el asistente de nuevo proyecto de NetBeans ofrece Maven y Gradle por delante de Ant [67].

**2. JUnit 5 con menos configuración**

- En Maven, Surefire (2.22.0 o superior) toma el motor de pruebas de las dependencias normales [35].
- En Gradle hay que activar **useJUnitPlatform()** de forma explícita en el bloque **test** [35].

**3. Estructura convencional y curva de aprendizaje baja**
- Maven sigue "convención sobre configuración": estructura estándar ("src/main/java", "src/test/java") y ciclo de vida predefinido [37].
- Maven tiene más documentación y recursos, y suele considerarse más sencillo de aprender que Gradle [68], [69].
- Se configura con un "pom.xml" en XML, frente al lenguaje Groovy o Kotlin de Gradle [69].

**4. Proyecto de tamaño reducido**

- En proyectos de un solo módulo, la diferencia de velocidad entre ambos es despreciable [70]. Gradle aprovecha más sus builds incrementales en proyectos grandes [71].
- Los builds de Maven son muy predecibles, porque dependen de convenciones y no de scripts personalizados [71].

##### 3.1.11.2. Qué asumimos al elegir Maven

- Gradle puede ser más rápido y flexible en proyectos grandes o con tareas personalizadas [71], [72] (el dato de velocidad lo da el propio fabricante de Gradle).
- La configuración en XML es más verbosa que un script de Gradle [69].
- Gradle también funciona bien en NetBeans: admite JUnit 4, JUnit 5 y TestNG. NetBeans ha actualizado tanto su Maven incluido como las herramientas de Gradle [42]. La elección no se debe a que Gradle no esté soportado.
- **Migraremos temporalmente el proyecto a Maven únicamente para realizar la etapa de testeo, pero el proyecto está hecho con Ant.**

##### 3.1.11.3. Cuándo reconsiderarlo

- Si el proyecto pasa a varios módulos o el tiempo de compilación se vuelve un problema [71].
- Si hacen falta tareas de construcción que Maven no cubre con sus plugins estándar [72].

#### 3.1.12. Migración parcial a Maven solo para las pruebas (el proyecto sigue en Ant)

**Objetivo:** añadir Maven al proyecto únicamente para compilar y ejecutar las pruebas con JUnit 5, sin cambiar cómo se compila, empaqueta y ejecuta la aplicación, que sigue con Ant.

**Por qué hace falta:** NetBeans ejecuta JUnit 5 apoyándose en Maven y Surefire. En NetBeans 10.0 no se implementó una integración específica de JUnit 5 para Ant [46].
Esta limitación hay que comprobarla en la versión que se use.

**Enfoque elegido:** mantener la estructura y los archivos de Ant ("build.xml", "nbproject/", "src/", "test/") y añadir un "pom.xml" en la raíz que apunte a los directorios existentes. Maven permite sustituir los directorios por defecto con "sourceDirectory" y "testSourceDirectory" [73], [74]. En una migración no hace falta mover las fuentes: basta con configurar Maven para que use las carpetas actuales [75].

##### 3.1.12.1. Pasos

**1. Preparar**

- Crear una rama para el cambio (por ejemplo, "feature/maven-testing") [66].
- Anotar la versión de Java, la carpeta de código (normalmente "src"), la carpeta de pruebas ("test"), las librerías externas y los archivos no Java dentro de "src" (imágenes, **.properties**) [75].

**2. Crear el "pom.xml" junto a "build.xml"**

- Declarar JUnit Jupiter con ámbito **test** y Surefire 2.22.0 o superior, que es la versión mínima para JUnit 5 [39].
- Apuntar Maven a las carpetas de Ant y copiar los archivos no Java como recursos, porque por defecto Maven solo los toma de "src/main/resources" [73].

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>[grupo]</groupId>
  <artifactId>[proyecto]</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.release>[misma versión de Java que en Ant]</maven.compiler.release>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>[5.x]</version>
      <scope>test</scope>
    </dependency>
    <!-- Solo si se decide usar AssertJ Swing -->
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-swing-junit</artifactId>
      <version>[3.x]</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <sourceDirectory>src</sourceDirectory>
    <testSourceDirectory>test</testSourceDirectory>
    <resources>
      <resource>
        <directory>src</directory>
        <excludes>
          <exclude>**/*.java</exclude>
        </excludes>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>[2.22.0 o superior]</version>
      </plugin>
    </plugins>
  </build>
</project>
```

**3. Resolver las librerías externas del proyecto**

- Si la librería está en Maven Central, declararla como dependencia normal [38].
- Si es un **.jar** suelto, no usar el ámbito **system**, que está marcado como obsoleto.
Instalarlo en el repositorio local con **mvn install:install-file** o crear un repositorio local dentro del proyecto [76].

**4. Compilar**

- Ejecutar **mvn compile** y corregir los errores de dependencias o de versión de Java hasta que compile con las mismas fuentes que Ant [75].

**5. Preparar las pruebas**

- Nombrar las clases de test como **\*Test**, **Test\***, **\*Tests** o **\*TestCase**, que son los patrones que Surefire detecta por defecto [77].
- Si ya hay pruebas escritas con JUnit 4, añadir el motor Vintage para poder ejecutarlas junto a las de JUnit 5 [78].

**6. Ejecutar**

- Lanzar **mvn test** desde la raíz del proyecto. Los informes quedan en "target/surefire-reports" [39].
- Si Maven no está instalado en el equipo, se puede usar el que incluye NetBeans (la versión 31 lo actualizó a 3.9.16) [42] o instalar uno.

**7. Convivir con Ant**

- Maven escribe en "target/" y añadir esa carpeta al ".gitignore" [41].
- No se modifica "build.xml" ni "nbproject/". Ant sigue compilando y ejecutando la aplicación como hasta ahora [75].

**8. Verificar y documentar**

- Ejecutar **mvn clean test** desde cero y comprobar que además **ant clean** y la compilación con Ant siguen funcionando [41].
- Documentar los pasos y los comandos en la sección "Pruebas" del README [60].
- Abrir un pull request para que otra persona del equipo revise el "pom.xml" [66].

##### 3.1.12.2. Qué no se hace

- No se mueven archivos a "src/main/" ni se renombran o eliminan "build.xml" y "nbproject/". Eso es una conversión completa del proyecto [75] y no la necesitamos.
- No se usa Maven para empaquetar ni para ejecutar la aplicación [75].

##### 3.1.12.3. Riesgos

- **Reconocimiento del proyecto en NetBeans:** mientras existan "nbproject/" y "build.xml", NetBeans seguirá tratándolo como proyecto Ant. En la guía de conversión completa se renombran esos archivos para que NetBeans reconozca el "pom.xml" [75].
En este enfoque las pruebas se lanzan con **mvn test** desde la terminal, y no hemos comprobado cómo se muestran los resultados en NetBeans.
- **Recursos que no se copian:** si falta el bloque "resources", las pruebas que usen imágenes o archivos **.properties** fallarán por no encontrarlos [73].
- **"No tests to run":** ocurre si las clases de test no siguen los patrones de nombre de Surefire [77].
- **Versiones de Java distintas:** si "maven.compiler.release" no coincide con la de Ant, pueden aparecer errores de compilación solo en Maven [75].
- **Librerías duplicadas:** al declararlas en Ant y en el "pom.xml", las dos listas pueden desincronizarse. Hay que actualizar ambas cuando se añada una librería [76].

#### 3.1.13. Glosario de términos técnicos

**Pruebas y JUnit**

- **Prueba unitaria:** prueba automática que comprueba una parte pequeña del código (aquí, un componente) de forma aislada [34].
- **JUnit 5 (Jupiter):** framework de Java para escribir y ejecutar pruebas. Jupiter es el motor y la API de anotaciones de la versión 5 [34].
- **`@Test`, `@BeforeEach`, `@AfterEach`:** anotaciones que marcan un método como prueba, como preparación antes de cada prueba y como limpieza después de cada prueba [34].
- **Aserción:** comprobación dentro de una prueba (por ejemplo, que un valor sea el esperado). Si no se cumple, la prueba falla [34].
- **Ámbito test:** forma de declarar una dependencia para que solo se use al probar y no se incluya en la aplicación final [38].
- **Objetos de prueba:** sustitutos sencillos de las dependencias reales (controlador, modelo, datos) que permiten probar el componente sin el resto de la aplicación [34].
- **Aislamiento (desacoplamiento):** probar el componente sin depender de la ventana principal ni del resto de la aplicación [34].
- **Prueba inestable (flaky):** prueba que a veces pasa y a veces falla sin que el código haya cambiado [62].

**Swing y AssertJ Swing**

- **Swing:** biblioteca de Java para crear interfaces gráficas de escritorio [52].
- **JFrame / JPanel:** una ventana y un contenedor de componentes de Swing, respectivamente [52].
- **Event Dispatch Thread (EDT):** hilo único donde Swing debe crear y modificar los componentes gráficos [14].
- **Violación del EDT:** acceso a un componente Swing desde un hilo distinto del EDT.
Puede causar fallos aleatorios [55].
- **AssertJ Swing:** biblioteca para probar interfaces Swing. Localiza componentes y simula acciones del usuario [36].
- **FailOnThreadViolationRepaintManager:** clase de AssertJ Swing que hace fallar una prueba si detecta una violación del EDT [56].
- **GuiActionRunner:** utilidad de AssertJ Swing para ejecutar código de interfaz dentro del EDT [55].
- **Robot:** objeto de AssertJ Swing que simula ratón y teclado [53].
- **GUITestRunner:** ejecutor de pruebas gráficas de AssertJ Swing diseñado para JUnit 4 [51].
- **Extensión de JUnit 5:** mecanismo para añadir comportamiento a las pruebas, como arrancar y cerrar el robot [48].
- **ComponentLookupException:** error de AssertJ Swing cuando no encuentra un componente con el nombre buscado [64].
- **WaitTimedOutError:** error de AssertJ Swing cuando se agota el tiempo de espera de una condición [63].

**Construcción y ejecución**

- **Maven:** herramienta que compila el proyecto, gestiona dependencias y ejecuta las pruebas [37].
- **Maven Surefire:** plugin de Maven que ejecuta las pruebas [41].
- **useJUnitPlatform():** configuración de Gradle para ejecutar pruebas con JUnit 5 [35].

**Entorno gráfico e integración continua**

- **Headless / HeadlessException:** modo o error de Java cuando no hay pantalla disponible [57].
- **Xvfb:** servidor gráfico virtual de Linux para ejecutar programas con interfaz sin pantalla real [58].
- **VNC:** sistema de escritorio remoto que puede servir de pantalla virtual en integración continua [59].
- **Integración continua (CI) / GitHub Actions:** ejecución automática de compilación y pruebas cada vez que se sube código [65].
- **Pull request:** propuesta de cambios en GitHub que otro miembro del equipo revisa antes de incorporarla [66].
- **Javadoc:** comentarios con formato especial que documentan clases y métodos de Java [60].

### 3.2. Pruebas de valores por defecto, getters/setters y validación del componente

#### 3.2.1. Resumen

Se crea una batería de pruebas unitarias con JUnit 5 [34] que comprueba los valores por defecto, el comportamiento de getters y setters y las reglas de validación de un componente Swing. Las pruebas priorizan la API pública y evitan depender de detalles privados de implementación [79], [80].

**Resultado esperado:** suite de pruebas completa, ejecutada, corregida junto al responsable del código y documentada.

---

#### 3.2.2. Desarrollo de las tareas

##### 3.2.2.1. Verificar los valores por defecto tras usar el constructor sin argumentos.

**Objetivo:** confirmar que el componente nace con un estado inicial correcto y predecible.

**Qué hay que hacer:**
- Crear una instancia de **[Componente]** con el constructor sin argumentos, sin la ventana principal.
- Listar las propiedades que tienen un valor por defecto definido (por ejemplo, tamaño, color, texto, estado inicial).
- Comprobar, solo a través de la API pública (getters), que cada propiedad devuelve el valor por defecto esperado [79].
- Evitar acceder a atributos privados o a detalles internos de implementación [79], [80].
- Si el valor por defecto de alguna propiedad no está definido en el contrato, anotarlo en el issue antes de probarlo.

**Resultado esperado:** una prueba por cada propiedad con valor por defecto, todas pasando.

##### 3.2.2.2. Comprobar que cada setter actualiza el valor que devuelve su getter.

**Objetivo:** asegurar que lo que se asigna con un setter es lo que después se lee con su getter.

**Qué hay que hacer:**
- Identificar todos los pares setter/getter públicos del componente.
- Para cada par, asignar un valor distinto del valor por defecto y comprobar que el getter devuelve exactamente ese valor.
- Verificar también que asignar un segundo valor distinto sustituye al primero.
- Comprobar que modificar una propiedad no altera el resto, agrupando las comprobaciones con **assertAll** para ver todos los fallos a la vez [49].
- Usar un test por cada par, con nombre que indique la propiedad comprobada.

**Resultado esperado:** todos los pares setter/getter cubiertos y comportándose de forma coherente.

##### 3.2.2.3. Verificar que los tamaños negativos se rechazan o se normalizan según el contrato definido.

**Objetivo:** dejar probado cómo reacciona el componente ante tamaños no válidos.

**Qué hay que hacer:**
- Confirmar con el equipo cuál es el contrato: rechazar el valor (lanzar excepción o ignorarlo) o normalizarlo (por ejemplo, a 0 o al valor mínimo). Anotarlo en el issue.
- Probar un valor negativo en cada propiedad de tamaño (ancho, alto u otras) y comprobar el comportamiento definido.
- Si se rechaza con excepción, comprobar el tipo de excepción con **assertThrows** [49].
- Si se normaliza, comprobar que el getter devuelve el valor normalizado y no el negativo.
- Comprobar que, tras un valor rechazado, el componente conserva su valor anterior.

**Resultado esperado:** pruebas que reflejan el contrato de tamaños negativos acordado, sin suposiciones.

##### 3.2.2.4. Comprobar el tratamiento de colores/textos nulos según las reglas acordadas.

**Objetivo:** dejar probado qué ocurre cuando se asigna **null** a un color o a un texto.

**Qué hay que hacer:**
- Confirmar con el equipo la regla para cada propiedad: rechazar el **null**, sustituirlo por un valor por defecto o aceptarlo tal cual. Anotarlo en el issue.
- Probar **null** en cada propiedad de color y de texto y comprobar el comportamiento acordado a través del getter.
- Si se rechaza con excepción, comprobarla con **assertThrows** [49].
- Probar también el texto vacío (**""**), para distinguirlo de **null**.
- Comprobar que el componente no queda en un estado inconsistente después de recibir un valor nulo.

**Resultado esperado:** reglas de nulidad acordadas y verificadas para todos los colores y textos.

##### 3.2.2.5. Comprobar que los cambios visuales solicitan actualización cuando corresponda, usando una estrategia de prueba razonable.

**Objetivo:** asegurar que, al cambiar una propiedad que afecta a la apariencia, el componente pide repintarse o recalcular su tamaño, sin necesidad de mostrar una ventana.

**Qué hay que hacer:**
- Distinguir qué propiedades afectan a la apariencia (color, texto, tamaño) y cuáles no. En los componentes estándar de Swing, cambiar una propiedad visible ya provoca **repaint** o **revalidate**, pero en un componente propio hay que invocarlos explícitamente [81].
- Definir una estrategia de prueba que no dependa de dibujar en pantalla. Por ejemplo, crear en el test una subclase del componente que cuente las llamadas a **repaint()** y **revalidate()**, o usar un espía (spy) si el proyecto ya incluye una librería de simulación.
- Comprobar que cambiar una propiedad visual provoca la solicitud de actualización esperada.
- Comprobar que asignar el mismo valor que ya tenía no genera actualizaciones innecesarias, si el contrato así lo define.
- Comprobar que las propiedades no visuales no solicitan actualización.
- Ejecutar las operaciones de interfaz dentro del Event Dispatch Thread cuando corresponda [81], [55].

**Resultado esperado:** pruebas que verifican la solicitud de actualización visual sin depender de la ventana principal ni de la pantalla.

##### 3.2.2.6. Añadir casos límite y nombres descriptivos a los tests.

**Objetivo:** ampliar la cobertura a los valores extremos y hacer que los tests se entiendan sin leer su código.

**Qué hay que hacer:**
- Añadir casos límite en las propiedades con restricciones: tamaño 0, tamaño mínimo y máximo permitidos, valores justo en el límite y justo fuera de él.
- Añadir casos de texto especiales: vacío, solo espacios, texto muy largo y caracteres especiales.
- Renombrar los tests con una convención común que indique qué se prueba y qué se espera (por ejemplo, **setWidth_negativeValue_isRejected**), o usar `@DisplayName` con una frase clara [34].
- Agrupar las pruebas por funcionalidad (valores por defecto, getters/setters, validación, actualización visual), por ejemplo con `@Nested` [34].
- Revisar que ningún test dependa del orden de ejecución ni de detalles privados del componente.

**Resultado esperado:** pruebas con casos límite cubiertos y nombres que explican por sí solos qué comprueban.

##### 3.2.2.7. Ejecutar la suite y corregir fallos junto al responsable del código.

**Objetivo:** comprobar que todas las pruebas pasan y resolver los fallos con quien conoce el código.

**Qué hay que hacer:**
- Ejecutar la suite completa desde cero: **mvn clean test** en Maven
- Anotar el resultado: pruebas ejecutadas, superadas, fallidas y omitidas.
- Para cada fallo, determinar si el error está en la prueba (expectativa mal escrita) o en el código (incumple el contrato acordado).
- Hablar con el responsable del código de **[Componente]** para decidir la corrección. No cambiar código ajeno sin acordarlo ni relajar una prueba solo para que pase.
- Corregir, volver a ejecutar la suite completa y repetir hasta que todo pase.
- Lanzar la suite más de una vez para confirmar que ninguna prueba depende del orden de ejecución [49].

**Resultado esperado:** suite completa en verde y lista de los fallos corregidos con la decisión tomada en cada uno.

##### 3.2.2.8. Documentar los casos cubiertos y el resultado.

**Objetivo:** dejar constancia de qué se ha probado, con qué resultado y qué queda sin cubrir.

**Qué hay que hacer:**
- Crear una tabla con una fila por caso probado: propiedad o regla, resultado esperado, resultado obtenido (superado o fallido) y nombre del test.
- Agrupar los casos por bloques: valores por defecto, getters/setters, tamaños negativos, textos y colores nulos, actualización visual y casos límite.
- Indicar el resultado global (pruebas ejecutadas, superadas y fallidas) junto con la fecha y la versión del código probado.
- Si se genera informe de cobertura con JaCoCo (en Maven, el informe HTML queda en "target/site/jacoco/index.html") [82], adjuntarlo y aclarar que mide qué líneas se ejecutan, no si las pruebas validan algo útil [83].
- Anotar los casos no cubiertos y el motivo, así como las decisiones de contrato tomadas (negativos y nulos).

**Resultado esperado:** documento con los casos cubiertos, sus resultados y las limitaciones conocidas.

##### 3.2.2.9. Documentación

**Objetivo:** que cualquier miembro del equipo entienda qué comprueba la suite y cómo ejecutarla.

**Qué hay que hacer:**
- Añadir Javadoc a la clase de test explicando qué comportamiento del componente prueba y qué reglas del contrato asume.
- Comentar brevemente los tests menos evidentes (casos límite y estrategia usada para comprobar la actualización visual).
- Actualizar la sección "Pruebas" del README con los comandos para ejecutar esta suite.
- Enlazar el issue y el documento de casos cubiertos.
- Revisar la ortografía y que los nombres de clases y métodos coincidan con el código.

**Resultado esperado:** documentación coherente con el código y comprensible para alguien que no ha participado en la tarea.

##### 3.2.2.10. Revisión de la tarea

**Objetivo:** verificar que se cumple todo lo pedido antes de cerrar el issue.

**Qué hay que hacer:**
- Repasar la lista de tareas del issue y marcar solo las completadas y comprobadas.
- Ejecutar la suite desde cero en un entorno limpio y confirmar que pasa.
- Comprobar que las pruebas usan solo la API pública y no acceden a detalles privados del componente [79], [80].
- Comprobar que el contrato de tamaños negativos y valores nulos está documentado y coincide con lo que prueban los tests.
- Revisar que los tests tienen nombres descriptivos y que los casos límite están cubiertos.
- Pedir revisión a otro miembro del equipo mediante pull request y resolver sus comentarios.

**Resultado esperado:** tareas verificadas, suite en verde y el issue listo para cerrarse.

---

#### 3.2.3. Riesgos y problemas previsibles

##### 3.2.3.1. Pruebas atadas a detalles privados

**Qué nos puede pasar:** si los tests acceden a atributos o métodos internos (o si se hacen públicos solo para poder probarlos), cualquier refactorización del componente rompe las pruebas aunque el comportamiento siga siendo correcto [79], [80].

**Cómo prevenirlo:**
- Probar solo a través de constructores, getters y setters públicos.
- No añadir métodos o getters solo para facilitar las pruebas.

##### 3.2.3.2. Pruebas de relleno que pasan sin validar nada

**Qué nos puede pasar:** probar getters y setters triviales es un debate abierto entre desarrolladores [84], [85]. Si los tests solo ejecutan el código sin comprobar valores reales, la cobertura parece alta pero no protege de errores [83].

**Cómo prevenirlo:**
- Asignar valores distintos de los valores por defecto antes de comprobar el getter.
- Comprobar siempre un resultado concreto, no solo que el código no falla.
- Si el equipo lo permite, valorar pruebas de mutación para detectar tests débiles [83], [85].

##### 3.2.3.3. Contrato sin definir (tamaños negativos y valores nulos)

**Qué nos puede pasar:** si no está acordado qué hace el componente con un tamaño negativo o un **null** (rechazar, normalizar o aceptar), cada persona escribe el test según su suposición y aparecen discusiones en la revisión.

**Cómo prevenirlo:**
- Acordar y anotar la regla en el issue antes de escribir los tests.
- Probar también texto vacío y valores justo en el límite.

##### 3.2.3.4. Valores por defecto que dependen del entorno

**Qué nos puede pasar:** algunos valores por defecto de componentes Swing (colores, fuentes, tamaños) los aporta el aspecto visual del sistema, por lo que pueden cambiar de un equipo a otro y hacer fallar un test que pasa en otro ordenador.

**Cómo prevenirlo:**
- Comprobar solo los valores por defecto que define nuestro propio código.
- Para los valores que aporta Swing, comparar con el valor del propio aspecto o no probarlos.

##### 3.2.3.5. Expectativas erróneas sobre la actualización visual

**Qué nos puede pasar:** los componentes estándar de Swing ya solicitan repintado al cambiar propiedades visibles, pero en un componente propio hay que hacerlo a mano [81].
Un test puede esperar una actualización que no existe, o dar por buena una que no se pide en el caso correcto.

**Cómo prevenirlo:**
- Definir qué propiedades deben solicitar actualización y cuáles no.
- Contar las solicitudes de repintado con una subclase de prueba, sin mostrar ventanas.
- Probar también que las propiedades no visuales no solicitan actualización.

##### 3.2.3.6. Accesos a la interfaz fuera del Event Dispatch Thread

**Qué nos puede pasar:** crear o modificar componentes fuera del EDT provoca excepciones aleatorias y problemas de pintado [81], difíciles de reproducir.

**Cómo prevenirlo:**
- Crear y modificar el componente dentro del EDT en las pruebas.
- Mantener instalado el detector de violaciones del EDT de AssertJ Swing [55].

##### 3.2.3.7. Estado compartido entre pruebas

**Qué nos puede pasar:** JUnit crea una instancia nueva de la clase por cada test, pero se puede cambiar a una sola instancia con `@TestInstance(PER_CLASS)` [49], y un campo estático también comparte estado. Así los tests empiezan a depender del orden de ejecución.

**Cómo prevenirlo:**
- Crear un componente nuevo en `@BeforeEach` para cada prueba.
- Evitar campos estáticos mutables.
- Ejecutar la suite varias veces para detectar dependencias de orden.

##### 3.2.3.8. Un solo fallo oculta al resto

**Qué nos puede pasar:** en un test con varias aserciones, la primera que falla detiene el test y no vemos qué más estaba mal. **assertAll** ejecuta todas las comprobaciones antes de informar de cuáles han fallado [49].

**Cómo prevenirlo:**
- Agrupar las comprobaciones relacionadas con **assertAll**.
- Mantener un test por propiedad cuando convenga localizar rápido el fallo.

##### 3.2.3.9. Informe de cobertura que engaña o no se genera

**Qué nos puede pasar:** una cobertura alta solo indica que el código se ha ejecutado, no que las pruebas lo validen [83]. Además, si el informe no se encuentra, SonarQube puede mostrar 0% de cobertura [86].

**Cómo prevenirlo:**
- Revisar la ruta del informe de JaCoCo y comprobar que se genera tras ejecutar las pruebas [82].
- Documentar el porcentaje junto con sus limitaciones, no como garantía de calidad.

##### 3.2.3.10. Corregir los tests en lugar del problema real

**Qué nos puede pasar:** al ejecutar la suite, es tentador modificar la prueba para que pase o tocar el código de otra persona sin avisar, lo que genera conflictos y oculta errores reales.

**Cómo prevenirlo:**
- Decidir con el responsable del código si el fallo está en la prueba o en el código.
- Anotar en el issue cada decisión tomada.

##### 3.2.3.11. Tests con nombres poco claros

**Qué nos puede pasar:** con nombres como "test1" o "testSetter", un fallo en el informe no dice qué regla se ha roto. Además, `@DisplayName` no se hereda entre clases [34], y el estilo se mezcla si cada persona usa el suyo.

**Cómo prevenirlo:**
- Acordar una convención de nombres única (propiedad, condición y resultado esperado).
- Agrupar las pruebas por funcionalidad con `@Nested` [34].

---

#### 3.2.4. Glosario de términos técnicos

**Diseño de las pruebas**
- **API pública:** conjunto de constructores y métodos públicos del componente. Es lo único que deben usar las pruebas [79].
- **Detalle de implementación:** parte interna del componente (atributos y métodos privados) que puede cambiar sin afectar al comportamiento [79], [80].
- **Contrato:** reglas acordadas sobre cómo debe comportarse el componente (por ejemplo, qué hace con un tamaño negativo).
- **Valor por defecto:** valor que tiene una propiedad justo después de crear el componente.
- **Constructor sin argumentos:** constructor que crea el componente sin recibir parámetros.
- **Getter / setter:** métodos públicos para leer y asignar el valor de una propiedad [84].
- **Validación:** comprobación de que un valor asignado es aceptable según el contrato.
- **Rechazar / normalizar:** rechazar es negarse a aceptar el valor (con excepción o ignorándolo); normalizar es convertirlo a un valor válido (por ejemplo, a 0).
- **Valor nulo (**null**):** ausencia de valor. No es lo mismo que un texto vacío (**""**).
- **Caso límite:** valor justo en el borde de lo permitido o fuera de él (0, mínimo, máximo, vacío).
- **Suite de pruebas:** conjunto completo de pruebas de un proyecto o de un componente.
- **Prueba de regresión:** prueba que detecta si un cambio posterior rompe algo que ya funcionaba.
- **Responsable del código (code owner):** persona que mantiene una parte del código y decide cómo corregirla.

**JUnit 5**
- **assertEquals:** aserción que comprueba que un valor obtenido coincide con el esperado [49].
- **assertThrows:** aserción que comprueba que un código lanza la excepción esperada [49].
- **assertAll:** agrupa varias aserciones y las ejecuta todas antes de informar de cuáles han fallado [49].
- **`@DisplayName`:** da a un test o a una clase un nombre legible en los informes [34].
- **`@Nested`:** permite agrupar pruebas relacionadas en clases internas [34].
- **`@TestInstance(PER_CLASS)`:** hace que JUnit use una sola instancia de la clase de pruebas para todos los tests, en lugar de una por test [49].

**Swing**
- **repaint():** solicita que el componente se vuelva a dibujar [81].
- **revalidate():** solicita que se recalcule la disposición y el tamaño del componente [81].
- **Event Dispatch Thread (EDT):** hilo único donde Swing debe crear y modificar los componentes gráficos [81], [55].
- **Aspecto visual (look and feel):** estilo que Swing aplica a los componentes y que puede cambiar valores por defecto como colores o fuentes.
- **Subclase de prueba / espía (spy):** versión del componente creada solo en el test, que registra cuántas veces se llama a un método (por ejemplo, **repaint()**).

**Calidad y resultados**
- **Cobertura de código:** porcentaje de líneas del código que se ejecutan durante las pruebas [83].
- **Pruebas de mutación:** técnica que introduce errores deliberados en el código para comprobar si las pruebas los detectan [83], [85].
- **Prueba de relleno:** prueba que se ejecuta pero no comprueba nada útil [85].
- **Informe de resultados:** archivo que genera la herramienta de pruebas con los tests superados, fallidos y omitidos.
- **Pull request:** propuesta de cambios en GitHub que otro miembro del equipo revisa antes de incorporarla.
- **Javadoc:** comentarios con formato especial que documentan clases y métodos de Java.

### 3.3. Prueba de simulación de interacción

#### 3.3.1. Simular un clic/interacción mediante la herramienta disponible o invocar el método de acción público

**Objetivo:** provocar en la prueba la acción del usuario sin intervención manual.

**Qué se hace:**
- **Opción A:** sin librerías gráficas: invocar el método de acción público del componente o, si es un botón, **doClick()**, que realiza un clic por programa [89]. El botón dispara entonces el evento de acción y se invoca **actionPerformed** en sus listeners [90].
- **Opción B:** con AssertJ Swing (solo si se ha decidido añadirla): localizar el componente por nombre y simular el clic, por ejemplo **window.button("nombre").click()** [53].
- Las operaciones sobre componentes Swing se ejecutan en el Event Dispatch Thread.
En las pruebas, con **GuiActionRunner.execute(...)** de AssertJ Swing [55].
- La opción elegida y su motivo se anotan en el issue.

**Resultado esperado:** la interacción se simula de forma repetible en la prueba.

#### 3.3.2. Verificar que se emite exactamente la notificación esperada

**Objetivo:** comprobar que, tras la interacción, el listener ha recibido una única notificación y con el contenido correcto.

**Qué se hace:**
- Comprobar el número de notificaciones recibidas con **assertEquals(esperado, actual)**, que compara el valor esperado con el real [91].
- Comprobar el contenido del evento recibido (fuente e información que lleva el objeto de evento) [87].
- Agrupar varias comprobaciones con **assertAll**, que ejecuta todas y reporta juntos todos los fallos [91].
- Si se espera una lista de eventos, compararla con **assertIterableEquals**, que indica el primer índice que difiere [91].

**Resultado esperado:** el test falla si no se notifica, si se notifica más de una vez o si el contenido no es el previsto.

#### 3.3.3. Verificar que no se notifica a un listener después de desregistrarlo

**Objetivo:** comprobar que un listener desregistrado deja de recibir notificaciones.

**Qué se hace:**
- Registrar el listener, desregistrarlo con el método **remove\<Evento\>Listener** del componente (por ejemplo, **removeActionListener**) [88], [89] y repetir la interacción.
- Comprobar con **assertEquals** que el número de notificaciones del listener no ha cambiado [91].
- Opcionalmente, comprobar que ya no figura entre los listeners del componente (en un botón, con **getActionListeners()**) [89].

**Resultado esperado:** tras desregistrarlo, el listener no recibe ninguna notificación nueva.

#### 3.3.4. Comprobar el cambio de estado observable tras la interacción

**Objetivo:** confirmar que, además de notificar, el componente actualiza su estado observable.

**Qué se hace:**
- Leer el estado del componente tras la interacción (por ejemplo, el texto de una etiqueta) y compararlo con el esperado mediante **assertEquals** [91]. Con AssertJ Swing se puede hacer con métodos como **requireText(...)** [53].
- Leer el estado dentro del Event Dispatch Thread cuando se acceda directamente al componente [55].
- Agrupar con **assertAll** las comprobaciones de varios elementos [91].

**Resultado esperado:** el estado observable coincide con el previsto después de la interacción.

#### 3.3.5. Ejecutar las pruebas en conjunto con el resto de la suite

**Objetivo:** comprobar que las nuevas pruebas funcionan junto con las demás y no se afectan entre sí.

**Qué se hace:**
- Ejecutar todas las pruebas desde la raíz del proyecto con **mvn test**: Surefire ejecuta los tests en la fase **test** [41].
- Comprobar que la clase de test se detecta por su nombre (**\*Test**, **Test\***, **\*Tests** o **\*TestCase**) [93].
- Ejecutar solo esta clase con **mvn -Dtest=NombreClase test** para comparar el resultado aislado y el resultado en conjunto [92].
- Revisar los informes en **target/surefire-reports** [41].

**Resultado esperado:** las pruebas pasan tanto solas como junto al resto de la suite.

#### 3.3.6. Registrar incidencias y repetir la prueba tras las correcciones

**Objetivo:** dejar constancia de los fallos encontrados y comprobar que quedan corregidos.

**Qué se hace:**
- Registrar cada problema encontrado como issue de GitHub, que sirve para planificar, discutir y seguir el trabajo, incluidos los errores [94].
- Tras corregir, repetir la prueba con **mvn test** y comprobar el informe de resultados [41].
- Anotar en el issue qué falló, qué se corrigió y el resultado de la repetición.

**Resultado esperado:** incidencias registradas y pruebas repetidas tras cada corrección.

#### 3.3.7. Documentar el procedimiento y los resultados

**Objetivo:** que cualquier miembro del equipo pueda repetir las pruebas y ver qué resultados dieron.

**Qué se hace:**
- Describir el procedimiento seguido: creación del listener, interacción simulada, comprobaciones de notificación, desregistro, estado observable y ejecución en la suite.
- Indicar los comandos para ejecutar las pruebas y dónde están los informes ("target/surefire-reports") [41].
- Recoger los resultados y las incidencias registradas [94].

**Resultado esperado:** procedimiento y resultados documentados en el repositorio.

| Prueba | Resultado |
|---|---|
| Notificación esperada | *Comprobado* |
| Sin notificación tras desregistrar | *Comprobado* |
| Cambio de estado observable | *Comprobado* |
| Ejecución en conjunto con la suite | *Comprobado* |

#### 3.3.8. Documentación

**Objetivo:** dejar el código de pruebas comprensible para quien no ha participado en la tarea.

**Qué se hace:**
- Añadir Javadoc a la clase de test explicando qué componente prueba y cómo se registra y desregistra el listener de prueba [60].
- Comentar brevemente la preparación en `@BeforeEach` y la limpieza en `@AfterEach` [34].
- Enlazar este issue desde la documentación y recoger las decisiones tomadas (por ejemplo, simular con **doClick()** o con AssertJ Swing) [94].

**Resultado esperado:** código de pruebas y documentación coherentes entre sí.

#### 3.3.9. Implementación y Resultados de las Pruebas de Simulación

Se ha completado con éxito la fase de pruebas unitarias para el componente **ComponenteStockScan**. El objetivo principal era validar el patrón Observer implementado, asegurando que la comunicación entre el componente y sus suscriptores funciona de manera fiable y sin efectos secundarios no deseados.

##### 3.3.9.1. Enfoque de las pruebas realizadas

Para garantizar la calidad del componente, se ha diseñado una suite de pruebas automatizadas con **JUnit 5** (integrada en Java 21). Dado que el componente no cuenta con un elemento gráfico nativo para simular clics, se optó por invocar directamente el método de acción público **notificarObservadores**, lo que permite un control total y repetible sobre el escenario de prueba.

Los escenarios clave que han sido validados son los siguientes:

- **Verificación de la notificación:** Se comprobó que, al simular la interacción, el listener registrado recibe exactamente un evento. Además, se validó que el evento transporta el código correcto, el mensaje esperado y que el objeto origen es, efectivamente, la instancia del componente.
- **Gestión del ciclo de vida (Desregistro):** Se verificó que al eliminar un listener mediante el método correspondiente, este deja de recibir notificaciones en futuras interacciones, garantizando que no queden "suscriptores fantasma".
- **Comprobación del estado observable:** Se aseguró que los datos reflejados en el evento emitido coinciden de manera exacta con el estado esperado tras la interacción.
- **Robustez del componente:** Se incluyeron comprobaciones para asegurar que el componente no lanza excepciones ni genera eventos si se intenta notificar cuando no hay ningún listener registrado, o si se intentan registrar listeners nulos o duplicados.

##### 3.3.9.2. Ejecución y Gestión con Maven

La ejecución de las pruebas se ha gestionado a través de **Maven**, cumpliendo con los requisitos de integración en la suite del proyecto:

- **Detección de pruebas:** El plugin **Maven Surefire** se encarga de ejecutar los tests en la fase **test** del ciclo de vida de Maven. Surefire detecta automáticamente la clase de prueba gracias a que su nombre sigue la convención establecida (**ComponenteStockScanTest**), cumpliendo con los patrones **\*Test**, **\*Tests** o **\*TestCase**.
- **Ejecución en conjunto:** Se ha ejecutado el comando **mvn test** desde la raíz del proyecto. Esto ha permitido comprobar que las nuevas pruebas funcionan correctamente junto con el resto de la suite, sin interferir entre sí.
- **Ejecución aislada:** Adicionalmente, se comprobó el resultado aislado de esta clase mediante el comando **mvn -Dtest=ComponenteStockScanTest test**, verificando que el comportamiento es idéntico tanto en aislamiento como en conjunto.
- **Informes generados:** Los resultados detallados de la ejecución han quedado registrados en los ficheros generados automáticamente por Surefire en el directorio **target/surefire-reports**.

##### 3.3.9.3. Resultados obtenidos (Salida de Maven)

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

**Conclusión:**
No se han detectado incidencias ni comportamientos inesperados durante la ejecución. El componente **ComponenteStockScan** demuestra ser robusto y cumple estrictamente con los requisitos de notificación y actualización de estado definidos en la issue. Se da por finalizada y validada la tarea de simulación de interacción.

## 4. Documentación de la API técnica y empaquetado del artefacto

### 4.1. Documentación en código con JavaDoc

Para preparar esta tarea, realicé el curso *Documentación del código con Javadoc* de OpenWebinars, con una duración de 43 minutos. En él aprendí a escribir comentarios de documentación y a generar una guía a partir del código Java. Al terminar, adjunté el certificado a la actividad del sprint [96]–[98].

JavaDoc permite documentar una clase, un constructor o un método mediante un comentario que empieza con `/**` y termina con `*/`. El comentario se coloca justo encima del elemento que describe. Además de explicar para qué sirve, se pueden utilizar etiquetas para aclarar qué información recibe, qué devuelve y en qué situaciones puede fallar [99].

| Etiqueta | Qué explica |
|---|---|
| `@param` | El significado de cada parámetro de un método o constructor. |
| `@return` | El valor que devuelve un método. |
| `@throws` | La excepción que puede producirse y la condición que la provoca. |
| `@author` | La autoría del elemento documentado. |
| `@version` | La versión del elemento documentado. |
| `@since` | La versión desde la que está disponible. |
| `@see` | Una referencia a otra clase, método o recurso relacionado. |

En StockScan documenté la **API pública**, es decir, las clases y los métodos que puede utilizar quien incorpore el componente a su programa. En `ComponenteStockScan` expliqué para qué sirve el panel, cómo se crea mediante su constructor sin argumentos y cómo se registra o se retira un oyente. Un oyente es el objeto que quiere recibir los avisos del componente [100].

También expliqué la relación entre `StockScanEvent` y `StockScanListener`. El primero contiene los datos de la notificación, como el código del artículo y el mensaje. El segundo define el método que recibe el aviso. Por ejemplo, si el componente comunica `ART-02` y `Stock actualizado`, el oyente recibe esos dos datos en el mismo evento [100].

En los métodos de consulta y modificación de propiedades expliqué qué valor se obtiene, qué valores se admiten y qué ocurre cuando un dato es incorrecto. Un caso concreto es el constructor de `StockScanEvent`: si el programa intenta crear un evento con un origen nulo, se produce una `IllegalArgumentException`. Documentar esa condición ayuda a utilizar la clase correctamente [100].
Por último, revisé los comentarios existentes, completé las explicaciones que faltaban y publiqué los cambios en la rama `main` de StockScan SGA. También se comprobó que JavaDoc podía generar la documentación pública con Java 21 sin emitir avisos [100], [101].

### 4.2. Compilación del artefacto reutilizable

El objetivo de esta tarea fue entregar el componente de escaneo en un **archivo JAR** que pudiera añadirse como biblioteca a otro proyecto. Un JAR agrupa en un único archivo las clases Java ya compiladas y los recursos que necesitan. Trabajé con Apache NetBeans, el JDK 21 y Apache Ant 1.10.17. Comprobé la versión de Ant mediante `ant -version` [102]–[106].

**Primera compilación.** Ejecuté **Clean and Build** en NetBeans para limpiar los resultados anteriores y volver a compilar. El proceso terminó con el mensaje `BUILD SUCCESSFUL` y generó `StockScanE.jar` en la carpeta `dist`. Este archivo contiene la aplicación completa. Para entregar únicamente el componente reutilizable preparé un segundo JAR [106].

**Incidencia con Java 21.** Durante las pruebas apareció una advertencia relacionada con las opciones `-source 21` y `-target 21`. Al añadir `--release 21` como argumento adicional, NetBeans seguía enviando las opciones anteriores y la compilación falló. La documentación de `javac` indica que `--release` no se puede combinar con `--source` ni con `--target`. Retiré el argumento adicional y, en la configuración final de Ant, utilicé el atributo `release` para compilar con la versión 21 [106]–[109].

**Generación de la biblioteca.** Añadí manualmente al archivo `build.xml` un objetivo de Ant llamado `jar-componente`. Al ejecutarlo, Ant crea una carpeta temporal, compila las clases necesarias y las empaqueta en `stockscan-componente-1.0.0.jar`. El número `1.0.0` identifica la versión de la biblioteca. El archivo se guarda en `StockScanE/dist/` [108]–[111].

| Elemento incluido en el JAR | Función |
|---|---|
| `BasePanel` | Es la clase base de la que hereda el componente. |
| `ComponenteStockScan` | Permite registrar oyentes y enviarles notificaciones. |
| `StockScanEvent` | Contiene los datos de cada aviso. |
| `StockScanListener` | Define cómo recibe el aviso el programa que utiliza la biblioteca. |

Para generar la biblioteca desde la raíz del repositorio se ejecuta el siguiente comando [109]:

```powershell
ant -f StockScanE/build.xml jar-componente
```

**Prueba realizada.** Primero comprobé qué clases contenía el archivo. Para ello utilicé la opción `t` de la herramienta `jar`, que permite listar el contenido del paquete [102], [106]:

```powershell
jar tf StockScanE/dist/stockscan-componente-1.0.0.jar
```

Después compilé un programa de prueba en una carpeta independiente utilizando el JAR como única dependencia de StockScan. Registré dos veces el mismo oyente y envié un aviso con el código `ART-02` y el mensaje `Stock actualizado`. La consola mostró una sola notificación. A continuación, retiré el oyente y envié otro aviso. Esta vez no apareció ningún mensaje nuevo. Con esta prueba comprobé que la biblioteca funcionaba fuera del proyecto original y que el registro y la retirada de oyentes se comportaban como indicaba su documentación [106].

### 4.3. Generación de la documentación técnica

El objetivo de esta tarea fue convertir los comentarios del apartado 4.1 en una guía que pudiera abrirse desde el navegador. La herramienta `javadoc`, incluida en el JDK, lee los comentarios del código fuente y genera páginas HTML con la descripción de las clases, los constructores y los métodos [112].

**Cómo generé la documentación.** NetBeans ya tenía configurada una tarea para generar JavaDoc. Lo que añadí manualmente en `build.xml` fue un objetivo llamado `javadoc-zip`. Este objetivo ejecuta la generación de JavaDoc, guarda las páginas y sus recursos en una carpeta temporal y después los comprime en `stockscan-javadoc.zip`. El ZIP se guarda en `StockScanE/dist/`. La configuración también establece que un aviso de documentación detenga el proceso para poder revisarlo antes de entregar el archivo [109], [112]–[114].

Para repetir el proceso después de modificar los comentarios del código se ejecuta este comando desde la raíz del repositorio [109]:

```powershell
ant -f StockScanE/build.xml javadoc-zip
```

**Prueba realizada.** La generación terminó sin avisos. Para comprobar la guía tal como la recibiría otro desarrollador, descomprimí el ZIP completo y abrí `index.html` en Chrome.
Desde el índice accedí al paquete del componente de escaneo y comprobé los enlaces entre sus páginas. En `ComponenteStockScan` revisé cómo crear el panel y registrar un oyente. En `StockScanEvent` comprobé la descripción del código del artículo y del mensaje. En `StockScanListener` localicé el método que recibe la notificación [115], [116].

Como resultado, el apartado 4 produjo dos archivos complementarios.
**`stockscan-componente-1.0.0.jar`** permite incorporar el componente a otro proyecto Java. **`stockscan-javadoc.zip`** contiene la guía para aprender a utilizarlo [111], [116].

## 5. Integración y consumo del componente en el proyecto transversal

### 5.1. Vinculación de la librería

Para que el proyecto transversal `StockScanE` pudiera utilizar el componente de escaneo, incorporé el JAR generado en la tarea 4.2 siguiendo estos pasos:

1. Copié `stockscan-componente-1.0.0.jar` en la carpeta `lib` del proyecto.
2. En `nbproject/project.properties`, añadí la ruta relativa
`lib/stockscan-componente-1.0.0.jar` a las bibliotecas de compilación (`javac.classpath`).
Así, NetBeans puede localizar el JAR en cualquier equipo que descargue el proyecto.
3. En `build.xml`, excluí de la compilación de la aplicación las cuatro clases e interfaces que ya contiene el JAR. De esta forma, la aplicación utiliza la biblioteca y evita duplicar esas clases.

**Comprobación.** Compilé el proyecto desde cero y ejecuté la prueba de notificaciones.
Verifiqué que Java cargaba `ComponenteStockScan`, `StockScanEvent` y `StockScanListener` desde el JAR. El programa recibió una única notificación con el código `ART-02` y el mensaje `Stock actualizado`. [Ver el cambio en GitHub](https://github.com/stockscan-team/stockscan-sga/commit/f0987a69d2bf4c6f2bb7864bd9e0188dda7d793f)

### 5.2. Incorporación visual

Este apartado corresponde a la tarea **3.5.2. Incorporación visual — #75** del tablero del sprint. Se presenta como 5.2 para mantener la numeración de esta memoria.

#### 5.2.1. Objetivo y selección de la pantalla

El objetivo de esta tarea fue incorporar el componente reutilizable `ComponenteAccionStockScan` a una pantalla activa de StockScanE y comprobar su integración con la interfaz existente.

Se seleccionó `StockPanel`, la pantalla de inventario. El componente se sitúa entre los filtros de categorías y la tabla de productos, dentro de una franja blanca que incluye una descripción de la acción y el botón **«Confirmar artículo»**.

Esta ubicación permite mostrar el componente dentro del contexto de gestión de productos, manteniendo visibles los filtros y la tabla.

La implementación fue preparada por Álvaro López de San Román y posteriormente revisada por Elena Sáez.

#### 5.2.2. Incorporación al formulario de NetBeans

Se incorporaron los siguientes elementos al formulario:

| Elemento | Responsabilidad |
|---|---|
| `panelAccionStockScan` | Contener la descripción y el componente visual. |
| `lblAccionStockScan` | Explicar que se trata de una acción de ejemplo. |
| `componenteAccionStockScan` | Mostrar el botón y gestionar su interacción y notificación. |

La creación de los elementos se realiza en `initComponents()`. La configuración del componente y el registro del receptor de eventos se completan en `configurarAccionStockScan()`.

Se actualizaron los archivos `StockPanel.java` y `StockPanel.form` para mantener la incorporación dentro de la estructura del formulario de NetBeans.

Los archivos relacionados con esta integración son:

- `src/com/stockscan/view/pages/StockPanel.java`.
- `src/com/stockscan/view/pages/StockPanel.form`.
- `src/com/stockscan/view/components/ComponenteAccionStockScan.java`.

#### 5.2.3. Configuración mediante la API pública

El componente se configura mediante sus métodos públicos, sin acceder directamente al botón interno desde la pantalla.

| Propiedad | Configuración utilizada |
|---|---|
| Texto | `setTexto("Confirmar artículo")`. |
| Tamaño preferido | `setPreferredSize(new Dimension(260, 60))`. |
| Disponibilidad | `setEnabled(true)`. |
| Colores y foco | Valores predeterminados definidos dentro del componente. |

Los métodos `getTexto()` y `setTexto(...)` permiten consultar y modificar el texto. Esta organización sigue la convención de propiedades JavaBeans, basada en métodos de consulta y modificación [18].

El método público `setTexto(...)` actualiza internamente el texto del botón mediante `setText(...)`. Ambos métodos pertenecen a niveles distintos: la pantalla utiliza la API del componente y este gestiona su botón interno.

La apariencia predeterminada fue considerada coherente con el resto de la interfaz durante la revisión visual. No obstante, esta versión no incorpora métodos públicos específicos para configurar los colores del botón o del borde de foco.

Por tanto, se distingue entre la aceptación visual de los valores predeterminados y la posibilidad de personalizar esos valores mediante la API pública.

#### 5.2.4. Distribución y actualización visual

El contenedor utiliza `BorderLayout` para distribuir sus elementos:

- La descripción se coloca en la región `CENTER`.
- El componente se coloca en la región `EAST`.

`BorderLayout` organiza los componentes por regiones y distribuye el espacio disponible teniendo en cuenta sus tamaños preferidos. En particular, la región central ocupa el espacio restante [124].

Después de configurar el componente, se invocan:

- `revalidate()`, para solicitar una nueva validación de la distribución.
- `repaint()`, para solicitar la actualización de la representación visual [1].

Durante la revisión realizada por Elena Sáez se probó la ventana al maximizarla y restaurarla. Según el resultado registrado, el componente mantuvo su alineación y no se observaron deformaciones ni solapamientos con otros elementos de `StockPanel`.

#### 5.2.5. Conservación de la pantalla existente

La incorporación mantiene los botones, los nombres de variables, los iconos, los filtros, los datos de ejemplo y los manejadores de eventos existentes.

La tabla se desplaza hacia abajo para dejar espacio a la nueva franja.

La revisión comunicada por Elena Sáez indica que la pantalla carga correctamente y que la incorporación no interfiere con la disposición de la tabla, los filtros ni el flujo de navegación comprobado.

Estas conclusiones corresponden a las comprobaciones realizadas durante la revisión de esta tarea.

#### 5.2.6. Notificación y alcance de la acción

Se registra una instancia de `FeedbackController` como receptora de las notificaciones del componente.

Cuando se activa el botón, el componente utiliza el código de ejemplo `ART-01`. La descripción visible informa de que se trata de una acción de demostración.

El alcance de esta integración es el siguiente:

- Mostrar el componente dentro de la pantalla de inventario.
- Permitir la interacción con el botón.
- Emitir la notificación y entregarla al receptor registrado.

La acción no está vinculada a la fila seleccionada en la tabla. No confirma un producto seleccionado, no modifica el inventario y no guarda datos en la base de datos.

Esta distinción evita interpretar la demostración del mecanismo de eventos como una operación de negocio completa. Este alcance corresponde a la incorporación visual aquí descrita; la conexión con la lógica de negocio se documenta por separado en el apartado 5.3.

#### 5.2.7. Revisión y resultados registrados

Después de preparar la implementación, se compartió el proyecto con Elena Sáez para realizar la revisión de la interfaz y del comportamiento integrado.

La siguiente tabla resume los resultados comunicados:

| ID | Comprobación | Resultado registrado |
|---|---|---|
| IV-01 | Compilación y apertura de la pantalla. | Compilación correcta y carga de la vista sin problemas. |
| IV-02 | Texto y dimensiones del componente. | Botón «Confirmar artículo» con la configuración prevista. |
| IV-03 | Coherencia de la apariencia. | Apariencia predeterminada considerada coherente con la interfaz. |
| IV-04 | Alineación al maximizar y restaurar. | No se observaron deformaciones ni solapamientos. |
| IV-05 | Conservación del diseño y navegación. | No se comunicaron interferencias en los elementos y recorridos comprobados. |
| IV-06 | Activación del botón. | Revisada la acción de ejemplo identificada mediante `ART-01`. |
| IV-07 | Necesidad de correcciones visuales. | No se comunicaron incidencias visuales que requirieran corrección. |

La revisión finalizó con el visto bueno de Elena Sáez para cerrar la tarea.

Los resultados anteriores proceden de su comentario de revisión. El material aportado para este fragmento no incluye una captura adicional de esa comprobación.

#### 5.2.8. Resultado y alcance de la entrega

El componente queda incorporado a `StockPanel`, dentro de una franja situada entre los filtros y la tabla de productos.

La entrega incluye:

- Incorporación al formulario de NetBeans.
- Configuración del texto, tamaño preferido y disponibilidad.
- Uso de la apariencia predeterminada del componente.
- Registro de un controlador receptor de notificaciones.
- Documentación de los cambios en `LEEME_TAREA_75.md`.
- Revisión de compilación, disposición visual e interacción por otra integrante del equipo.

La revisión valida la integración visual descrita. La personalización de colores mediante métodos públicos y la vinculación de la acción con un producto real no forman parte de lo implementado en esta versión de la tarea.

### 5.3. Conexión con el flujo de datos

#### 5.3.1. Investigación

**Objetivo.** Averiguar cómo se enlazan los eventos de un componente Swing con el controlador y la lógica de negocio sin introducir esa lógica en el componente, y decidir el mecanismo para StockScan SGA.

##### 5.3.1.1. Resumen

- En Swing, un componente es una **fuente de eventos** y el resto de objetos son **oyentes** que se registran en él con **addXListener**. Cuando ocurre algo, la fuente llama al método del oyente y le pasa un objeto evento que describe lo ocurrido [87].
- En una arquitectura MVC, el **controlador** traduce la interacción de la vista en operaciones sobre el modelo, y el **modelo** no conoce ni la vista ni el controlador [117].
Los objetos de dominio deben funcionar sin referencia a la presentación [118].
- El flujo de datos tiene dos sentidos: **vista --> controlador --> modelo** (acción del usuario) y **modelo --> controlador/vista** (el modelo avisa del cambio y la vista se actualiza) [117], [118].
- Los oyentes se ejecutan en el mismo hilo que el dibujado de la interfaz, por lo que deben ser muy rápidos, el trabajo largo se hace en otro hilo [22], [120].
- Para StockScan, el componente ya cumple el primer sentido con su propio **StockScanListener**, la conexión consiste en que el controlador lo implemente, se registre y delegue en el modelo.

##### 5.3.1.2. El flujo de datos en MVC

| Sentido | Quién actúa | Qué ocurre |
|---|---|---|
| Vista --> modelo | Usuario, vista, controlador | Un oyente de la vista llama a un método del controlador, y este actualiza el modelo [117]. |
| Modelo --> vista | Modelo, controlador, vista | El modelo notifica el cambio de una propiedad con **PropertyChangeSupport**. El controlador, registrado como oyente, lo reenvía a las vistas, que actualizan sus componentes [117], [122]. |

Dos observaciones de las fuentes consultadas:
- El controlador actualiza el modelo, no la vista directamente, las vistas observan el modelo [118].
- El mecanismo de observadores es implícito y puede costar seguirlo leyendo el código [118]. Por eso conviene que cada vínculo (quién se registra dónde) esté documentado.

##### 5.3.1.3. Mecanismo que ya existe en el componente

**ComponenteStockScan** implementa el patrón **Observer** [121] con tres piezas:

| Pieza | Función |
|---|---|
| **StockScanListener** | Interfaz con un único método, **alNotificarAccion(StockScanEvent)**. |
| **StockScanEvent** | Evento con el origen (el componente), un **codigo** y un **mensaje**. |
| **ComponenteStockScan** | Guarda los oyentes y ofrece **addStockScanListener**, **removeStockScanListener** y **notificarObservadores(codigo, mensaje)**. |

Comportamiento documentado en su código:
- El registro ignora los oyentes nulos y los duplicados.
- La entrega se hace en orden de registro con un único evento.
- Si no hay oyentes no se crea el evento.
- Si un oyente lanza una excepción, esta se propaga y se interrumpe la entrega a los siguientes.
Es el mismo esquema que describe el tutorial de Oracle (fuente, evento, oyente y manejador, con varios oyentes posibles para una misma fuente) [87], con una interfaz propia en lugar de **ActionListener**.

##### 5.3.1.4. Registrar el listener desde el controlador

Reglas que se deducen de las fuentes y del propio componente:

1. **Quién se registra.** El controlador (o un punto de integración) implementa **StockScanListener**. La vista no contiene lógica y solo aloja el componente [119].
2. **Cuándo.** Al construir la vista, antes de que el componente pueda notificar.
3. **Cuándo se anula.** Al cerrar o sustituir la vista, con **removeStockScanListener**, para evitar notificaciones a objetos obsoletos.
4. **Cuántos oyentes.** Pueden registrarse varios para una misma fuente [87], pero la entrega se interrumpe si uno lanza una excepción, así que cada controlador debe capturar sus propios errores.

##### 5.3.1.5. Evitar lógica de negocio en el componente

Dos fuentes coinciden en el criterio:
- Los objetos de dominio deben ser autónomos y no depender de la presentación [118].
- El patrón **Passive View** extrae todo el comportamiento específico de la aplicación de la vista y lo lleva a un controlador. La vista solo traslada el gesto del usuario [119]. Su ventaja es que el controlador se puede probar sin la interfaz, sustituyendo la vista por un objeto de prueba [119].

Aplicado al proyecto, el componente solo notifica (**notificarObservadores**), no consulta datos, no valida stock ni decide alertas. Comprobación práctica: el componente no importa nada de "controller", "model" ni "java.sql".

##### 5.3.1.6. Alternativas valoradas

| Alternativa | Cómo funciona | Ventajas | Inconvenientes |
|---|---|---|---|
| **Listener propio (StockScanListener)** | El componente notifica un código y un mensaje al controlador | Ya implementado, desacoplado, fácil de probar | El significado del código debe acordarse y documentarse |
| **ActionListener estándar** | El componente dispara **ActionEvent** con un **actionCommand** [87] | Tipo conocido de Swing | Requiere reescribir el componente y su API |
| **Controlador que reenvía al modelo y a las vistas** | Esquema MVC con **PropertyChangeSupport** [117] | Sincroniza varias vistas sin que se conozcan [118] | Comportamiento implícito, más difícil de depurar [118] |
| **Passive View** | La vista solo delega, el controlador actualiza los componentes [119] | Controlador probable sin interfaz | Controlador más grande y con más trabajo de actualización [119] |

**Decisión recomendada:** mantener el listener propio del componente para el sentido vista --> controlador, y usar **PropertyChangeSupport** para el sentido modelo --> vista.
Así se aprovecha lo ya implementado y se respeta la separación MVC + DAO del proyecto.

##### 5.3.1.7. Cómo se comprueba la conexión

- Se prueba el controlador de forma aislada, con un evento creado a mano y un objeto de prueba en lugar del servicio, como propone **Passive View** [119].
- Se prueba la integración: un evento del componente debe llegar al controlador una sola vez, con el código y el mensaje enviados, y dejar de llegar tras anular el registro. Estas comprobaciones pueden automatizarse con JUnit 5 [34].
- Se comprueba por revisión de importaciones que el componente no depende de capas inferiores.

##### 5.3.1.8. Conclusiones

1. El componente ya ofrece el punto de conexión: un oyente propio que entrega un código y un mensaje.
2. El controlador es el único que interpreta ese evento y decide la operación de negocio.
3. Las operaciones lentas (acceso a datos) se ejecutan fuera del hilo de la interfaz y el resultado vuelve a él.
4. El camino inverso (modelo → vista) usa propiedades enlazadas.
5. Quedan por acordar con el equipo los códigos de acción y los nombres de los servicios o controladores que los atienden.

#### 5.3.2. Implementación

##### 5.3.2.1. Registro del listener desde el controlador

**StockController** implementa **StockScanListener** y se registra en el componente con **conectar**, y anula el registro con **desconectar**:

```java
public void conectar(ComponenteAccionStockScan componente) {
    Objects.requireNonNull(
        componente, "El componente no puede ser null."
    );

    if (this.componente != null) {
        throw new IllegalStateException(
            "El controlador ya está conectado a un componente."
        );
    }

    this.componente = componente;
    componente.addStockScanListener(this);
}

public void desconectar() {
    if (componente != null) {
        componente.removeStockScanListener(this);
        componente = null;
    }
}
```

El registro lo hace el controlador y no la vista, de acuerdo con **Passive View** [119]. Se rechaza conectar dos componentes al mismo controlador para que el vínculo sea único y fácil de seguir [118].

##### 5.3.2.2. Traducción del evento a la operación de negocio

El controlador solo atiende eventos de su componente con el código **ART-01**. Ignora los demás orígenes y códigos:

```java
@Override
public void alNotificarAccion(StockScanEvent evento) {
    if (componente != null
        && evento.getSource() == componente
        && ACCION_CONFIRMAR_ARTICULO.equals(evento.getCodigo())) {
        confirmarArticulo(componente);
    }
}
```

La operación se ejecuta con **SwingWorker** porque el acceso a datos puede ser lento y los oyentes se ejecutan en el hilo de eventos [22], [120]. **doInBackground()** se ejecuta en un hilo de trabajo y **done()** en el hilo de eventos cuando termina, por lo que solo **done()** toca la interfaz [123]:

```java
private void confirmarArticulo(ComponenteAccionStockScan origen) {
    origen.setEnabled(false);

    new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            servicio.confirmarArticulo();
            return null;
        }

        @Override
        protected void done() {
            origen.setEnabled(true);
            avisador.accept(obtenerResultado(this));
        }
    }.execute();
}
```

El botón se deshabilita mientras dura la operación para evitar confirmaciones duplicadas, y se vuelve a habilitar al terminar, tanto si hay éxito como si hay error. Si el servicio lanza una excepción, **get()** la entrega envuelta en una **ExecutionException**, y el controlador la convierte en un mensaje de error sin propagarla a otros oyentes [123].

##### 5.3.2.3. Respuesta en la vista

El controlador recibe en su constructor un **Consumer\<String\>** que la vista le proporciona para mostrar el resultado, de modo que el controlador no depende de ninguna etiqueta o ventana concreta [119]. Ejemplo de integración en una pantalla:

```java
ComponenteAccionStockScan botonConfirmar = new ComponenteAccionStockScan();
JLabel lblResultado = new JLabel(" ");

StockController controlador = new StockController(
    servicioStock,      // implementación real de ServicioStock
    lblResultado::setText
);
controlador.conectar(botonConfirmar);
```

Al cerrar la pantalla debe llamarse a **controlador.desconectar()** [87].

##### 5.3.2.4. Sin lógica de negocio en el componente

**ComponenteAccionStockScan** solo notifica su **codigoAccion** y su **texto**, no llama a servicios ni a DAO. La revisión de importaciones de "view/components" no encuentra ninguna referencia a "controller", "model" ni "java.sql". El modelo tampoco conoce la vista: **ServicioStock** no importa nada de Swing ni de **view**, como exige MVC [117].

##### 5.3.2.5. Vínculo entre evento y lógica de negocio

Tabla del vínculo implementado, para que el mecanismo implícito de observadores quede documentado [118]:

| Componente | Código | Oyente | Operación de negocio | Respuesta en la vista |
|---|---|---|---|---|
| **ComponenteAccionStockScan** ("Confirmar artículo") | **ART-01** | **StockController** | **ServicioStock.confirmarArticulo()** | "Artículo confirmado." o "No se pudo confirmar el artículo: …" |
| Botones de las pantallas con **FeedbackStockScan** | "NombrePanel.btnX" | "FeedbackController" | Ninguna, solo escribe la notificación en la consola | Ninguna |

Los códigos de los botones de navegación y filtros solo llegan a **FeedbackController**, porque **MainController** todavía es una clase reservada sin navegación implementada.

##### 5.3.2.6. Pruebas del recorrido completo

La clase **PruebaFlujoConfirmacion** pulsa el botón real del componente y recorre controlador, servicio de prueba y respuesta, sustituyendo el acceso a datos por un objeto de prueba como propone **Passive View** [119]. Resultado obtenido:

| Prueba | Resultado |
|---|---|
| Botón deshabilitado mientras se procesa | PASS |
| La respuesta vuelve a la vista | PASS |
| Servicio llamado una vez fuera del hilo de eventos | PASS |
| Mensaje de éxito y botón habilitado de nuevo | PASS |
| Error del servicio mostrado y botón recuperado | PASS |
| Código desconocido ignorado por el controlador | PASS |
| Tras desconectar no se llama al servicio | PASS |

Para la comprobación visual, **DemoFlujoConfirmacion** abre una ventana con el botón y una etiqueta de resultado, usando un servicio simulado que tarda un segundo.

##### 5.3.2.7. Compilación y regresión

El proyecto completo ("src" y "test") compila con 0 errores en Java 21, la versión configurada en NetBeans. Pruebas de regresión ejecutadas tras los cambios:

| Clase de prueba | Resultado |
|---|---|
| **PruebaEncapsulacion** | Todas en PASS |
| **PruebaBasePanel** | Todas en PASS |
| **PruebaFeedbackIntegrado** | Todas en PASS |
| **PruebaNotificacion** | Una única notificación **ART-02**, como se esperaba |
| **PruebaConstructores** | Todas en PASS |
| **PruebaAccesores** | Todas en PASS |
| **PruebaFlujoConfirmacion** | Todas en PASS |

Las pruebas son clases con "main", podrían migrarse a JUnit 5 para ejecutarlas automáticamente desde NetBeans [34].

##### 5.3.2.8. Conclusiones

1. El componente ofrece el punto de conexión: un oyente propio que entrega un código y un mensaje.
2. **StockController** es el único que interpreta ese evento y decide la operación de negocio [117], [119].
3. La operación lenta se ejecuta fuera del hilo de la interfaz y el resultado vuelve a él mediante **done()** [120], [123].
4. El camino inverso (modelo --> vista) usará propiedades enlazadas cuando el modelo esté implementado [117], [122].
5. Queda por validar con el equipo la implementación de **ServicioStock** y la lista de códigos de acción.

## 6. Balance y revisión final

La documentación recoge la conexión entre interacción, notificación y respuesta visual; el procedimiento de pruebas; la preparación del JAR y de Javadoc; y la vinculación de la biblioteca al proyecto transversal.

| Bloque | Contenido disponible | Revisión pendiente |
|---|---|---|
| 1 | Posición reservada. | Incorporar la documentación del equipo. |
| 2 | Implementación, capturas, grabaciones y pruebas de notificación. | Contrastar el catálogo por pantalla y completar FB-01 a FB-11. |
| 3 | Procedimientos y registro de tres pruebas sin fallos. | Corregir la correspondencia de citas y asociar los resultados a la versión probada. |
| 4 | Documentación de API, JAR, Javadoc y enlaces a entregables. | Comprobar la accesibilidad de los entregables en la revisión de entrega. |
| 5 | Vinculación del JAR documentada. | Incorporar los demás subapartados que correspondan al alcance del sprint. |

### 6.1. Lista de comprobación para la entrega

- [ ] Incorporar el apartado 1.
- [ ] Completar la fecha de cierre acordada por el equipo.
- [ ] Identificar la versión o commit final al que corresponde la memoria.
- [ ] Revisar las referencias y su correspondencia con las citas, especialmente en 3.
- [ ] Completar las pruebas pendientes sin sustituir los resultados esperados por resultados obtenidos.
- [ ] Comprobar que las imágenes y grabaciones se visualizan en GitHub.
- [ ] Revisar los enlaces al código, JAR y documentación Javadoc.
- [ ] Incorporar las aportaciones restantes de integración, si forman parte de la entrega.
- [ ] Realizar la revisión conjunta del equipo antes de cerrar la memoria.

## 7. Referencias

[1] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 11 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JComponent.html

[2] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 11 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JPanel.html

[3] «Diseño de una interfaz gráfica de usuario Swing en el IDE NetBeans». Accedido: 11 de noviembre de 2026. [En línea]. Disponible en: https://netbeans.apache.org/tutorial/main/kb/docs/java/quickstart-gui/

[4] «Herencia (Tutoriales de Java™ > Aprendiendo el lenguaje Java > Interfaces y herencia)». Accedido: 11 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html

[5] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 11 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JFrame.html

[6] «Controlling Access to Members of a Class (The Java™ Tutorials > Learning the Java Language > Classes and Objects)». Accedido: 18 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html

[7] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 19 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Objects.html

[8] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 18 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html

[9] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 18 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Component.html

[10] «Color (Java SE 21 & JDK 21)». Accedido: 27 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Color.html

[11] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 27 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/IdentityHashMap.html

[12] «Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification». Accedido: 27 de noviembre de 2026. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/Container.html

[13] Oracle, “Writing JavaBeans Components,” *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/javabeans/writing/index.html. [Consulta: 26 de noviembre de 2026].

[14] Oracle, “Initial Threads,” *The Java Tutorials — Concurrency in Swing*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html. [Consulta: 26 de noviembre de 2026].

[15] Oracle, “JavaBeans Component Design Conventions,” *The Java EE 5 Tutorial*. [En línea]. Disponible en: https://docs.oracle.com/cd/E19316-01/819-3669/bnais/index.html. [Consulta: 27 de noviembre de 2026].

[16] Oracle, “Using the this Keyword,” *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html. [Consulta: 27 de noviembre de 2026].

[17] Oracle, “How to Write Doc Comments for the Javadoc Tool.” [En línea]. Disponible en: https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html. [Consulta: 27 de noviembre de 2026].

[18] Oracle, “Properties,” *The Java Tutorials — Writing JavaBeans Components*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/javabeans/writing/properties.html. [Consulta: 27 de noviembre de 2026].

[19] Oracle, “Solving Common Layout Problems,” *The Java Tutorials — Laying Out Components Within a Container*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/layout/problems.html. [Consulta: 27 de noviembre de 2026].

[20] A. Fowler, “Painting in AWT and Swing,” *Oracle Technical Resources*. [En línea]. Disponible en: https://www.oracle.com/java/technologies/painting.html. [Consulta: 27 de noviembre de 2026].

[21] Oracle, *"Writing Event Listeners,"* The Java™ Tutorials. [En línea]. Disponible: https://docs.oracle.com/javase/tutorial/uiswing/events/index.html

[22] Oracle, *"General Information about Writing Event Listeners,"* The Java™ Tutorials. [En línea]. Disponible: https://docs.oracle.com/javase/tutorial/uiswing/events/generalrules.html

[23] Oracle, *"Class MouseAdapter (Java SE 21 & JDK 21),"* Java™ Platform, Standard Edition 21 API Specification. [En línea]. Disponible: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/MouseAdapter.html

[24] Oracle, *"Class FocusAdapter (Java SE 21 & JDK 21),"* Java™ Platform, Standard Edition 21 API Specification. [En línea]. Disponible: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/FocusAdapter.html

[25] Oracle, *"Class KeyAdapter (Java SE 21 & JDK 21),"* Java™ Platform, Standard Edition 21 API Specification. [En línea]. Disponible: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/event/KeyAdapter.html

[26] Oracle Corporation, "Package java.util — Class EventObject and Interface EventListener," *Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventObject.html y https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventListener.html

[27] Oracle Corporation, "Class ArrayList\<E\>," *Java® Platform, Standard Edition & Java Development Kit Version 21 API Specification* (Paquete "java.util"). [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html

[28] Sun Microsystems / Oracle Corporation, *JavaBeans™ API Specification*, Versión 1.01-A, Cap. 6 ("Events"), pp. 26–38, 1997. [En línea]. Disponible en: https://download.oracle.com/otndocs/jcp/7224-javabeans-1.01-fr-spec-oth-JSpec/

[29] Ministerio de Educación y Formación Profesional, "Real Decreto 405/2023, de 29 de mayo, por el que se actualizan los títulos de la formación profesional del sistema educativo de Técnico Superior en Desarrollo de Aplicaciones Multiplataforma," *Boletín Oficial del Estado (BOE)*, núm. 132, pp. 78436–78506, 3 de junio de 2023 (Módulo Profesional 0488: Desarrollo de Interfaces, RA 3, Criterio de Evaluación d). [En línea]. Disponible en: https://www.boe.es/eli/es/rd/2023/05/29/405

[30] Oracle Corporation, “ButtonModel,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/ButtonModel.html. [Consulta: 25 de noviembre de 2026].

[31] Oracle Corporation, “EventObject,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/EventObject.html. [Consulta: 25 de noviembre de 2026].

[32] Oracle Corporation, “How to Use Key Bindings,” *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html. [Consulta: 25 de noviembre de 2026].

[33] "Difference between JUnit 4 and JUnit 5," Educative. [Online]. Available: https://www.devpath.com/courses/unit-testing-java-junit5/difference-between-junit-4-and-junit-5

[34] JUnit Team, "JUnit 5 User Guide." [Online]. Available: https://junit.org/junit5/docs/current/user-guide/

[35] nipafx, "JUnit 5 Setup in IntelliJ, Eclipse, Maven, and Gradle." [Online]. Available: https://nipafx.dev/junit-5-setup/

[36] AssertJ, "assertj/assertj-swing: Fluent interface for functional testing of Swing user interfaces," GitHub. [Online]. Available: https://www.github.com/assertj/assertj-swing (accessed Nov. 14, 2026).

[37] Apache Software Foundation, "Introduction to the Standard Directory Layout," Apache Maven. [Online]. Available: https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html

[38] Apache Software Foundation, "Introduction to the Dependency Mechanism," Apache Maven. [Online]. Available: https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html (accessed Nov. 14, 2026).

[39] "JUnit 5 + Maven examples," Mkyong. [Online]. Available: https://mkyong.com/?p=15175

[40] AssertJ Swing, "Assertj Swing quick start," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-quick-start/ (accessed Nov. 14, 2026).

[41] Apache Software Foundation, "Maven Surefire Plugin," Apache Maven. [Online]. Available: https://maven.apache.org/surefire/maven-surefire-plugin/ (accessed Nov. 14, 2026).

[42] Apache NetBeans, "Releases: apache/netbeans," GitHub. [Online]. Available: https://github.com/apache/netbeans/releases

[43] P. Esselbach, "Apache NetBeans 32 RC3 Released: Final Candidate Targets November GA Launch," LinuxCompatible, Nov. 4, 2026. [Online]. Available: https://www.linuxcompatible.org/story/apache-netbeans-32-rc3-released-final-candidate-targets-november-ga-launch

[44] Apache NetBeans, "Using NbModuleSuite & friends (JUnit Version)," Apache NetBeans Wiki. [Online]. Available: https://netbeans.apache.org/wiki/main/netbeanstestfaq/DevFaqUsingSimpletests/

[45] JUnit Team, "IDE Support," JUnit 5 User Guide. [Online]. Available: https://docs.junit.org/5.14.2/running-tests/ide-support

[46] Apache NetBeans, "Apache NetBeans 10.0 New and Noteworthy," Apache Software Foundation. [Online]. Available: https://cwiki.apache.org/confluence/display/NETBEANS/Apache+NetBeans+10.0+New+and+Noteworthy

[47] J. McDonnell, "[NETBEANS-6] Adding Support for JUnit 5 #892," GitHub, apache/netbeans. [Online]. Available: https://github.com/apache/netbeans/pull/892

[48] "Migrating from JUnit 4 to JUnit 5," Baeldung. [Online]. Available: https://www.baeldung.com/junit-5-migration

[49] nipafx, "JUnit 5 Basics: @Test, Lifecycle, Assertions, Assumptions, And More." [Online]. Available: https://nipafx.dev/junit-5-basics/

[50] "JUnit 4 vs JUnit 5: Key Differences and When to Use Each," Leyaa. [Online]. Available: https://leyaa.ai/codefly/learn/junit/qna/junit-4-vs-junit-5-difference

[51] "Support for JUnit 5 #259," GitHub, assertj/assertj-swing. [Online]. Available: https://github.com/assertj/assertj-swing/issues/259

[52] Oracle, "Using Top-Level Containers," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html (accessed Nov. 14, 2026).

[53] AssertJ Swing, "Assertj Swing basics," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-basics/ (accessed Nov. 14, 2026).

[54] AssertJ Swing, "Assertj Swing lookup," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-lookup/ (accessed Nov. 14, 2026).

[55] AssertJ Swing, "Assertj Swing EDT," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-edt/ (accessed Nov. 14, 2026).

[56] AssertJ Swing, "FailOnThreadViolationRepaintManager," AssertJ Swing 2.0.1 API. [Online]. Available: https://javadoc.io/static/org.assertj/assertj-swing/2.0.1/org/assertj/swing/edt/FailOnThreadViolationRepaintManager.html (accessed Nov. 14, 2026).

[57] Oracle, "Using Headless Mode in the Java SE Platform," Oracle Technical Resources. [Online]. Available: https://www.oracle.com/technical-resources/articles/javase/headless.html (accessed Nov. 14, 2026).

[58] X.Org Foundation, "Xvfb: virtual framebuffer X server for X Version 11," X.Org documentation. [Online]. Available: https://www.x.org/X11R6.8.2/doc/Xvfb.1.html (accessed Nov. 14, 2026).

[59] AssertJ Swing, "Assertj Swing running," AssertJ Swing Documentation. [Online]. Available: https://assertj-swing.readthedocs.io/en/latest/assertj-swing-running/ (accessed Nov. 14, 2026).

[60] Oracle, "Javadoc FAQ," Oracle Java Technologies. [Online]. Available: https://www.oracle.com/java/technologies/javase/javadoc-faq.html (accessed Nov. 14, 2026).

[61] "Testing Swing elements in a headless environment (GitLab CI)," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/58963414/testing-swing-elements-in-a-headless-environment-gitlab-ci (accessed Nov. 14, 2026).

[62] "Swing UI tests on GitHub Actions," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/70758763/swing-ui-tests-on-github-actions (accessed Nov. 14, 2026).

[63] AssertJ Swing, "WaitTimedOutError," AssertJ Swing 1.2.0 API. [Online]. Available: https://www.javadoc.io/static/org.assertj/assertj-swing/1.2.0/org/assertj/swing/exception/WaitTimedOutError.html (accessed Nov. 14, 2026).

[64] AssertJ Swing, "ComponentLookupException," AssertJ Swing 1.2.0 API. [Online]. Available: https://www.javadoc.io/static/org.assertj/assertj-swing/1.2.0/org/assertj/swing/exception/ComponentLookupException.html (accessed Nov. 14, 2026).

[65] GitHub, "Understanding GitHub Actions," GitHub Docs. [Online]. Available: https://docs.github.com/en/actions/get-started/understand-github-actions (accessed Nov. 14, 2026).

[66] GitHub, "About pull requests," GitHub Docs. [Online]. Available: https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests (accessed Nov. 14, 2026).

[67] Apache NetBeans, "Apache NetBeans (incubating) 11.0 Features." [Online]. Available: https://netbeans.apache.org/download/nb110/index.html

[68] "Apache Maven vs Gradle," StackShare. [Online]. Available: https://stackshare.io/gradle/vs/maven

[69] R. Singh, "Gradle vs Maven: Differences, Performance, and Which Build Tool Should You Choose?," upGrad. [Online]. Available: https://www.upgrad.com/blog/gradle-vs-maven/

[70] "Java Netbeans: java with maven vs java with gradle," Feb. 18, 2026. [Online]. Available: https://dedetoknotes.blogspot.com/2026/02/java-netbeans-java-with-maven-vs-java.html

[71] K. Brzezinski, "Maven vs Gradle," DEV Community. [Online]. Available: https://dev.to/kuba_brzezinski_2d3356a2b/maven-vs-gradle-536b

[72] Gradle, "Maven and Gradle," Gradle Inc. [Online]. Available: https://gradle.org/maven-and-gradle

[73] "Changing an Ant project to Maven," Stack Overflow. [Online]. Available: https://kiwix.lema.org/stackoverflow.com_en_all_2023-11/questions/20289118/changing-an-ant-project-to-maven

[74] "Non-standard sourceDirectory and testSourceDirectory in Apache Commons POMs," Apache Commons Developers mailing list. [Online]. Available: https://www.mail-archive.com/dev@commons.apache.org/msg25313.html

[75] G. Ingham, "Convert Ant based NetBeans project to a Maven project," IDR Solutions. [Online]. Available: https://blog.idrsolutions.com/convert-ant-based-netbeans-project-to-a-maven-project/

[76] "How do I add local jar files (not yet part of the Maven repository) to a Maven project?," Stack Overflow. [Online]. Available: https://kaweah.freedombox.rocks/kiwix/content/stackoverflow.com_en_all_2023-11/a/31023523

[77] "How do I configure Maven Surefire for JUnit tests?," Kodejava. [Online]. Available: https://kodejava.org/?p=18456

[78] "JUnit 5 Maven dependency pom.xml example," HowToDoInJava. [Online]. Available: https://howtodoinjava.com/junit-5/junit-5-maven-dependency-pom-xml-example/

[79] Enterprise Craftsmanship, "Exposing private state to enable unit testing." [Online]. Available: https://enterprisecraftsmanship.com/posts/exposing-private-state-to-enable-unit-testing/

[80] C. Austin, "Don't write test-only methods," 2012. [Online]. Available: https://chadaustin.me/2012/12/dont-write-test-only-methods/

[81] Oracle, "Specific Debug Tips for Swing," Java Platform, Standard Edition Troubleshooting Guide. [Online]. Available: https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/swing002.html

[82] Mkyong, "Maven – JaCoCo code coverage example." [Online]. Available: https://mkyong.com/?p=14796

[83] ismail2ov, "mutation-testing-with-maven," GitHub. [Online]. Available: https://github.com/ismail2ov/mutation-testing-with-maven

[84] M. Seemann, "Test trivial code," ploeh blog, Mar. 2013. [Online]. Available: https://blog.ploeh.dk/2013/03/08/test-trivial-code/

[85] "Should you test trivial code?," TechBeacon. [Online]. Available: https://techbeacon.com/should-you-test-trivial-code

[86] "Unable to see code coverage in multi-module Maven project with JaCoCo 0.8.10," SonarSource Community. [Online]. Available: https://community.sonarsource.com/t/unable-to-see-code-coverage-in-multi-module-maven-project-with-jacoco-0-8-10/178981

[87] Oracle, "Introduction to Event Listeners," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html (accessed Nov. 19, 2026).

[88] Oracle, "Writing Bean Events," The Java Tutorials: JavaBeans. [Online]. Available: https://docs.oracle.com/javase/tutorial/javabeans/writing/events.html (accessed Nov. 19, 2026).

[89] Oracle, "AbstractButton," Java Platform, Standard Edition 8 API Specification. [Online]. Available: https://docs.oracle.com/javase/8/docs/api/javax/swing/AbstractButton.html (accessed Nov. 19, 2026).

[90] Oracle, "How to Write an Action Listener," The Java Tutorials: Creating a GUI With Swing. [Online]. Available: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html (accessed Nov. 19, 2026).

[91] "JUnit Assertions: assertEquals, assertAll and More (JUnit 5)," HowToDoInJava. [Online]. Available: https://howtodoinjava.com/junit5/junit-5-assertions-examples/ (accessed Nov. 19, 2026).

[92] Apache Software Foundation, "Running a Single Test," Apache Maven Surefire Plugin. [Online]. Available: https://maven.apache.org/plugins/maven-surefire-plugin/examples/single-test.html (accessed Nov. 19, 2026).

[93] Apache Software Foundation, "Inclusions and Exclusions of Tests," Apache Maven Surefire Plugin. [Online]. Available: https://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html (accessed Nov. 19, 2026).

[94] GitHub, "About issues," GitHub Docs. [Online]. Available: https://docs.github.com/issues/tracking-your-work-with-issues/about-issues (accessed Nov. 19, 2026).

[95] StockScan Team, “3.4 Documentación de la API técnica y empaquetado del artefacto,” actividad n.º 59, GitHub, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-dii/issues/59.

[96] OpenWebinars, “Documentación del código con Javadoc,” curso en línea. Disponible en: https://openwebinars.net/cursos/documentacion-codigo-javadoc/.

[97] OpenWebinars, *Formaciones Becas Despega*, catálogo de cursos, entrada “Documentación del código con Javadoc”, 43 min. [En línea]. Disponible en: https://cdn.openwebinars.net/recursos/FORMACIONES-BECAS-DESPEGA.pdf.

[98] OpenWebinars, “Certificado de Documentación del código con Javadoc,” certificado de Ángel Luis Sánchez Pérez, 2026. [En línea]. Disponible en: https://github.com/user-attachments/files/34800580/certificado_documentacion_del_codigo_con_javadoc.pdf.

[99] Oracle, “Documentation Comment Specification for the Standard Doclet (JDK 21),” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/specs/javadoc/doc-comment-spec.html.

[100] StockScan Team, “3.4.1 Documentación en código (Javadoc),” actividad n.º 71, con ejemplo de comentario, certificado y revisión del componente, GitHub, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-dii/issues/71.

[101] Á. L. Sánchez Pérez, “Actualizar la documentación JavaDoc,” *StockScan SGA*, commit `57fdf01`, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-sga/commit/57fdf016ed779e2904b1826221a1236199a8ddf3.

[102] Oracle, “The jar Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/specs/man/jar.html.

[103] Oracle, “Oracle Java SE Support Roadmap.” [En línea]. Disponible en: https://www.oracle.com/java/technologies/java-se-support-roadmap.html.

[104] Apache Software Foundation, *Apache Ant User Manual*. [En línea]. Disponible en: https://ant.apache.org/manual/.

[105] Apache Software Foundation, “Apache Ant 1.10.17 Released,” *Apache Ant Project News*, 2026. [En línea]. Disponible en: https://ant.apache.org/antnews.html.

[106] StockScan Team, “3.4.2 Compilación del artefacto reutilizable,” actividad n.º 72, con las capturas de NetBeans y los resultados de las pruebas, GitHub, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-dii/issues/72.

[107] Oracle, “The javac Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/specs/man/javac.html.

[108] Apache Software Foundation, “Javac Task,” *Apache Ant User Manual*. [En línea]. Disponible en: https://ant.apache.org/manual/Tasks/javac.html.

[109] StockScan Team, “Configuración de compilación y empaquetado de StockScanE,” archivo `build.xml`, *StockScan SGA*, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/build.xml.

[110] Apache Software Foundation, “Jar Task,” *Apache Ant User Manual*. [En línea]. Disponible en: https://ant.apache.org/manual/Tasks/jar.html.

[111] StockScan Team, “Componente de escaneo de StockScan,” biblioteca Java, versión 1.0.0, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/dist/stockscan-componente-1.0.0.jar.

[112] Oracle, “The javadoc Command,” *Java Platform, Standard Edition 21*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/specs/man/javadoc.html.

[113] Apache Software Foundation, “Javadoc Task,” *Apache Ant User Manual*. [En línea]. Disponible en: https://ant.apache.org/manual/Tasks/javadoc.html.

[114] Apache Software Foundation, “Zip Task,” *Apache Ant User Manual*. [En línea]. Disponible en: https://ant.apache.org/manual/Tasks/zip.html.

[115] StockScan Team, “3.4.3 Generación de la documentación técnica,” actividad n.º 73, con el procedimiento y las comprobaciones realizadas, GitHub, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-dii/issues/73.

[116] StockScan Team, “Documentación JavaDoc de StockScan SGA,” sitio HTML distribuido en `stockscan-javadoc.zip`, 2026. [En línea]. Disponible en: https://github.com/stockscan-team/stockscan-sga/blob/41764e5/StockScanE/dist/stockscan-javadoc.zip.

[117] Oracle, «Java SE Application Design With MVC», *Oracle Technical Resources*. [En línea]. Disponible en: https://www.oracle.com/technical-resources/articles/javase/application-design-with-mvc.html

[118] M. Fowler, «GUI Architectures», *martinfowler.com*, 18 jul. 2006. [En línea]. Disponible en: https://www.martinfowler.com/eaaDev/uiArchs.html

[119] M. Fowler, «Passive View», *martinfowler.com*. [En línea]. Disponible en: https://www.martinfowler.com/eaaDev/PassiveScreen.html

[120] Oracle, «Worker Threads and SwingWorker», *The Java Tutorials*. [En línea]. Disponible en: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/worker.html

[121] E. Gamma, R. Helm, R. Johnson y J. Vlissides, *Design Patterns: Elements of Reusable Object-Oriented Software*. Reading, MA, EE. UU.: Addison-Wesley, 1994.

[122] Oracle, «PropertyChangeSupport (Java SE 21)», *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/beans/PropertyChangeSupport.html

[123] Oracle, «SwingWorker (Java SE 21)», *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/SwingWorker.html

[124] Oracle Corporation, “BorderLayout,” *Java SE 21 API Specification*. [En línea]. Disponible en: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/java/awt/BorderLayout.html. [Consulta registrada en el fragmento original: 26 de noviembre de 2026].
