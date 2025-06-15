document.addEventListener("DOMContentLoaded", function () {
    const password = document.getElementById("pass");
    const toggle = document.querySelector(".toggle-password");

    toggle.addEventListener("click", () => {
        if (pass.type === "password") {
            pass.type = "text";
            toggle.classList.remove("bx-show-alt");
            toggle.classList.add("bx-hide");
        } else {
            pass.type = "password";
            toggle.classList.add("bx-show-alt");
            toggle.classList.remove("bx-hide");
        }
    });
});