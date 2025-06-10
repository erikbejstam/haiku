# Little diary

I'll be writing in this file every time I've been working on the project, just to that I can remember when i pick it up again what it was I actually did last time, and why I did it.

### 10/6

Yesterday I added some security, and wanted to start experimenting with users. I still don't exactly what I am doing, and I should probably make that clearer to myself.
Today I implemented the User-part of the model, although very minimally. It is not done at all. 
I am still kind of confused in many ways on what every little thing in a Spring application does, and tomorrow I should probably write some Anki flashcards and stuff
to internalize that a little better. 
I should also understand kind of *how* the UserDetails in memory thing differs from the H2 DB. 
But I think however that I should keep on experimenting with H2 however. 

I created the initial data which seems to work. There is one user and one haiku when you start the app. There is no connection between the entities
in the model yet however.

I have the UserController, UserRepository and the UserService. I'm not sure I wrote them correctly.
I guess the next step will be to flesh those out, and experimenting with the user.html page,
and implementing some simple security options in the `SecurityFilterChain`. 