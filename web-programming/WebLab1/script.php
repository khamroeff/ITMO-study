<?php
    session_start();
if (!isset($_SESSION['results'])) {
        $_SESSION['results'] = array();
}

$answer="";
if (isset($_POST['X'])&&isset($_POST['Y'])&&isset($_POST['R'])) {
    $startTime = microtime(true); //Время начала
    $now = date("d.m.y H:i");
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
    $workTime = round($workTime, 7);
    $result = array($now, $workTime, $X, $Y, $R, $answer); //Заполняем переменную
    array_push($_SESSION['results'], $result);      //сессии 
}
    ?>
    <!--Вывод-->
    <h3 id="answer"> <?php echo $answer; ?></h3>
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