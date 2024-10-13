# Simulación Interactiva del Tablero de Galton hecho por Daniel Andrés Moreno Rey y Rodrigo Rojas Redondo

https://github.com/campana-gauss

https://github.com/campana-gauss/graficoCampanaDeGauss.git

Este proyecto es una **simulación visual del Tablero de Galton**, también conocido como la "Máquina de la Distribución Normal". A través de este tablero, se puede ver cómo se forma una **distribución normal** (o curva de campana) cuando muchas bolas caen a través de una serie de clavos y aterrizan en contenedores en la parte inferior.

### ¿Qué es el Tablero de Galton?

El **Tablero de Galton** es un dispositivo inventado por Sir Francis Galton que muestra cómo una distribución aleatoria de eventos puede formar una **curva de campana**, también conocida como **distribución normal**. Al dejar caer bolas desde la parte superior del tablero, estas rebotan en clavos colocados en varias filas y, a través de una serie de decisiones aleatorias (ir hacia la izquierda o derecha), las bolas finalmente caen en contenedores en la parte inferior. A medida que más bolas caen, comienzan a acumularse en los contenedores formando una distribución normal.

### ¿Cómo funciona la simulación?

1. **Caída de bolas**: Las bolas comienzan a caer desde la parte superior del tablero y rebotan aleatoriamente a la izquierda o derecha a medida que atraviesan las filas de clavos.
   
2. **Rebotes y acumulación**: A medida que las bolas descienden, se acumulan en los contenedores que están en la parte inferior del tablero. Cuanto más bolas caen, más notable es la **distribución en forma de campana** en los contenedores.

3. **Concurrencia y Hilos**: El backend está diseñado para manejar la simulación de forma eficiente utilizando **hilos de ejecución**. Cada componente del proceso de simulación, como la producción de las bolas y el procesamiento de su trayectoria, es manejado por **hilos independientes**. Esto permite que el proceso sea **concurrente**, es decir, que varios hilos trabajen simultáneamente para manejar múltiples bolas que caen al mismo tiempo.

4. **Actualización en tiempo real**: A través de una tecnología llamada **Server-Sent Events (SSE)**, el backend envía actualizaciones en tiempo real al navegador. Esto significa que a medida que las bolas caen y se acumulan en los contenedores, el tablero se actualiza de forma dinámica sin necesidad de refrescar la página.

### ¿Cómo lo hicimos?

#### Backend

El proyecto está basado en **Java** con el framework **Spring Boot**. El backend maneja toda la lógica de la simulación, incluyendo la creación de las bolas, la asignación aleatoria de su trayectoria y el cálculo de su acumulación en los contenedores. Los **hilos de ejecución** son una parte fundamental, ya que permiten que varias bolas caigan al mismo tiempo, simulando el comportamiento real de un tablero de Galton.

El backend también es responsable de enviar las actualizaciones al navegador mediante SSE (Server-Sent Events), lo que asegura que las bolas se vean caer en tiempo real.

#### Frontend

El **frontend** utiliza **D3.js**, una biblioteca de JavaScript muy potente para crear gráficos interactivos. A través de D3.js, visualizamos el tablero de Galton y mostramos el camino que siguen las bolas a medida que rebotan en los clavos y aterrizan en los contenedores.

El diseño es **interactivo y dinámico**, lo que significa que puedes ver cómo se desarrolla la simulación en tiempo real directamente en tu navegador.

### ¿Qué puedes ver?

Cuando inicias la simulación, verás:

- Un tablero en el centro de la pantalla con varias filas de clavos.
- Bolas rojas que caen desde la parte superior, rebotando en los clavos a la izquierda y derecha, y acumulándose en los contenedores inferiores.
- A medida que más bolas caen, verás cómo la acumulación de las bolas en los contenedores comienza a parecerse a una **distribución normal** (curva de campana).

### ¿Por qué usamos hilos y concurrencia?

El uso de **hilos de ejecución** es clave para simular de manera realista el comportamiento de la caída de las bolas. Cada bola es manejada como una tarea independiente que se ejecuta en un hilo separado. Esto significa que el programa puede **procesar múltiples bolas a la vez**, haciendo que la simulación sea mucho más eficiente.

Sin los hilos, el programa solo podría manejar una bola a la vez, lo cual haría la simulación más lenta y menos realista. Con la concurrencia, cada bola sigue su propio camino y cae a su propio ritmo, permitiendo que la simulación ocurra de manera **paralela**, como en el mundo real.

### ¿Cómo puedes probarlo?

1. **Ejecutar el backend**: 
   - Inicia el backend en Spring Boot utilizando el siguiente comando:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Esto iniciará el servidor en `http://localhost:8080`.

2. **Acceder al frontend**:
   - Abre tu navegador web y visita la página `http://localhost:8080/grafica.html`. Aquí verás la simulación en acción.

### Conclusión

Este proyecto ilustra cómo el **Tablero de Galton** es una poderosa herramienta visual para comprender la **distribución normal**. Mediante el uso de **hilos**, **concurrencia** y la visualización en tiempo real con **D3.js**, hemos creado una simulación interactiva que muestra cómo, a partir de eventos aleatorios, se puede formar un patrón matemáticamente predecible.

Esperamos que disfrutes experimentando con esta simulación tanto como nosotros disfrutamos desarrollándola. 😊

