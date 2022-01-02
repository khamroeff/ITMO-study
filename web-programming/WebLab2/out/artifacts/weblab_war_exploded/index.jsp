<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Лабораторная работа №2 // Khamroev S.</title>
</head>
<!-- <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head> -->
<body>
    <div class="header"> <!--Шапка страницы-->
        <p>
            Lab2
        </p>
        <p>
            <b>Хамроев Салимджон Восидович Группа</b> P3231
        </p>
        <p>
            <b>вариант:</b> 31203
        </p>
        <hr> <!--Горизонтальная линия-->
    </div>

<div class="main">
    <div class="form">
        <form method="GET" action="controller" id="form_send">
        <div class="elem"> 
            Координата X:<br>
                    <input type="radio" id="selectedX" name="xArr" value="-5">
                    <label for="choice1">-5</label>
                    <input type="radio" id="selectedX" name="xArr" value="-4">
                    <label for="choice2">-4</label>
                    <input type="radio" id="selectedX" name="xArr" value="-3">
                    <label for="choise3">-3</label>
                    <input type="radio" id="selectedX" name="xArr" value="-2">
                    <label for="choise4">-2</label>
                    <input type="radio" id="selectedX" name="xArr" value="-1">
                    <label for="choise5">-1</label> <br>
                    <input type="radio" id="selectedX" name="xArr" value="0">
                    <label for="choise6">0</label>
                    <input type="radio" id="selectedX" name="xArr" value="1">
                    <label for="choise7">1</label>
                    <input type="radio" id="selectedX" name="xArr" value="2">
                    <label for="choise8">2</label>
                    <input type="radio" id="selectedX" name="xArr" value="3">
                    <label for="choise9">3</label>
        </div>
        <div class="elem">
            Выберите R:<br>
            <select name="r">
            <option value="1">1</option>
            <option value="1.5">1.5</option>
            <option selected value="2">2</option>
            <option value="2.5">2.5</option>
            <option value="3">3</option>
           </select>
        </div>
        <div class="elem">
            Координата Y:
                <label>
                    <input type="text" id="coordinateY" class="coordinateY" name="Y" placeholder="from -5 to 3" required>
                </label>
            <!-- <button id="submit">Отправить</button> -->
        </div>
        <div class="elem">
            <input type="submit" id="submit" value="Отправить">
        </div>
        <div class="elem">
            <p><input id="resetForm" type="reset"></p>
        </div>
        </form>
        <div class="elem">
            <p id="errorFields">Ошибка ввода, заполнены не все поля</p>
        </div>
    </div>
    <div class="main__area">
        <canvas id="canvas"></canvas>
    </div>
</div>

    <div class="scroll-table">
    <table class="result_table">
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
            <th>Время отправки</th>
            <th>Время работы скрипта</th>
        </tr>
        </thead>
        <tbody>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function digitY(){
                    this.value = this.value.replace(/[^\d\,\-]/g, "");//разрешаем ввод только цифр 0-9, запятой и минуса

                    if(this.value.lastIndexOf("-")> 0) {//если пользователь вводит тире (минус) не самым первым символом...
                     this.value = this.value.substr(0, this.value.lastIndexOf("-"));//то удаляем этот минус
                     }

                    if(this.value[0]==="-") {//если первый символ это минус (число отрицательно)...
                        if (new Set().add("6").add("7").add("8").add("9").has(this.value[1]))
                        this.value = this.value.substr(0, 1);
                        if(this.value[1]==="5" && this.value[2]!==""){
                        this.value = this.value.substr(0, 2);//то запрещаем вводить число больше 0
                        }
                        if(this.value.length>2 && this.value[2]!==",") this.value=this.value[0]+this.value[1]+","+this.value[2];//если третий символ введён и он не запятая, то вставляем запятую между вторым и третим символом
                        if(this.value.length>8) this.value = this.value.substr(0, 8);//если количество символов равно 8 (5 знаков после запятой), не даём вводить больше
                    }else{//если число положительно (первым введён не минус, а цифра)...
                        if (new Set().add("4").add("5").add("6").add("7").add("8").add("9").has(this.value[0])){
                            this.value = this.value.substr(0,0)//то эта цифра должна быть от 0 до 3
                        }
                        if(this.value[0]==="3" && this.value[1]!==""){
                            this.value = this.value.substr(0, 1);//то эта цифра должна быть от 0 до 5
                         }

                        if(this.value.length>1 && this.value[1]!==",") this.value=this.value[0]+","+this.value[1];//если второй символ введён и он не запятая, то вставляем запятую между первым и вторым символом
                        if(this.value.length>7) this.value = this.value.substr(0, 7);//если количество символов равно 7 (5 знаков после запятой), не даём вводить больше
                    }

                    if(this.value.match(/,/g)!==null&&this.value.match(/,/g).length > 1) {//не даём ввести больше одной запятой
                        this.value = this.value.substr(0, this.value.lastIndexOf(","));
                    }
                    if (parseFloat(this.value))

                    if(this.value.match(/-/g)&&this.value.match(/-/g).length > 1) {//не даём ввести больше одного минуса
                         this.value = this.value.substr(0, this.value.lastIndexOf("-"));
                    }
                }
                $("#resetForm").click(function(){
                    $("#errorFields").hide();
                    refreshCanvas();
                })
           /**     function submitForm() {
                    $("#errorFields").hide();
                    let checkY = ($("#coordinateY").val()).replaceAll(",", ".");
                    let checkxArr = $('input[name="xArr"]:checked').val();
                    if (checkY !== "" && checkxArr !== undefined) {
                        let xArr = document.querySelectorAll('input[name="xArr"]:checked');
                        let y = document.getElementById('coordinateY').value;
                        let r = document.querySelector('select').value;
                        xArr = Object.values(xArr);
                        xArr = xArr.map(element => element.value);
                        xArr.forEach(x => drawPoint(x, y));
                        sendRequest(xArr, y, r);
                        //2
                    //    let str = getData();
                        // submit(str);
                    }
                    else{
                        $("#errorFields").show();
                    }
                }
*/
                document.querySelector(".coordinateY").onkeyup = digitY;

</script>

<script src="canvasTest.js"></script>
<script src="formActions.js"></script>
</body>
</html>
