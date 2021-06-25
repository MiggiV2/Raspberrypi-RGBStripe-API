function showAskModal() {
    new bootstrap.Modal(document.getElementById('ask-modal')).show();
}

function redirect() {
    const url = 'http://' + window.location.hostname + ':' + window.location.port + '/';
    window.location.href = url + 'old';
}