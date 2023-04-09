function submitAction(id) {
    const checkBoxes = document.getElementsByClassName("checkBoxes");
    var isChecked = false;
    for(var i =0; i< checkBoxes.length; i++){
        if(checkBoxes[i].checked){
            isChecked= true;
        }
    }
    if(isChecked) {
        document.getElementById("songForm").method="post";
        const selectedSongs = Array.from(form.querySelectorAll('input[name="selectedSongs"]:checked'))
            .map((check) => (checkbox.value));
        const data = new URLSearchParams();
        selectedSongs.forEach((songId) => {
            data.append("selectedSongs", songId);
        });
        const url = "/films/" + id;
        fetch(url, {
            method: "POST",
            body: data
        });
    }
    else{
        alert("You must select at least one song!")
    }
}