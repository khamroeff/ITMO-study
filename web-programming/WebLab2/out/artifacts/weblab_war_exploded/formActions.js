function sendRequest(xArr, y, r) {
    $.get('controller', {
        'x': xArr,
        'y': y,
        'r': r,
    }).done((data) => {
        $('.result_table tbody tr').remove();
        let arrayOfResults = JSON.parse(data);
        arrayOfResults.forEach(object => {
            let tableRow = "<tr>";
            for (let key in object) {
                    tableRow += `<td>${object[key]}</td>`;
            }
            tableRow += "</tr>";
            $('.result_table tbody').append(tableRow);
        });
    });
}




function clickOnCanvas(canvas, event) {
    if (checkR()) {
        let rect = canvas.getBoundingClientRect()
        let width = canvas.width;
        let height = canvas.height;
        let x = (event.clientX - rect.left - width / 2) / step; // - 0.45;
        let y = (height / 2 - event.clientY + rect.top) / step; // + 0.48;
        let R = document.querySelector('select').value;
        let r = R;
        x = x.toFixed(2).replace(".00", "");
        y = y.toFixed(2).replace(".00", "");
        //console.log(x, y, r);
        let d = checkTrueFalse(x, y, r);
        drawPoint(x, y, d);
        sendRequest(x, y, r);
    }
}

function submitForm(e) {
    e.preventDefault();
    if (checkX() && checkY() && checkR()) {
        let xArr = document.querySelector('input[name="xArr"]:checked').value;
        let y = document.getElementById("coordinateY").value;
        let r = document.querySelector('select').value;
        let d = checkTrueFalse(xArr, y, r);
        drawPoint(xArr, y, d);
        sendRequest(xArr, y, r);
    }
}

function checkTrueFalse(x, y, r){
    return (x>=-r/2 && x<=0 && y>=0 && y<=r) ||
        (x*x +y*y <= r*r/4 && x>=0 && y<=0) ||
        ((x >= -r) && (x <= 0) && (y <= 0) && (y >= -r)) && (r>=-y-x);
}

function checkY() {
    let y = document.getElementById("coordinateY");
    if (y.value.trim() === "") {
        wrongFieldY.textContent = "Поле Y должно быть заполнено";
        return false;
    }
    y.value = y.value.substring(0, 10).replace(',', '.');
    if (!(y.value && !isNaN(y.value))) {
        wrongFieldY.textContent = "Y должен быть числом!";
        return false;
    }
    if (y.value <= -5 || y.value >= 5) {
        wrongFieldY.textContent = "Y должен принадлежать промежутку: (-5; 5)!";
        return false;
    }
    return true;
}

function checkX() {
    let xButtons = document.getElementsByName("xArr");
    let counter = 0;
    xButtons.forEach(x => {
        if (x.checked)
            counter++
    });
    if (counter === 0) {
        wrongFieldX.textContent = "Вы должны выбрать минимум одно значение X";
        return false
    }
    return true;
}


function checkR() {
    if (document.querySelector('select').value === undefined) {
        wrongFieldR.textContent = "Вы должны выбрать одно значение R";
        return false
    }
    return true;
}

let wrongFieldX = document.getElementById("wrong_field_X");
let wrongFieldY = document.getElementById("wrong_field_Y");
let wrongFieldR = document.getElementById("wrong_field_R");

document.querySelector('select').addEventListener("change", refreshCanvas);

document.getElementById("submit").addEventListener('click', submitForm);
loadCanvas();

function addRow(x,y,r,result,time,delta){
    let tbody = document.getElementById("results").getElementsByTagName("tbody")[0];
    let row = document.createElement("tr");

    let tdX = document.createElement("td");
    tdX.appendChild(document.createTextNode(x));

    let tdY = document.createElement("td");
    tdY.appendChild(document.createTextNode(y));

    let tdR = document.createElement("td");
    tdR.appendChild(document.createTextNode(r));

    let tdResult = document.createElement("td");
    tdResult.appendChild(document.createTextNode(result));

    let tdTime = document.createElement("td");
    tdTime.appendChild(document.createTextNode(time));

    let tdDelta = document.createElement("td");
    tdDelta.appendChild(document.createTextNode(delta));

    row.appendChild(tdX);
    row.appendChild(tdY);
    row.appendChild(tdR);
    row.appendChild(tdResult);
    row.appendChild(tdTime);
    row.appendChild(tdDelta);
    tbody.appendChild(row);
}
function getData() {
    let data = "x=";
    document.getElementsByName("xArr").forEach(x => {
        if (x.checked) {
            data += x.value
        }
    })
    data += "&y=" + document.getElementById('coordinateY').value;
    data += "&r=" + document.querySelector('select').value;;
    return data;

}
/**
    function submit(str){
        $.ajax({
            url: '/controller',
            type: 'GET',
            data: str,
            dataType: 'json',
            success: function(data){
                addRow(data[0][0], data[0][1], data[0][2], data[0][3], data[0][4], data[0][5]);
            },
            error: function(data){
                alert("error");
            }
        });
    }
    */
/**
$.post('controller', {
    'reload': 'true',
}).done((data) => {
    let arrayOfResults = JSON.parse(data);
    $(".scroll-table tr:gt(0)").remove();
    arrayOfResults.forEach(result => {
        let nRow = '<tr>';
        for (let key in result) 
            nRow += `<td>${result[key]}</td>`;
        nRow += '</tr>';
        $('#result-table').append(nRow);
    });
}); */

$.get('controller', {
    'reload': true
}).done((data) => {
    $('.result_table tbody tr').remove();
    let arrayOfResults = JSON.parse(data);
    arrayOfResults.forEach(object => {
        let tableRow = "<tr>";
        for (let key in object) {
            tableRow += `<td>${object[key]}</td>`;
        }
        tableRow += "</tr>";
        $('.result_table tbody').append(tableRow);
    });
});


