document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('stainForm');
    const resultContainer = document.getElementById('result');
    const API_URL = 'http://localhost:8080/api/stains';

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const material = document.getElementById('material').value;
        const stainType = document.getElementById('stainType').value;

        try {
            // Mostrar indicador de carga
            resultContainer.style.display = 'block';
            resultContainer.innerHTML = '<p>Buscando solución...</p>';

            // Realizar la petición al backend
            const response = await fetch(`${API_URL}/search?material=${encodeURIComponent(material)}&stainType=${encodeURIComponent(stainType)}`);
            
            if (response.ok) {
                const data = await response.json();
                resultContainer.innerHTML = `
                    <h3>Solución encontrada:</h3>
                    <div class="solution-card">
                        <p><strong>Material:</strong> ${data.material}</p>
                        <p><strong>Tipo de mancha:</strong> ${data.stainType}</p>
                        <p><strong>Solución:</strong> ${data.solution}</p>
                    </div>
                `;
            } else if (response.status === 404) {
                resultContainer.innerHTML = `
                    <div class="no-solution">
                        <h3>No se encontró una solución específica</h3>
                        <p>Lo sentimos, no tenemos una solución específica para esta combinación de material y tipo de mancha.</p>
                        <p>¿Te gustaría sugerir una solución para esta mancha?</p>
                        <button onclick="suggestSolution('${material}', '${stainType}')">Sugerir solución</button>
                    </div>
                `;
            } else {
                throw new Error('Error en la búsqueda');
            }
        } catch (error) {
            resultContainer.innerHTML = `
                <div class="error-message">
                    <h3>Error</h3>
                    <p>Lo sentimos, ha ocurrido un error al buscar la solución.</p>
                    <p>Por favor, intenta de nuevo más tarde.</p>
                </div>
            `;
            console.error('Error:', error);
        }
    });
});

// Función para sugerir una solución
async function suggestSolution(material, stainType) {
    const solution = prompt('Por favor, ingresa tu solución para esta mancha:');
    
    if (solution) {
        try {
            const response = await fetch('http://localhost:8080/api/stains', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    material: material,
                    stainType: stainType,
                    solution: solution
                })
            });

            if (response.ok) {
                alert('¡Gracias por tu sugerencia!');
            } else {
                throw new Error('Error al guardar la sugerencia');
            }
        } catch (error) {
            alert('Lo sentimos, no pudimos guardar tu sugerencia. Por favor, intenta de nuevo más tarde.');
            console.error('Error:', error);
        }
    }
} 