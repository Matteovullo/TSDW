<?php

namespace App\Http\Controllers;

use App\Models\Player;

use App\Models\Team;
use Illuminate\Http\Request;

class PlayerController extends Controller
{
    public function index()
    {
        return view('players.index', ['players' => Player::all()]);
    }

    /*
    public function create()
    {
        return view('players.create');
    }
    */

    public function create()
    {
        $teams = Team::all(); // Ottieni tutti i team dal database
        return view('players.create', ['teams' => $teams]);
    }

    public function store(Request $request)
    {
        Player::create($request->all());
        return redirect()->route('players.index');
    }

    public function show(Player $player)
    {
        return view('players.show', ['player' => $player]); // Suppongo che tu voglia visualizzare i dettagli di un singolo giocatore
    }

    public function edit(Player $player)
    {
        return view('players.edit', ['player' => $player, 'teams' => Team::all()]);
    }

    public function update(Request $request, Player $player)
    {
        $player->update($request->all());
        return redirect()->route('players.index');
    }

    public function destroy(Player $player)
    {
        $player->delete();
        return redirect()->route('players.index');
    }
}
