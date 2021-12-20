import { render } from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./Main/Main";
import Login from "./Main/Login";
import Singup from "./Main/Signup";
import Maintop from "./Main/Maintop";
import Mainfriends from "./Main/Mainfriends";
import MainOptions from "./Main/Mainoptions";
import MainProfile from "./Main/Mainprofil";
import Mainoptionsprofile from "./Main/Mainoptionprofil";
import Maindelet from "./Main/Maindelet";

const rootElement = document.getElementById("root");
import Switch from '@mui/material/Switch';
render(
  <BrowserRouter>
    <Routes>
      <Route path="main/:id" element={<Main />}  />
      <Route path="signup/" element={<Singup />} />
      <Route path="main/top/:id" element={<Maintop />} />
      <Route path="main/friends/:id" element={<Mainfriends />} />
      <Route path="main/options/:id" element={<MainOptions />} /> 
      <Route path="main/options/:id/profile" element={<Mainoptionsprofile />} /> 
      <Route path="main/options/:id/delet" element={<Maindelet />} /> 
      <Route path="main/:id/profile/:id" element={<MainProfile />} /> 
      <Route path="/" element={<Login />} />
    </Routes>
  </BrowserRouter>,
  rootElement
);
