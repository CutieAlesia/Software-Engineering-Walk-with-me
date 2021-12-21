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


let fetchData = { 
    method: 'POST', 
    headers: new Headers()
}

var ID;
var IDsearch;
var Username;
var bio;


export default function Swipe() {
    const [value, setValue] = React.useState("Controlled");
    setID()
    getallinfo()
    return (
        <div className="swipe">
            <Grid container spacing={3} height={400}>
                <Grid item xs={9}>
                    <Item>
                        <Avatar
                            sx={{ width: "100%", height: 250 }}
                            alt="Aemy Sharp"
                            src="/static/images/avatar/1.jpg"
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
                        <Button variant="contained" sx={{ width: "100%", height: 100 }} onClick={() => { Add(); }}>
                            ADD
                        </Button>
                        <Button variant="contained" sx={{ width: "100%", height: 100 }}onClick={() => { Block(); }}>
                            BLock
                        </Button>
                        <Button variant="contained" sx={{ width: "100%", height: 100 }}onClick={() => { Report();}}>
                            Report
                        </Button>
                    </Item>
                </Grid>
            </Grid>

            <Grid container spacing={3} height={170}>
                <Grid item xs={3}>
                    <Item>
                        <TextField
                            sx={{ width: "100%", height: 120 }}
                            id="Bio"
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

            </Grid>
        </div>
    );
}


function Add(){
    var URL = IP + "relations/changeLike?" + Key + "&id=" + ID + '&id2=' + IDsearch+"&like="+1;
    fetch(URL, fetchData)

}
function Block(){
    var URL = IP + "relations/addBlock?" + Key + "&id=" + ID+'&id2='+IDsearch;
    fetch(URL, fetchData)
}
function Report(){
}

function getid(index) {

    var str = window.location.href;
    return str.split("/")[index];

}

function setID() {
    ID = getid(4);
    console.log(ID)
    IDsearch = getid(6);
    console.log(IDsearch)
}



function getallinfo() {
    var URL = IP + "info/getUser?" + Key + "&id=" + IDsearch;
    asyncCall(URL)

}

async function asyncCall(URL) {
    let response = await fetch(URL);
    let user = await response.json();
    Username =  user.username
    bio =  user.bio
    document.querySelector("#Bio").value = bio
    document.querySelector("#Username").value = Username


}