<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Student extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'cognome', 'eta'];

    public function books(){
        return $this->belongTo(Book::class);
    }
}
