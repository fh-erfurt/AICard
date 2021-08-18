/**
 *  JS to show and unshow friendTable in editLearnSet.html
 */
window.addEventListener('DOMContentLoaded', (event) => {
    var button = document.getElementById("showAddFriendsAsAdminTable");
    var table = document.getElementById("friendTable");

    button.addEventListener("click", () => {
        console.log("button Pressed");
        if (table.style.display === "none") {
            table.style.display = "block";
        } else {
            table.style.display = "none";
        }
    })

});