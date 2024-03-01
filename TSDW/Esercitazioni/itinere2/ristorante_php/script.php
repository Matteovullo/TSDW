<?php

$conn=new mysqli("localhost", "root", "root", "ristorante");

if($conn->connect_error)
    die("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"]=="GET"){
    if($_GET["action"]=="read_c"){
        $query="SELECT * FROM cameriere";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                print("<p>id: ".$rows["id"].", nome: ".$rows["nome"].
                ", cognome: ".$rows["cognome"].", username: ".$rows["username"].
                ", password: ".$rows["password"]."<p>");
                
                print("<form action='script.php' method='post'>");
                print("<button type=submit>elimina</button>");
                print("<input type='hidden' value='delete' name='action'>");
                print("<input type='hidden' value='".$rows["id"]."' "."name='id'>");
                print("</form>");
            }
        }
    }

    if($_GET["action"]=="read_t"){
        $query="SELECT * FROM tavolo";
        $result=$conn->query($query);

        if($result->num_rows > 0){
            while($rows=$result->fetch_assoc()){
                print("<p>id: ".$rows["id"].", id_camerire: ".$rows["id_cameriere"].
                ", num_posti: ".$rows["num_posti"].", stato: ".$rows["stato"]."<p>");

                if($rows["stato"] == "occupato"){
                    print("<form action='script.php' method='post'>");
                    print("<button type=submit>libera</button>");
                    print("<input type='hidden' value='update_l' name='action'>");
                    print("<input type='hidden' value='".$rows["id"]."' "."name='id'>");
                    print("<input type='hidden' value='".$rows["id_cameriere"]."' "."name='id_cameriere'>");
                    print("</form>");
                }else if($rows["stato"] == "libero"){
                    print("<form action='script.php' method='post'>");
                    print("<input type='hidden' value='update_o' name='action'>");
                    print("<input type='hidden' value='".$rows["id"]."' "."name='id'>");
                    print("<label for='id_camariere'>inserire cameriere: </label>");
                    print("<input type='text' name='id_cameriere' id='id_cameriere'><br>");
                    print("<button type='submit'>occupa</button>");
                    print("</form>");
                }
            }
        }
    }
}

if($_SERVER["REQUEST_METHOD"]=="POST"){
    if($_POST["action"]=="create"){
        $nome=$_POST["nome"];
        $cognome=$_POST["cognome"];
        $username=$_POST["username"];
        $password=$_POST["password"];

        $query="INSERT INTO cameriere (nome, cognome, username, password) VALUES (?, ?, ?, ?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ssss", $nome, $cognome, $username, $password);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Cameriere inserito correttamente";
        }
    }

    if($_POST["action"]=="delete"){
        $id=$_POST["id"];

        $query="DELETE FROM cameriere WHERE id=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("s", $id);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Cameriere inserito correttamente";
        }
    }

    if($_POST["action"]=="update_l"){
        $stato="libero";
        $id=$_POST["id"];
        $id_cameriere=null;

        $query="UPDATE tavolo SET id_cameriere=?, stato=? WHERE id=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sss", $id_cameriere, $stato, $id);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Stato cambiato correttamente";
        }
    }

    if($_POST["action"]=="update_o"){
        $stato="occupato";
        $id=$_POST["id"];
        $id_cameriere=$_POST["id_cameriere"];

        $query="UPDATE tavolo SET id_cameriere=?, stato=? WHERE id=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("sss", $id_cameriere, $stato, $id);
        $stmt->execute();

        if($stmt->affected_rows > 0){
            echo "Stato cambiato correttamente";
        }
    }
}

?>