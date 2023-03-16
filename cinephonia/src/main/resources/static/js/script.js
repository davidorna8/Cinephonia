function showFilmsForm(){
    let content=document.getElementById('uploadFilms');
    let display=content.style.display;
    if(display==="none"|display===""){
        content.style.display='block';
    }else{
        content.style.display='none';
    }
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