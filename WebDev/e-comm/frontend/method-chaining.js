// function inifiniteConcat(stringToConcat) {
//   return this + " " + stringToConcat;
// }

// function customString(stringToConcat) {
//   return {
//     infiniteConcat: function () {
//       return customString(stringToConcat);
//     },
//   }
// }

// var x = customString("Hello");
// x.infiniteConcat("world");


/** When a function returns an object allowing you to call another method on that object, the returned object has atleast one property that is a function on that object 
 * 
 */

function customString(initialValue) {
  this.value = initialValue;

  this.infiniteConcat = function(stringToConcat) {
    let newValue = this.value + " " + stringToConcat;
    return new customString(newValue);
  }
}

var x = new customString("hello");

x.infiniteConcat("world"); //hello world
x.infiniteConcat("cat") //hello cat
// but x still is hello
