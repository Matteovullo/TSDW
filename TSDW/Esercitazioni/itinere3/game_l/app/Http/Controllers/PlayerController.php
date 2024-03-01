<?php

namespace App\Http\Controllers;

use App\Models\Player;
use Illuminate\Http\Request;

class PlayerController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view("players.index", ["players"=>Player::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view("players.create");
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Player::create($request->all());
        return redirect()->route('players.index');
    }
    /**
     * Display the specified resource.
     */
    public function show(Player $player)
    {
        return view('players.show', ['player'=>$player]);
    }
    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Player $player)
    {
        return view('players.edit', ['player'=>$player]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Player $player)
    {
        $player->update($request->all());
        return redirect()->route('players.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Player $player)
    {
        $player->delete();
        return redirect()->route('players.index');
    }
}
