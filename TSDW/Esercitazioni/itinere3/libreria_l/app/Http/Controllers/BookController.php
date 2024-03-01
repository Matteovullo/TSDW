<?php

namespace App\Http\Controllers;

use App\Models\book;
use App\Models\student;
use Illuminate\Http\Request;

class BookController extends Controller
{
    public function index()
    {
        return view('books.index', ["books"=>Book::all()]);
    }

    public function create()
    {
        return view('books.create', ["students"=>Student::all()]);
    }

    public function store(Request $request)
    {
        Book::create($request->all());
        return redirect()->route('books.index');
    }

    public function show(Book $book)
    {
        return view('books.show', ["book"=>$book]);
    }

    public function edit(Book $book)
    {
        return view('books.edit', ["students"=>Student::all(), "book"=>$book]);
    }

    public function update(Request $request, Book $book)
    {
        $book->update($request->all());
        return redirect()->route('books.index');
    }

    public function destroy(Book $book)
    {
        $book->delete();
        return redirect()->route('books.index');
    }

}
