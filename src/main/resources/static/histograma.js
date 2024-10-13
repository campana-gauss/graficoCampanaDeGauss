function generarGrafica(data, element, color) {
    var margin = {top: 20, right: 20, bottom: 30, left: 40};
    var width = 800 - margin.left - margin.right;
    var height = 600 - margin.top - margin.bottom;

    // Eliminar la grÃ¡fica existente
    d3.select(element).select("svg").remove();

    var svg = d3.select(element)
        .append("svg")
        .attr("width", 800) // aumenta el ancho de la gráfica
        .attr("height", 600) // aumenta el alto de la gráfica
        .append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    var x = d3.scaleLinear()
        .domain([0, d3.max(data)])
        .range([0, width]);


    svg.append("g")
        .attr("transform", "translate(0," + height + ")")
        .call(d3.axisBottom(x));

    var histogram = d3.histogram()
        .value(function(d) { return d; })
        .domain(x.domain())
        .thresholds(x.ticks(70));

    var bins = histogram(data);

    var y = d3.scaleLinear()
        .range([height, 0]);
    y.domain([0, d3.max(bins, function(d) { return d.length; })]);

    svg.append("g")
        .call(d3.axisLeft(y));

    svg.selectAll("rect")
        .data(bins)
        .enter()
        .append("rect")
        .attr("x", 1)
        .attr("transform", function(d) { return "translate(" + x(d.x0) + "," + y(d.length) + ")"; })
        .attr("width", function(d) { return (x(d.x1) - x(d.x0)) * 2; })
        .attr("height", function(d) { return height - y(d.length); })
        .style("fill", color); // Usar el color pasado como argumento
}

function graficarEdad() {
    d3.json("/edades").then(function(data) {
        generarGrafica(data, "#my_dataviz", "#69b3a2"); // Color para Edad
    });
}

function graficarAltura() {
    d3.json("/alturas").then(function(data) {
        generarGrafica(data, "#my_dataviz", "#ff7f0e"); // Color para Altura
    });
}

function graficarPeso() {
    d3.json("/pesos").then(function(data) {
        generarGrafica(data, "#my_dataviz", "#2ca02c"); // Color para Peso
    });
}

function graficarNotaFinal() {
    d3.json("/notasFinales").then(function(data) {
        generarGrafica(data, "#my_dataviz", "#d62728"); // Color para Nota Final
    });
}

document.getElementById("edad").addEventListener("click", graficarEdad);
document.getElementById("altura").addEventListener("click", graficarAltura);
document.getElementById("peso").addEventListener("click", graficarPeso);
document.getElementById("notaFinal").addEventListener("click", graficarNotaFinal);