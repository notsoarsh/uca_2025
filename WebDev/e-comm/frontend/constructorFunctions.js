var studentsList = [
    {name: "name1" , age : 20, college : "colleg1"}
];

var stduent1 = {
    name : "name1",
    age : 20,
    college : "college1",
    finalResult : function(){
        return `${this.name} is ${this.age} years old of ${this.college}`;
    }

};

var students2 = new studentsList("name", 21, "college", 600);
