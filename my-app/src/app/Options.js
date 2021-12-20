import React from "react";
import Button from "@mui/material/Button";


var ID;

export default function Options() {
  setID()
  return (
    <div>
      <Button
        fullWidth
        sx={{ m: 0.5 }}
        variant="contained"
        onClick={() => {
          profil();
        }}
      >
        {" "}
        Profil{" "}
      </Button>

      <Button
        fullWidth
        sx={{ m: 0.5 }}
        variant="contained"
        onClick={() => {
          prefi();
        }}
      >
        {" "}
        Preferenze{" "}
      </Button>
      <Button
        fullWidth
        sx={{ m: 0.5 }}
        variant="contained"
        onClick={() => {
          bilder();
        }}
      >
        {" "}
        Preferenze{" "}
      </Button>
      <Button
        fullWidth
        sx={{ m: 0.5 }}
        variant="contained"
        onClick={() => {
          acountlöschen();
        }}
      >
        {" "}
        Account löschen{" "}
      </Button>
    </div>
  );
}


function setID() {
  ID = getid(5);
  console.log(ID)
}
function getid(index) {

  var str = window.location.href;
  return str.split("/")[index];

}



function profil() {
  window.location.href = "/main/options/" + ID + "/profile";
}
function prefi() { }
function bilder() { }
function acountlöschen() {
  window.location.href = "/main/options/" + ID + "/delet";
}