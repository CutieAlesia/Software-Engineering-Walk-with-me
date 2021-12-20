import React from "react";

import { ButtonGroup } from "@mui/material";

import Button from "@mui/material/Button";


import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Divider from "@mui/material/Divider";
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


export default function Swipe() {
    const [value, setValue] = React.useState("Controlled");
    setID()
    return (


        <div className="swipe">
            Möchten sie ihren account pemanent löschen ?
            <Divider>
                <ButtonGroup
                    variant="contained"
                    aria-label="outlined primary button group"
                >
                    <Button
                        onClick={() => {
                            löschen();
                        }}
                    >
                        Ja
                    </Button>

                    <Button
                        onClick={() => {
                            nichtlöschen();
                        }}
                    >
                        Nein
                    </Button>
                </ButtonGroup>
            </Divider>

        </div>
    );
}


async function löschen() {
    var url = IP + "user/deleteUser?" + Key + "&id=" + ID


    let fetchData = {
        method: 'POST'
    }
    await fetch(url, fetchData)
    window.location.href = "/";
}


function nichtlöschen() {
    window.location.href = "/main/"+ID;
 }



function getid(index) {

    var str = window.location.href;
    return str.split("/")[index];

}
function setID() {
    ID = getid(5);
    console.log(ID)
}