<!DOCTYPE html>
<html lang="en">
<head>
    <title>Лабораторная работа №1 // Khamroev S.</title>
    <meta http-equiv="Content-Type" content="text/html"; charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<div class="header"> <!--Шапка страницы-->
        <h3>Хамроев Салимджон Восидович</h3>
        <p>
            <b>Группа</b> P3231
        </p>
        <p>
            <b>вариант:</b> 31018
        </p>
        <hr> <!--Горизонтальная линия-->
    </div>
<div class="form">
        <form>
            <div class="elem">
                Координата X:
                <label>
                    <input type="text" id="coordinateX" class="coordinateX" name="X" placeholder="from -5 to 3" required>
                </label>
            </div>
            <div class="elem"> 
                Координата Y:<br>
                        <input type="radio" id="selectedY" name="Y" value="-5">
                        <label for="choice1">-5</label>
                        <input type="radio" id="selectedY" name="Y" value="-4">
                        <label for="choice2">-4</label>
                        <input type="radio" id="selectedY" name="Y" value="-3">
                        <label for="choise3">-3</label>
                        <input type="radio" id="selectedY" name="Y" value="-2">
                        <label for="choise4">-2</label>
                        <input type="radio" id="selectedY" name="Y" value="-1">
                        <label for="choise5">-1</label> <br>
                        <input type="radio" id="selectedY" name="Y" value="0">
                        <label for="choise6">0</label>
                        <input type="radio" id="selectedY" name="Y" value="1">
                        <label for="choise7">1</label>
                        <input type="radio" id="selectedY" name="Y" value="2">
                        <label for="choise8">2</label>
                        <input type="radio" id="selectedY" name="Y" value="3">
                        <label for="choise9">3</label>
            </div>

            <div class="elem"> 
                    Параметр R:
                    <label>
                        <input type="text" id="valueR" class="valueR" name="R" placeholder="from 2 to 5" required>
                    </label>
            </div>
                <div class="elem">
                    <input type="button" class="submit" onclick="CheckPoints()" value="Отправить">
                </div>
                <div class="elem">
                    <p><input id="resetForm" type="reset"></p>
                </div>
        </form>
        <div class="elem">
                 <p id="errorFields">Ошибка ввода, заполнены не все поля</p>
         </div>
    </div>
             
             <div class="picture">
                <img src="img/task.png">
            </div>
            <div>
                 <p id="answer" style="display: inline" ></p>
            </div>
            <div id="some-text"></div>
   <script>
                //Валидация
                function digitX(){
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

                function digitR(){
                    this.value = this.value.replace(/[^\d\,]/g, "");//разрешаем ввод только цифр 0-9 и запятой

                    if (new Set().add("0").add("1").add("6").add("7").add("8").add("9").has(this.value[0])){
                            this.value = this.value.substr(0,0)//то эта цифра должна быть от 0 до 3
                        }
                    if(this.value[0]==="5" && this.value[1]!==""){
                            this.value = this.value.substr(0, 1);//то эта цифра должна быть от 0 до 5
                         }

                    if(this.value.length>1 && this.value[1]!==",") this.value=this.value[0]+","+this.value[1];//если второй символ введён и он не запятая, то вставляем запятую между первым и вторым символом
                    if(this.value.length>7) this.value = this.value.substr(0, 7);//если количество символов равно 7 (5 знаков после запятой), не даём вводить больше
                    

                    if(this.value.match(/,/g)!==null&&this.value.match(/,/g).length > 1) {//не даём ввести больше одной запятой
                        this.value = this.value.substr(0, this.value.lastIndexOf(","));
                    }
                    if (parseFloat(this.value))

                    if(this.value.match(/-/g)&&this.value.match(/-/g).length > 1) {//не даём ввести больше одного минуса
                         this.value = this.value.substr(0, this.value.lastIndexOf("-"));
                    }
                }
                $.post("script.php")
               .done(result => $("#some-text").html(result));
                $("#resetForm").click(function(){
                $("#errorFields").hide();
                
                })

    

                function CheckPoints() {
                    $("#errorFields").hide();
                    let R = ($("#valueR").val()).replaceAll(",", ".");
                    let X = ($("#coordinateX").val()).replaceAll(",", ".");
                    let Y = $('input[name="Y"]:checked').val();
                    if (R !== "" && X !== "" && Y !== null) {
                        $.post("script.php",{
                                "R": R,
                                "X": X,
                                "Y": Y
                            }
                        )
                            .done(result => $("#some-text").html(result));
                    }
                    else{
                        $("#errorFields").show();
                    }
                }
                document.querySelector(".valueR").onkeyup = digitR; 
                document.querySelector(".coordinateX").onkeyup = digitX;
            </script>

</body>
</html>
