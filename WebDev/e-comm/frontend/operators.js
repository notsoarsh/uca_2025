// Mostly used in react

//conditional (ternary) operator
// optional chaining operator
// nullish coalescing operator

//----------------------------------

// shorthand for if-else - ternary
  const isLoggedIn = false;
  const userName = isLoggedIn ? "John doe" : "Guest";
  console.log(userName);

// optional chaining

// Say we have a user
var user1 = {
  "academics" : {
    "college" : "collegename",
  }
}

//if we try to acccess user1.academics.profile.name , shows error
// so we can use user1.academics.profile?.name; //here we check if it exists

// ---------------------------------
// (High chance in interview)
// nullish coalescing operator - to provide default val for null or undefined
var firstName;
var lastName;
var usrName;

var displayName = firstName ?? lastName ?? userName ?? "guest";

// Where ever we are using . operator , we should always use (?.) , to check if the value is present or not before accessing