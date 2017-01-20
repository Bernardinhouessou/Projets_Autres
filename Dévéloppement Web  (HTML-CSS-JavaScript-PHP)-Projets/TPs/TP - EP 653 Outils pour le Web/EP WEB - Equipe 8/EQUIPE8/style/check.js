function validate(e, id, regexp) {
    setTimeout(function() {
        var text = e.value;

        var correct = regexp.test(text);

        var span = document.getElementById(id);
        span.style.display = correct ? "none" : "inline";
    }, 0);

    return true;
}


function validateNumber(e, id) {
    return validate(e, id, /^[0-9]+$/);
}

function validateLetter(e, id) {
    return validate(e, id, /^[a-zA-Z ]+$/);
}

function validateLetterNumber(e, id) {
    return validate(e, id, /^[0-9a-zA-Z ]+$/);
}

function validateMail(e, id) {
    return validate(e, id, /^[a-z0-9\.-_]+@[a-z0-9\.-_]+\.[a-z]{2,5}$/i);
}

function validateTelephone(e, id) {
    return validate(e, id, /^0[0-9]{9}$/);
}