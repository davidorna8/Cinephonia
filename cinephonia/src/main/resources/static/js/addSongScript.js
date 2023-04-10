function submitAction(id) { // function to get the list of checked checkboxes in addSongs form in a film page
    const checkBoxes = document.getElementsByClassName("checkBoxes"); // get the form
    var isChecked = false; // check that the user checked at least one checkbox
    for(var i =0; i< checkBoxes.length; i++){
        if(checkBoxes[i].checked){
            isChecked= true;
        }
    }
    if(isChecked) { // if any checkbox is checked:
        document.getElementById("songForm").method="post";
        // get a list of ids (checkboxes values) of checked checkboxes
        const selectedSongs = Array.from(form.querySelectorAll('input[name="selectedSongs"]:checked'))
            .map((check) => (checkbox.value));
        const data = new URLSearchParams(); // the selectedSongs list is a RequestParam in controller
        selectedSongs.forEach((songId) => {
            data.append("selectedSongs", songId);// add ids of selected songs to the list
        });
        const url = "/films/" + id;
        fetch(url, { // post the list in the controller
            method: "POST",
            body: data
        });
    }
    else{ // if no checkbox is checked -> alert
        alert("You must select at least one song!")
    }
}