import React from "react";
import { Link as RouterLink, MemoryRouter as Router } from "react-router-dom";
import { ButtonGroup } from "@mui/material";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import List from "@mui/material/List";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Divider from "@mui/material/Divider";
import Friends from "../app/Friendlist";
import { IP, jan, Key } from "../variable";

var ID;
const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary
}));

export default function Main() {
  setID()
  return (
    <div className="Main">
      <Grid container spacing={2}>
        <Grid item xs={9}>
          <Item>
            <h1>Walk with Me</h1>
          </Item>
        </Grid>
        <Grid item xs={3}>
          <Item>
            <Button
              variant="contained"
              component={RouterLink}
              to="/"
              onClick={() => {
                logout();
              }}
            >
              Logout
            </Button>
          </Item>
        </Grid>
      </Grid>
      <Divider textAlign="right">
        <TextField
          sx={{ bgcolor: "white" }}
          id="userserchtext"
          label="User"
          variant="outlined"
        />
        <Button
          variant="contained"
          onClick={() => {
            search();
          }}
        >
          Search
        </Button>
      </Divider>
      <Divider>
        <ButtonGroup
          variant="contained"
          aria-label="outlined primary button group"
        >
          <Button
            onClick={() => {
              swipen();
            }}
          >
            Swipen
          </Button>

          <Button
            onClick={() => {
              topuser();
            }}
          >
            Top User
          </Button>
          <Button
            onClick={() => {
              freundesliste();
            }}
          >
            Freundesliste
          </Button>
          <Button
            onClick={() => {
              einstellungen();
            }}
          >
            Einstellungen
          </Button>
        </ButtonGroup>
      </Divider>

      <Grid container spacing={3}>
        <Grid item xs>
          <Item>
            <List
              sx={{
                width: "100%",
                maxWidth: "100%",
                bgcolor: "background.paper",
                overflow: "auto",
                maxHeight: 500,
                "& ul": { padding: 0 }
              }}
              subheader={<li />}
            >
              {[0, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1].map((value) => {
                const labelId = `checkbox-list-secondary-label-${value}`;
                return (
                  <ListItem key={value} disablePadding>
                    <ListItemButton>
                      <ListItemAvatar>
                        <Avatar
                          alt={`Avatar n°${value + 1}`}
                          src={`/static/images/avatar/${value + 1}.jpg`}
                        />
                      </ListItemAvatar>
                      <ListItemText
                        id={labelId}
                        primary={`Line item ${value + 1}`}
                      />
                    </ListItemButton>
                  </ListItem>
                );
              })}
            </List>
          </Item>
        </Grid>
        <Grid item xs={6}>
          <Item>
            <Friends />
          </Item>
        </Grid>
        <Grid item xs>
          <Item>
            <List
              sx={{
                width: "100%",
                maxWidth: "100%",
                bgcolor: "background.paper",
                position: "relative",
                overflow: "auto",
                maxHeight: 250,
                "& ul": { padding: 0 }
              }}
              subheader={<li />}
            >
              {[0, 1, 2, 3, 3, 3, 3, 3, 3, 3].map((value) => {
                const labelId = `checkbox-list-secondary-label-${value}`;
                return (
                  <ListItem key={value} disablePadding>
                    <ListItemButton>
                      <ListItemAvatar>
                        <Avatar
                          alt={`Avatar n°${value + 1}`}
                          src={`/static/images/avatar/${value + 1}.jpg`}
                        />
                      </ListItemAvatar>
                      <ListItemText
                        id={labelId}
                        primary={`Line item ${value + 1}`}
                      />
                    </ListItemButton>
                  </ListItem>
                );
              })}
            </List>

            <List
              sx={{
                width: "100%",
                maxWidth: "100%",
                bgcolor: "background.paper",
                position: "relative",
                overflow: "auto",
                maxHeight: 250,
                "& ul": { padding: 0 }
              }}
              subheader={<li />}
            >
              {[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0].map((value) => {
                const labelId = `checkbox-list-secondary-label-${value}`;
                return (
                  <ListItem key={value} disablePadding>
                    <ListItemButton>
                      <ListItemAvatar>
                        <Avatar
                          alt={`Avatar n°${value + 1}`}
                          src={`/static/images/avatar/${value + 1}.jpg`}
                        />
                      </ListItemAvatar>
                      <ListItemText
                        id={labelId}
                        primary={`Line item ${value + 1}`}
                      />
                    </ListItemButton>
                  </ListItem>
                );
              })}
            </List>
          </Item>
        </Grid>
      </Grid>
    </div>
  );
}


function logout() {

  window.location.href = "/";
}
function swipen() {
  console.log(Username)
    window.location.href = "/main/" + ID;
  
}
function topuser() {
  window.location.href = "/main/top/" + ID;
}
function freundesliste() {
  window.location.href = "/main/friends/" + ID;
}
function einstellungen() {
  window.location.href = "/main/options/" + ID;
}
function search() { 
  var URL = IP + 'user/findByUsername?'+Key+'&username='+document.querySelector("#userserchtext").value;
  fetch(URL)
    .then(response => response.json())
    .then(function (data) {
    window.location.href = "/main/"+ID+'/profile/'+data.id;
    })  
  
   

}

function getid(index) {

  var str = window.location.href;
  return str.split("/")[index];

}
function setID() {
  ID = getid(5);
  console.log(ID)
}
