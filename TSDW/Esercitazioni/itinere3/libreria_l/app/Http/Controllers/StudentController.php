<?php

namespace App\Http\Controllers;

use App\Models\Student;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    public function index()
    {
        return view('students.index', ["students"=>Student::all()]);
    }

    public function create()
    {
        return view("students.create");
    }
    /*
    public function store(Request $request)
    {
        Student::create($request->all());
        return redirect()->route('students.index');
    }
    */
    public function store(Request $request)
    {
        $data = $request->validate([
            'nome' => 'required',
            'cognome' => 'required',
            'eta' => 'required',
        ]);

    Student::create($data);
    return redirect()->route('students.index');
    }


    public function show(Student $student)
    {
        return view('students.show', ["student"=>$student]);
    }

    public function edit(Student $student)
    {
        return view('students.edit', ["student"=>$student]);
    }

    public function update(Request $request, Student $student)
    {
        $student->update($request->all());
        return redirect()->route('students.index');
    }

    public function destroy(Student $student)
    {
        $student->delete();
        return redirect()->route('students.index');
    }
}
