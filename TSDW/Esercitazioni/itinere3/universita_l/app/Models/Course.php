<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Course extends Model
{
    use HasFactory;

    protected $fillable=['nome', 'cfu', 'dipartimento'];

    public function student(){
        return $this->belongsTo(Student::class);
    }
}
