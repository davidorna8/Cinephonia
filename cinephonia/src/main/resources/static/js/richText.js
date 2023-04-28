function frameOn(){
    richTextField.document.designMode= 'On';
}
function boldText(){
    richTextField.document.execCommand('bold',false,null);
}

function italicText(){
    richTextField.document.execCommand('italic',false,null);
}

function underlinedText(){
    richTextField.document.execCommand('underline',false,null);
}

function colorBlack(){
    richTextField.document.execCommand('ForeColor',false, 'black');
}

function colorRed(){
    richTextField.document.execCommand('ForeColor',false, 'red');
}

function colorBlue(){
    richTextField.document.execCommand('ForeColor',false, 'blue');
}

function colorGreen(){
    richTextField.document.execCommand('ForeColor',false, 'green');
}
function newFilm() {
    var form = document.getElementById("filmForm");
    form.elements["synopsis"].value = window.frames['richTextField'].document.body.innerHTML;
    form.submit();
    // alert to notify the user a new film is created
    alert("Film correctly registered!");
    return false;
}