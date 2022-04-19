package com.example.taskmanagement;


//I do not know what this class is responsible for, I need more clarification and examples
//Clarification from Eden:
/* I dont know if we need a class to do this, however we need a way to store tasks that the user wants to keep in a way that is not just temporary.
*   I have a feeling if we just used a large vector to store all the data that we need from sessoin to session on the app, it would be temporary and the phone would delete it
*   So we need a more permenant solution for this. As it stands now, I think that it actually is ok that we dont have a database, as we can test all our methods with
*   functions that only use temporary, made-up data, however this is still a very imporatnat problem that needs to be solved soon.
*
*   Also, thank you Pouria for researching this. */


/*
       Response from Pouria:
No problem. I did some research and wrote a lot in detail in the Jira post but now that I understand what we need...
we definitely need this class.
    This class would be responsible for the following:
        Adding Tasks
        Deleting Tasks
        Editing Tasks
        When user exists out of app, tasks stays

There are several ways to implement this, if you look on the Jira post, you can find them.
I say we use the Room library, but I would have to do some tests on my own to see which is best.


 */
public class Database {
}
