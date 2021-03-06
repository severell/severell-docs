document.addEventListener('keydown', logKey);

function logKey(e) {
    console.log(e);
    if(e.code == "Slash") {
        let search = document.getElementById("search");
        let isFocused = (document.activeElement === search);
        if(!isFocused) {
            e.preventDefault();
        }
        search.focus();
    }
}