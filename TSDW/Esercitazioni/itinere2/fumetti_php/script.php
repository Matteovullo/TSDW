<?php

$conn=new mysqli("localhost", "root", "root", "fumetteria");
if($conn->connect_error){
    die("Connessione non riuscita".$conn->connect_error);
}

$columns_to_check = ['nome_autore', 'cognome_autore'];

foreach ($columns_to_check as $column_name) {
    $query = "SELECT COUNT(*) AS count_column
              FROM information_schema.columns
              WHERE table_schema = 'fumetteria'
              AND table_name = 'fumetti'
              AND column_name = '$column_name'";

    $result = $conn->query($query);

    if ($result) {
        $row = $result->fetch_assoc();
        $count = $row['count_column'];

        if ($count == 0) {
            $alter_query = "ALTER TABLE `fumetti`
            ADD COLUMN `nome_autore` VARCHAR(45) NULL AFTER `anni`,
            ADD COLUMN `cognome_autore` VARCHAR(45) NULL AFTER `nome_autore`";
            $conn->query($alter_query);

            echo "Colonna '$column_name' aggiunta con successo.<br>";
        } else {
            echo "La colonna '$column_name' esiste già nella tabella.<br>";
        }
    } else {
        echo "Errore nella query.<br>";
    }
}

$query2="SELECT * FROM fumetti";
$result=$conn->query($query2);

if($result->num_rows>0){
    while($rows=$result->fetch_assoc()){
        $string=$rows["autore"];
        $parts=explode("-", $string);
        $id=$rows["id"];

        $q="UPDATE fumetti SET nome_autore=?, cognome_autore=? WHERE id=?";
        $stmt=$conn->prepare($q);
        $stmt->bind_param("ssi", $parts[0], $parts[1], $id);
        $stmt->execute();

        echo "modifica effettuata!"."<br>";
    }
}

?>