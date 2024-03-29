<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Order extends Model
{
    use HasFactory;

    protected $fillable=['prezzo', 'tavolo', 'client'];

    public function client(){
        return $this->hasMany(Client::class);
    }
}
