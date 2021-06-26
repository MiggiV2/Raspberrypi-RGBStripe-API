function allowDrop(ev) {
    ev.preventDefault();
    var colums = document.getElementsByClassName('col-md-3');
    for (var i = 0; i < colums.length; i++) {
        colums[i].style.border = "thin solid white";
        colums[i].style.paddingTop = '2rem';
        colums[i].style.maxHeight = '200px';
    }
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    if (ev.target.id.startsWith('colum')) {
        var data = ev.dataTransfer.getData("text");
        var card = document.getElementById(data);
        var startIndex = getIndex(ev.target);
        var targetIndex = getIndexFromParentID(card);
        if (startIndex != targetIndex) {
            ev.target.appendChild(card);
            if (!isValid(startIndex)) {
                move(startIndex, targetIndex);
                sendOrder();
            }
        }
    }
    var colums = document.getElementsByClassName('col-md-3');
    for (var i = 0; i < colums.length; i++) {
        colums[i].style.border = 'none';
        colums[i].style.paddingTop = '0';
        colums[i].style.maxHeight = '200px';
    }
}

function startMoveing(ev) {
    var childs = ev.target.childNodes;
    childs.forEach(element => {
        var index = element.id.split("-")[1];
        if (index != lastIndex) {
            move(index);
        }
    });
}

function move(index, lastIndexToMove) {
    index = Number(index);
    lastIndexToMove = Number(lastIndexToMove);

    var moveLeft = lastIndexToMove > index;
    var card = getFirstCardByColumIndex(index);
    index = udpateIndex(lastIndexToMove, index);
    var nextColum = document.getElementById('colum-' + index);

    if (nextColum != null) {
        append(nextColum, card);
        if (moveLeft && index < lastIndexToMove) {
            move(index, lastIndexToMove);
        }
        if (!moveLeft && index > lastIndexToMove) {
            move(index, lastIndexToMove);
        }
    }
}

function udpateIndex(lastIndexToMove, index) {
    if (lastIndexToMove > index) {
        index++
    } else {
        index--;
    }
    return index;
}

function isValid(index) {
    var colum = document.getElementById('colum-' + index);
    return colum.childNodes.length === 1;
}

function getFirstCardByColumIndex(index) {
    var colum = document.getElementById('colum-' + index);
    return colum.childNodes[0];
}

function getIndex(element) {
    var index = element.id.split("-")[1];
    return Number(index);
}

function getIndexFromParentID(cardElement) {
    var colum = cardElement.parentNode;
    var index = colum.id.split("-")[1];
    return Number(index);
}

function getParent(cardElement) {
    return cardElement.parentNode;
}