<?php

namespace App\Http\Controllers;

use App\Models\Game;
use App\Models\Player;
use Illuminate\Http\Request;

class GameController extends Controller
{
    public function index()
    {
        return view('games.index', ["games" => Game::all()]);
    }

    public function create()
    {
        return view('games.create', ["players" => Player::all()]);
    }

    public function store(Request $request)
    {
        Game::create($request->all());
        return redirect()->route('games.index');
    }

    public function show(Game $game)
    {
        return view('games.show', ['game' => $game]);
    }

    public function edit(Game $game)
    {
        return view('games.edit', ['game' => $game, 'players' => Player::all()]);
    }

    public function update(Request $request, Game $game)
    {
        $game->update($request->all());
        return redirect()->route('games.index');
    }

    public function destroy(Game $game)
    {
        $game->delete();
        return redirect()->route('games.index');
    }
}

