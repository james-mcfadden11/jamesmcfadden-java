Social Networking Kata
----------------------

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

### Scenarios

**Posting**: Alice can publish messages to a personal timeline

> \> Alice -> I love the weather today    
> \> Bob -> Darn! We lost!     
> \> Bob -> Good game though.

**Reading**: Bob can view Alice’s timeline

> \> Alice    
> \> I love the weather today (5 minutes ago)    
> \> Bob    
> \> Good game though. (1 minute ago)     
> \> Darn! We lost! (2 minutes ago)

**Following**: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> \> Charlie -> I'm in New York today! Anyone wants to have a coffee?     
> \> Charlie follows Alice    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)    
> \> Alice - I love the weather today (5 minutes ago)

> \> Charlie follows Bob    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)     
> \> Bob - Good game though. (1 minute ago)     
> \> Bob - Damn! We lost! (2 minutes ago)     
> \> Alice - I love the weather today (5 minutes ago)

### General requirements

- Application must use the console for input and output;
- User submits commands to the application:
    - posting: \<user name> -> \<message>
    - reading: \<user name>
    - following: \<user name> follows \<another user>
    - wall: \<user name> wall 