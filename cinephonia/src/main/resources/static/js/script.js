function showFilmsForm(){ // The film form is initially hidden
    let content=document.getElementById('uploadFilms'); // gets the div where the form is contained
    let display=content.style.display; // and gets its display
    if(display==="none"|display===""){ // if it is hidden it will be shown
        content.style.display='block';
    }else{
        content.style.display='none'; // if it is shown, then it will be hidden
    }
    let content2=document.getElementById('allFilms'); // in any case, the full films list must be hidden
    content2.style.display='none';
}

function showSongsForm(){ // the same as in showFilmsForm()
    let content2=document.getElementById('uploadSongs');
    let display=content2.style.display;
    if(display==="none"|display===""){
        content2.style.display='block';
    }else{
        content2.style.display='none';
    }
    let content3=document.getElementById('allSongs');
    content3.style.display='none';
}
function newFilm() { // alert to notify the user a new film is created
    alert("Film correctly registered!");
    return false;
}
function newSong(){ // alert to notify the user a new song is created
    alert("Song correctly registered!");
    return false;
}

function showAllFilms(){ // the films list is initially hidden
    let content=document.getElementById('allFilms'); // takes the div containing the list (cards)
    let display=content.style.display; // gets the display
    if(display==="none"|display===""){ // if it is hidden, it passes to be shown
        content.style.display='block';
    }else{ // if it's being shown, it will be hidden
        content.style.display='none';
    }
    let content2=document.getElementById('uploadFilms'); // in any case the upload form must be hidden
    content2.style.display='none';
}

function showAllSongs(){ // same as in showAllFilms()
    let content=document.getElementById('allSongs');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
    let content2=document.getElementById('uploadSongs');
    content2.style.display='none';
}

function deleteFilm(id,name){ // the user must confirm if the film is deleted
    var ans = window.confirm("Are you sure you want to delete film "+name+"?")
    if(ans){
        location.href="/films/delete/" + id; // redirected to the page confirming deletion
    }
}

function deleteSong(id,name){ // the user must confirm if the song is deleted
    var ans = window.confirm("Are you sure you want to delete song "+name+"?")
    if(ans){
        location.href="/songs/delete/" + id; // redirected to the page confirming deletion
    }
}

function deleteUser(id,name){
    if(id=="0"){ // it is not allowed to delete admin
        alert("You can't delete user admin.")
    }
    else {
        var ans = window.confirm("Are you sure you want to delete user " + name + "?\n(Your uploaded films and songs will be admin's property)")
        if (ans) {
            location.href = "/users/delete/" + id; // confirm deletion
        }
    }
}

function updateUser(id) { // alert to notify that admin can not be updated
    if (id == "0") {
        alert("You can't update user admin.")
    } else {
        location.href = "/updateUser/" + id;
    }
}

function addFilms(){ // show checkbox form to add films to a song
    let content=document.getElementById('filmCheckBox');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
}

function addSongs(){ // show checkbox form to add songs to a film

    let content=document.getElementById('songCheckBox');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
}

function getCurrentURL () { // get url
    return window.location.href
}

function hideAddSongs(){ // function to solve a problem we had in "/filmInfo"
    const url = getCurrentURL()
    let content=document.getElementById('addSongs')
    if(url.includes("/filmInfo")){ // if we are in this url, it is not allowed to add songs to a film
        content.style.display='none'
    }
}

function hideAddFilms(){ // function to solve a problem we had in "/songInfo"
    const url = getCurrentURL()
    let content=document.getElementById('addFilms')
    if(url.includes("/songInfo")){ // if we are in this url, it is not allowed to add films to a song
        content.style.display='none'
    }
}