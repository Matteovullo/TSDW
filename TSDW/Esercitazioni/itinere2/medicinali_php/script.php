<?php

$conn=new mysqli("localhost", "root", "root", "Database");

if($conn->connect_error){
    die("Connessione non riuscita".$conn->connect_error);
}

if($_SERVER["REQUEST_METHOD"]=="GET"){
    if($_GET["action"]=="read"){
        $query="SELECT * FROM farmacia";
        $res=$conn->query($query);

        if($res->num_rows>0){
            while($rows=$res->fetch_assoc()){
                print("<p>ID: ".$rows["id"].", Nome: ".$rows["nome"].
                ", principio: ".$rows["principio"].", forma: ".$rows["forma"].
                ", dosaggio: ".$rows["dosaggio"].", scadenza: ".$rows["scadenza"]."<p>");
            }
        }
    }
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if($_POST["action"]=="create"){
        $nome=$_POST["nome"];
        $principio=$_POST["principio"];
        $forma=$_POST["forma"];
        $dosaggio=$_POST["dosaggio"];
        $scadenza=$_POST["scadenza"];

        $query="INSERT INTO farmacia (nome, principio, forma, dosaggio, scadenza) VALUES (?,?,?,?,?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sssss", $nome, $principio, $forma, $dosaggio, $scadenza);
        $stmt->execute();

        echo "Farmaco inserito correttamente";
    }

    if($_POST["action"]=="delete"){
        $nome=$_POST["nome"];

        $query="DELETE FROM farmacia WHERE nome=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("s", $nome);
        $stmt->execute();

        echo "Farmaco eliminato correttamente";
    }

    if($_POST["action"]=="update"){
        $nome=$_POST["nome"];
        $scadenza=$_POST["scadenza"];

        $query="UPDATE farmacia SET scadenza=? WHERE nome=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $scadenza, $nome);
        $stmt->execute();

        echo "Farmaco aggiornato correttamente";
    }
}
?>