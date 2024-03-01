<?php

namespace App\Http\Controllers;

use App\Models\Continent;
use Illuminate\Http\Request;

class ContinentController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('continents.index', ['continents'=>Continent::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('continents.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Continent::create($request->all());
        return redirect()->route('continents.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(Continent $continent)
    {
        return view('continents.show', ['continent'=>$continent]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Continent $continent)
    {
        return view('continents.edit', ['continent'=>$continent]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Continent $continent)
    {
        $continent->update($request->all());
        return redirect()->route('continents.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Continent $continent)
    {
        $continent->delete();
        return redirect()->route('continents.index');
    }
}
