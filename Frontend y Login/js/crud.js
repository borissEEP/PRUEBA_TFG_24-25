const API_URL = 'http://localhost:8080/empleados';

function toggleMenu() {
  var menu = document.getElementById("dropdownMenu");
  menu.classList.toggle("oculto");
}

window.addEventListener('click', function(e) {
  var menu = document.getElementById("dropdownMenu");
  if (!document.querySelector('.menu-perfil').contains(e.target)) {
    menu.classList.add("oculto");
  }
});


    document.addEventListener('DOMContentLoaded', cargarEmpleados);

    function cargarEmpleados() {
      fetch(API_URL)
        .then(res => res.json())
        .then(data => {
          const tbody = document.getElementById('empleados-table-body');
          tbody.innerHTML = '';
          data.forEach(emp => {
            const row = document.createElement('tr');
            row.innerHTML = `
              <td>${emp.id}</td>
              <td>${emp.nombre}</td>
              <td>${emp.apellido}</td>
              <td>${emp.puesto}</td>
              <td>${emp.codigoEmpleado}</td>
              <td class="table-actions">
                <button class="btn" onclick='editarEmpleado(${JSON.stringify(emp)})'>Editar</button>
                <button class="btn btn-danger" onclick='eliminarEmpleado(${emp.id})'>Borrar</button>
                <button class="btn btn-tareas" onclick='verTareas(${emp.id}, "${emp.nombre}")'>Ver Tareas</button>
              </td>
            `;
            tbody.appendChild(row);
          });
        });
}
    
function llenarSelectorEmpleados() {
  fetch(API_URL)
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('select-empleado');
      select.innerHTML = '<option value="">-- Selecciona un empleado --</option>';
      data.forEach(emp => {
        const option = document.createElement('option');
        option.value = emp.id;
        option.textContent = `${emp.nombre} ${emp.apellido}`;
        select.appendChild(option);
      });
    });
}

document.addEventListener('DOMContentLoaded', () => {
  cargarEmpleados();
  llenarSelectorEmpleados();
});
function asignarTarea() {
  const empleadoId = document.getElementById('select-empleado').value;
  const descripcion = document.getElementById('tarea-desc').value;

  if (!empleadoId || !descripcion.trim()) {
    alert('⚠️ Debes seleccionar un empleado y escribir una tarea.');
    return;
  }

  const tarea = { descripcion };

  fetch(`http://localhost:8080/tareas/asignar?empleadoId=${empleadoId}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(tarea)
  })
    .then(res => {
      if (!res.ok) throw new Error("Error al asignar tarea");
      return res.json();
    })
    .then(() => {
      alert('✅ Tarea asignada correctamente.');
      document.getElementById('tarea-desc').value = '';
    })
    .catch(err => alert("❌ " + err.message));
}
function verTareas(empleadoId, nombreEmpleado) {
  fetch(`http://localhost:8080/tareas/empleado/${empleadoId}`)
    .then(res => res.json())
    .then(tareas => {
      const container = document.getElementById('tareas-container');
      const titulo = document.getElementById('titulo-tareas');
      const lista = document.getElementById('lista-tareas');

      titulo.textContent = `Tareas de: ${nombreEmpleado}`;
      lista.innerHTML = '';

      if (tareas.length === 0) {
        lista.innerHTML = '<li>🟡 No tiene tareas asignadas</li>';
      } else {
        tareas.forEach(tarea => {
          const item = document.createElement('li');
          item.innerHTML = `
            📝 ${tarea.descripcion} (${tarea.estado} ${tarea.fechaAsignacion})
            <button class="btn btn-danger" onclick="eliminarTarea(${tarea.id}, ${empleadoId})">Terminar tarea</button>
          `;
          lista.appendChild(item);
        });
      }

      container.style.display = 'block';
    });
}

function eliminarTarea(id) {
  if (confirm("¿Estás seguro que desea terminar esta tarea?")) {
    fetch(`http://localhost:8080/tareas/${id}`, {
      method: "DELETE"
    })
    .then(res => {
      if (!res.ok) throw new Error("Error al eliminar tarea");
      alert("✅ La tarea fue terminada");
      location.reload();
    })
    .catch(err => {
      console.error(err);
      alert("❌ Error de red: " + err.message);
    });
  }
}

    function mostrarFormulario() {
      document.getElementById('form-container').classList.add('show');
      document.getElementById('form-title').innerText = 'Añadir Empleado';
      document.getElementById('empleado-form').reset();
      document.getElementById('empleado-id').value = '';
    }

    function ocultarFormulario() {
      document.getElementById('form-container').classList.remove('show');
    }

    function editarEmpleado(emp) {
      mostrarFormulario();
      document.getElementById('form-title').innerText = 'Editar Empleado';
      document.getElementById('empleado-id').value = emp.id;
      document.getElementById('nombre').value = emp.nombre;
      document.getElementById('apellido').value = emp.apellido;
      document.getElementById('puesto').value = emp.puesto;
      document.getElementById('codigoEmpleado').value = emp.codigoEmpleado;
    }

    function guardarEmpleado(event) {
  event.preventDefault();

  const id = document.getElementById('empleado-id').value;
  const nombre = document.getElementById('nombre').value;
  const apellido = document.getElementById('apellido').value;
  const puesto = document.getElementById('puesto').value;
  const codigoEmpleado = document.getElementById('codigoEmpleado').value;

  const empleado = { nombre, apellido, puesto, codigoEmpleado };

  // Verificamos si el código del empleado ya existe
  fetch(`${API_URL}/buscar/codigo?codigo=${codigoEmpleado}`)
    .then(res => res.json())
    .then(data => {
      const codigoUsado = data.some(e => String(e.id) !== id); // si existe otro con ese código

      if (codigoUsado) {
        alert("⚠️ El código de empleado ya está en uso por otro empleado.");
        return;
      }

      // Si no hay problema, hacemos POST o PUT
      const method = id ? 'PUT' : 'POST';
      const url = id ? `${API_URL}/${id}` : API_URL;

      fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(empleado)
      })
        .then(response => {
          if (!response.ok) {
            return response.text().then(msg => { throw new Error(msg); });
          }
          return response.json();
        })
        .then(() => {
          cargarEmpleados();
          ocultarFormulario();
        })
        .catch(err => {
          alert("❌ Error: " + err.message);
        });

    });
}

    function eliminarEmpleado(id) {
      if (confirm('¿Seguro que quieres eliminar este empleado?')) {
        fetch(`${API_URL}/${id}`, { method: 'DELETE' })
          .then(() => cargarEmpleados());
      }
}