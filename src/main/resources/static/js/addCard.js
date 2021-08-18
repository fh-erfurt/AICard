/**
 * JS to display correct input field for each card type and card side in addCard.html
 */
window.addEventListener('DOMContentLoaded', (event) => {

    var cardFrontType = document.getElementById("cardFrontType");
    var cardBackType = document.getElementById("cardBackType");


    var textFileFrontInput = document.getElementById("textFileFrontInput");
    var pictureFileFrontInput = document.getElementById("pictureFileFrontInput");
    var videoFileFrontInput = document.getElementById("videoFileFrontInput");
    var audioFileFrontInput = document.getElementById("audioFileFrontInput");


    var textFileBackInput = document.getElementById("textFileBackInput");
    var pictureFileBackInput = document.getElementById("pictureFileBackInput");
    var videoFileBackInput = document.getElementById("videoFileBackInput");
    var audioFileBackInput = document.getElementById("audioFileBackInput");

    cardFrontType.addEventListener("change", function () {
        if (cardFrontType.value === "TextFile") console.log("TIE");

        if (cardFrontType.value === "TextFile") {
            console.log(cardFrontType.value);
            textFileFrontInput.style.display = "block";
            pictureFileFrontInput.style.display = "none";
            videoFileFrontInput.style.display = "none";
            audioFileFrontInput.style.display = "none";
        }
        if (cardFrontType.value === "AudioFile") {
            console.log(cardFrontType.value);
            textFileFrontInput.style.display = "none";
            pictureFileFrontInput.style.display = "none";
            videoFileFrontInput.style.display = "none";
            audioFileFrontInput.style.display = "block";
        }
        if (cardFrontType.value === "PictureFile") {
            console.log(cardFrontType.value);
            textFileFrontInput.style.display = "none";
            pictureFileFrontInput.style.display = "block";
            videoFileFrontInput.style.display = "none";
            audioFileFrontInput.style.display = "none";
        }
        if (cardFrontType.value === "VideoFile") {
            console.log(cardFrontType.value);
            textFileFrontInput.style.display = "none";
            pictureFileFrontInput.style.display = "none";
            videoFileFrontInput.style.display = "block";
            audioFileFrontInput.style.display = "none";
        }


    });

    cardBackType.addEventListener("change", function () {

        if (cardBackType.value === "TextFile") {
            textFileBackInput.style.display = "block";
            pictureFileBackInput.style.display = "none";
            videoFileBackInput.style.display = "none";
            audioFileBackInput.style.display = "none";
        }
        if (cardBackType.value === "PictureFile") {
            textFileBackInput.style.display = "none";
            pictureFileBackInput.style.display = "block";
            videoFileBackInput.style.display = "none";
            audioFileBackInput.style.display = "none";
        }
        if (cardBackType.value === "VideoFile") {
            textFileBackInput.style.display = "none";
            pictureFileBackInput.style.display = "none";
            videoFileBackInput.style.display = "block";
            audioFileBackInput.style.display = "none";
        }
        if (cardBackType.value === "AudioFile") {
            textFileBackInput.style.display = "none";
            pictureFileBackInput.style.display = "none";
            videoFileBackInput.style.display = "none";
            audioFileBackInput.style.display = "block";
        }
    });


});
