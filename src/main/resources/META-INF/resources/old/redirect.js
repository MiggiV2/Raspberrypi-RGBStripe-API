function redirect() {
    const url = 'http://' + window.location.hostname + ':' + window.location.port + '/';
    window.location.href = url + 'new';
}