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
    var ans = window.confirm("Are you sure you want to delete "+name+"?")
    if(ans){
        location.href="/films/delete/" + id;
    }
}

function deleteSong(id,name){
    var ans = window.confirm("Are you sure you want to delete "+name+"?")
    if(ans){
        location.href="/songs/delete/" + id;
    }
}