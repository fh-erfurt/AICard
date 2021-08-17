/**
 * JS to check if both entered passwords in registration.html and updateProfile.html are the same
 * throws alert Message and stops default
 */
window.addEventListener('DOMContentLoaded', (event) => {

    var password1 = document.getElementById("passwordProfessor1");
    var password2 = document.getElementById("passwordProfessor2");
    var submitButton = document.getElementById("submitButton");

    submitButton.addEventListener("click", function (event){
        if(password1.value !== password2.value){
            alert("Passwörter stimmen nicht überein");
            event.preventDefault();
        }
    });

});
