function submitAction(id) {
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