<?php

$conn=new mysqli("localhost", "root", "root", "libreria");

if($conn->connect_error)
    die.("Connessione non riuscita".$conn->connect_error);

if($_SERVER["REQUEST_METHOD"] == "POST"){
    if($_POST["action"]=="create"){
        $id=$_POST["isbn"];
        $title=$_POST["titolo"];
        $author=$_POST["autore"];
        $publisher=$_POST["publicazione"];
        $country=$_POST["paese"];
        $price=$_POST["prezzo"];

        $query="INSERT INTO books2 (isbn, title, author, publisher, country, price) VALUES (?,?,?,?,?,?)";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("ssssss", $id, $title, $author, $publisher, $country, $price);
        $stmt->execute();

        echo "Libro inserito correttamente";
    }

    if($_POST["action"]=="search"){
        $id=$_POST["autore"];
        
        $query="SELECT * FROM books2 WHERE author=?";
        $stmt=$conn->prepare($query);
        $stmt->bind_param("s", $id);
        $stmt->execute();
        $res=$stmt->get_result();

        if($res->num_rows > 0){
            while($rows=$res->fetch_assoc()){
                print("<p>isbn: ".$rows["isbn"].", titolo: ".$rows["title"].", publicazione: ".$rows["publisher"].
                ", prezzo: ".$rows["price"].", paese: ".$rows["country"].", autore: ".$rows["author"]);
            }
        }
    }
    
    if($_POST["action"]=="increment"){
        $query="UPDATE books2 SET price=price+price*0.1 WHERE country=105";
        $res=$conn->query($query);

        $query="UPDATE books2 SET price=price+price*0.2 WHERE country<>105";
        $res=$conn->query($query);
    }
}

?>

<html>
    <body>
        <h2>Inseriento<h2>
        <form action="script.php" method="post">
            <label for="isbn">isbn:</label>
            <input type="text" name="isbn" id="isbn" required><br>

            <label for="titolo">titolo:</label>
            <input type="text" name="titolo" id="titolo" required><br>

            <label for="publicazione">publicazione:</label>
            <input type="text" name="publicazione" id="publicazione" required><br>

            <label for="prezzo">prezzo:</label>
            <input type="text" name="prezzo" id="prezzo" required><br>
            
            <label for="paese">paese:</label>
            <select name="paese">
                <?php
                    $conn=new mysqli("localhost", "root", "root", "libreria");

                    if($conn->connect_error)
                        die.("Connessione non riuscita".$conn->connect_error);

                    $query="SELECT id, name FROM country";
                    $res=$conn->query($query);

                    if($res->num_rows > 0){
                        while($rows=$res->fetch_assoc()){
                            $id=$rows["id"];
                            $name=$rows["name"];
                            print("<option value=".$id.">".$name."<option>");
                        }
                    }

                ?>
            </select><br>

            <label for="autore">autore:</label>
            <select name="autore">
            <?php
                $conn=new mysqli("localhost", "root", "root", "libreria");

                if($conn->connect_error)
                    die.("Connessione non riuscita".$conn->connect_error);

                $query="SELECT id, firstname FROM authors";
                $res=$conn->query($query);

                if($res->num_rows > 0){
                    while($rows=$res->fetch_assoc()){
                        $id=$rows["id"];
                        $firstname=$rows["firstname"];
                        print("<option value=".$id.">".$firstname."<option>");
                    }
                }

            ?>
            </select><br>

            <input type="hidden" value="create" name="action">
            <button type="submit">inserimento</button>
        </form>

        <h2>Cerca<h2>
        <form action="script.php" method="post">
        </select><br>

        <label for="autore">autore:</label>
        <select name="autore">
            <?php
                $conn=new mysqli("localhost", "root", "root", "libreria");

                if($conn->connect_error)
                    die.("Connessione non riuscita".$conn->connect_error);

                $query="SELECT id, firstname FROM authors";
                $res=$conn->query($query);

                if($res->num_rows > 0){
                    while($rows=$res->fetch_assoc()){
                        $id=$rows["id"];
                        $firstname=$rows["firstname"];
                        print("<option value=".$id.">".$firstname."<option>");
                    }
                }

            ?>
        </select><br>

            <input type="hidden" value="search" name="action">
            <button type="submit">cerca</button>
        </form> 

        <h2>Incrementa<h2>
        <form action="script.php" method="post">
            <input type="hidden" value="increment" name="action">
            <button type="submit">incrementa</button>
        </form>
     </body>
 </html>

