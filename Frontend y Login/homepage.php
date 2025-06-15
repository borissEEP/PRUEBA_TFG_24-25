<?php
session_start();
include("connect.php");

if (!isset($_SESSION['email'])) {
    header("Location: index.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/homepage.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn-icons-png.flaticon.com/512/2830/2830573.png">
    <title>Portal Empleados</title>
</head>
<body>
<div class="menu-perfil">
  <div class="avatar" onclick="toggleMenu()">
    <img src="https://cdn-icons-png.flaticon.com/512/64/64572.png" alt="Avatar">
  </div>
  <div id="dropdownMenu" class="dropdown oculto">
    <a href="perfil.php">Mi perfil</a>
    <a href="pdf/presentacion.pdf" download="Empresa Ruiz Bros">¿Quienes somos?</a>
    <a href="pdf/manual.pdf" download="Manual usuario">Manual de Usuario</a>
    <a href="logout.php">Cerrar sesión</a>
  </div>
</div><br><br><br><br>

        <h1>
            Te damos la bienvenida, <?php 
            if(isset($_SESSION['email'])){
                $email=$_SESSION['email'];
                $query=mysqli_query($conn, "SELECT usuarios.* FROM `usuarios` WHERE usuarios.email='$email'");
                while($row=mysqli_fetch_array($query)){
                    echo $row['nombre'].' '.$row['apellido'];
                }
            }
            ?>   
        </h1>
       <h1>Gestor de Empleados</h1>

  <div class="center">
    <button class="btn btn-agregar" onclick="mostrarFormulario()">Añadir Empleado</button>
  </div><br><br>

  <div id="form-container">
    <h3 id="form-title">Añade un Empleado</h3>
    <form id="empleado-form" onsubmit="guardarEmpleado(event)">
      <input type="hidden" id="empleado-id">
      <input type="text" id="nombre" placeholder="Nombre" required>
      <input type="text" id="apellido" placeholder="Apellido" required>
      <input type="text" id="puesto" placeholder="Puesto" required>
      <input type="text" id="codigoEmpleado" placeholder="Código de Empleado" required>
      <button class="btn" type="submit">Guardar</button>
      <button class="btn btn-secondary" type="button" onclick="ocultarFormulario()">Cancelar</button>
    </form>
  </div>

  <table>
    <thead>
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Puesto</th>
        <th>Código empleado</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody id="empleados-table-body"></tbody>
    
  </table><br><br><br>
  <div class="center">
  <h3>Asignar Tarea a un Empleado</h3>
  <select id="select-empleado"></select>
  <input type="text" id="tarea-desc" placeholder="Descripción de la tarea">
  <button class="btn" onclick="asignarTarea()">Asignar Tarea</button>
</div>
<br>
<div id="tareas-container" style="display: none;">
  <h3 id="titulo-tareas">Tareas de:</h3>
  <ul id="lista-tareas"></ul>
</div>
  
   <script type="text/javascript" src="js/crud.js"></script>
</body>
</html>