document.addEventListener('DOMContentLoaded', () => {
    // Get the button 
    const buttonNerd = document.getElementById('nerdButton');
    // get a reference to where we want to put the data
    const message = document.getElementById('message');
    // Add the event listener
    buttonNerd.addEventListener('click', (event) => {
        // call the external api

        console.log('1');

        fetch('http://api.icndb.com/jokes/random?exclude=explicit&limitTo=nerdy')
            // process the first promise, just whether it will work
            .then((response) => {
                // call the method to get the actual data
                console.log('2');

                return response.json();
            })
            // process the second promise, get the actual data
            .then((data) => {
                // put the data in the message
                console.log('3');

                message.innerHTML = data.value.joke;
            })


        console.log('4');
    })

    const randomButton = document.getElementById('randomButton');
    randomButton.addEventListener('click', (event) => {
        fetch('http://api.icndb.com/jokes/random?exclude=explicit')
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                message.innerHTML = data.value.joke;
                console.table(data);
            })
            .catch((error) => {
                console.log(error);
            })
    })
})