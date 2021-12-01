document.addEventListener('DOMContentLoaded', () => {
    // Get the button 
    const buttonNerd = document.getElementById('nerdButton');
    // get a reference to where we want to put the data
    const message = document.getElementById('message');
    // Add the event listener
    buttonNerd.addEventListener('click', (event) => {
        // call the external api
        fetch('http://api.icndb.com/jokes/random?exclude=explicit&limitTo=nerdy')
            // process the first promise, just whether it will work
            .then((response) => {
                // call the method to get the actual data
                return response.json();
            })
            // process the second promise, get the actual data
            .then((data) => {
                // put the data in the message
                message.innerHTML = data.value.joke;
            })
        //alert("Don't hurt me.");
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