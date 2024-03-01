<?php

$conn=new mysqli("localhost", "root", "root", "magazzino");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"]=="GET"){
    if($_GET["action"]=="read"){
        $query="SELECT * FROM prodotti";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                if($rows["giacenza"] > 0){
                    echo "<p>ID: ".$rows["id"].", nome: ".$rows["nome_prodotto"].
                        ", giacenza: ".$rows["giacenza"].", prezzo: ".$rows["prezzo"]."<p>";

                    print("<form action='script.php' method='post'>");
                    print("<input type='hidden' value='update' name='action'>");
                    print("<label for='giacenza'>Giacenza: </label>");
                    print("<input type='text' id='giacenza' name='giacenza' required>");
                    print("<input type='hidden' value='".$rows["nome_prodotto"]."' "."name='nome'>");
                    print("<button type='submit'>compra</button>");
                    print("</form>");

                    print("<form action='script.php' method='post'>");
                    print("<input type='hidden' value='delete' name='action'>");
                    print("<input type='hidden' value='".$rows["nome_prodotto"]."' "."name='nome'>");
                    print("<button type='submit'>elimina</button>");
                    print("</form>");
                }
            }
        }
    }
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if($_POST["action"]=="create"){
        $nome_corso=$_POST["nome"];
        $giacenza=$_POST["giacenza"];
        $prezzo=$_POST["prezzo"];
        $query="INSERT INTO prodotti (nome_prodotto, giacenza, prezzo) VALUES (?, ?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sss", $nome_corso, $giacenza, $prezzo);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Prodotto inserito correttamente"."<br>";
        }
    }

    if($_POST["action"]=="update"){
        $nome_corso=$_POST["nome"];
        $giacenza_n=$_POST["giacenza"];
        $query="UPDATE prodotti SET giacenza=giacenza-? WHERE nome_prodotto=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ss", $giacenza_n, $nome_corso);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Prodotto acquistato correttamente"."<br>";
        }
    }

    if($_POST["action"]=="delete"){
        $nome_corso=$_POST["nome"];
        $query="DELETE FROM prodotti WHERE nome_prodotto=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("s", $nome_corso);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Prodotto eliminato correttamente"."<br>";
        }
    }
}

?>