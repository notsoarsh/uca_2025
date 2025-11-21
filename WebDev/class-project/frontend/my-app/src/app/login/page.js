"use client";
import Link from "next/link";
import { useEffect } from "react";
import Styles from "./login.module.css";
import Button from "../components/button";

  //reavalidate
  // export const revalidate = 2;
  //why should we give revalidate as 0, ? it will revalidate after every sec
  //but since it static page it should not be rerendered
  //hence we dont use revalidate here
  //this page is static in term of html but not the behavior
  
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
      <div style={{ margin: "30px" }}>
        <h1>Welcome to login page</h1>

        <form className="max-w-sm mx-auto">
          <div className="mb-5">
            <label
              htmlFor="email-alternative"
              className="block mb-2.5 text-sm font-medium text-heading"
            >
              Your email
            </label>
            <input
              type="email"
              id="email-alternative"
              className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow placeholder:text-body"
              placeholder="name@flowbite.com"
              required
            />
          </div>
          <div className="mb-5">
            <label
              htmlFor="password-alternative"
              className="block mb-2.5 text-sm font-medium text-heading"
            >
              Your password
            </label>
            <input
              type="password"
              id="password-alternative"
              className="bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-base focus:ring-brand focus:border-brand block w-full px-3 py-2.5 shadow placeholder:text-body"
              placeholder="••••••••"
              required
            />
          </div>
          <div className="flex items-start mb-5">
            <label htmlFor="remember-alternative" className="flex items-center h-5">
              <input
                id="remember-alternative"
                type="checkbox"
                value=""
                className="w-4 h-4 border border-default-medium rounded-xs bg-neutral-secondary-medium focus:ring-2 focus:ring-brand-soft"
                required
              />
              <p className="ms-2 text-sm font-medium text-heading select-none">
                I agree with the{" "}
                <a href="#" className="text-fg-brand hover:underline">
                  terms and conditions
                </a>
                .
              </p>
            </label>
          </div>
          <Button variant="light">Login</Button>
        </form>
      </div>
      <Link href="/">Go to home</Link>
    </>
  );
}

//this is the default for the login path
//here classNameNameName cant be classNameName as classNameName is reserved
