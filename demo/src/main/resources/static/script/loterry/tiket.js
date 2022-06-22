window.addEventListener('load', generate_numvers_tiket())

function generate_numvers_tiket(){
    const numbers_tiket = []
    for (let i = 0; i <=60; i++) {
        numbers_tiket.push(i)
    }

    const tiket = document.querySelector('.numbers_tiket')
    tiket.insertAdjacentHTML('beforeend', numbers_tiket.map(
                            tiket =>{return create_tag(tiket)}))
}

function create_tag(tiket) {
    return `<span id="${tiket}">${tiket}</span>`
}
