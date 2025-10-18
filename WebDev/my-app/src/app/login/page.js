"use client";
import Link from "next/link";
import { useEffect } from "react";

export default function Login() {
  // localStorage.setItem("token" , "1234"); //doesnt work as a browser feature . even if we use useclient this line wont be skipped , and this needs to be used with useclient and useEffect
  // useEffect(() => {
  //   localStorage.setItem("token" , "1234"); 
  // },[]);

  const loginHandler = (e) => {
    //Do login here

  };
  return (
    <>
    <div style={{margin : "30px"}}>
    <h1>Welcome to login page
    </h1>
    <form>
      <input type="text" placeholder="Username"/>
      <br />
      <input type="password" placeholder="Password"/>
      <br/>
      <button type="submit" onClick={loginHandler}>Login</button>
      </form>
    </div>
    <Link href="/">Go to home</Link>
    </>
    
  );
}

//this is the default for the login path
