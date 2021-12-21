import React from "react";
import "../styles.css";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import Button from "@mui/material/Button";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";
import Avatar from "@mui/material/Avatar";
import TextField from "@mui/material/TextField";
import { IP, jan, Key, getactiveUser } from "../variable";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary
}));
var ID;
var Username = "A";
var randomID = 0;
var bio = "";
var profilpic = "";








var likedislike

let fetchData = {
  method: 'POST',
  data: likedislike,
  headers: new Headers()
}

export default function Swipe() {
  const [value, setValue] = React.useState("Controlled");
  setID(),
    getallinfo()
  return (
    <div className="swipe">
      <Grid container spacing={3} height={400}>
        <Grid item xs={3}>
          <Item>
            <Button sx={{ width: "100%", height: 300 }} variant="contained" onClick={() => { Like(); }}>
              Like
            </Button>
          </Item>
        </Grid>
        <Grid item xs={6}>
          <Item>
            <Avatar
              sx={{ width: "100%", height: 250 }}
              alt={Username}
              src={profilpic}
            />
            <TextField
              sx={{ width: "100%", height: 50 }}
              id="Username"
              multiline
              maxRows={4}
              disabled
            />
          </Item>
        </Grid>
        <Grid item xs={3}>
          <Item>
            <Button variant="contained" sx={{ width: "100%", height: 300 }} onClick={() => { Dislike(); }}>
              Dislike
            </Button>
          </Item>
        </Grid>
      </Grid>

      <Grid container spacing={3} height={170}>
        <Grid item xs={3}>
          <Item>
            <TextField
              sx={{ width: "100%", height: 120 }}
              id="bio"
              multiline
              maxRows={4}
              disabled
            />
          </Item>
        </Grid>
        <Grid item xs={6}>
          <Item>
            {" "}
            <TextField
              sx={{ width: "100%", height: 120 }}
              id="outlined-multiline-flexible"
              multiline
              maxRows={4}
              disabled
            />
          </Item>
        </Grid>
        <Grid item xs={3}>
          <Item>
            {" "}
            <ImageList
              sx={{ width: "100%", height: 90 }}
              cols={3}
              rowHeight={90}
            >
              {itemData.map((item) => (
                <ImageListItem key={item.img}>
                  <img
                    src={`${item.img}?w=164&h=164&fit=crop&auto=format`}
                    srcSet={`${item.img}?w=164&h=164&fit=crop&auto=format&dpr=2 2x`}
                    alt={item.title}
                    loading="lazy"
                  />
                </ImageListItem>
              ))}
            </ImageList>
          </Item>
        </Grid>
      </Grid>
    </div>
  );
}
function setID() {
  ID = getid(4);
  console.log(ID)
}
function getid(index) {

  var str = window.location.href;
  return str.split("/")[index];

}
function getallinfo() {
  var URL = IP + "user/getMatch?" + Key + "&first=" + ID;
  asyncCall(URL)
}

async function asyncCall(URL) {
  let response = await fetch(URL);
  let user = await response.json();
  randomID = await user.id
  Username = await user.username
  document.querySelector("#Username").value = Username
  var URLa = IP + "info/getUser?" + Key + "&id=" + randomID
  response = await fetch(URLa);
  user = await response.json();
  Username = await user.username
  document.querySelector("#Username").value = Username
  bio = await user.bio
  document.querySelector("#bio").value = bio
  profilpic = await user.avatar
}

async function Like() {

  var URL = IP + "relations/changeLike?" + Key + "&id=" + ID + '&id2=' + randomID + "&like=" + 1;
  console.log(URL)
  await fetch(URL, fetchData)
  window.location.href = "/main/" + ID;
}

async function Dislike() {
  var URL = IP + "relations/changeLike?" + Key + "&id=" + ID + '&id2=' + randomID + "&like=" + 2;
  await fetch(URL, fetchData)
  window.location.href = "/main/" + ID;
}



const itemData = [
  {
    img: "https://images.unsplash.com/photo-1551963831-b3b1ca40c98e",
    title: "Breakfast"
  },
  {
    img: "https://images.unsplash.com/photo-1551782450-a2132b4ba21d",
    title: "Burger"
  },
  {
    img: "https://images.unsplash.com/photo-1522770179533-24471fcdba45",
    title: "Camera"
  },
  {
    img: "https://images.unsplash.com/photo-1444418776041-9c7e33cc5a9c",
    title: "Coffee"
  },
  {
    img: "https://images.unsplash.com/photo-1533827432537-70133748f5c8",
    title: "Hats"
  },
  {
    img: "https://images.unsplash.com/photo-1558642452-9d2a7deb7f62",
    title: "Honey"
  },
  {
    img: "https://images.unsplash.com/photo-1516802273409-68526ee1bdd6",
    title: "Basketball"
  },
  {
    img: "https://images.unsplash.com/photo-1518756131217-31eb79b20e8f",
    title: "Fern"
  },
  {
    img: "https://images.unsplash.com/photo-1597645587822-e99fa5d45d25",
    title: "Mushrooms"
  },
  {
    img: "https://images.unsplash.com/photo-1567306301408-9b74779a11af",
    title: "Tomato basil"
  },
  {
    img: "https://images.unsplash.com/photo-1471357674240-e1a485acb3e1",
    title: "Sea star"
  },
  {
    img: "https://images.unsplash.com/photo-1589118949245-7d38baf380d6",
    title: "Bike"
  }
];
