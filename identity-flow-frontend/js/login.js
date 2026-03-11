const loginForm = document.querySelector("#login-form");
const inputEmail = document.querySelector(".email");
const inputPassword = document.querySelector(".pass");

async function login() {
    try {
        const response = await fetch("http://localhost:8080/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify({
                email: inputEmail.value,
                pass: inputPassword.value
            })
        });

        if (!response.ok) {
            alert("E-mail ou senha inválidos.");
            return;
        }

        const data = await response.json();

        // Se o backend retorna { token: "Bearer ..." }
        localStorage.setItem("token", data.token);

        alert("Login realizado com sucesso!");
        window.location.href = "index.html";

    } catch (error) {
        console.error("Erro ao realizar login:", error);
        alert("Não foi possível realizar login.");
    }
}

loginForm.addEventListener("submit", function (event) {
    event.preventDefault();
    login();
});