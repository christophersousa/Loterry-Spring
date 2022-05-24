const submitCadastro = document.querySelector("#registerCliente")
submitCadastro.addEventListener("click", (e) => {
    e.preventDefault();
    const password = document.querySelector("#password").value
    const confirmPassword = document.querySelector("#confirmPassword").value
    const error_message = document.querySelector(".error-message")

    if(password === confirmPassword) {
        e.defaultPrevented
    }else{
        const alert = `<span class="alert">A senha deve ser a mesma!</span>`
        error_message.style.display = 'block'
        error_message.insertAdjacentHTML("afterbegin", alert)
    }


})