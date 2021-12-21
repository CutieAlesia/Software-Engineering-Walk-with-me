import React from "react";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemText from "@mui/material/ListItemText";
import List from "@mui/material/List";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Avatar from "@mui/material/Avatar";
import { IP, jan, Key, getactiveUser } from "../variable";



var ID;
let username = [];


export default function Friendlist() {
  setID()

  const [loaded, setloaded] = React.useState(false);

  getallinfo().then(() => {
    setloaded(true)
  })
  return (
    <div>
     <List style={loaded? null:{display:"none"}}
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
        {username.map((value) => {
          const labelId = `usernamelist${value}`;
          return (
            <ListItem key={value} disablePadding disabled>
              <ListItemButton>
                <ListItemAvatar>
                  <Avatar
                    alt={`Avatar n°${value + 1}`}
                    src={`/static/images/avatar/${value + 1}.jpg`}
                  />
                </ListItemAvatar>
                <ListItemText id={labelId} primary={` ${value}`} />
              </ListItemButton>
            </ListItem>
          );
        })}
      </List>
    </div>
  );
}




function getid(index) {

  var str = window.location.href;
  return str.split("/")[index];

}
function setID() {
  ID = getid(5);
}

function getallinfo() {
  var URL = IP + "relations/getMatches?" + Key + "&id=" + ID;

  return asyncCall(URL)
}

async function asyncCall(URL) {
  try {
    let response = await fetch(URL);
    let user = await response.json();
    var i = 0;
    user.forEach((element) => {
      var url = IP + "relations/getMatches?" + Key + "&id=" + element.id;
      fetch(url).then(response => response.json())
        .then(function (data) {
          username[i] = data.username
          i = i + 1
        })
    });
    console.log(username)
  } catch (err) {
    console.log(err)
  }
}


