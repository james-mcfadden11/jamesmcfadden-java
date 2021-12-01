const apiKey = "2dc52b2b";

document.addEventListener('DOMContentLoaded',() => {
    const searchButton = document.getElementById('btnSearch');
    searchButton.addEventListener('click',(event) => {
        // get reference to html for results
        const movieResults = document.getElementById('movieData');
        let searchTerm = document.getElementById('movie').value;
        movieResults.innerText = "Getting your movies";
        
        fetch("http://www.omdbapi.com/?apikey=" + apiKey + "&s=" + searchTerm)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.table(data);
            console.table(data.Search);
            movieResults.innerText = '';
            for (let index = 0; index < data.Search.length; index++) {
                
                movieResults.appendChild(buildRow(data.Search[index]));
            }
        })
        .catch((error) => {
            console.log(error);
        })

        console.log('run this next');

    })

});

function buildRow(movieItem)
{
    const tr = document.createElement('tr');
    const posterCell = document.createElement('td');
    const imdbCell = document.createElement('td');
    const titleCell = document.createElement('td');
    const yearCell = document.createElement('td');
    const img = document.createElement('img');

    img.src = movieItem.Poster;
    posterCell.appendChild(img);

    imdbCell.innerText = movieItem.imdbID;
    titleCell.innerText = movieItem.Title;
    yearCell.innerText = movieItem.Year;

    tr.appendChild(posterCell);
    tr.appendChild(imdbCell);
    tr.appendChild(titleCell);
    tr.appendChild(yearCell);

    return tr;
}