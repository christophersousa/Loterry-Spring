const submitCadastro = document.querySelector("#registerCliente")
submitCadastro.addEventListener("click", (e) => {
    e.preventDefault();
    const password = document.querySelector("#password")
    const confirmPassword = document.querySelector("#confirmPassword")

    if(password === confirmPassword) {
        e.defaultPrevented()
    }else{
        const alert = `<span class="alert">A senha deve ser a mesma!</span>`
        confirmPassword.insertAdjacentElement("afterbegin", alert)
    }


})