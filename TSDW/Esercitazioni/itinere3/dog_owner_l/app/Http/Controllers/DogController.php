<?php

namespace App\Http\Controllers;

use App\Models\Dog;
use App\Models\Owner;
use Illuminate\Http\Request;

class DogController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('dogs.index', ["dogs"=>Dog::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('dogs.create', ["owners"=>Owner::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Dog::create($request->all());
        return redirect()->route('dogs.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(Dog $dog)
    {
        return view('dogs.show', ["dog"=>$dog]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Dog $dog)
    {
        return view('dogs.edit', ["dog"=>$dog, "owners"=>Owner::all()]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Dog $dog)
    {
        $dog->update($request->all());
        return redirect()->route('dogs.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Dog $dog)
    {
        $dog->delete();
        return redirect()->route('dogs.index');
    }
}
