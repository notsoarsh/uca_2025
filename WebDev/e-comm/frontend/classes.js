//Class in js
//Classes are just the syntax on top of the existing prototype-based inheritance and constructor functions


//Introduced with es6
/** Classification of classes
 * Constructor
 * Inheritance
 */

//What was the problem ? 
// Frameworks like react implemented class based approach much earlier the popular frameworks like React and Angular
// Browser could not identify the class

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
        return "fail";
    }
  }
};
// undefined
var std1 = new studentS("arsh",200);
var std2 = new studentS("arshpal",300);

//problem with this approach is that there are multiple functions created for different objects

//Class based approach

class Student {
    constructor (fName, obtainedMarks) {
      this.fName = fName;
      this.obtainedMarks = obtainedMarks;
    }
    checkResult () {
        let percentage = (this.obtainedMarks / maxMrks) * 100;
        if (percentage > 40) {
          return "pass";
        } else {
            return "fail";
        }
    }
}

//Another way for constructor function (Optimised)
var thisInsideStudentS;
function studentConst(fName, obtMarks) {
  thisInsideStudentS = this;
  this.fName = fName;
  this.obtMarks = obtMarks;
};

studentConst.prototype.checkRes = function() {
    let percent = (this.obtMarks / maxMrks) * 100;
    if(percent > 40){
        return "pass";
    }else{
        return "fail";
    }
}



/////////////////////
// Transpilers were something that allowed browser to understand code that is not js.
//Eg Babel


