const PROBLEMS_PER_SET = 10;
const MIN_VALUE = 0;
const MAX_VALUE = 9;
const MAX_RESULT_VALUE = MAX_VALUE * MAX_VALUE;

let problemSet = [];
let currentProblem = 1;
let score = 0;

/*
 * Generates a problem set with {PROBLEMS_PER_SET} problems
 *
 */
function generateProblemSet() {
  problemSet = [];
  
  for (i = 0; i < PROBLEMS_PER_SET; ++i) {
    const random1 = getRandomNumber(MAX_VALUE, MIN_VALUE);
    const random2 = getRandomNumber(MAX_VALUE, MIN_VALUE);
    const problem = {
      left: random1,
      right: random2,
      answer: random1 * random2
    }
    const answers = shuffleArray([problem.answer, getRandomNumber(MAX_RESULT_VALUE), getRandomNumber(MAX_RESULT_VALUE), getRandomNumber(MAX_RESULT_VALUE)]);
    problemSet.push({ problem, answers });
  }
}

/*
[
  {
    problem: {
      left: 1,
      right: 2,
      answer: 2
    },
    answers: [2, 51, 33, 0]
  },
  {
    problem: {
      left: 4,
      right: 2,
      answer: 8
    },
    answers: [2, 51, 33, 8]
  },
]

*/


/**
 * Utility function to generate a random number based on max
 * @param {number} max
 * @param {number} min
 */
function getRandomNumber(max = 9, min = 0) {
  return Math.floor((Math.random() * ((max + 1) - min)) + min);
}

/**
 * Utility function to shuffle the items in an array
 * @param {object} arr
 */
function shuffleArray(arr) {
  return arr.sort(() => Math.random() - 0.5)
}



/*

 -- When the page loads
 
 Start Over ->
    Reset problem counter
    Reset Score counter
    Show a problem
      -> Come up with two random numbers, figure out solution, generate 3 random answers
      -> Two random numbers into UI
      -> 4 answer options into UI

  Setup event handlers 
   -> Click Start Over
   -> Click any Answer Button

 -- When they click Start Over

 Start Over ->
    Reset problem counter
    Reset Score counter
    Show a problem
      -> Come up with two random numbers, figure out solution, generate 3 random answers
      -> Two random numbers into UI
      -> 4 answer options into UI


 -- When they click an Answer Button
  Verify the answer
   -> if it's right, change the score
   -> if it's wrong, beep and tell them they're a failure!
  
  Change problem count
  Next problem
    -> Show a problem
      -> Come up with two random numbers, figure out solution, generate 3 random answers
      -> Two random numbers into UI
      -> 4 answer options into UI

*/
 
function startOver() {
  /*
    Reset problem counter
    Reset Score counter
    Show a problem
      -> Come up with two random numbers, figure out solution, generate 3 random answers
      -> Two random numbers into UI
      -> 4 answer options into UI
  */

  // reset problem counter
  currentProblem = 1;
  document.querySelector('.currentProblem').innerText = currentProblem;

  // reset score
  score = 0;
  document.querySelector('.currentScore').innerText = score;

  // generate fresh set of problems with answer options
  generateProblemSet();

  // display current problem (first problem)
  const currentProblemObject = problemSet[currentProblem - 1];
  const problem = currentProblemObject.problem;
  document.querySelector('.expression').innerText = `${problem.left} * ${problem.right}`;

  // display answer options
  const answers = currentProblemObject.answers;
  let i = 0;
  document.querySelectorAll('#answers li').forEach(li => {
    li.innerText = answers[i];
    i++;
  });
}

function answerChosen() {
  /* 
    Verify the answer
   -> if it's right, change the score
   -> if it's wrong, beep and tell them they're a failure!
  
  Change problem count
  Next problem
    -> Show a problem
      -> Come up with two random numbers, figure out solution, generate 3 random answers
      -> Two random numbers into UI
      -> 4 answer options into UI

  */
}

function attachEventListeners() {
  document.getElementById('btnStartOver').addEventListener('click', startOver);

  document.querySelectorAll('#answers li').forEach(li => {
    li.addEventListener('click', answerChosen);
  });
}

document.addEventListener('DOMContentLoaded', () => {
  startOver();
  attachEventListeners();
});

