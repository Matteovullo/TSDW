<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Author extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'cognome', 'eta'];

    public function boos(){
        return $this->belongTo(Book::class);
    }
}
