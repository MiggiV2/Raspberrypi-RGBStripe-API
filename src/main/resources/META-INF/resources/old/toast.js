var option = {
    animation: true,
    delay: 4000
}

function Toasty01() {
    var toastHTMLElement = document.getElementById("liveToast01");

    var toastElement = new bootstrap.Toast(toastHTMLElement, option);
    toastElement.show();
}

function Toasty02() {
    var toastHTMLElement = document.getElementById("liveToast02");

    var toastElement = new bootstrap.Toast(toastHTMLElement, option);
    toastElement.show();
}