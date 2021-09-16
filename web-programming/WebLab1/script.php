<?php
    session_start();
if (!isset($_SESSION['historyResults'])) {
    $_SESSION['historyResults'] = array();
}
$message="";
if (isset($_POST['X'])&&isset($_POST['Y'])&&isset($_POST['R'])) {
//запоминаем время начала работы скрипта
    $start = microtime(true);
//получаем дату и время по москве
    date_default_timezone_set('Europe/Moscow');
    $now = date("d.m.y H:i");
//получаем параметры из index.php

    $X = $_POST['X'];
    $Y = $_POST['Y'];
    $R = $_POST['R'];

    if (
        (($Y >= -$R / 2 && $Y <= 0) && ($X >= -$R && $X <= 0)) ||
        ($Y >= 0 && $X >= 0 && $Y + $X <= $R / 2) ||
        ($Y <= 0 && $X >= 0 && $Y * $Y + $X * $X <= $R * $R / 4)
    )
        $message = "Точка входит в область";
    else $message = "Точка не входит в область";
//получаем время окончания работы скрипта
    $finish = microtime(true);
//высчитываем время работы (разницу) и округляем
    $timeWork = $finish - $start;
    $timeWork = round($timeWork, 7);
//заполняем переменную сессии для отображения всей таблицы
    $result = array($now, $timeWork, $X, $Y, $R, $message);
    array_push($_SESSION['historyResults'], $result);
}
?>
<!--Выводим сообщение о результате-->
<h3 id="message"> <?php echo $message; ?></h3>
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
        for ($m=0;$m<sizeof($_SESSION['historyResults']);$m++){
            echo "<tr>";
            echo "<td>".$_SESSION['historyResults'][$m][0]."</td>";
            echo "<td>".$_SESSION['historyResults'][$m][1]."</td>";
            echo "<td>".$_SESSION['historyResults'][$m][2]."</td>";
            echo "<td>".$_SESSION['historyResults'][$m][3]."</td>";
            echo "<td>".$_SESSION['historyResults'][$m][4]."</td>";
            echo "<td>".$_SESSION['historyResults'][$m][5]."</td>";
            echo "</tr>";
        }
        ?>
    </table>
</div>

