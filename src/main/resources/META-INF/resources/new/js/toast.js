var option = {
    animation: true,
    delay: 4000
}

function showToast() {
    var toastHTMLElement = document.getElementById("liveToast01");
    toastHTMLElement.hidden = false;
    var toastElement = new bootstrap.Toast(toastHTMLElement, option);
    toastElement.show();
}