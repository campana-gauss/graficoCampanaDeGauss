// Parámetros del tablero de Galton
const numRows = 15;  // Número de filas de clavos
const numCols = 15;  // Número de contenedores en la parte inferior

// Crear SVG para el tablero de Galton más grande
const margin = {top: 50, right: 30, bottom: 30, left: 30};
const width = 800 - margin.left - margin.right;  // Ancho del tablero
const height = 800 - margin.top - margin.bottom;  // Altura del tablero
const contenedorAltura = 40;  // Altura de los contenedores
const radioBola = 5;  // Tamaño de cada bola

// Mantener el número de bolas acumuladas en cada contenedor
const contenedorBolas = Array(numCols).fill(0);  // Inicialmente, todos los contenedores están vacíos

const svg = d3.select("#my_dataviz")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// Crear clavos en el tablero
function crearClavos() {
    for (let row = 0; row < numRows; row++) {
        const y = (height / numRows) * row;
        for (let col = 0; col <= row; col++) {
            const x = (width / (numRows + 1)) * (col + (numRows - row) / 2);
            svg.append("circle")
                .attr("cx", x)
                .attr("cy", y)
                .attr("r", 5)
                .style("fill", "#333");
        }
    }
}

// Crear los contenedores en la parte inferior del tablero
function crearContenedores() {
    for (let i = 0; i < numCols; i++) {
        const x = (width / numCols) * i + (width / numCols) / 2;
        svg.append("rect")
            .attr("x", x - (width / numCols) / 2)
            .attr("y", height)
            .attr("width", width / numCols)
            .attr("height", contenedorAltura)
            .style("fill", "#69b3a2");
    }
}

// Simular la caída de una bola
function caerBola(contenedor) {
    const bola = svg.append("circle")
        .attr("cx", width / 2)  // Posición inicial en el centro superior
        .attr("cy", 0)
        .attr("r", radioBola)
        .style("fill", "red");

    let currentX = width / 2;
    let currentY = 0;

    // Limitar los movimientos a los límites del tablero
    function limitarMovimiento(x) {
        const minX = radioBola;  // Límite izquierdo (bordes del tablero)
        const maxX = width - radioBola;  // Límite derecho
        return Math.max(minX, Math.min(maxX, x));  // Restringir dentro del rango
    }

    // Función para animar el movimiento de la bola paso a paso con probabilidades controladas
    function animarCaida(row) {
        if (row < numRows) {
            const y = (height / numRows) * row;

            // Proporcionalidad del centro: las bolas tienen más probabilidad de moverse hacia el centro
            const centro = width / 2;
            const distanciaAlCentro = Math.abs(currentX - centro);
            const probabilidadCentro = 0.5 + (distanciaAlCentro / centro) * 0.5;  // Más cerca al centro, mayor la probabilidad de moverse hacia el centro

            const direction = Math.random() < probabilidadCentro ? -1 : 1;  // Moverse hacia el centro con más probabilidad
            currentX += direction * (width / (numRows + 1)) / 2;
            currentX = limitarMovimiento(currentX);  // Limitar el movimiento a los límites del tablero
            currentY = y;

            // Mover la bola a la siguiente fila de clavos
            bola.transition()
                .duration(150)  // Aumenta la velocidad de caída
                .attr("cx", currentX)
                .attr("cy", currentY)
                .on("end", function() {
                    animarCaida(row + 1);  // Continuar la caída en la siguiente fila
                });
        } else {
            // Cuando la bola llega al contenedor, determinar cuántas bolas ya hay en ese contenedor
            const finalX = (width / numCols) * contenedor + (width / numCols) / 2;
            const nivel = contenedorBolas[contenedor];  // Número de bolas ya en este contenedor
            const finalY = height - (nivel + 1) * (radioBola * 2);  // Subir según cuántas bolas hay

            // Actualizar el conteo de bolas en el contenedor
            contenedorBolas[contenedor]++;

            // Mover la bola al contenedor final y acumularla sobre las demás bolas
            bola.transition()
                .duration(200)
                .attr("cx", finalX)
                .attr("cy", finalY);
        }
    }

    // Iniciar la animación desde la fila superior
    animarCaida(0);
}

// Conectar al servidor SSE para simular la caída de las bolas
function conectarSSE() {
    const eventSource = new EventSource("/datosSimulacion");

    eventSource.onmessage = function(event) {
        const data = JSON.parse(event.data);  // Datos recibidos del servidor

        // Simular la caída de más bolas en los contenedores
        data.forEach((conteo, index) => {
            for (let i = 0; i < conteo; i++) {
                caerBola(index);  // Simular la caída de una bola en el contenedor 'index'
            }
        });
    };

    eventSource.onerror = function(error) {
        console.error("Error en la conexión SSE:", error);
        eventSource.close(); // Cerrar la conexión si hay un error
    };
}

// Crear clavos y contenedores más grandes
crearClavos();
crearContenedores();

// Iniciar la conexión SSE
conectarSSE();
