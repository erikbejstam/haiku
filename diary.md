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


### 12/6 

#### First session

I started by, just to get going, adding functionality in the menu fragment that switches between a login/logout button depending on whether the client is 
logged in or not.

Then I implemented some slightly nicer design, just so it's not so hard on the eyes.

#### Second session

I added a Postgres database. In the Java code, nothing much changed. I just had problems at first setting things up. But basically I 
1. Installed Postgres
2. Set it up as a service with systemctl
2. Created a local postgres user on my machine
3. Created a user *in* Postgres called haikuist, and generated a pw for him.
4. When I tried logging in, it didn't work. I realized in the Postgres conf file some settings where set as authenticating the user with "ident" or something, which didn't work on my machine, so I had to set that to "md5" for password authentication instead.
5. Then it worked, and my app could connect to the db.
6. I had to set some specific settings in applications.properties for the data to be created on startup, but after a while it started working.

### 14/6

Late night session, not super much done. I changed application.properties so it doesn't mess with the db data/drop all the tables
upon start up. I just initialized the new schema and init data in psql in terminal instead.

And about that: I wrote down the exact design of the database (which is very simple). I then made sure the Hibernate
code seems fine. I want to practice more Hibernate though, but it seems to work pretty well for now.
The details of the schema can be seen in `schema.sql`. I'll probably extend in the future, but for now it works fine.

I also added the DB into IntelliJ. I don't really know yet what that lets me do but there's that.

### 15/6 

I added a form input on the main page. Then I added a basic controller, that redirects the user to home page, not saving the haiku rn.

I renamed the controllers. So now I have no rest controllers, they're mvc controllers i think. I have the MainController that renders the pages basically, and then HaikuController and
UserController for doing a CRUD action on one of the entities respectively, and then rendering one of the same pages. Example: MainController renders "/". HaikuController creates
a haiku, saves it, and THEN renders "/".