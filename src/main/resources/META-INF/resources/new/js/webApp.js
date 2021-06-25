const mainDiv = document.getElementById('mode-div');
const url = 'http://' + window.location.hostname + ':' + window.location.port + '/rgb-stripe';
var currentRow = createNode('div');
currentRow.className = 'row'

fetch(url)
    .then((resp) => resp.json())
    .then(function(items) {
        return items.map((currentItem, i) => handleCurrentItem(currentItem, i, items));
    })
    .catch(function(error) {
        console.log(error);
    });

function handleCurrentItem(currentItem, i, items) {
    let col = createColum();
    let item = createItem();
    let body = createBody();
    let info = createInfo(currentItem);
    let button = createButton(currentItem);

    append(body, button)
    append(item, info);
    append(item, body);
    append(col, item);
    append(currentRow, col);

    handleLastItem(items, i);
}

function handleLastItem(objects, i) {
    if (objects.length === i + 1) {
        append(mainDiv, currentRow);
    }
}

function createNode(element) {
    return document.createElement(element);
}

function append(parent, el) {
    return parent.appendChild(el);
}

function createColum() {
    let col = createNode('div')
    col.className = 'col-md-3'
    return col;
}

function createItem() {
    let item = createNode('div');
    item.className = 'card border rgb-mode';
    return item;
}

function createInfo(currentItem) {
    let info = createNode('p');
    info.innerHTML = currentItem.nickName;
    info.className = 'card-header border'
    return info;
}

function createBody() {
    let body = createNode('div');
    body.className = 'card-body border';
    return body;
}

function createButton(currentItem) {
    let button = createNode('input');
    button.type = 'button';
    button.value = 'RUN';
    button.className = 'btn btn-success btn-lg';
    button.addEventListener('click', function() {
        sendPost(currentItem.mode);
    });
    return button;
}

function sendPost(modeValue) {

    const data = {
        mode: modeValue
    };

    fetch(url + '/mode', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => {
            handleResponse(response);
        });
}

function handleResponse(response) {
    if (response.status == 200) {
        showToast();
    } else {
        if (response != null) {
            showErrorModal(response.statusText);
            console.log(response);
        } else {
            showErrorModal("Offline?");
            console.log(response);
        }
    }
}

function showErrorModal(errorStr) {
    document.getElementById('error-message').innerHTML = errorStr;
    new bootstrap.Modal(document.getElementById('error-modal')).show();
}