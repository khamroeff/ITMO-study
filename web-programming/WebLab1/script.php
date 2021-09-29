<?php
    session_start();
if (!isset($_SESSION['results'])) {
        $_SESSION['results'] = array();
}
date_default_timezone_set('Europe/Moscow');
$options = array(
    'options' => array(
        'min_range' => -5,
        'max_range' => 3,
    )
);
$answer="";
$nameErr="Результат: ";
$checkWrong=False;
if (isset($_POST['X'])&&isset($_POST['Y'])&&isset($_POST['R'])) {
    $startTime = microtime(true); //Время начала
    $now = date("d.m.y H:i");
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if ($_POST["R"] > 5 || $_POST["R"] < 2) {
           $nameErr = "invalid data ";
           $checkWrong=True;
        }else {
           $R = ($_POST["R"]);
        }
        if ($_POST["X"] > 3 || $_POST["X"] < -5) {
            $nameErr = "invalid data ";
            $checkWrong=True;
         }else {
            $X = ($_POST["X"]);
         }
         if (in_array($_POST["Y"], array("-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3"))) {
             $Y = ($_POST["Y"]);
         } else {
             $nameErr = "invalid Y";
             $checkWrong=True;
         }
        
    }
    $X = $_POST['X'];
    $Y = $_POST['Y'];
    $R = $_POST['R'];
    if (
        (($Y <= $R && $Y >= 0) && ($X >= -$R / 2 && $X <= 0)) ||
        ($Y <= 0 && $X >= 0 && $Y + 2 * $X <= $R / 2) ||
        ($Y >= 0 && $X >= 0 && $Y * $Y + $X * $X <= $R * $R / 4)
    ) $answer = "Точка входит в область";
    else $answer = "Точка не входит в область";
    $finishTime = microtime(true); //Время завершения
    $workTime = $finishTime - $startTime; //Расчет времени работы
    $workTime = round($workTime, 7)*60;
    $result = array($now, $workTime, $X, $Y, $R, $answer); //Заполняем переменную
    array_push($_SESSION['results'], $result);      //сессии 
}
    function validate($X, $Y, $R)
    {
        
    }
    ?>
    <!--Вывод-->
    <h3 id="answer"> <?php if($checkWrong == TRUE) echo $nameErr; else echo $answer; ?></h3>
    <div>
        <table class="table" id="table">
        <thead>
            <tr>
                <td>Дата и время запроса</td>
                <td>Время выполнения</td>
                <td>Координата X</td>
                <td>Координата Y</td>
                <td>Параметр R</td>
                <td>Результат</td>
            </tr>
        </thead>
        <?php
        for ($m=0;$m<sizeof($_SESSION['results']);$m++){
            echo "<tr>";
            echo "<td>".$_SESSION['results'][$m][0]."</td>";
            echo "<td>".$_SESSION['results'][$m][1]."</td>";
            echo "<td>".$_SESSION['results'][$m][2]."</td>";
            echo "<td>".$_SESSION['results'][$m][3]."</td>";
            echo "<td>".$_SESSION['results'][$m][4]."</td>";
            echo "<td>".$_SESSION['results'][$m][5]."</td>";
            echo "</tr>";
        }
        ?>
        </table>
    </div>