<?php

namespace App\Http\Controllers;

use App\Models\River;
use App\Models\Continent;
use Illuminate\Http\Request;

class RiverController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('rivers.index', ['rivers'=>River::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('rivers.create', ['continents'=>Continent::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        River::create($request->all());
        return redirect()->route('rivers.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(River $river)
    {
        return view('rivers.show', ['river'=>$river]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(River $river)
    {
        return view('river.edit', ['river'=>$river, 'continents'=>Continent::all()]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, River $river)
    {
        $river->update($request->all());
        return redirect()->route('rivers.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(River $river)
    {
        $river->delete();
        return redirect()->route('rivers.index');
    }

    public function filter()
    {
        $rivers=River::where("length", ">", 3000)->get();
        return view('rivers.index', ['rivers'=>$rivers]);
    }
}
