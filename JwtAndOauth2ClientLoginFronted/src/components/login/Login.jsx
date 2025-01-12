import axios from "axios";
import { useContext, useState } from "react";


const Login = ()=>{
    return (
        <div className="login">
          <div className="lContainer">
            <input
              type="text"
              placeholder="username"
              id="username"
              //onChange={handleChange}
              className="lInput"
            />
            <input
              type="password"
              placeholder="password"
              id="password"
              //onChange={handleChange}
              className="lInput"
            />
            <button  className="lButton">
              Login
            </button>
          </div>
          <a href="http://localhost:8090/oauth2/authorization/google">google</a>
        </div>
      );
}

export default Login;