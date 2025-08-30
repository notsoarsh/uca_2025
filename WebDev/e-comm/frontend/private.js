
class Student {
    #fName;
    constructor (fName, obtainedMarks) {
      this.#fName = fName;
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
    
    getName() {
        return this.#fName;
    }
}
var student1 = new Student("name1", 234);
// console.log(student1.fName); // throws error
let FName = student1.getName();
console.log(FName);


/**
 * Private properties are the properties that are not accesible outside the class.
 * They are declared using a #,and even if we want to access the property we use a hash 
 */