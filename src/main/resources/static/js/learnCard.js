/**
 * JS to rotate card during a LearningSession
 * enabled buttons to evaluate own cardknowledge after the backside of the card was first seen
 */
window.addEventListener('DOMContentLoaded', (event) => {
    var front = document.getElementById("front");
    var back = document.getElementById("back");
    var btnReverse = document.getElementById("reverse");

    var btnDumm = document.getElementById("dumm");
    var btnSchlau = document.getElementById("schlau");
    // TODO : buttons deaktivieren funktioniert noch nicht ganz -.-
    btnReverse.addEventListener("click",()=>{
        if(front.style.display == "block"){
            front.style.display = "none";
            back.style.display = "block";
            btnDumm.disabled = "";
            btnSchlau.disabled = "";
        }else{
            front.style.display = "block";
            back.style.display = "none";

        }
    });
});