<?php

namespace App\Http\Controllers;

use App\Models\Game;
use Illuminate\Http\Request;
use App\Models\Player;

class GameController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view("games.index", ["games"=>Game::all()]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view("games.create", ["players"=>Player::all()]);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        Game::create($request->all());
        return redirect()->route('games.index');
    }
    /**
     * Display the specified resource.
     */
    public function show(Game $game)
    {
        return view('games.show', ['game'=>$game]);
    }
    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Game $game)
    {
        return view('games.edit', ['game'=>$game, 'players'=>Player::all()]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Game $game)
    {
        $game->update($request->all());
        return redirect()->route('games.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Game $game)
    {
        $game->delete();
        return redirect()->route('games.index');
    }
}