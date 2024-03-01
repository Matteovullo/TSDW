<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FilmController;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/read', [FilmController::class, 'read']);

Route::post('/insert', [FilmController::class, 'insert']);

Route::get('/update', function () {
    return view('update');
});

Route::put('/update', [FilmController::class, 'update']);

Route::delete('/delete', [FilmController::class, 'delete']);