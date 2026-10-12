//Create variable for the guess spots
let nodeList = getNodeList();
let checkList = getCheckList();
let checkDots = getCheckDots();
let currentCheckedColor = '';
let currentRowNum = 11;
let resultColors = [];
let guessColors = [];
let answerDom = getAnswerDom()

//Copy the color of the clicked ball into the variable currentCheckedColor
function onClickBall(color) {
    currentCheckedColor = color;
}

//Paste the color selected into an empty spot
function onSetBallColor(e) {
    if (currentCheckedColor && e.disabled === false) { 
        e.style.backgroundColor = currentCheckedColor;
        guessColors.push(currentCheckedColor); 
    }
}

//The function for "check" buttons
function onCheckClick() {
    if (guessColors.length === 4) {
        let currentRowCheckDotsDom = checkDots[currentRowNum];
        let whiteCheckDotNum = guessColors.filter(guessColor => resultColors.includes(guessColor)).length; 
        let redCheckDotNum = 0;
        let redCheckDotNumClone = 0;
        if (whiteCheckDotNum > 0) {
            redCheckDotNum = guessColors.filter((guessColor, index) => guessColor === resultColors[index]).length; 
            redCheckDotNumClone = redCheckDotNum;
        }

        //The number of white dots can be determined by subtracting the number of red dots from the total number of white dots
        whiteCheckDotNum = whiteCheckDotNum - redCheckDotNum; 
        currentRowCheckDotsDom.forEach(currentRowCheckDotDom => {
            if (redCheckDotNum > 0) {
                currentRowCheckDotDom.style.backgroundColor = 'red';
                redCheckDotNum--;
                return; 
            }
            if (whiteCheckDotNum > 0) {
                currentRowCheckDotDom.style.backgroundColor = 'white';
                whiteCheckDotNum--;
                return;
            }
        })
        //If all 4 dots turn red and they have matched the same color and the same spot
        if (redCheckDotNumClone === 4) {
            showAnswer();
            setTimeout(() => {
                alert('You win!')
            }, 0); 
            return;
        }
        //If the current row number is greater than 0
        if (currentRowNum > 0) {
            currentRowNum--;
            setCheckDisplay(); 
            setCheckRow();
        } else {
            showAnswer();
            setTimeout(() => {
                alert('You lose!')
            }, 0);
        }

    } else {
        alert('Please fill in all the spots in this row before pressing "Check"!')
    }
}


function getNodeList() {
    let termList = [...document.querySelectorAll('.termLeft')]; 
    return termList.map(list => {  
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
        return [...list.querySelectorAll('.check-dot')]
    })
}

function getAnswerDom() {
    //Put the answers from the "?" section into the array 
    return [...document.querySelector('.answer').querySelectorAll('li')]; //Notes: querySelector() returns the first Element within the document that matches the specified selector
}

//Initiate the initNode function
function initNode() {
    nodeList.forEach(row => row.forEach(item => {
        item.style.backgroundColor = '#422821';
        item.disabled = false;
    }))
}

//Displaying/hiding the "check" buttons 
function setCheckDisplay() {
    checkList.forEach((check, index) => { 
        if (index === currentRowNum) {
            check.style.display = 'block';
        } else {
            check.style.display = 'none';
        }
    })
}


function setCheckRow() {
    nodeList.forEach((row, index) => {
        if (index !== currentRowNum) {
            row.forEach(item => { item.disabled = true })
        } else {
            row.forEach(item => { item.disabled = false })
        }
    })
}

function createResultColors(params) {
    let totalColor = ['red', 'green', 'blue', 'yellow', 'skyblue', 'orange', 'black', 'white'];
    let result = [];
    while (result.length < 4) {
        let index = Math.floor(Math.random() * 8)
        if (!result.includes(index)) {
            result.push(index);
        }
    }
    
    result.forEach((val, index) => {
        resultColors[index] = totalColor[val];
    })
}

//Display answers
function showAnswer(params) {
    answerDom.forEach((item, index) => {
        item.style.backgroundColor = resultColors[index];
        item.innerText = '';
    })
}


//Start the game
function start() {
    initNode();
    setCheckDisplay();
    setCheckRow();
    createResultColors();
}

//Start a new game
start();