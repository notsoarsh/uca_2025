


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
    <Likes2Component/>
    <ProductsComponenet/>
    <FormComponent/>
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

//DOM update wrt to React is component re render
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
    <Button label="Like" clickBehavior={updateLikes}/> 
    <Button label ="UndoLike" clickBehavior={updateLikes}/>
    </>
  );
};

function Likes2Component() {
  const [liked, setLiked] = React.useState("no");
  // React.useEffect(() => {
  //   //call on every render
  // });

// --------------------------------------------------->
  // React.useEffect(() => {
  //   //call only once on component mount
  //   let data = fetchData();
  //   initialLikes = data;
  //   return () => {}; //cleanup function

  // } , []);

  // function fetchData() {
  //   //code to fetch data from backend
  // }
  // fetchData(); //This will lead to fetch data funciton being called for every re-render

  //Open mei koi bhi js functionality call ni krni ya stack change ni krna!!!!!!!!!!

  // ------------------------------------------------->
  // React.useEffect(() => {
  // //fetch the backend data

  // }, [liked]); 

  const updateLiked = () => {
    if(liked === "no") {
      setLiked("yes");
    } else {
      setLiked("no");
    }  
  };

  return (
    <>
    <br/>
    <section>
    This is my first comment.
    </section>
    <p>Liked - {liked}</p>
    <Button label={liked === "yes" ? "no" : "yes"} clickBehavior = {updateLiked}/>
    </>
  );
}
//Semantic elements are those markups those tell what is the purpose of the tag , helps in SEO , eg section

//Creating a resuable component Button
function Button(props) {
  return (
    <button 
    style={{ 
      margin: "2px",
      padding: "5px",
      border: "2px solid black", 
      fontWeight: "600"}}
      onClick = {props.clickBehavior}
      >
      {props.label}
     </button> 
  )
}


function FormComponent(props) {
  let tempVariable = React.useRef(10);
  const [arr1, setArr1] = React.useState(["TextBox1", "TextBox2", "TextBox3"]);
  function changeOrder() {
    const updatedArr = [...arr1].reverse();
    console.log(tempVariable);
    tempVariable += 20;
    setArr1(updatedArr);
  }

  // React.useEffect(() => {
  //   fetchProductList();
  // }, [arr1]);
  return (
  <div>
    {
      arr1.map((item) =>  {
        return(
        <div>
        <input type="text" placeholder={item}></input>
        </div>
      )
        
      })}
      <Button label="Change Order" clickBehavior={changeOrder}/>
        <div>Hello World!</div>
  </div>
  )
}
function ProductsComponenet(props) {
  const [productList, setProductList] = React.useState([]);
  React.useEffect(() => {
    setTimeout(() => {
      setProductList(productsListFromServer);
    }, 2000);
  }, []);

  const [show, setShow] = React.useState(false);
  function toggleDisplayText() {
    setShow(!show);
  }
  console.log(props.products);
  return (
    <>
    <h3>Products List</h3>
    {
      productList.length === 0 ? (<p>Loading data from server...</p>) : 
      (<table>
            <thead>
                <tr>
                    <th>Serial Number</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    
                </tr>
            </thead>
            <tbody>
                {productList.map((product,index) => {
                  return (
                    <tr key={`index-${product.name}`}> 
                        <td>
                            {index+1}
                        </td>
                        <td>
                            {product.name}
                        </td>
                        <td>
                            {product.price}
                        </td>
                        <td>
                            {product.desc}
                        </td>
                    </tr>
                  );
                })}
            </tbody>
        </table>)
    }
    
    <ol>
      <li>P1</li>
      <li>P2</li>
    </ol>  
     {
        show ? <p>{show ? "Welcome user" : ""}</p> : ''
      }
      <Button label= "Show welcome text" clickBehavior={toggleDisplayText}/>
      
    </>
    
  );
}

// React.useState() - ye react ka internal method hai jo ek array return krta hai, used for managing the state of the component. First element is the state of the component and second is the func that changes the state

//Here Button component is child and Likes Componenent is parent.
//Updation in child has to be controlled by child thats why we send "label" in likes component for the button.

//Any updates to the dom, react calls it a re render

//Component sirf 3 bar hi render hoga, jab props update hoga, jab state hoga ya phir jab parent component re-render hoga
//Re-render - Jo state ko change krne wala func tha wo call hora 

//React mei lets say HTML render krta js dwara say for eg "likes - 0" and wo update hora bar bar and lets say ki ye ek p tag mei tha, html bar bar is p tag ko rerender krega , lekin react kya krega sirf state ko change krega, mtlb likes - "(incremented by react)"...refer to dynamicNode example

// React hooks - Hooks are basically reusable methods that provides a way to customise any coponent/ change the state of the react component when the ui is rendering, re- rendering, even trigger a rerender etc, component is flushed out of dom
// Some react hooks :
  // -- useState
  // -- useEffect ... 1st -What to do , 2nd When to do
  // when to do -> 3 variations decided by an array
  // -> empty array - call it only once when the component is mounted
  // -> no array - call everytime the component is rendered
  // -> array with dependencies - //call when the dependency is rendered
  // -- customHooks




//Whenever we are rendering a list , always have a key property , that ensures uniqueness in sorting and other operations.
//Key used to make the code work properly with React. React me jab updates krne data mei , tab index wala feature kam ata, kaise batch bnane.
//LETS say ki list mei koi element uda diya , to react ko delta maintain kra to jis index pe change hua wo delta hoga


// Jab inna kam React krvara browser se, to Client Site Rendering bahut heavy hoti, isliye react ne collab kiya Nextjs sath ki jo kam server side render ho wo hum server se render kre



//How to get some value from child to parent... CALLBACK!!!!
// function foo1() {
//     let x = 20;
//     let rValue;
//     foo2(x, innerCallBack);
//     function innerCallBack(arg2) {
//         rValue = arg2;
//     }
//     console.log(rValue);
// }

// function foo2(arg1, innerCallBack) {
//     console.log(arg1);
//     innerCallBack(30);
// }

// foo1();

// ************** PARENT 
//                  |
//                  |
//                /   \                        LETS SAY WE WANT TO ACCESS DATA FROM CHILD1 TO CHILD 2 , WE USE                                              CALLBACKS TO UPDATE THE PARENT AND FROM THERE WE CAN ACCESS
//              /       \
//             /         \
//           CHILD1       CHILD2

//cmds for nextjs 
// npx create-next-app@latest




































































































































































































































