<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\StudentsController;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/read', [StudentsController::class, 'read']);

Route::post('/insert', [StudentsController::class, 'insert']);

Route::post('/form', [StudentsController::class, 'form']);

Route::post('/update', [StudentsController::class, 'update']);
