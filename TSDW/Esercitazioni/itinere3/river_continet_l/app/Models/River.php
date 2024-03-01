<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class River extends Model
{
    use HasFactory;

    protected $fillable=['name', 'lenght', 'continent_id'];

    public function rivers(){
        return $this->hasMany(River::class);
    }
}
