/**
 * All named functions will have the function keyword and
 * a name followed by parentheses.
 * 
 * @returns {number} 1
 */
function returnOne() {
  return 1;
}

/**
 * Functions can also take parameters. These are just variables that are filled
 * in by whoever is calling the function.
 *
 * Also, we don't *have* to return anything from the actual function.
 *
 * @param {any} value the value to print to the console
 */
function printToConsole(value) {
  console.log(value);
}

/**
 * Write a function called multiplyTogether that multiplies two numbers together. But 
 * what happens if we don't pass a value in? What happens if the value is not a number?
 *
 * @param {number} firstParameter the first parameter to multiply
 * @param {number} secondParameter the second parameter to multiply
 */
function multiplyTogether(firstParameter, secondParameter) {
  return firstParameter * secondParameter;
}

/**
 * This version makes sure that no parameters are ever missing. If
 * someone calls this function without parameters, we default the
 * values to 0. However, it is impossible in JavaScript to prevent
 * someone from calling this function with data that is not a number.
 * Call this function multiplyNoUndefined
 *
 * @param {number} [firstParameter=0] the first parameter to multiply
 * @param {number} [secondParameter=0] the second parameter to multiply
 */
function multiplyNoUndefined(firstParameter = 0, secondParameter = 0) {
  return firstParameter * secondParameter;
}

 
/**
 * Functions can return earlier before the end of the function. This could be useful
 * in circumstances where you may not need to perform additional instructions or have to
 * handle a particular situation.
 * In this example, if the firstParameter is equal to 0, we return secondParameter times 2.
 * Observe what's printed to the console in both situations.
 * 
 * @param {number} firstParameter the first parameter
 * @param {number} secondParameter the second parameter
 */
function returnBeforeEnd(firstParameter, secondParameter) {
  console.log("This will always fire.");

  if (firstParameter == 0) {
    console.log("Returning secondParameter times two.");
    return secondParameter * 2;
  }

  //this only runs if firstParameter is NOT 0
  console.log("Returning firstParameter + secondParameter.");
  return firstParameter + secondParameter;
}

/**
 * Scope is defined as where a variable is available to be used.
 *
 * If a variable is declare inside of a block, it will only exist in
 * that block and any block underneath it. Once the block that the
 * variable was defined in ends, the variable disappears.
 */
function scopeTest() {
  // This variable will always be in scope in this function
  let inScopeInScopeTest = true;

  {
    // this variable lives inside this block and doesn't
    // exist outside of the block
    let scopedToBlock = inScopeInScopeTest;
  }

  // scopedToBlock doesn't exist here so an error will be thrown
  if (inScopeInScopeTest && scopedToBlock) {
    console.log("This won't print!");
  }
}

function createSentenceFromUser(name, age, listOfQuirks = [], separator = ', ') {
  let description = `${name} is currently ${age} years old. Their quirks are: `;

  // Bob is currently 30 years old. Their quirks are: 
  // [ "weird", "passionate", "intelligent" ]
  // ->   "weird|passionate|intelligent"

  return description + listOfQuirks.join(separator);
}

function ourOwnJoin(listOfQuirks, separator) {
  let joinResult = '';

  for(let i = 0; i < listOfQuirks.length - 1; i++) {
    joinResult += listOfQuirks[i] + separator;
  }

  return joinResult + listOfQuirks[listOfQuirks.length - 1];
}

function ourOwnJoinWithReduce(listOfQuirks, separator) {

  return listOfQuirks.reduce((accumulator, currValue, index, array) => {
    if(index === array.length - 1) {
      return accumulator + currValue;
    }

    return accumulator + currValue + separator;
  }, '');
}


let myArray = [1, 2, 3];
myArray.some((value) => value === 1); // true
myArray.every(value => value === 1);  // false
myArray.map(value => value / 10);     // [.1, .2, .3]

myArray.map(value => {
  if (value > 2) {
    return value * 2;
  }

  return value;
});

// [ 1, 2, 3, 4, 5, 6 ]
// [3, 6]
// [6, 12]
myArray
  .filter(value => value % 3 === 0)
  .map(value => value * 2);


/**
 * Takes an array and, using the power of anonymous functions, generates
 * their sum.
 *
 * @param {number[]} numbersToSum numbers to add up
 * @returns {number} sum of all the numbers
 * 
 * 
 * [5, 3, 4, 1]
 * accumulator  currValue
 *  0             5
 *  5             3
 *  8             4
 *  12            1
 * 
 *  13
 * 
 */
function sumAllNumbers(numbersToSum) {
  return numbersToSum.reduce((accumulator, currValue) => {
    return accumulator + currValue;
  }, 0);
}

/**
 * Takes an array and returns a new array of only numbers that are
 * multiples of 3
 *
 * @param {number[]} numbersToFilter numbers to filter through
 * @returns {number[]} a new array with only those numbers that are
 *   multiples of 3
 */
function allDivisibleByThree(numbersToFilter) {
  newArray = [];

  for(let i = 0; i < numbersToFilter.length; i++) {
    if(numbersToFilter[i] % 3 === 0) {
      newArray.push(numbersToFilter[i]);
    }
  }

  return newArray;
}

function allDivisibleByThree(numbersToFilter) {
  return numbersToFilter.filter(isDivisibleByThree);
}

function isDivisibleByThree(x) {
  return x % 3 === 0;
}

function allDivisibleByThree(numbersToFilter) {
  const isDivisibleByThree = function (x) {
    return x % 3 === 0;
  };

  return numbersToFilter.filter(isDivisibleByThree);
}

function allDivisibleByThree(numbersToFilter) {
  return numbersToFilter.filter(function (x) {
    return x % 3 === 0;
  });
}


function allDivisibleByThree(numbersToFilter) {
  const isDivisibleByThree = (x) => x % 3 === 0;
  
  return numbersToFilter.filter(isDivisibleByThree);
}


function allDivisibleByThree(numbersToFilter) {
  return numbersToFilter.filter(x => x % 3 === 0);
}
