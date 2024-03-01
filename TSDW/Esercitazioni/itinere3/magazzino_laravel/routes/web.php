<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\MagazzinoController;

Route::get('/', function () {
    return view('index');
});

Route::get('/read', [MagazzinoController::class, 'read']);

Route::post('/insert', [MagazzinoController::class, 'insert']);

Route::post('/update', [MagazzinoController::class, 'update']);

Route::put('/update', function(){
    return view('update');
});

