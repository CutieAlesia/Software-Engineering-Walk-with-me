import React from "react";
import { Grid, TextField, Paper, Button } from "@material-ui/core";
import { IP, jan, Key, setid, getid } from "../variable";

const Signuppage = () => {
  return (
    <div style={{ padding: 30 }}>
      <Paper>
        <Grid
          container
          spacing={3}
          direction={"column"}
          justify={"center"}
          alignItems={"center"}
        >
          <Grid item xs={12}>
            <TextField label="Username" id="Username"></TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField label="Password" id="Password"></TextField>
          </Grid>
          <Grid item xs={12}>

            <Button
              fullWidth
              variant="contained"
              onClick={() => {
                registration();
              }}
            >
              Registration
            </Button>
            <Button
              fullWidth
              variant="contained"
              onClick={() => {
                login();
              }}
            >
              {" "}
              Login{" "}
            </Button>
          </Grid>
        </Grid>
      </Paper>
    </div>
  );
};

export default Signuppage;

 function registration() {
  var username = document.querySelector("#Username").value
  var pw = document.querySelector("#Password").value
  var url = IP + 'user/newUser?' + Key + '&username=' + username + '&password=' + pw;
  var URL = IP + 'user/loginByUsername?' + Key + '&username=' + username + '&password=' + pw;


  if (username === "") {
    alert('bitte geben sie ein usernamen ein')
  } else {
    if (pw === "") {
      alert('bitte geben sie ein Passwort ein')
    } else {


      let data = {
        username: document.querySelector("#Username").value,
        password: document.querySelector("#Password").value
      }

      let fetchData = {
        method: 'POST',
        body: data,
        headers: new Headers()
      }


      fetch(url, fetchData).then(()=>{ 
        fetch(URL)
        .then(response => response.json())
        .then(function (data) {
          alert("Anmeldung abgeschlossen");
          window.location.href = "/main/" + data.id;
        }).catch((error) => {
          alert('user schon vorhanden')
        });})

     

    }
  }






}

function login() {
  window.location.href = "/";
}
