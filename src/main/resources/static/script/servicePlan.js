var selected = "0";
let options = document.querySelectorAll("#select-service-plan option");

Array.from(document.getElementsByClassName("user-select")).forEach(selectItem => {
    selectItem.addEventListener("click",
        (event) => {
            unShowTarget(selected);
            selected = event.target.dataset.value
            showTarget(selected);
        }
    )
})

let unShowTarget = (index) => {
   let showTarget = document.getElementById("output-" + index);
   showTarget.style.display="none";
}

let showTarget = (index) => {
    let showTarget = document.getElementById("output-" + index);
    showTarget.style.display="block";
}

showTarget(selected);