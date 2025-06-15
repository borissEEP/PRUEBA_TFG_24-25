<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Portal de Empleados</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="icon" href="https://static.wikia.nocookie.net/bigtimerush/images/4/4a/Site-favicon.ico/revision/latest?cb=20221207061336">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container" id="signup" style="display:none;">
    <h1 class="titulo-form">Registro</h1>
    <form method="post" action="register.php">
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" name="nombre" id="nombre" placeholder="First Name" required>
            <label for="nombre">Nombre</label>
        </div>
        <div class="input-group">
            <i class="fas fa-user"></i>
            <input type="text" name="apellido" id="apellido" placeholder="Last Name" required>
            <label for="apellido">Apellido</label>
        </div>
        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" name="email" id="email" placeholder="Email" required>
            <label for="email">Email</label>
        </div>
        <div class="input-group">
            <i class="fas fa-lock"></i>
            <input type="password" name="password" id="password" placeholder="password" required>
            <label for="password">Contraseña</label>
        </div>
        <input type="submit" class="btn" value="Registrarme" name="signUp">
    </form>
    <div class="links">
        <p>¿Ya tienes una cuenta?</p>
        <button id="signInButton">Sign In</button>
    </div>
    </div>
    <div class="container" id="signIn">
    <h1 class="titulo-form">Iniciar Sesión</h1>
    <form method="post" action="register.php">
        
        <div class="input-group">
            <i class="fas fa-envelope"></i>
            <input type="email" name="email" id="email" placeholder="Email" required>
            <label for="email">Email</label>
        </div>

       <div class="input-group" style="position: relative;">
    <i class="fas fa-lock"></i>
    <input type="password" name="password" id="pass" placeholder="Contraseña" required>
    <label for="password">Contraseña</label>
    <i class='bx bx-show-alt toggle-password'></i>
</div>

        <input type="submit" class="btn" value="Iniciar Sesión" name="signIn">
    </form>

    <div class="links">
        <p>¿No tienes una cuenta?</p>
        <button id="signUpButton">Sign Up</button>
    </div>
</div>

    <script src="./js/script.js"></script>
    <script src="./js/mostrar.js"></script>
</body>
</html>
