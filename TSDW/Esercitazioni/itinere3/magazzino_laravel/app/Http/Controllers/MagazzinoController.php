<?php

namespace App\Http\Controllers;
use Illuminate\Support\Facades\DB;

class MagazzinoController extends Controller
{
    public function read(){
        $prodotti=DB::table('prodotti')->get();
        return view('read', ['prodotti'=>$prodotti]);
    }

    public function insert(){
        DB::table('prodotti')->insert([
            "nome_prodotto" => $_POST["nome_prodotto"],
            "giacenza" => $_POST["giacenza"],
            "prezzo" => $_POST["prezzo"]
        ]);

        return redirect("/read");
    }


    public function delete(){
        
    }

    public function update(){
        DB::table('prodotti')->where("id", $_POST["id"])->update([
            "nome_prodotto" => $_POST["nome_prodotto"],
            "giacenza" => $_POST["giacenza"],
            "prezzo" => $_POST["prezzo"]
        ]);

        return redirect("/read");
    }
}
