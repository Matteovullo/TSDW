<?php

namespace App\Http\Controllers;

use App\Models\Author;
use App\Models\Book;
use Illuminate\Http\Request;

class BookController extends Controller
{
        /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('books.index', ["books"=>Book::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('books.create', ["authors"=>Author::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Book::create($request->all());
        return redirect()->route('books.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(Book $book)
    {
        return view('books.show', ["b"=>$book]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Book $book)
    {
        return view('books.edit', ["b"=>$book, "authors"=>Author::all()]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Book $book)
    {
        $book->update($request->all());
        return redirect()->route('books.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Book $book)
    {
        $book->delete();
        return redirect()->route('books.index');
    }

    public function deleteAll()
    {
        Book::query()->delete();

        return redirect('/books');
    }

    public function decreaseAllPrice(Request $request)
    {
    $percentage = $request->input('percentage');

    if ($percentage && is_numeric($percentage)) {
        Book::query()->decrement('prezzo', $percentage);
    }

    return redirect('/books');
    }

}
