<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class FilmController extends Controller
{
    public function read(){
        $films=DB::table('attori')->get();
        return view("read", ['films'=>$films]);
    }

    public function insert(){
        DB::table('attori')->insert([
            "nome" => $_POST["nome"],
            "cognome" => $_POST["cognome"],
            "data_nascita" => $_POST["data_nascita"]
        ]);

        return redirect("/read");
    }

    public function update(){
        DB::table("attori")->where("id_attori", $_POST["id_attori"])->update([
            "nome" => $_POST["nome"],
            "cognome" => $_POST["cognome"],
            "data_nascita" => $_POST["data_nascita"]
        ]);

        return redirect("/read");
    }

    public function delete(Request $request){
        DB::table('attori')->where("id_attori", $request->input("id_attori"))->delete();
        return redirect("/read");
    }

}
