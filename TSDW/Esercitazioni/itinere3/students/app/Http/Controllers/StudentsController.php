<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Students;

class StudentsController extends Controller
{
    public function read(){
        return view('read', ['students'=>Students::all()]);
    }

    public function insert(Request $request){
        Students::create($request->all());
        return redirect('/read');
    }

    public function form(Request $request){
        if($request->input('action') === 'update'){
            return view('update', ['students'=>(object)$request->all()]);
        }

        if($request->input('action') === 'delete'){
            $students=Students::find($request->input('id'));
            $students->delete();
            return redirect('/read');
        }
    }

    public function update(Request $request){
        $students=Students::find($request->input('id'));
        $students->update($request->all());
        return redirect('/read');
    }
}
