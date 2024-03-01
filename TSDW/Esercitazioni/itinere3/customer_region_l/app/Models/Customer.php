<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Customer extends Model
{
    use HasFactory;

    protected $fillable=['name', 'job', 'img', 'id_region', 'price'];

    public function regions(){
        return $this->hasMany(Region::class);
    }
}
