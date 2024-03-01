<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB;

class FilmController extends Controller
{
    public function read(){
        $films=DB::table('films')->get();
        return view('read', ["films"=>$films]);
    }

    public function insert(){
        DB::table("films")->insert([
            "titolo" => $_POST["titolo"],
            "paese" => $_POST["paese"],
            "regista" => $_POST["regista"]
        ]);

        return redirect("/read");
    }

    public function update(){
        DB::table("films")->where("id", $_POST["id"])->update([
            "titolo" => $_POST["titolo"],
            "paese" => $_POST["paese"],
            "regista" => $_POST["regista"]
        ]);

        return redirect("/read");
    }

    public function delete(){
        DB::table('films')->where("id", $_POST["id"])->delete();

        return redirect("/read");
    }
}
