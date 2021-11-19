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


function toggleShowHide() {
  const elements = document.querySelectorAll('.show-hide');
  elements.forEach((element) => {
    element.classList.toggle("hidden", currentProblem == PROBLEMS_PER_SET);
  });
}

function resetCurrentProblem() {
  currentProblem = 0;
}

function resetScore() {
  score = 0;
  document.querySelector('.currentScore').innerText = score;
}

function incrementScore() {
  score++;
  document.querySelector('.currentScore').innerText = score;
}

function showNextProblem() {
  currentProblem++;
  document.querySelector('.currentProblem').innerText = currentProblem;

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

function gameOver() {
  // hide the problem and answer options
  toggleShowHide();
}

function startOver() {
  // reset problem counter
  resetCurrentProblem();

  // reset score
  resetScore();

  // generate fresh set of problems with answer options
  generateProblemSet();
  showNextProblem();

  // show the problem and answer options
  toggleShowHide();
}

function answerChosen(e) {
  const usersAnswer = Number.parseInt(e.currentTarget.innerText);
  if (usersAnswer === problemSet[currentProblem - 1].problem.answer) {
    incrementScore();
  }

  if (currentProblem == PROBLEMS_PER_SET) {
    gameOver();
  } else {
    showNextProblem();
  }
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

