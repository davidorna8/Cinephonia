function submitAction(id){
    const selectedFilms = Array.from(form.querySelectorAll('input[name="selectedFilms"]:checked'))
        .map((check) => (checkbox.value));
    const data = new URLSearchParams();
    selectedFilms.forEach((filmId) => {
        data.append("selectedFilms",filmId);
    });
    const url = "/song/"+id;
    fetch(url, {
        method: "POST",
        body: data
    });
}