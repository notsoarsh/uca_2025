

var app = document.getElementById("reactapp");
var root = ReactDOM.createRoot(app);
// root.render(<DescriptionComponent name = "Arsh" />); //these are js not html

// function descriptionComponent(name) {
//   return <h1> React managed code {name} </h1>;
// }

//only those methods with pascal case naming are considered as react component

// function DescriptionComponent(props) {
//   return <h1>React managed code {props.name} </h1>    //standard
// }
//props are read only
// function DescriptionComponent({name}) {  //here name is a destructured project
//   // name = "Valid modified name"  //this should not be done js allows this but in react why would u want to change he arguement
//   return (
//     <> 
//     <h1>React managed code {name ?? "Guest User"} </h1>  
//     <h5>This is website description</h5>  
//     </>
//   ); 
// }
//we update the the virtual dom and react at runtime updates the delta of the dom


//* Notes */ 
// Runtime pe elements ko create krta hai
// Isse pehle js ke through populate krte the
// react ke ane se sirf jo required hai use bnate via dom

// Pehle display : none krke bhi krte the but same bat ki element dom mei pdha hua
// Jo element ni chaiye use hatao dom se jo chaiye wo rakho
// "jsx is basically dynamic html"
//Designing mei ek "BOX" ka concept hota hai, pages ko boxes mei divide krte

//Props are basically read only arguements , hence immutable


root.render(<App/>);

//We create a main component App jo render hoga

function App() {
  return (
    <div>
    <DescriptionComponent name = "Valid name"/>
    <LikesComponent/>
    </div>
  )
}

function DescriptionComponent(props) {
  // props.name = "Valid modified name"; //will throw error
  return (
    <>
    <h1>React managed code {props.name ?? "Guest User"}</h1>
    <p>Website description</p>
    </>
  )
}

//Now we will try to create a Likes Component , the one that gave birth to react

function LikesComponent(props) {
  // let likes = 25; //this is not the state 
  // function setLikes(){
  //   likes++;
  //   console.log(likes); // this does not update on the dom
  // }
   let [likes, setLikes] = React.useState(0); //useState are React hooks jo basically components pe changes krti dynamically

   function updateLikes() {
    setLikes(likes + 1); 
     //async function hai isliye console pe outdated value show hori, basically initialisation pe jo value hai wo async kam krke show hori isliye hume lgra purani val hai
    console.log("Likes", likes);
   }
    return (
    <>
    <p>Likes {likes}</p>
    <Button label="Like" updateLikes={updateLikes}/> 
    <Button label ="Dislike" updateLikes={updateLikes}/>
    </>
  );
};

//Creating a resuable component Button
function Button(props) {
  return (
    <button 
    style={{ 
      margin: "2px",
      padding: "5px",
      border: "2px solid black", 
      fontWeight: "600"}}
      onClick = {props.updateLikes}
      >
      {props.label}
     </button> 
  )
}

// React.useState() - ye react ka internal method hai jo ek array return krta hai, used for managing the state of the component. First element is the state of the component and second is the func that changes the state


























































































































































































































































