// window.addEventListener('DOMContentLoaded', (event) => {
//     const cardDiv = document.getElementById("addCard");
//     const btn = document.getElementById("add");
//
//     const kase = document.getElementById("kase");
//
//     btn.addEventListener("click",()=>{
//         var addCard = document.createElement("div");
//         addCard.classList.add("card");
//
//         var form = document.createElement("form");
//         form.method = "post";
//         form.action = "/addCard";
//
//         var input1 = document.createElement("input");
//         input1.classList.add("txt-input");
//         input1.name ="front";
//         input1.type = "text";
//         form.appendChild(input1);
//
//         var input2 = document.createElement("input");
//         input2.classList.add("txt-input");
//         input2.name ="back";
//         input2.type = "text";
//         form.appendChild(input2);
//
//         var save = document.createElement("button");
//         save.classList.add("btn");
//         save.classList.add("save");
//         save.id = "saveCard";
//         save.innerHTML = "save";
//         save.name = "save";
//         form.appendChild(save);
//
//         var del = document.createElement("button");
//         del.classList.add("btn")
//         del.classList.add("del");
//         del.id = "deleteCard";
//         del.innerHTML = "X";
//         del.name = "delete";
//         form.appendChild(del);
//
//         addCard.appendChild(form);
//         cardDiv.appendChild(addCard);
//     });
// });
//
