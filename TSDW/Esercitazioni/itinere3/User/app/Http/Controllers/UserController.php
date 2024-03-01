<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Model\Users;

class UserController extends Controller
{
    public function read(){
        return view('read', ['user'=>User::all()]);
    }

}
