<?php

use Illuminate\Support\Facades\Route;

use App\Http\Controllers\GameController;

use App\Http\Controllers\PlayerController;


Route::get('/', function () {
    return view('welcome');
});

Route::resource('games', GameController::class);

Route::resource('players', PlayerController::class);
