function showFilmsForm(){
    let content=document.getElementById('uploadFilms');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
    let content2=document.getElementById('allFilms');
    content2.style.display='none';
}

function showSongsForm(){
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
function newFilm() {
    alert("Film correctly registered!");
    return false;
}
function newSong(){
    alert("Song correctly registered!");
    return false;
}

function showAllFilms(){
    let content=document.getElementById('allFilms');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
    let content2=document.getElementById('uploadFilms');
    content2.style.display='none';
}

function showAllSongs(){
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

function deleteFilm(id,name){
    var ans = window.confirm("Are you sure you want to delete film "+name+"?")
    if(ans){
        location.href="/films/delete/" + id;
    }
}

function deleteSong(id,name){
    var ans = window.confirm("Are you sure you want to delete song "+name+"?")
    if(ans){
        location.href="/songs/delete/" + id;
    }
}

function deleteUser(id,name){
    if(id=="0"){
        alert("You can't delete user admin.")
    }
    else {
        var ans = window.confirm("Are you sure you want to delete user " + name + "?\n(Your uploaded films and songs will be admin's property)")
        if (ans) {
            location.href = "/users/delete/" + id;
        }
    }
}

function updateUser(id) {
    if (id == "0") {
        alert("You can't update user admin.")
    } else {
        location.href = "/updateUser/" + id;
    }
}

function addFilms(){
    
    let content=document.getElementById('filmCheckBox');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
}

function addSongs(){

    let content=document.getElementById('songCheckBox');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
}

function getCurrentURL () {
    return window.location.href
}

function hideAddSongs(){
    const url = getCurrentURL()
    let content=document.getElementById('addSongs')
    if(url.includes("/filmInfo")){
        content.style.display='none'
    }
}

function hideAddFilms(){
    const url = getCurrentURL()
    let content=document.getElementById('addFilms')
    if(url.includes("/songInfo")){
        content.style.display='none'
    }
}