function submitAction(id){ // function to get the list of checked checkboxes in addFilms form in a song page
    const checkBoxes = document.getElementsByClassName("checkBoxes"); // get the form
    var isChecked = false; // check that the user checked at least one checkbox
    for(var i =0; i< checkBoxes.length; i++){
        if(checkBoxes[i].checked){
            isChecked= true;
        }
    }
    if(isChecked) { // if any checkbox is checked:
        document.getElementById("filmForm").method="post";
        // get a list of ids (checkboxes values) of checked checkboxes
        const selectedFilms = Array.from(form.querySelectorAll('input[name="selectedFilms"]:checked'))
            .map((check) => (checkbox.value));
        const data = new URLSearchParams(); // the selectedFilms list is a RequestParam in controller
        selectedFilms.forEach((filmId) => {
            data.append("selectedFilms", filmId); // add ids of selected films to the list
        });
        const url = "/song/" + id;
        fetch(url, { // post the list in the controller
            method: "POST",
            body: data
        });
    }
    else{// if no checkbox is checked -> alert
        alert("You must select at least one film!");

    }
}