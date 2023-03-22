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
}

function newFilm(){
   let content3=document.getElementById('uploadFilms')
    content3.style.display='none';
   alert("Film correctly registered!");
   return false;
}

function newSong(){
    let content4=document.getElementById('uploadSongs')
    content4.style.display='none';
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