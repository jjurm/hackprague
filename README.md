# SummApp

An awesome app which analyzes your phone calls and provides a list of possible actions based on them. Pure magic!

The app was invented and developed as a project at the [HackPrague 2017](http://hackprague.com/) hackathon. We won the following prizes:

- **2nd place**

	Prize: 20 000 CZK (800 EUR)

- **LondonTheatreDirect - The most innovative UX for mobile event discovery**

	Prize: Oculus Rift HD VR

	*Demonstration of the user journey from the first time use of the app to the discovery of the relevant event for the user. The prize will go to the most unconventional and creative approach.*

## Inspiration

The realisation of the need to grab a pen in order to extract main information throughout a call was shocking. We were traveling by train and Juro, our team member, had to call a few people. He seemed very calm and happy about his call just until someone started telling him a plethora of stuff. Events, addresses, requests, everything. He stared looking for a pen in a real hurry. It did not take long until we have realised that there has to be a better way. This is where our idea stems from. 

## What it does

The story begins with the app listening to a call you make. Just after that, you can notice a pleasant notification suggesting you to undertake further actions based on what you said during the call. For example:

- **Event:** You and your friend have agreed to meet tomorrow at 6pm in the park. But who would like to risk getting messed up by memorizing all those events? SummApp shows you a nice summary of the event and easily enables you to add the event directly to your calendar.
- **Place:** Did you just tell your mom that you will come to her office? But how to get there? Open the navigation to a place you mentioned just by one tap.
- **Contact:** You told your friend to call Mr. Edrick, but he doesn't have his number. SummApp suggests you to directly share Mr. Edrick's business card with your friend.
- **Reminder:** Stop forgetting to get done things you promised your father you will do. Easily set up suggested reminders.
- **Additional info:** Did you mention some additional information in the call, e.g. an email or webpage addresses? What about suggesting you to directly interact with them, or share them with whom you called?

## How we built it

We used Google Speech for speech to text, API.AI for text processing and classification together with London Theatre Direct for information about nearby events.

## Challenges we ran into

It is not trivial to name all the challenges since there were so many we barely remember the beginnings. Technically, there were two significant problems. Firstly, getting the speech recognition to work offline with continuous speech. Secondly, how to seamlessly connect all the components of the app.

There were also some team challenges as for the majority of us this was our first Hackathon.

## Accomplishments that we're proud of

We enjoy tackling challenges step-by-step and every time we feel like we are marching forward we get a huge sense of fulfillment. Our biggest strides are always in the finishing touches of the whole project.

## What we learned

Of course we worked with a number of tools such as API.AI or Google Speech which taught us extra hacking lessons. For some of our team members this was the second project apart from "Hello world" in Android Studio so that is quite exciting as well.

## What's next for SummApp

A lot of hustle and hopefully a snippet of luck.

## Authors

- Eduard Čuba ([edynox](https://github.com/edynox))
- Pavol Drotár ([padr31](https://github.com/padr31))
- Juraj Mičko ([jjurm](https://github.com/jjurm))
- Michal Pándy ([mpmisko](https://github.com/mpmisko))

## Screenshots

<img src="https://github.com/jjurm/hackprague/raw/master/screenshots/main_activity.png" alt="Main activity" width="216" height="384" /> <img src="https://github.com/jjurm/hackprague/raw/master/screenshots/call_detail.png" alt="Call detail" width="216" height="384" /> <img src="https://github.com/jjurm/hackprague/raw/master/screenshots/call.png" alt="Call" width="216" height="384" /> <img src="https://github.com/jjurm/hackprague/raw/master/screenshots/notification.png" alt="Notification" width="216" height="384" /> <img src="https://github.com/jjurm/hackprague/raw/master/screenshots/google_calendar.png" alt="Google Calendar" width="216" height="384" /> <img src="https://github.com/jjurm/hackprague/raw/master/screenshots/google_maps.png" alt="Google Maps" width="216" height="384" />
