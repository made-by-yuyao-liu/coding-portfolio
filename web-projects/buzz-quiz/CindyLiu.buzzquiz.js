"use strict";

function submit() {
  const output = document.getElementById("results");
  output.innerHTML = "<h2><b>Test Results ⤵</b></h2>";

  //ENTER NAME  
    let name = "";
    name = document.getElementById("name");
  if (name == "") {
    output.innerHTML += "<p>No name was entered!</p>";
  } else {
    output.innerHTML += "<b>Your name: " + name.value; 
  }
    
   //ENTER DATE
    let date = "";
    date = document.getElementById("date");
    if (name = "") {
      output.innerHTML += "<p>No date was entered!</p>";
    } else {
      output.innerHTML += "<p><b>Today's date: " + date.value + "</b></p>";
    } 

  //QUESTION 1 (using if, else if, else)
  const answer11Btn = document.getElementById("answer11");
  const answer12Btn = document.getElementById("answer12");
  const answer13Btn = document.getElementById("answer13");
  const answer14Btn = document.getElementById("answer14");
  const answer15Btn = document.getElementById("answer15");
  let yourAnswerForQuestion1;
  if (answer11Btn.checked == true) {
    yourAnswerForQuestion1 = "9"; 
    output.innerHTML += "<p><b>Question #1: Correct! ☺️</b></p>";
  } else if(answer12Btn.checked == true) {
    yourAnswerForQuestion1 = "1";
    output.innerHTML += "<p><b>Question #1: Incorrect ☹</b></p>";
  } else if(answer13Btn.checked == true) {
    yourAnswerForQuestion1 = "-9";
    output.innerHTML += "<p><b>Question #1: Incorrect ☹</b></p>";
  } else if(answer14Btn.checked == true) {
    yourAnswerForQuestion1 = "3";
    output.innerHTML += "<p><b>Question #1: Incorrect ☹</b></p>";
  } else if(answer15Btn.checked == true) {
    yourAnswerForQuestion1 = "2";
    output.innerHTML += "<p><b>uestion #1: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#1 ☹</b></p>";
  }

  //QUESTION 2 (using if, else if, else)
  const answer21Btn = document.getElementById("answer21");
  const answer22Btn = document.getElementById("answer22");
  const answer23Btn = document.getElementById("answer23");
  const answer24Btn = document.getElementById("answer24");
  const answer25Btn = document.getElementById("answer25");
  let yourAnswerForQuestion2;
  if(answer22Btn.checked == true) {
    yourAnswerForQuestion2 = "1";
    output.innerHTML += "<p><b>Question #2: Correct! ☺️</b></p>";
  } else if(answer21Btn.checked == true) {
    yourAnswerForQuestion2 = "19"; 
    output.innerHTML += "<p><b>Question #2: Incorrect ☹</b></p>";
  } else if(answer23Btn.checked == true) {
    yourAnswerForQuestion2 = "⅓";
    output.innerHTML += "<p><b>Question #2: Incorrect ☹</b></p>";
  } else if(answer24Btn.checked == true) {
    yourAnswerForQuestion2 = "-1";
    output.innerHTML += "<p><b>Question #2: Incorrect ☹</b></p>";
  } else if(answer25Btn.checked == true) {
    yourAnswerForQuestion2 = "3";
    output.innerHTML += "<p><b>Question #2: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#2 ☹</b></p>";
  }


  //QUESTION 3 (using if, else if, else)
  const answer31Btn = document.getElementById("answer31");
  const answer32Btn = document.getElementById("answer32");
  const answer33Btn = document.getElementById("answer33");
  const answer34Btn = document.getElementById("answer34");
  const answer35Btn = document.getElementById("answer35");
  let yourAnswerForQuestion3;
  if(answer35Btn.checked == true) {
    yourAnswerForQuestion3 = "11";
    output.innerHTML += "<p><b>Question #3: Correct! ☺️</b></p>";
  } else if(answer31Btn.checked == true) {
    yourAnswerForQuestion3 = "10";
    output.innerHTML += "<p><b>Question #3: Incorrect ☹</b></p>";
  } else if(answer32Btn.checked == true) {
    yourAnswerForQuestion3 = "13"; 
    output.innerHTML += "<p><b>Question #3: Incorrect ☹</b></p>";
  } else if(answer33Btn.checked == true) {
    yourAnswerForQuestion3 = "19";
    output.innerHTML += "<p><b>Question #3: Incorrect ☹</b></p>";
  } else if(answer34Btn.checked == true) {
    yourAnswerForQuestion3 = "56";
    output.innerHTML += "<p><b>Question #3: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#3 ☹</b></p>";
  }

 //QUESTION 4 (using if, else if, else)
  const answer41Btn = document.getElementById("answer41");
  const answer42Btn = document.getElementById("answer42");
  const answer43Btn = document.getElementById("answer43");
  const answer44Btn = document.getElementById("answer44");
  const answer45Btn = document.getElementById("answer45");
  let yourAnswerForQuestion4;
  if(answer43Btn.checked == true) {
    yourAnswerForQuestion4 = "7";
    output.innerHTML += "<p><b>Question #4: Correct! ☺️</b></p>";
  } else if(answer41Btn.checked == true) {
    yourAnswerForQuestion4 = "1"; 
    output.innerHTML += "<p><b>Question #4: Incorrect ☹</b></p>";
  } else if(answer42Btn.checked == true) {
    yourAnswerForQuestion4 = "4";
    output.innerHTML += "<p><b>Question #4: Incorrect ☹</b></p>";
  } else if(answer44Btn.checked == true) {
    yourAnswerForQuestion4 = "5";
    output.innerHTML += "<p><b>Question #4: Incorrect ☹</b></p>";
  } else if(answer45Btn.checked == true) {
    yourAnswerForQuestion4 = "3";
    output.innerHTML += "<p><b>Question #4: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was chosen for question#4 ☹</b></p>";
  }
  
 //QUESTION 5 (using if, else if, else)
  const answer51Btn = document.getElementById("answer51");
  const answer52Btn = document.getElementById("answer52");
  const answer53Btn = document.getElementById("answer53");
  const answer54Btn = document.getElementById("answer54");
  const answer55Btn = document.getElementById("answer55");
  let yourAnswerForQuestion5;
  if(answer51Btn.checked == true) {
    yourAnswerForQuestion5 = "288"; 
    output.innerHTML += "<p><b>Question #5: Correct! ☺️</b></p>";
  } else if(answer52Btn.checked == true) {
    yourAnswerForQuestion5 = "2";
    output.innerHTML += "<p><b>Question #5: Incorrect ☹</b></p>";
  } else if(answer53Btn.checked == true) {
    yourAnswerForQuestion5 = "24";
    output.innerHTML += "<p><b>Question #5: Incorrect ☹</b></p>";
  } else if(answer54Btn.checked == true) {
    yourAnswerForQuestion5 = "0.44";
    output.innerHTML +="<p><b>Question #5: Incorrect ☹</b></p>";
  } else if(answer55Btn.checked == true) {
    yourAnswerForQuestion5 = "144";
    output.innerHTML += "<p><b>Question #5: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#5 ☹</b></p>";
  }

 //QUESTION 6 (using if, else if, else)
  const answer61Btn = document.getElementById("answer61");
  const answer62Btn = document.getElementById("answer62");
  const answer63Btn = document.getElementById("answer63");
  const answer64Btn = document.getElementById("answer64");
  const answer65Btn = document.getElementById("answer65");
  let yourAnswerForQuestion6;
  if(answer65Btn.checked == true) {
    yourAnswerForQuestion6 = "16";
    output.innerHTML += "<p><b>Question #6: Correct! ☺️</b></p>";
  } else if(answer61Btn.checked == true) {
    yourAnswerForQuestion6 = "4"; 
    output.innerHTML += "<p><b>Question #6: Incorrect ☹</b></p>";
  } else if(answer62Btn.checked == true) {
    yourAnswerForQuestion6 = "2";
    output.innerHTML += "<p><b>Question #6: Incorrect ☹</b></p>";
  } else if(answer63Btn.checked == true) {
    yourAnswerForQuestion6 = "8";
    output.innerHTML += "<p><b>Question #6: Incorrect ☹</b></p>";
  } else if(answer64Btn.checked == true) {
    yourAnswerForQuestion6 = "1";
    output.innerHTML += "<p><b>Question #6: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#6 ☹</b></p>";
  }

   
 //QUESTION 7 (using if, else if, else)
  const answer71Btn = document.getElementById("answer71");
  const answer72Btn = document.getElementById("answer72");
  const answer73Btn = document.getElementById("answer73");
  const answer74Btn = document.getElementById("answer74");
  const answer75Btn = document.getElementById("answer75");
  let yourAnswerForQuestion7;
  if(answer75Btn.checked == true) {
    yourAnswerForQuestion7 = "450"; 
    output.innerHTML += "<p><b>Question #7: Correct! ☺️</b></p>";
  } else if(answer71Btn.checked == true) {
    yourAnswerForQuestion7 = "45";
    output.innerHTML += "<p><b>Question #7: Incorrect ☹</b></p>";
  } else if(answer72Btn.checked == true) {
    yourAnswerForQuestion7 = "15";
    output.innerHTML += "<p><b>Question #7: Incorrect ☹</b></p>";
  } else if(answer73Btn.checked == true) {
    yourAnswerForQuestion7 = "2";
    output.innerHTML += "<p><b>Question #7: Incorrect ☹</b></p>";
  } else if(answer74Btn.checked == true) {
    yourAnswerForQuestion7 = "4";
    output.innerHTML += "<p><b>Question #7: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#7 ☹</b></p>";
  }

 //QUESTION 8 (using if, else if, else)
  const answer81Btn = document.getElementById("answer81");
  const answer82Btn = document.getElementById("answer82");
  const answer83Btn = document.getElementById("answer83");
  const answer84Btn = document.getElementById("answer84");
  const answer85Btn = document.getElementById("answer85");
  let yourAnswerForQuestion8;
  if(answer83Btn.checked == true) {
    yourAnswerForQuestion8 = "12";
    output.innerHTML += "<p><b>Question #8: Correct! ☺️</b></p>";
  } else if(answer81Btn.checked == true) {
    yourAnswerForQuestion8 = "24"; 
    output.innerHTML += "<p><b>Question #8: Incorrect ☹</b></p>";
  } else if(answer82Btn.checked == true) {
    yourAnswerForQuestion8 = "3";
    output.innerHTML += "<p><b>Question #8: Incorrect ☹</b></p>";
  } else if(answer84Btn.checked == true) {
    yourAnswerForQuestion8 = "6";
    output.innerHTML += "<p><b>Question #8: Incorrect ☹</b></p>";
  } else if(answer85Btn.checked == true) {
    yourAnswerForQuestion8 = "8";
    output.innerHTML += "<p><b>Question #8: Incorrect ☹</b></p>";
  } else {
    output.innerHTML += "<p><b>No answer was selected for question#8 ☹</b></p>";
  }

//TEXT INPUT  
  let email = "";
  email = document.getElementById("email");
  if (email == "") {
    output.innerHTML += "<b>No email was entered!</b>";
  } else {
    output.innerHTML += "<b>Your email is: " + email.value;
  }

//DROP-DOWN  
  const survey = document.getElementById("type");
  let index = survey.selectedIndex;
  let surveyType = survey.options[survey.selectedIndex];
  let surveyText = survey.options[survey.selectedIndex].text;
  output.innerHTML += "<p><b>Self survey responce: <b>" + surveyText;
  let selfsurvey;
  if(type.checked == true) {
    selfsurvey = "Great";
    document.getElementById("type").innerHTML += "<b>Self Survey Responce: Great! 😊</b>";
  } 
  else if(answer41Btn.checked == true) {
    selfsurvey = "Okay"; 
    document.getElementById("type").innerHTML += "<b>Self Survey Responce: Okay~ 😑</b>";
  } 
  else if(answer42Btn.checked == true) {
    selfsurvey = "Badly";
    document.getElementById("type").innerHTML += "<b>Self Survey Responce: Badly...😞</b>";
  } 

//OUTPUT
  output.style.color = "black";
  output.style.background = "#FFE4E1";
  output.style.border = "groove 18px";
  output.style.margin = "auto";
  output.style.width = "80%";
  output.style.padding = "20px";
  output.style.display = "grid";
  output.style.fontFamily = "fantasy";

//SCORE CALCULATION
let scorePerQuestion = 12.5;
let totalScore = 0;

//QUESTION 1
if(answer11Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 2
if(answer22Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 3
if(answer35Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 4
if(answer43Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 5
if(answer51Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 6
if(answer65Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 7
if(answer75Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//QUESTION 8
if(answer83Btn.checked == true) {
  totalScore = totalScore + scorePerQuestion;
}
//FINAL GRADE (PERCENTAGE)
output.innerHTML += `<h2><b>Your Percentage Grade: ${totalScore}%</b></h2>`;

//FINAL GRADE (LETTER)
if (totalScore >= 90  && totalScore <= 100) {
  output.innerHTML += "<h2><b>Your Letter Grade: A+</b></h2>";
} else if (totalScore >=85 && totalScore <=89) {
  output.innerHTML += "<h2><b>Your Letter Grade: A</b></h2>";
} else if (totalScore >=80 && totalScore <=84) {
  output.innerHTML += "<h2><b>Your Letter Grade: A-</b></h2>";
} else if (totalScore >=77 && totalScore <=79) {
  output.innerHTML += "<h2><b>Your Letter Grade: B+</b></h2>";
} else if (totalScore >=73 && totalScore <=76) {
  output.innerHTML += "<h2><b>Your Letter Grade: B</b></h2>";
} else if (totalScore >=70 && totalScore <=72) {
  output.innerHTML += "<h2><b>Your Letter Grade: B-</b></h2>";
} else if (totalScore >=65 && totalScore <=69) {
  output.innerHTML += "<h2><b>Your Letter Grade: C+</b></h2>";
} else if (totalScore >=60 && totalScore <=64) {
  output.innerHTML += "<h2><b>Your Letter Grade: C</b></h2>";
} else if (totalScore >=55 && totalScore <=59) {
  output.innerHTML += "<h2><b>Your Letter Grade: C-</b></h2>";
} else if (totalScore >=50 && totalScore <=54) {
  output.innerHTML += "<h2><b>Your Letter Grade: D</b></h2>";
} else {
  output.innerHTML += "<h2><b>Your Letter Grade: F</b></h2>";
}
}
