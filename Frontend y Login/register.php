<?php

include 'connect.php';

if(isset($_POST['signUp'])){
    $nombre = $_POST['nombre'];
    $apellido = $_POST['apellido'];
    $email = $_POST['email'];
    $contraseña = $_POST['password'];
    $contraseña = md5($contraseña);

    $checkEmail = "SELECT * From usuarios where email = '$email'";
    $result = $conn->query($checkEmail);
    if($result->num_rows > 0){
        echo "Dirección de correo ya existe!";
    } else{
        $insertQuery = "INSERT INTO usuarios(nombre, apellido, email, contraseña)
        VALUES ('$nombre', '$apellido', '$email', '$contraseña')";
    if($conn->query($insertQuery)==TRUE){
        header("location: index.php");
    } else{
        echo "Error:".$conn->error;
    }
    }
    

}

if(isset($_POST['signIn'])){
    $email = $_POST['email'];
    $contraseña = $_POST['password'];
    $contraseña = md5($contraseña);

    $sql = "SELECT * FROM usuarios WHERE email = '$email' and contraseña = '$contraseña'";
    $result = $conn->query($sql);
    if($result->num_rows > 0){
        session_start();
        $row=$result->fetch_assoc();
        $_SESSION['email'] =$row['email'];
        header("Location: homepage.php");
        exit();
    }
    else{
        echo "Error, Contraseña o Email no encontrado, vuelve a intentar";
    }
}

?>