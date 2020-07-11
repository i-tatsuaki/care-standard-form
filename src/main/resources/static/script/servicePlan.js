var selected = "0";

let selector = document.getElementById("select-service-plan");
let options = document.querySelectorAll("#select-service-plan option");
selector.addEventListener("change", () => {
    unShowTarget(selected);
    selected = options[selector.selectedIndex].value
    showTarget(selected);
});

let unShowTarget = (index) => {
   let showTarget = document.getElementById("output-" + index);
   showTarget.style.display="none";
}

let showTarget = (index) => {
    let showTarget = document.getElementById("output-" + index);
    showTarget.style.display="block";
}

showTarget(selected);