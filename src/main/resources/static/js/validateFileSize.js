/**
 * JS to check if the entered file has a maximum size of 50mb
 * throws alert Message and removes file from input
 *
 * if manipulated the server will throw an error
 */
window.addEventListener('DOMContentLoaded', (event) => {

    var pictureFileFrontInput = document.getElementById("cardFrontPictureFileInput");
    var videoFileFrontInput = document.getElementById("cardFrontVideoFileInput");
    var audioFileFrontInput = document.getElementById("cardFrontAudioFileInput");

    var pictureFileBackInput = document.getElementById("cardBackPictureFileInput");
    var videoFileBackInput = document.getElementById("cardBackVideoFileInput");
    var audioFileBackInput = document.getElementById("cardBackAudioFileInput");

    var submitButton = document.getElementById("submitButton");
    const maxFileSize = 50 * 1024 * 1024;

    var fileInputArray = [pictureFileFrontInput, videoFileFrontInput, audioFileFrontInput,
        pictureFileBackInput, videoFileBackInput, audioFileBackInput];

    fileInputArray.forEach(item => {
        item.addEventListener('change', event => {
            console.log(item.files[0].size);
            console.log(maxFileSize);
            if (item.files[0].size >= maxFileSize) {
                item.value = '';
                alert("Eingegeben Datei überschreitet 50mb und wurde entfernt!");
                console.log("file too big");
            }

        })
    })


    //
    // pictureFileFrontInput.addEventListener("change", function ()
    // {
    //     checkFilesize(this);
    // });
    //
    // function checkFilesize(fileInput) {
    //     console.log(fileInput.files[0].size );
    //     console.log(maxFileSize);
    //     if(fileInput.files[0].size >= maxFileSize) {
    //         fileInput.value = '';
    //         alert("Eingegeben Datei überschreitet 50mb und wurde entfernt!");
    //         console.log("file too big");
    //     };
    // };
});
