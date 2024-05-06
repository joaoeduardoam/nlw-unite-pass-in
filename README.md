# nlw-unite-pass-in

Project made on the 2024 NLW Unite event by Rocketseat

# About the project

> pass.in is a backend Java application for managing participants in in-person events.
>
- The tool allows the organizer to register an event and open a public registration page.
- Registered participants can issue a credential for check-in on the day of the event.
- The system will scan the participant's credentials to allow entry to the event.

## Requirements

### Functional requirements

- The organizer must be able to register a new event;
- The organizer must be able to view event data;
- The organizer must be able to view the list of participants;
1. The participant must be able to register for an event;
- The participant must be able to view their registration badge;
- The participant must be able to check-in at the event;

### Business rules

- Participants can only register for an event once;
- Participants can only register for events with available places;
- Participants can only check-in to an event once;

### Non-functional requirements

- Check-in at the event will be carried out using a QRCode;
