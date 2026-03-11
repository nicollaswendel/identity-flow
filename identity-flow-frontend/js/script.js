const formulario = document.querySelector("#register-form");
const inputCompleteName = document.querySelector(".complete_name");
const inputEmail = document.querySelector(".email");
const inputUsername = document.querySelector(".username");
const inputPassword = document.querySelector(".pass");
const inputTelephone = document.querySelector(".tel");
const logoutButton = document.querySelector(".logout-btn");
const statusText = document.querySelector(".status-text");
const registerButton = document.querySelector(".register-btn");

function getToken() {
    return localStorage.getItem("token");
}

function updateScreenState() {
    const token = getToken();
    const isLogged = !!token;

    statusText.textContent = isLogged
        ? "Usuário autenticado."
        : "Você precisa estar logado para cadastrar.";

    formulario.querySelectorAll("input").forEach(input => {
        input.disabled = !isLogged;
    });

    registerButton.disabled = !isLogged;
    logoutButton.style.display = isLogged ? "block" : "none";
}

async function register() {
    const token = getToken();

    if (!token) {
        alert("Você precisa fazer login antes de cadastrar.");
        window.location.href = "login.html";
        return;
    }

    try {
        const response = await fetch("http://localhost:8080/users", {
            method: "POST",
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json",
                "Authorization": token
            },
            body: JSON.stringify({
                completeName: inputCompleteName.value,
                email: inputEmail.value,
                username: inputUsername.value,
                pass: inputPassword.value,
                telephone: inputTelephone.value
            })
        });

        if (!response.ok) {
            alert("Não foi possível cadastrar o usuário.");
            return;
        }

        alert("Usuário cadastrado com sucesso!");
        clean();

    } catch (error) {
        console.error("Erro ao cadastrar usuário:", error);
        alert("Erro na comunicação com o backend.");
    }
}

function clean() {
    inputCompleteName.value = "";
    inputEmail.value = "";
    inputUsername.value = "";
    inputPassword.value = "";
    inputTelephone.value = "";
}

function logout() {
    localStorage.removeItem("token");
    alert("Logout realizado com sucesso!");
    updateScreenState();
    window.location.href = "login.html";
}

formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    register();
});

logoutButton.addEventListener("click", logout);

updateScreenState();