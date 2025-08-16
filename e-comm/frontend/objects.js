//Creating an object using object literal
var studentObj1 = {
    name : "name",
    age : 20,
    isActive : true,
    calculate : function() {
        return this.age;
    }

}


var objUsingNewObj1 = new Object();  //creates an empty object
var objUsingNewObj2 = new Object({name : "Arsh"});   

//if we want to create a new obj using an existing one

var objUsingAssign = Object.assign({}, objUsingNewObj1); //.assign([destination], [source])
var objUsingAssign = Object.assign(objUsingNewObj1, {age : 20}); //.assign([destination], [source])



// ---------------Example------------------
var objUsingLiteral1 = {
    name : "name",
    age : 20,
    isActive : true,
    calculate : function() {
        return age * 2;
    }
}

var obj2UsingAssign = objUsingLiteral1; //here it is passed by reference
//now any change in obj2UsingAssign will be reflected on obj1UsingLiteral


// ---- Example for Object.create() -------
var obj1UsingCreate = {
  name : "name1",
}
// undefined
var obj2UsingCreate = Object.create(obj1UsingCreate);
// undefined
obj2UsingCreate.name
// "name1"
obj2UsingCreate.name = "Arsh"
// "Arsh"
obj2UsingCreate.name
// "Arsh"
obj1UsingCreate.name
// "name1"

// ** Object.create() says that create a new object using the prototype of the existing object **


/**
 * When we are comparing two objects, their reference is checked not the value
 * For eg 
 * var x1 = {a : 1};
 * var x2 = {a : 1};
 * x1 == x2 , gives false
 * but if
 * var x1 = x2;
 * x1 == x2 returns true
 */

// If we still want to change the properties of the parent object using Object.create() , we use __proto__ 


// // --------------- Example for Prototypical Inheritance --------------
// var objUsingcreate1 = {
//     name : "name",
//     age : 20,
// }

// var objUsingCreate2 = Object.create(obj1UsingCreate);

// objUsingcreate1.name
// 'name'
// objUsingCreate2.name
// 'name'

// Actual new objects are create when
 // - {} is used
 // using spread operator




