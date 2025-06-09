# What I'm Learning From This Project

1. Config everything
2. Set up rest controller with @RestController, @RequestMapping etc
3. Integrate DB. Right now H2. Mainly stuff in application.properties. 
4. Add Model with @Entity and stuff.
5. Add Service, basically calling on Repo
6. Add Repository

... And that's pretty much what I've done for now. 

Note: when you build the POST controller, make sure it returns a ResponseEntity.

Note: when POSTing from Postman, the content-type should be application/json and the body should contain the 
variable names of the *class*, not the table variables. So `createdBy`, not `created_by`.

Next, I implement a get(id) function. In this case, you need to use @PathVariable.
It's seems proper here to start thinking about the case where an id is supplied that doesn't correspond to a row in the db.
A simple solution for now would be for the service to get Optional<Haiku> and then return `haiku.orElse(null)`. 
Then the controller can look if `haiku == null` and throw new `ResponseStatusException(HttpStatus.NOT_FOUND)`.
Else it just returns a ResponseEntity<Haiku>.

---

## Security

I'm trying to set up some simple security and user functionality right now. So basically what you need to do is, you have to create a filter chain.
So far what I've done is create a very simple such a chain, allowing all http requests access to css and jss files, but denying 
entry into for example the main html page. For now, Spring has an automatic login page that it redirects to.
The same for the logout page. This has just been for trying to learn this stuff.

I also used a very minimal UserDetailsService that is in-memory for now (so it is actually corresponding to a db). Upon start up
Spring creates a user and puts it into this UserDetailsService that "is the db" for this simple dev environment.
Later on, you'll have a more complex UserDetailsService that is functioning more like the API to the database that you
will be using. Then it'll look more like the HaikuService. 
