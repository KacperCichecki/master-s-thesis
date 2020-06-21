function deleteRow(r) {
    let rowIndex = r.parentNode.parentNode.rowIndex;
    let jsonId =  r.parentNode.parentNode.cells[0].textContent;
    document.getElementById("jsonTable").deleteRow(rowIndex);
    deleJson(jsonId);
}

function deleJson(jsonId) {
    let xhr = new XMLHttpRequest();
    xhr.open('PUT', 'delete-json?jsonId=' + jsonId);
    xhr.send()
}