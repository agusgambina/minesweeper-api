# Minesweeper API

This project is an API for minesweeper. 


## Actions

### Create a new Game

```
    url:    `localhost:9000/game/`
    type:   POST
    body:   { "player" -> "player_one", "columns" -> 15, "rows" -> 15, "mines" -> 15 }
```

### Play a turn

```
    url:    `localhost:9000/move/`
    type:   POST
    body:   { "gameId" -> 100, "position" -> { "column" -> 5, "row" -> 10}}
```
