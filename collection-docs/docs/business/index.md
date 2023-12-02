# Business

## Business Rules

Collection is a software focused on allowing it's user to, well, manage collections of different kinds.

Different kinds of collections will be implemented over time and some maybe will have a higher priority that others, and this priority is solely based on what I want to do.

Some collections may have external integrations (e.g. Scryfall for MTG), but these are not guaranteed.

The supported kinds of collections are:

### Trading Card Games (TCG)

Trading cards games are one of the most popular collecting hobbies. I personally collect MTG and Flesh And Blood, and those two will be the main supported TCGs on the software first release.

Each entry of a TCG collection will contain the following fields:

- Card name (required)
- Type
- Rarity
- Set
- Rating/Grading
- Comment
- Image

### Books

Each entry of a Book collection will contain the following fields:

- Book name (required)
- Author (required)
- Publisher
- ISBN
- Rating
- Comment
- Image

### Vinyls

Each entry of a Vinyl collection will contain the following fields:

- Vinyl name (required)
- Artist (required)
- Record Label
- Rating
- Comment
- Image

### Games

Each entry of a Game collection will contain the following fields:

- Name (required)
- Rating
- Comment
- Image