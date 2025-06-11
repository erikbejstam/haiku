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

### 11/6

#### First session

Today I started off with writing a thymeleaf "fragment" that will be the navbar basically.

Then I update the user and main html pages to render haikus, and have links between the two pages, so it is easily navigable. 

Note: Thymeleaf uses the getters in the classes for getting the data. At first I just got a bunch of errors, but it was because i hadn't
written the getters for the right data, so Thymeleaf couldn't fint it. Remember this.

I also updated the User and Haiku classes to make the database tables interlink. I haven't fully tested this yet.
But basically I put `@OneToMany` and `@ManyToOne` annotations on the proper fields in the classes.

#### Second session

In the second session I set up the PasswordEncoder. So it is a class that Spring uses, and it's a bit of a black box for me right now
since I'm not the one doing the signin/signup stuff myself right now. 

Basically. We have a UserDetailsService which we need because Spring uses that for getting a user from the database. 
And not just getting a user, but creating a UserDetails object. That's just how Spring wants to deal with this stuff. 
That's because there has to be a standardized way of handling login. A UserDetails object has just username, password, role and optionally flags.
This makes it very easy to deal with. So when someone puts in their credentials in the login form, Spring takes that and grabs the UserDetails from
the DB, and compares it. If they're the same, the user is now logged in. 

Note: I had to manually encrypt password "p" and put it into the data.sql haikuuser entry. 