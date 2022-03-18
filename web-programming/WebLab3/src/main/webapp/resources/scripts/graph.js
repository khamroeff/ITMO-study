let buffer = [];
let canv;
// const rProportionY = 0.3375;
// const rProportionX = 0.33;
// const xOffset = -15;
// const yOffset = -16;
const centerX = 145;
const centerY = 143;
const rPixels = 106;
//
 function addShot(x, y, r, success) {
   buffer.push({x,y,success,r});
 }
//
function init(valR) {
    valR *= step;
    clearBuffer();
    canv = document.getElementById("canvas");
    canv.addEventListener('mousedown', event => clickOnChart(canv, event));
    document.getElementById("toSend:_t27").addEventListener("click", clickFireButton);
    ctx.globalAlpha = 1;
        drawRectangle(valR);
        drawTriangle(valR);
        drawCircle(valR);
        drawAXIS();
}

function clickFireButton() {
    let valX = +document.getElementById("toSend:X_hidden").value;
    let valY = +document.getElementById("toSend:y-input").value;
    drawPoint(valX, valY);
}
//
function clearBuffer() {
    buffer = [];
}
//
// function getBuffer() {
//     return buffer;
// }
//
function drawDots() {
    console.log("called");
    // canv.getContext("2d").clearRect(0, 0, canv.width, canv.height);
    for (let dot of buffer.reverse()) {
        drawPoint(dot.x,dot.y);
    }
   // drawDot(buffer.reverse()[0], canv, false);

}

//
// function drawDot({x, y, r, success}, canvas, isGray) {
//     let context = canvas.getContext("2d");
//     let drawnX = (rProportionY * (x / r) + 0.5 )* canvas.width + xOffset;
//     let drawnY = (-rProportionX * (y / r) + 0.5 )* canvas.height + yOffset;
//
//     console.log(success)
//     if (!isGray) {
//         if (success) {
//             context.fillStyle = 'red';
//         } else {
//             context.fillStyle = 'blue';
//         }
//     }
//     else {
//         context.fillStyle = 'gray';
//     }
//     context.lineWidth = 1.5;
//     context.strokeStyle = 'black';
//
//     context.beginPath();
//     context.arc(drawnX, drawnY, 3.5, 0, Math.PI * 2, false);
//     context.stroke();
//     context.closePath();
//     context.fill();
// }
// //
// function processClick(eventObj) {
//     let clickX = eventObj.pageX;
//     // - this.offsetLeft;
//     let clickY = eventObj.pageY;
//     // - this.offsetTop;
//     let xToR = (clickX - centerX) / rPixels;
//     let yToR = (-clickY + centerY) / rPixels;
//     xToR = xToR.toFixed(2);
//     yToR = yToR.toFixed(2);
//     console.log(xToR);
//     console.log(yToR);
//     document.getElementById("j_idt14:hidden_x").value = xToR;
//     document.getElementById("j_idt14:hidden_y").value = yToR;
//     document.getElementById("j_idt14:hidden_submit").click();
//     document.getElementById("j_idt14:hidden_x").value = null;
//     document.getElementById("j_idt14:hidden_y").value = null;
// }

function clickOnChart(canvas, event) {
    let rect = canvas.getBoundingClientRect();
    let width = canvas.width;
    let height = canvas.height;
    let x = (event.clientX - rect.left - width / 2) / step;
    let y = (height / 2 - event.clientY + rect.top) / step;
    x = x.toFixed(2).replace(".00", "");
    y = y.toFixed(2).replace(".00", "");

    document.getElementById("toSend:X_hidden").value = x;
    document.getElementById("toSend:_t16").value = x;
    document.getElementById("toSend:y-input").value = y;
    document.getElementById("toSend:_t27").click();
}

function setR() {
    let valR = +document.getElementById("toSend:R_hidden").getAttribute("value");
    document.getElementById("toSend:_t25").setAttribute("value", valR);
}

function setX() {
    let valX = +document.getElementById("toSend:X_hidden").getAttribute("value");
    document.getElementById("toSend:_t16").setAttribute("value", valX);

}

let axis_separator_offset = 5;
let step = 50;
let canvas = document.getElementById("canvas"),
    ctx = canvas.getContext("2d");
canvas.width = 510;
canvas.height = 510;
let width = canvas.width;
let height = canvas.height;
// canvas.addEventListener('mousedown', event => clickOnCanvas(canvas, event));


function drawCanvas() {
    let valR = +document.getElementById("toSend:R_hidden").getAttribute("value") * step;
    ctx.globalAlpha = 1;
    drawRectangle(valR);
    drawTriangle(valR);
    drawCircle(valR);
    drawAXIS();
}

function drawTriangle(rValue) {
    ctx.fillStyle = '#3399FF';
    ctx.beginPath();
    ctx.moveTo(width / 2, height / 2);
    ctx.lineTo(width / 2 - rValue, height / 2);
    ctx.lineTo(width / 2, height / 2 - rValue);
    ctx.fill();
}

function drawCircle(rValue) {
    ctx.beginPath();
    ctx.fillStyle = '#3399FF';
    ctx.strokeStyle = '#3399FF';
    ctx.arc(width / 2, height / 2, rValue / 2, Math.PI/2, Math.PI);
    ctx.lineTo(width / 2, height / 2);
    ctx.fill();
    ctx.stroke();
}

function drawRectangle(rValue) {
    ctx.fillStyle = '#3399FF';
    ctx.strokeStyle = '#3399FF';
    ctx.beginPath();
    ctx.fillRect(width / 2, height / 2, rValue / 2, -rValue);
}

function drawAXIS() {
    ctx.strokeStyle = 'black';
    ctx.fillStyle = 'black';
    ctx.beginPath();
    ctx.moveTo(width / 2, 0);
    ctx.lineTo(width / 2, height);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(0, height / 2);
    ctx.lineTo(width, height / 2);
    ctx.stroke();
    ctx.strokeText("Y", 240, 10);
    ctx.strokeText("X", 500, height / 2 - 10);
    ctx.stroke();
    for (let i = -5; i <= 5; i++) {
        ctx.beginPath();
        let x = width / 2 + step * i;
        ctx.moveTo(x, height / 2 + axis_separator_offset);
        ctx.lineTo(x, height / 2 - axis_separator_offset);
        if (i !== 0) {
            ctx.fillText(i.toString(), x - axis_separator_offset / 2, height / 2 + 3 * axis_separator_offset);
        }
        ctx.stroke();
    }

    for (let i = -5; i <= 5; i++) {
        ctx.beginPath();
        let y = height / 2 + step * i;
        ctx.moveTo(width / 2 + axis_separator_offset, y);
        ctx.lineTo(width / 2 - axis_separator_offset, y);
        if (i !== 0) {
            ctx.fillText((-i).toString(), width / 2 + axis_separator_offset, y + axis_separator_offset);
        }
        ctx.stroke();
    }
}

function drawPoint(x, y) {
    let pointColor = "red";
    let r = +document.getElementById("toSend:R_hidden").getAttribute("value");

    if (checkHit(x, y, r))
        pointColor = "green";
    ctx.beginPath();
    ctx.arc(canvas.width / 2 + x * step, canvas.height / 2 - y * step, axis_separator_offset, 0, Math.PI * 2);
    ctx.fillStyle = pointColor;
    ctx.strokeStyle = pointColor;
    ctx.fill();
    ctx.stroke();
}

function checkHit(x, y, r) {
    let result = false;
    if (x >= 0 && y >= 0)
        result = x <= r / 2 && y <= r;
    else if (x <= 0 && y >= 0)
        result = r + x > y;
    else if (x <= 0 && y <= 0)
        result = Math.sqrt(x * x + y * y) <= r / 2;
    return result;
}

function clearCanvas() {
    //ctx.save();
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    //ctx.restore();
}

function refreshCanvas() {
    clearCanvas();
    drawCanvas();
}

// let valR = 2 * step;
// ctx.globalAlpha = 1;
// drawRectangle(valR);
// drawTriangle(valR);
// drawCircle(valR);
// drawAXIS();
