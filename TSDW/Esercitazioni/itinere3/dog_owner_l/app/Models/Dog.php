<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Dog extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'eta', 'owner'];

    public function owner(){
        return $this->hasMany(Owner::class);
    }
}
