function submitAction(id){
    const checkBoxes = document.getElementsByClassName("checkBoxes");
    var isChecked = false;
    for(var i =0; i< checkBoxes.length; i++){
        if(checkBoxes[i].checked){
            isChecked= true;
        }
    }
    if(isChecked) {
        document.getElementById("filmForm").method="post";
        const selectedFilms = Array.from(form.querySelectorAll('input[name="selectedFilms"]:checked'))
            .map((check) => (checkbox.value));
        const data = new URLSearchParams();
        selectedFilms.forEach((filmId) => {
            data.append("selectedFilms", filmId);
        });
        const url = "/song/" + id;
        fetch(url, {
            method: "POST",
            body: data
        });
    }
    else{
        alert("You must select at least one film!");

    }
}