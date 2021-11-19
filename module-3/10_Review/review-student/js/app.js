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
