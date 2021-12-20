import React from "react";
import { Grid, TextField, Paper, Button } from "@material-ui/core";
import { IP, jan, Key, setid, getid } from "../variable";



var ID = 0;
function Loginpage() {
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
            <TextField
              type="text"
              name="Email"
              id="Email"
              label="Email"
            ></TextField>
          </Grid>
          <Grid item xs={12}>
            <TextField
              type="Password"
              name="Password"
              id="Password"
              label="Password"
            ></TextField>
          </Grid>

          <Grid item xs={12}>
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
            <Button
              fullWidth
              variant="contained"
              onClick={() => {
                registration();
              }}
            >
              {" "}
              Registration{" "}
            </Button>
          </Grid>
        </Grid>
      </Paper>
    </div>
  );
}

export default Loginpage;

function login() {
  var URL = IP + 'user/loginByUsername?' + Key + '&username=' + document.querySelector("#Email").value + '&password=' + document.querySelector("#Password").value;
  
    fetch(URL)
      .then(response => response.json())
      .then(function (data) {
        alert("Anmeldung abgeschlossen");
        window.location.href = "/main/" + data.id;
      }).catch((error) => {
        alert('user oder passwort falsch')
      });
}

function registration() {
  window.location.href = "/signup";
}

