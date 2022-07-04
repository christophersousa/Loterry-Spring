$('#number-random').on('click', function(e) {
    for(let i = 1; i <=6; i++) {
      let number = document.querySelector(`#n${i}`)
      let value = Math.floor(Math.random() * 60) + 1;
      number.setAttribute('value', value)
    }
})