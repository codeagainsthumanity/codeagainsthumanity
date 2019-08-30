// -- LEVEL 1
// -- Tables and References

// Creating tables
Table users as U {
  id int
	full_name varchar
  created_at timestamp
  expansions list<pack>
  removed_by_user_cards list<whitecard>
  black_cards_won <blackcard>
  pic_url String
  language String
}

Table gameInstance {
  game_id int 
  owner_id int // users id
  current_whitedeck List<whitecard>
  current_blackdeck List<blackcard>
  show_rules boolean
}

Table blackDeck {
  blackdeck List<blackcard>
}

Table whiteDeck {
  whitedeck List<whitecard>
}



// Creating references
// You can also define relaionship separately
// > many-to-one; < one-to-many; - one-to-one
Ref: U.id < gameInstance.game_id  


//----------------------------------------------//
