<?php

namespace App\Http\Controllers;

use App\Models\Team;
use App\Models\Player;
use Illuminate\Http\Request;

class TeamController extends Controller
{
    public function index()
    {
        return view('teams.index', ["teams"=>Team::all()]);
    }

    public function create()
    {
        return view('teams.create', ["players"=>Player::all()]);
    }

    public function store(Request $request)
    {
        Team::create($request->all());
        return redirect()->route('teams.index');
    }

    public function show(Team $team)
    {
        return view('teams.', ["team"=>$team]);
    }

    /*
    public function edit(Team $team)
    {
        return view('teams.edit', ["team"=>$team, "teams"=>Team::all()]);
    }
    */

    public function edit(Player $player)
    {
        $teams = Team::all(); // Recupera tutti i team dal database
        return view('players.edit', ['player' => $player, 'teams' => $teams]);
    }

    public function update(Request $request, Team $team)
    {
        $team->update($request->all());
        return redirect()->route('teams.index');
    }

    public function destroy(Team $team)
    {
        $team->delete();
        return redirect()->route('teams.index');
    }
}
