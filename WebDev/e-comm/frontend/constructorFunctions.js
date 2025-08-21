var studentsList = [
    {name: "name1" , age : 20, college : "colleg1"}
];

var maxMrks = 500;
var stduent1 = {
    fName : "name1",
    obtMrks : 500,
    checkRes : function() {
        let percent = (this.obtMrks / maxMrks) * 100;
        if(percent > 40) {
            return "pass";
        } else {
            return "fail";
        }
    }
    

};

stduent1.checkRes();
var students2 = new studentsList("name", 21, "college", 600);


// Approach 2 - Using Functions

function createStudent(fName, obtMrks){
    return { fName : fName,
    obtMrks : obtMrks ,
    checkRes : function() {
        let percent = (this.obtMrks / maxMrks) * 100;
        if(percent > 40) {
            return "pass";
        } else {
            return "fail";
        }
    }
  };
}


var std1 = createStudent("Arsh",350);
// undefined
var std2 = createStudent("Abhay" , 400);
// undefined
std1.checkRes()
// "pass"
std2.checkRes()
// "pass" 


//Approach 2 - Constructor Functions
// Whenever JS encounters new keyword, it creates a new object. Bind the this keyword

function createStudent(fName, obtMrks){
    console.log(this);
}

function student(fName, obtMarks) {
  this.fName = fName;
    this.obtMarks = obtMarks;
}
// undefined
var std1 = new student("arsh",100);
// undefined
this.std1
// Object { fName: "arsh", obtMarks: 100 }

var thisInsideStudentS;
function studentS(fName, obtMarks) {
  thisInsideStudentS = this;
  this.fName = fName;
  this.obtMarks = obtMarks;
  this.checkRes = function () {
    let percent = (this.obtMarks / maxMrks) * 100;
    if(percent > 40){
        return "pass";
    }else{
        return "false";
    }
  }
};
// undefined
var std2 = new studentS("arsh",200);
// undefined
std2 === thisInsideStudentS
// true 


//this manipulation in the student func prototype and available to all the instances of student objs
student.prototype.checkMarks = function() {
    return this.obtMarks;
}