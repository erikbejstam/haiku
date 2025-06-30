# Little diary

I'll be writing in this file every time I've been working on the project, just to that I can remember when I pick it up again what it was I actually did last time, and why I did it.

### 10/6

Yesterday I added some security, and wanted to start experimenting with users. I still don't exactly what I am doing, and I should probably make that clearer to myself.
Today I implemented the User-part of the model, although very minimally. It is not done at all. 
I am still kind of confused in many ways on what every little thing in a Spring application does, and tomorrow I should probably write some Anki flashcards and stuff
to internalize that a little better. 
I should also understand kind of *how* the UserDetails in memory thing differs from the H2 DB. 
But I think however that I should keep on experimenting with H2, however. 

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
3. Created a local postgres user on my machine
4. Created a user *in* Postgres called haikuist, and generated a pw for him.
5. When I tried logging in, it didn't work. I realized in the Postgres conf file some settings where set as authenticating the user with "ident" or something, which didn't work on my machine, so I had to set that to "md5" for password authentication instead.
6. Then it worked, and my app could connect to the db.
7. I had to set some specific settings in applications.properties for the data to be created on startup, but after a while it started working.

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

### 16/6

I've implemented the controller function and functionality for adding a haiku more completely now. I don't know if I'm following best practices at all, and I'm not 100% sure
I've done everything right everywhere haha. I should probably go back and look at this later. What I do now:

The controller function receives the form data, and I have a @Valid check only on haiku.text (that is the only thing that comes from the from).
Then the controller calls haikuService.process, which in turns uses authentication context + userService to get the right (currently logged in user) form DB.
Ok, and then it adds that user + timestamp to the haiku object, that is now complete. Then it uses haikuService.save() to save it to db.

This all seems to work. But I have to have some more validation, and just make sure everything is properly written. One weird thing I immediately see, is there is a difference
between \n and "down arrow" symbol in the database. Look in the database window in IntelliJ and you'll see. What's that about??

### 19/6

Added the background image. Note: I have to fix, or at least mull over, a problem. The html body element does not cover the whole viewport right now, which maybe it should.
Because the background image, that is in the body element, does not cover the whole screen right now, of that reason. I did an ugly solution *for now*, which is
that is made the background color the same color as the background image, so it looks as if the whole "background" is one whole image/background elemenet or whatever.
Maybe the will always work, I'm not sure. I just need to think that through. It's been added to the todo-list.

### 26/6

Added docker-compose for running Postgres DB in container. I think it'll be very beneficial to have it containerized.
I have to make sure to add an env file and put in the proper variables. I think then
I should be able to spin up a postgres db in the container. I just want to configure the
environment so that I can develop on any machine, preferably. 

Ok, it doesn't seem that messy to set this up. You basically don't have to chang anything
in the Spring config, like the application.properties or anything. 
Since in your docker-compose you map the container running the db to 5432, it will 
work not changing anything basically. You just have to shut down the "normal" postgres DB running at that address.
So just 
1) make sure you have an env file (preferably when you start the db, so it auto-sets up your db). 
In the env file you have db name, user, pw.
2) Then make sure you have exported a system env variable for the password that application.properties can use.
3) Start the container with `docker-compose up -d` (detached). 

... and the container should be up and running and you should be able to conenct to it i think?
Try at home. Windows is super bad and i didn't have the patience to fix everything right now at work.

### 28/6 

#### First session

Today it's all about UI. I've started by trying to figure out how the actual poem on the home page should be rendered.
I will have the controller serve a bunch of poems still, but all of them will be hidden except the first one
(using Thymeleaf - you can include iterStat which gives iteration data when you're doing a loop, like index).

So I've solved the above I think. I've also centered the poem. It used padding to center it, because if I did align-items: center ofc
it centered it within the flexbox, but that results in it being slightly below center of the page since the flexbox isn't the entire page.
This will do for now.

I will now have to figure out how to display the text in a nicer way, and also make the menu button show its items in a nice way.

#### Second session 

I think I managed to solve the problem of the newlines, which basically is just getting out of the way. If you just 
write three line, like you should, in the form, and then let the formatting of those newlines stay as they are, everything
will appear just fine. 

#### Third session

Updated the design of the Haiku. I think it looks ok now. Updated design a little it of /post and /users/{id}, but those
still need a lot of more work. 
I then added the menu button, and started working on making it work correctly. 
I added a script to make it toggelable, but the menu items still have a default look, and the links don't work.
Otherwise I'm pretty happy with today - I think the design significantly improved. GG

### 29/6

#### First session

Continued on design path. Front page looks pretty nice, I'm happy with it for now. Presentable. The functionality is not there, and I probably want to 
fix a lot of details (everything from the background to CSS best practices to color schemes etc). But the initial impression of entering the front page
is pretty pleasant, I think. I decided to inline the SVGs since there aren't that many of them, and it just felt easier implementing on-hover color changes that way.
I added the arrow SVGs. I still feel like I should take a good hard look at all the CSS in my project, but maybe that's for a later time :) It always becomes a bit 
messy when you don't have the full plan/layout in your head and you just add stuff on top of each other. For example the menu, which for now has absolute positioning.
It's probably better if I make it relative with a flexbox etc. I think it's going to make designing easier when I'm making the design responsive etc. 

I think I'm happy enough with the raw design of the front page to add a little screenshot of it to the readme, just so visitors get an initial presentation of what the
website is about and what it looks like.

#### Second session

Added the functionality for navigating between haiku. Works good. Tried to add animation but didn't work, will have to fix that later. Apparently an element can't fade in 
if it has `display: none` which i had for the hidden haiku. So I don't know if i have to have a temporary class maybe, that removes that property, but still keeps it invisible. We'll see.

I'm also unsure of the font. I really like Bodoni Moda, but it's a bit hard to read, unfortunately. I tried to find something else, but didn't manage to find anything 
that looked nice. I'll have to find something better later.

### 30/6

No code written today. I've been thinking about what are the next essential steps. I basically *have* the data I want in the application, now it's mostly matter of displaying it.
I think it's five categories that are TODO: 

1. Design all pages - user, post, , login/signup, options, search results?
2. Functionality - follow, custom haiku feeds, enable rules, languages, etc
3. Database implementation of said functionality - following relationships, upvotes on haikus etc
4. Security - making sure everything is secure and safe.
5. "Real user" functionality, like signing up and getting an email.

I'm not sure, but I think a good order could be 1 (partially), 4, 5, 2, 3? 