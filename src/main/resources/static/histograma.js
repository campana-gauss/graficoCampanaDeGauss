// Función para generar el histograma con los datos simulados
function generarGrafica(data, element, color) {
    var margin = {top: 10, right: 30, bottom: 30, left: 40},
        width = 460 - margin.left - margin.right,
        height = 400 - margin.top - margin.bottom;

    // Eliminar cualquier gráfica anterior
    d3.select(element).select("svg").remove();

    // Crear el contenedor SVG
    var svg = d3.select(element)
        .append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // Escala para el eje X (contenedores)
    var x = d3.scaleLinear()
        .domain([0, data.length - 1])  // Ajustar según el número de contenedores
        .range([0, width]);

    // Añadir el eje X
    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

    // Escala para el eje Y (número de bolas en los contenedores)
    var y = d3.scaleLinear()
        .range([height, 0]);
    y.domain([0, d3.max(data)]);  // El dominio Y se ajusta según el número máximo de bolas

    // Añadir el eje Y
    svg.append("g")
        .call(d3.axisLeft(y));

    // Dibujar las barras del histograma
    svg.selectAll("rect")
        .data(data)
        .enter()
        .append("rect")
        .attr("x", function(d, i) { return x(i); })  // Posición de la barra en el eje X
        .attr("y", function(d) { return y(d); })  // Altura de la barra
        .attr("width", x(1) - x(0) - 1)  // Ajustar el ancho de la barra
        .attr("height", function(d) { return height - y(d); })  // Altura según el número de bolas
        .style("fill", color);
}

// Función para conectarse al servidor usando SSE
function conectarSSE() {
    const eventSource = new EventSource("/datosSimulacion");

    eventSource.onmessage = function(event) {
        const data = JSON.parse(event.data);  // Datos recibidos del servidor
        generarGrafica(data, "#my_dataviz", "#69b3a2"); // Actualizar la gráfica con los datos en tiempo real
    };

    eventSource.onerror = function(error) {
        console.error("Error en la conexión SSE:", error);
        eventSource.close(); // Cerrar la conexión si hay un error
    };
}

// Iniciar la conexión SSE al cargar la página
conectarSSE();
