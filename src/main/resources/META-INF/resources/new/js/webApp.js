const mainDiv = document.getElementById('mode-div');
const url = 'http://' + window.location.hostname + ':' + window.location.port + '/rgb-stripe';
var lastIndex;
var currentRow = createNode('div');
currentRow.className = 'row'

fetch(url)
    .then(resp => resp.json())
    .then(items => {
        lastIndex = items.length - 1;
        return items.map((currentItem, i) => handleCurrentItem(currentItem, i));
    })
    .catch(error => console.log(error));

function getOrder() {
    fetch(url + '/order')
        .then(resp => {
            if (resp.status != 200) {
                throw new Error('Nothing to do!');
            }
            return resp.json();
        })
        .then(items => applyOrder(items))
        .catch(error => {
            console.log(error);
        });
}

function sendApplyRequest(modeValue) {

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

function sendOrder() {
    fetch(url + '/order', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: getOrderJSON(),
        })
        .then(response => {
            handleResponse(response);
        });
}

function applyOrder(items) {
    console.log('applying...')
    for (const key in items) {
        var card = document.getElementById(key);
        var colum = document.getElementById(items[key]);
        try {
            append(colum, card);
        } catch (e) {
            console.log(e);
        }
    }
}

function handleCurrentItem(currentItem, i) {
    let col = createColum(i);
    let card = createCard(i);
    let body = createBody();
    let info = createInfo(currentItem);
    let button = createButton(currentItem);

    append(body, button)
    append(card, info);
    append(card, body);
    append(col, card);
    append(currentRow, col);

    handleLastItem(i);
}

function handleLastItem(index) {
    if (lastIndex === index) {
        append(mainDiv, currentRow);
        fillLastLine();
        getOrderJSON();
        console.log('Last Item!');
        getOrder();
    }
}

function fillLastLine() {
    const itemsInLastLine = (lastIndex + 1) % 4;
    if (itemsInLastLine != 0) {
        const missing = 4 - itemsInLastLine;
        for (var i = 0; i < missing; i++) {
            let col = createColum(lastIndex + i + 1);
            append(currentRow, col);
        }
    }
}

function createNode(element) {
    return document.createElement(element);
}

function append(parent, el) {
    return parent.appendChild(el);
}

function createColum(i) {
    let col = createNode('div')
    col.id = 'colum-' + i;
    col.className = 'col-md-3'
    col.setAttribute('ondrop', 'drop(event)');
    col.setAttribute('ondragover', 'allowDrop(event)');
    return col;
}

function createCard(i) {
    let item = createNode('div');
    item.id = 'card-' + i;
    item.className = 'card border rgb-mode';
    item.setAttribute('draggable', true);
    item.setAttribute('ondragstart', 'drag(event)');
    return item;
}

function createInfo(currentItem) {
    let info = createNode('p');
    info.innerHTML = currentItem.nickName;
    info.className = 'card-header border';
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
        sendApplyRequest(currentItem.mode);
    });
    return button;
}

function getOrderJSON() {
    var json = '{';
    var colums = document.getElementsByClassName('col-md-3');
    for (var i = 0; i < colums.length - 1; i++) {
        var colum = colums[i];
        var card = colum.childNodes[0];
        if (typeof card != 'undefined') {
            json += '"' + card.id + '": "' + colum.id + '",';
        }
    }
    json = json.substring(0, json.length - 1);
    json += "}";
    return json;
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