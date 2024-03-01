<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Owner extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'cognome'];

    public function dog(){
        return $this->belongTo(Dog::class);
    }
}
