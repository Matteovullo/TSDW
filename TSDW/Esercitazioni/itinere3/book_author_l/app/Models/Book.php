<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'volume', 'prezzo', 'author'];

    public function authors(){
        return $this->hasMany(Author::class);
    }
}
