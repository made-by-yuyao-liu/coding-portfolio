//Create variable for the guess spots
//Notes: A NodeList object is a list of nodes extracted from a document
let nodeList = getNodeList();
//Create variable including all the the "check" buttons
let checkList = getCheckList();
//Create variable for the check dots beside each line
let checkDots = getCheckDots();
//Create variable for tracking the current selected color
let currentCheckedColor = '';
//Create variable for the current row number
let currentRowNum = 11;
//Create an array for tracking the colors in the answers
let resultColors = [];
//Create an array for tracking the guessed colors
let guessColors = [];
//Create variable for the answer DOM at the top
let answerDom = getAnswerDom()

//Copy the color of the clicked ball into the variable currentCheckedColor
function onClickBall(color) {
    currentCheckedColor = color;
}

//Paste the color selected into an empty spot
function onSetBallColor(e) {
    //If the a color is copied into currentCheckedColor and e is enabled
    if (currentCheckedColor && e.disabled === false) { //Notes: === is the strict equality operator checks whether its two operands are equal, operands of different types are different.
        //The currently selected color will be pasted into the background color of the empty spot
        e.style.backgroundColor = currentCheckedColor;
        //The current selected color gets pushed into the array guessColors
        guessColors.push(currentCheckedColor); //Notes: The push() method adds new items to the end of an array, and changes the length of the array to the new one
    }
}

//The function for "check" buttons
function onCheckClick() {
    //Decide whether 4 colors has been selected, and whether the length of the array is equal to 4
    if (guessColors.length === 4) {
        //Store the 4 check dots in the current row into the variable currentRowCheckDotsDom
        let currentRowCheckDotsDom = checkDots[currentRowNum];
        //Filter the number of guess colors that are included with result colors and store them in whiteCheckDotNum 
        //Notes: The filter() method creates a new array with all elements that pass the test implemented by the provided function.
        //Notes: The includes() method determines whether an array includes a certain value, returning true or false.
        let whiteCheckDotNum = guessColors.filter(guessColor => resultColors.includes(guessColor)).length; 
        //Set the number of red check dots to 0
        let redCheckDotNum = 0;
        //Create a duplicate for redCheckDotNum for furture use, set it's value also to 0
        let redCheckDotNumClone = 0;
        //If the number of white dots is greater than 0, then check how many of these white dots are in the right position
        if (whiteCheckDotNum > 0) {
            //Filter the number of colors that match the colors in the result
            redCheckDotNum = guessColors.filter((guessColor, index) => guessColor === resultColors[index]).length; 
            //Paste the value in redCheckDotNum to its duplicate
            redCheckDotNumClone = redCheckDotNum;
        }

        //The number of white dots can be determined by subtracting the number of red dots from the total number of white dots
        whiteCheckDotNum = whiteCheckDotNum - redCheckDotNum; 
        //For each element in the array currentRowCheckDotsDom
        currentRowCheckDotsDom.forEach(currentRowCheckDotDom => { //Notes: The forEach() method executes a provided function once for each array element
            //If the number of red check dots is greater then 0
            if (redCheckDotNum > 0) {
                //Change the background color of these dots to red
                currentRowCheckDotDom.style.backgroundColor = 'red';
                //The number of red dot decreases by 1
                redCheckDotNum--;
                //End the if statement
                return; //Notes: The return statement ends function execution and specifies a value to be returned to the function caller
            }
            //If the number of white check dots is greater then 0
            if (whiteCheckDotNum > 0) {
                //Change the background color of these dots to white
                currentRowCheckDotDom.style.backgroundColor = 'white';
                //The number of white dot decreases by 1
                whiteCheckDotNum--;
                //End the if statement
                return;
            }
        })
        //If all 4 dots turn red and they have matched the same color and the same spot
        if (redCheckDotNumClone === 4) {
            //Display the correct answer at the top
            showAnswer();
            setTimeout(() => {
                //Display alert that the player has won
                alert('You win!')
            }, 0); //Notes: 0 is time (seconds)
            //End the if statement
            return;
        }
        //If the current row number is greater than 0
        if (currentRowNum > 0) {
            //Move the current row number up by 1
            currentRowNum--;
            //Display the corresponding "check" botton on that line
            setCheckDisplay(); 
            //The current row is activated and the other rows are disabled
            setCheckRow();
        } else {
            //If all guesses have been used up, the answer is shown
            showAnswer();
            setTimeout(() => {
                //Display alert that the player has lost
                alert('You lose!')
            }, 0);
        }

    //If "check" is pressed before all 4 spots have been filled in
    } else {
        //Show alert and ask player to fill in all spots before pressing "check"
        alert('Please fill in all the spots in this row before pressing "Check"!')
    }
}


function getNodeList() {
    //Put all the elements in termLeft into the array termList
    let termList = [...document.querySelectorAll('.termLeft')]; //Notes: querySelectorAll() returns a static NodeList representing a list of the document's elements that match the specified group of selectors
    return termList.map(list => { //Notes: list => represents function(list)
        //Notes: map() creates a new array with the results of calling a function on every element in the array.
        //Select all the spots 
        return [...list.querySelectorAll('li')]
    })
}

function getCheckList() {
    //Put all the elements in termBtn into an array 
    return [...document.querySelectorAll('.termBtn')];
}

function getCheckDots() {
    //Put all the elements in tips into the array checkDotsList
    let checkDotsList = [...document.querySelectorAll('.tips')];
    return checkDotsList.map(list => {
        //Put all elements in check-dot into an array 
        return [...list.querySelectorAll('.check-dot')]
    })
}

function getAnswerDom() {
    //Put the answers from the "?" section into the array 
    return [...document.querySelector('.answer').querySelectorAll('li')]; //Notes: querySelector() returns the first Element within the document that matches the specified selector
}

//Initiate the initNode function
function initNode() {
    //For every row in nodeList
    nodeList.forEach(row => row.forEach(item => {
        //Set the background color of each item to dark brown
        item.style.backgroundColor = '#422821';
        //Enable all items
        item.disabled = false;
    }))
}

//Displaying/hiding the "check" buttons 
function setCheckDisplay() {
    //For each element in checkList, there are 2 aprameters: "check" button ad its index
    checkList.forEach((check, index) => { //Notes: check the the index of the check button [0,1,2,3...]
        //When the current row number is matched with the index number
        if (index === currentRowNum) {
            //Show the "check" button
            check.style.display = 'block';
        } else {
            check.style.display = 'none';
            //If they do not match, hide the "check" button
        }
    })
}


function setCheckRow() {
    //Walk through all the elements in the nodeList
    nodeList.forEach((row, index) => {
        //If the index is not the same row as the current row
        if (index !== currentRowNum) {
            //Disable items from this row
            row.forEach(item => { item.disabled = true })
        } else {
            //If they do match, enable items from this row
            row.forEach(item => { item.disabled = false })
        }
    })
}

function createResultColors(params) {
    //Put the 8 selection colors in to the array called totalColor
    let totalColor = ['red', 'green', 'blue', 'yellow', 'skyblue', 'orange', 'black', 'white'];
    //Put the coloring results in the array called result
    let result = [];
    //Randomly choose 4 colors
    while (result.length < 4) {
        //Add up by whole numbers
        //Randomly produce an integer less than 8, set this number as index
        let index = Math.floor(Math.random() * 8)
        //If the result does not already have this number
        if (!result.includes(index)) {
            //Push this number in to the array
            result.push(index);
        }
    }
    
    //"val" contains the indices of the 4 randomely choosen colors
    result.forEach((val, index) => {
        //Correspond the indices to the color it corresponds with at the top, therefore the "?" will have received the 4 randomized colors
        resultColors[index] = totalColor[val];
    })
}

//Display answers
function showAnswer(params) {
    answerDom.forEach((item, index) => {
        //Change the background color of the items to the color corresponding to the index
        item.style.backgroundColor = resultColors[index];
        item.innerText = '';
    })
}


//Start the game
function start() {
    //Initialize nodes
    initNode();
    //Set the display for "check" buttons
    setCheckDisplay();
    //Set the row that is currently enabled
    setCheckRow();
    //Initialize the randomized color picking, which are the answers
    createResultColors();
}


//Start a new game
start();