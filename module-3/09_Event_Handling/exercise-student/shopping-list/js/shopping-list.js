let allItemsIncomplete = true;
const pageTitle = 'My Shopping List';
const groceries = [
  { id: 1, name: 'Oatmeal', completed: false },
  { id: 2, name: 'Milk', completed: false },
  { id: 3, name: 'Banana', completed: false },
  { id: 4, name: 'Strawberries', completed: false },
  { id: 5, name: 'Lunch Meat', completed: false },
  { id: 6, name: 'Bread', completed: false },
  { id: 7, name: 'Grapes', completed: false },
  { id: 8, name: 'Steak', completed: false },
  { id: 9, name: 'Salad', completed: false },
  { id: 10, name: 'Tea', completed: false }
];

/**
 * This function will get a reference to the title 
 * and set its text to the value
 * of the pageTitle variable that was set above.
 */
function setPageTitle() {
  const title = document.getElementById('title');
  title.innerText = pageTitle;
}

/**
 * This function will loop over the array of groceries 
 * that was set above and add them to the DOM.
 */
function displayGroceries() {
  const ul = document.querySelector('ul');
  groceries.forEach((item) => {
    const li = document.createElement('li');
    li.innerText = item.name;
    const checkCircle = document.createElement('i');
    checkCircle.setAttribute('class', 'far fa-check-circle');
    li.appendChild(checkCircle);
    ul.appendChild(li);
  });
}

setPageTitle();
displayGroceries();

// ------------------------------------------------

function markComplete(item) {
  if (!item.classList.contains('completed')) {
    item.classList.add('completed');
    const checkMark = item.children[0];
    checkMark.classList.add('completed');
  }
}

function markIncomplete(item) {
  if (item.classList.contains('completed')) {
    item.classList.remove('completed');
    const checkMark = item.children[0];
    checkMark.classList.remove('completed');
  }
}

function toggle(button, listItems) {
  if (allItemsIncomplete) {
    button.innerText = 'Mark All Incomplete';
    listItems.forEach(item => markComplete(item));  
  } else {
    button.innerText = 'Mark All Complete';
    listItems.forEach(item => markIncomplete(item));
  }
  allItemsIncomplete = !allItemsIncomplete;
}

document.addEventListener('DOMContentLoaded', () => {
  const listItems = document.querySelectorAll('li');

  // mark item complete
  listItems.forEach(item => {
    item.addEventListener('click', () => {
      markComplete(item);
    });
  });

  // mark item incomplete
  listItems.forEach(item => {
      item.addEventListener('dblclick', () => {
        markIncomplete(item);
      });
    });

  // toggle all complete/incomplete
  const button = document.getElementById('toggleAll');
  button.addEventListener('click', () => {
    toggle(button, listItems);
  });

});
