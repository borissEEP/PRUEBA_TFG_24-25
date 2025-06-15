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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/perfil.css">
    <title>Mi perfil</title>
</head>
<body>
<div class="perfil-contenedor">
    <h1>Mi Perfil</h1>
    <?php 
    if(isset($_SESSION['email'])){
        $email = $_SESSION['email'];
        $query = mysqli_query($conn, "SELECT * FROM usuarios WHERE email='$email'");
        if($row = mysqli_fetch_array($query)){
            echo "<p><strong>Nombre:</strong> " . $row['nombre'] . "</p>";
            echo "<p><strong>Apellidos:</strong> " . $row['apellido'] . "</p>";            
            echo "<p><strong>Email:</strong> " . $row['email'] . "</p>";
            
        }
    }
    ?>
</div>
    
</body>
</html>