<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Game extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'prezzo', 'player'];

    public function player(){
        return $this->hasMany(Player::class);
    }
}
